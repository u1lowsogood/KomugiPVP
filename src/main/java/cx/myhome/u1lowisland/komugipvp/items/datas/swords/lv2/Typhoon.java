package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv2;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordClickR;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerKill;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3.DeathCrawler;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3.Hiraishin;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Typhoon extends CoolTimeableItem implements ISwordClickR, ISwordPlayerAttack, ISwordPlayerKill {

    private final double DISTANCE = 13;
    private final double AMOUNT = 8;

    public Typhoon(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.Typhoon.getItemStack(), EXPbyItem.LV2, 7*20);
        super.upgradeItems.add(new Hiraishin(kgPlayer));
        super.upgradeItems.add(new DeathCrawler(kgPlayer));
    }

    @Override
    public void clickR(Player clicker) {

        if(!isCanUse())return;
        startCD();

        Location startLoc = clicker.getEyeLocation();
        startLoc.add(0,-2,0);
        final double eyeVecX = startLoc.getDirection().getX();
        final double eyeVecZ = startLoc.getDirection().getZ();
        Vector addVec = new Vector(eyeVecX,0,eyeVecZ).normalize().multiply(DISTANCE/AMOUNT);
        Vector addVec4start = new Vector(eyeVecX,0,eyeVecZ).normalize();

        BukkitRunnable typhoon = new BukkitRunnable() {

            Location typhoonLoc = startLoc.clone().add(addVec4start.multiply(1));
            int counter = 0;

            public void run() {

                counter++;
                if(!(counter < AMOUNT))this.cancel();

                typhoonLoc.add(addVec);
                typhoonLoc.getWorld().playSound(typhoonLoc, Sound.ENTITY_BLAZE_SHOOT, 0.8f, 1f);

                drawCircleLocations(typhoonLoc.clone(), 2.2, 7,1.3,counter*90);


                for (Entity victim : typhoonLoc.getWorld().getNearbyEntities(
                        typhoonLoc,1.5,5,1.5,(e) -> e.getType() == EntityType.PLAYER)) {

                    if(!(victim instanceof Player)) return;
                    if(victim.equals(clicker))return;

                    victim.setVelocity(new Vector(0, 0.9, 0));
                }

                for (Entity victim : typhoonLoc.getWorld().getNearbyEntities(
                        typhoonLoc,1.5,5,1.5,(e) -> e.getType() == EntityType.ARMOR_STAND)) {
                    victim.setVelocity(new Vector(0, 0.9, 0));
                }

            }
        };

        clicker.getWorld().playSound(clicker.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 0.7f, 0.7f);
        typhoon.runTaskTimer(ServiceLocator.getInstance().getPlugin(), 0, 7);

    }

    public void drawCircleLocations(Location center, double radius, double height, double distance, double cycle_deg){

        ArrayList<Location> locs = new ArrayList<>();

        final double MAXDEGREE = ((height/distance)*360)+cycle_deg;
        final double MAXHEIGHT = height;

        for (double degree = cycle_deg; degree < MAXDEGREE; degree += 9) {

            double rad = Math.toRadians(degree);

            double currentRadius = radius * (degree / MAXDEGREE);

            double x = center.getX() + (currentRadius * Math.cos(rad));
            double z = center.getZ() + (currentRadius * Math.sin(rad));

            double y = center.getY() + height * (degree-cycle_deg) / (MAXDEGREE-cycle_deg);

            Location loc = center.clone();
            loc.setX(x);
            loc.setY(y);
            loc.setZ(z);
            locs.add(loc);
        }

        for(Location loc : locs) {
            center.getWorld().spawnParticle(Particle.CLOUD, loc, 1, 0, 0, 0, 0, null, true);
        }
    }


}
