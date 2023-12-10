package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordClickR;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import java.util.ArrayList;

public class Hirai_shin extends CoolTimeableItem implements ISwordClickR {

    private final double RADIUS = 4.6;
    private final double AMOUNT = 8;
    private final int RANGE = 30;

    public Hirai_shin(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.Hirai_shin.getItemStack(), EXPbyItem.LV4,15*20);
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

        if(!isCanUse())return;
        startCD();

        Location clickerLoc = clicker.getLocation();

        Block block = getItelated(clicker);

        Location loc = block.getLocation();
        loc.add(0.5,0.5,0.5);

        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 2);
        loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 70, 1, 1, 1, 0, dustOptions, true);


        loc.add(0,10,0);


        clicker.getWorld().playSound(clicker.getLocation(), Sound.ENTITY_WARDEN_DEATH, 1.3f, 0.7f);
        clicker.getWorld().playSound(clicker.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, 1.2f, 1f);

        BukkitRunnable teleport = new BukkitRunnable() {

            int counter = 0;

            public void run() {

                int why = counter;
                counter++;

                if(counter<8) {
                    drawMagicField(loc, RADIUS*((double)counter/(double)8),0.8,(why*10),10,10,10,
                            Particle.REDSTONE, Color.AQUA,false);
                }else if(8==counter) {
                    clicker.getWorld().playSound(clicker.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.2f, 0.7f);
                    clicker.getWorld().playSound(clicker.getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, 1.2f, 1f);
                    drawMagicField(loc, RADIUS,0.8,(why*10),4,5,25 ,Particle.LAVA, Color.RED,false);
                    drawMagicField(loc, RADIUS,0.8,(why*10),3,4,30,Particle.REDSTONE, Color.RED,false);
                }else if(9<counter&&counter<16){
                    drawMagicField(loc, RADIUS,0.8,(why*10),3,4,30,Particle.REDSTONE, Color.RED,false);
                }else if(16<=counter){
                    if(counter % 2==0) {
                        for (Entity victim : loc.getWorld().getNearbyEntities(
                                loc, RADIUS, 10, RADIUS, (e) -> e.getType() == EntityType.ARMOR_STAND)) {

                            victim.getWorld().strikeLightningEffect(victim.getLocation());
                            victim.getWorld().spawnParticle(Particle.LAVA, victim.getLocation(),
                                    30, 0.4, 1.8, 0.4, 0, null, true);

                        }
                        for (Entity victim : loc.getWorld().getNearbyEntities(
                                loc, RADIUS,10, RADIUS,(e) -> e.getType() == EntityType.PLAYER)) {

                            if(victim.equals(clicker))return;

                            victim.getWorld().strikeLightningEffect(victim.getLocation());
                            victim.getWorld().spawnParticle(Particle.LAVA, victim.getLocation(),
                                    30, 0.4, 1.8, 0.4, 0, null, true);

                            ((Player) victim).damage(2);
                        }
                    }

                    if(counter%8==0){
                        drawMagicField(loc, RADIUS,0.8,(why*10),3,4,30,Particle.REDSTONE, Color.RED,true);
                    } else{
                        drawMagicField(loc, RADIUS,0.8,(why*10),3,4,30,Particle.REDSTONE, Color.RED,false);
                    }
                }

                if(54<counter){
                    this.cancel();
                }
            }
        };

        teleport.runTaskTimer(ServiceLocator.getInstance().getPlugin(), 0, 5);

    }

    public ArrayList<Location> drawCircle(Location center, double radius, int hindo){

        ArrayList<Location> locs = new ArrayList<>();
        final double MAXDEGREE = 360;

        for (double degree = 0; degree < MAXDEGREE; degree++) {

            double rad = Math.toRadians(degree);

            double x = center.getX() + (radius * Math.cos(rad));
            double z = center.getZ() + (radius * Math.sin(rad));

            Location loc = center.clone();
            loc.setX(x);
            loc.setZ(z);

            if(degree % hindo==0) {
                locs.add(loc);
            }
        }

        return locs;
    }

    public void drawMagicField(Location center, double radius, double distance, int cycle,
                               int outer_hindo, int inner_hindo, int line_hindo, Particle particle, Color color, boolean drawIn){

        ArrayList<Location> locs = new ArrayList<>();
        ArrayList<Location> threePoint1 = new ArrayList<>();
        ArrayList<Location> threePoint2 = new ArrayList<>();

        final double MAXDEGREE = 360;

        //外苑を描画
        locs.addAll(drawCircle(center,radius+distance,outer_hindo));

        //内縁を描画
        locs.addAll(drawCircle(center,radius,inner_hindo));

        if(drawIn) {
            //locs.addAll(drawCircle(center, 0, 20));
            locs.addAll(drawCircle(center, 1.4, 7));
        }

        //３点を取得
        for (double degree = 0; degree < MAXDEGREE; degree++) {

            double rad = Math.toRadians(degree);

            double x = center.getX() + (radius * Math.cos(rad));
            double z = center.getZ() + (radius * Math.sin(rad));

            Location loc = center.clone();
            loc.setX(x);
            loc.setZ(z);

            if(degree%120==cycle%120){
                threePoint1.add(loc);
            }
            if(degree%120==((-(cycle%120))+135)%120){
                threePoint2.add(loc);
            }

            if(degree % inner_hindo==0) {
                locs.add(loc);
            }
        }

        locs.addAll(drawTriangle(threePoint1,center,line_hindo));
        locs.addAll(drawTriangle(threePoint2,center,line_hindo));

        for(Location loc : locs) {
            if(particle == Particle.REDSTONE) {
                Particle.DustOptions dustOptions = new Particle.DustOptions(color, 1);
                center.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0, 0, dustOptions, true);
            }else{
                center.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0, null, true);
            }
        }
    }

    private ArrayList<Location> drawTriangle(ArrayList<Location> point3, Location center, int linehindo) {

        ArrayList<Location> locs = new ArrayList<>();

        for(double line = 0; line < linehindo; line++){
            for(int i=0; i<3; i++) {
                Location loc1 = point3.get(i);
                Location loc2 = point3.get((i==2)? 0 : i+1);

                double x = loc1.getX()+((loc2.getX()-loc1.getX())*(line/linehindo));

                double z = loc1.getZ();

                if(loc2.getX() == loc1.getX()){
                    z += ((loc2.getZ()-loc1.getZ())*(line/linehindo));
                }else {
                    z = ((loc2.getZ() - loc1.getZ()) / (loc2.getX() - loc1.getX())) * (x - loc1.getX()) + loc1.getZ();
                }

                Location loc = center.clone();
                loc.setX(x);
                loc.setZ(z);

                locs.add(loc);
            }
        }
        return locs;
    }
}
