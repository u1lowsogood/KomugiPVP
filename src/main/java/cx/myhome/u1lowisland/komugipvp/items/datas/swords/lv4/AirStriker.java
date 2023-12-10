package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordClickR;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.library.DrawForm;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.function.Consumer;

public class AirStriker extends CoolTimeableItem implements ISwordClickR {

    private final int RANGE=30;
    private final double DISTANCE = 13;
    private final double AMOUNT = 8;
    private final double SPREAD = 2.2;
    private final double RADIUS = 2.7;
    private final double DAMAGE_RADIUS = 4;

    DrawForm df;

    public AirStriker(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.AirStriker.getItemStack(), EXPbyItem.LV4,5*20);
        df = new DrawForm();
    }

    private Block getItelated(Player player) {

        BlockIterator it = new BlockIterator(player, RANGE);

        while ( it.hasNext() ) {

            Block block = it.next();

            if ( block.getType() != Material.AIR ) {
                return block;
            }
        }

        return null;
    }

    @Override
    public void clickR(Player clicker) {

        if(getItelated(clicker) == null){
            clicker.playSound(clicker, Sound.BLOCK_NOTE_BLOCK_BASS,1,1f);
            return;
        }

        Block centerBlock = getItelated(clicker);
        Location center = centerBlock.getLocation().add(0.5,1.2,0.5);

        if(!isCanUse())return;
        startCD();

        ArrayList<Location> strikePoints = new ArrayList<>();
        strikePoints.add(center);
        strikePoints.add(center.clone().add(-SPREAD,0,-SPREAD));
        strikePoints.add(center.clone().add(-SPREAD,0, SPREAD));
        strikePoints.add(center.clone().add(SPREAD,0,-SPREAD));
        strikePoints.add(center.clone().add(SPREAD,0, SPREAD));

        int delay=0;
        for(Location strikePoint : strikePoints){
            airStrike(strikePoint, delay);
            delay+=4;
        }

    }

    public void airStrike(Location strikePoint, int delay){

        final double HEIGHT = 15;
        final int TIME = 30;

        Consumer<Double> drawCircle = (progress) -> {

            for(Vector out : df.drawCircleLocations(RADIUS, (int) (progress*20)+15)){
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
                strikePoint.getWorld().spawnParticle(Particle.REDSTONE, strikePoint.clone().add(out),
                        1, 0, 0, 0, 0, dustOptions, true);
            }
        };

        Runnable drawLine = () -> {
            for(int i=0; i<HEIGHT; i++){
                Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
                strikePoint.getWorld().spawnParticle(Particle.REDSTONE, strikePoint.clone().add(0,i,0),
                        1, 0, 0, 0, 0, dustOptions, true);
            }
        };

        Consumer<Double> drawBOMB = (progress) -> {
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.BLACK, 5);
            strikePoint.getWorld().spawnParticle(
                    Particle.REDSTONE,
                    strikePoint.clone().add(0,HEIGHT-(HEIGHT*progress),0),
                    1, 0, 0, 0, 0, dustOptions, true);

        };

        BukkitRunnable strike = new BukkitRunnable() {

            int counter = 0;
            public void run() {

                if(counter==0){
                    strikePoint.getWorld().playSound(strikePoint,Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,0.7f,1f);
                    strikePoint.getWorld().playSound(kgPlayer.getPlayer(),Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,0.7f,1f);
                }

                double progress = (double)counter/(double)TIME;

                drawBOMB.accept(progress);
                drawLine.run();
                drawCircle.accept(progress);

                if(counter>=TIME){
                    strikePoint.getWorld().playSound(strikePoint,Sound.ENTITY_LIGHTNING_BOLT_IMPACT,0.7f,0.8f);
                    strikePoint.getWorld().playSound(kgPlayer.getPlayer(),Sound.ENTITY_LIGHTNING_BOLT_IMPACT,0.7f,1f);
                    strikePoint.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, strikePoint,
                            1, 0, 0, 0, 0, null, true);
                    strikePoint.getWorld().spawnParticle(Particle.LAVA, strikePoint,
                            30, RADIUS, RADIUS, RADIUS, 0, null, true);

                    for (Entity victim : strikePoint.getWorld().getNearbyEntities(
                            strikePoint, DAMAGE_RADIUS, DAMAGE_RADIUS, DAMAGE_RADIUS, (e) -> e.getType() == EntityType.ARMOR_STAND)) {
                        victim.setVelocity(new Vector(0,0.5,0));
                    }
                    for (Entity victim : strikePoint.getWorld().getNearbyEntities(
                            strikePoint, DAMAGE_RADIUS, DAMAGE_RADIUS, DAMAGE_RADIUS, (e) -> e.getType() == EntityType.PLAYER)) {

                        if(!victim.equals(kgPlayer.getPlayer())) {
                            ((Player) victim).damage(5);
                            victim.setVelocity(new Vector(0, 0.5, 0));
                            victim.setFireTicks(80);
                        }

                    }

                    this.cancel();
                }

                counter++;
            }
        };

        strike.runTaskTimer(ServiceLocator.getInstance().getPlugin(), delay, 1);

    }
}
