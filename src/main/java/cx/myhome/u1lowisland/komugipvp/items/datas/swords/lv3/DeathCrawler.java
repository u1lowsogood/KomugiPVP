package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordClickR;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.AirStriker;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.Exculibar;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;

public class DeathCrawler extends CoolTimeableItem implements ISwordClickR {

    private final double DISTANCE = 13;
    private final double AMOUNT = 8;

    public DeathCrawler(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.DeathCrawler.getItemStack(), EXPbyItem.LV3,20*20);
        super.upgradeItems.add(new Exculibar(kgPlayer));
        super.upgradeItems.add(new AirStriker(kgPlayer));
    }


    @Override
    public void clickR(Player clicker) {

        if(!isCanUse())return;

        Location clickerLoc = clicker.getLocation();

        BlockIterator it = new BlockIterator(clicker,30);

        Block block = null;
        Block past = null;

        Location loc = null;

        while(true){
            if(it.hasNext()) {
                past = block;
                block = it.next();
                if(past!=null){
                    loc = past.getLocation();
                }else{
                    loc = block.getLocation();
                }
                if (block.getType() != Material.AIR){
                    for(int i=0; i<3; i++){
                        if(block.getLocation().clone().add(0,i,0).getBlock().getType()==Material.AIR &&
                           block.getLocation().clone().add(0,i+1,0).getBlock().getType()==Material.AIR){
                            loc = block.getLocation().clone().add(0,i,0);
                            break;
                        }
                    }
                    break;
                }
            }else{
                clicker.playSound(clicker, Sound.BLOCK_NOTE_BLOCK_BASS,1,1f);
                return;
            }
        }

        startCD();

        loc.add(0.5,0,0.5);

        clicker.getWorld().playSound(clicker.getLocation(), Sound.ENTITY_WARDEN_DEATH, 1f, 1f);

        Location finalLoc = loc;
        BukkitRunnable teleport = new BukkitRunnable() {

            int counter = 0;
            public void run() {

                for(int i=0; i<6; i++) {
                    drawCircleLocations(finalLoc.clone().add(0,i/2,0), 1);
                }

                if(5<=counter) {
                    clicker.teleport(finalLoc.clone().add(0, 0, 0).setDirection(clicker.getEyeLocation().getDirection()));
                    clicker.getWorld().playSound(clicker.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, 0.7f, 1.2f);
                    clicker.getWorld().playSound(clicker.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1.2f);
                    //clicker.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,70,0));
                    this.cancel();
                }

                counter++;
            }
        };

        teleport.runTaskTimer(ServiceLocator.getInstance().getPlugin(), 0, 5);

    }

    public void drawCircleLocations(Location center, double radius){

        ArrayList<Location> locs = new ArrayList<>();

        final double MAXDEGREE = 360;

        for (double degree = 0; degree < MAXDEGREE; degree += 9) {

            double rad = Math.toRadians(degree);

            double x = center.getX() + (radius * Math.cos(rad));
            double z = center.getZ() + (radius * Math.sin(rad));

            Location loc = center.clone();
            loc.setX(x);
            loc.setZ(z);
            locs.add(loc);
        }

        for(Location loc : locs) {
            center.getWorld().spawnParticle(Particle.SPELL_WITCH, loc, 1, 0, 0, 0, 0, null, true);
        }
    }
}
