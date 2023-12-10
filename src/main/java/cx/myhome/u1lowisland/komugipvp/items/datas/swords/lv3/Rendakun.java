package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordClickR;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.AirStriker;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.Exculibar;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.HomerunBat;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Rendakun extends CoolTimeableItem implements ISwordPlayerAttack, ISwordClickR {

    public Rendakun(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.Rendakun.getItemStack(), EXPbyItem.LV3,3*20);
        super.upgradeItems.add(new Exculibar(kgPlayer));
        super.upgradeItems.add(new HomerunBat(kgPlayer));
    }

    @Override
    public void attackPlayer(Player attacker, Player victim) {

        super.attackPlayer(attacker, victim);

        if(!isCanUse())return;
        startCD();

        Vector vec = attacker.getEyeLocation().getDirection().normalize().multiply(0.1).add(new Vector(0,0.2,0));

        BukkitRunnable typhoon = new BukkitRunnable() {
            int i=0;
            @Override
            public void run() {
                if(i<3){
                    victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 0.8f, 1f);
                    victim.getWorld().spawnParticle(Particle.CRIT, victim.getLocation(),
                            30, 1, 1, 1, 0, null, true);
                    victim.setVelocity(victim.getVelocity().add(vec));
                    victim.damage(1);
                    victim.setNoDamageTicks(3);
                    i++;
                }else{
                    this.cancel();
                }
            }
        };

        typhoon.runTaskTimer(ServiceLocator.getInstance().getPlugin(), 0, 3);
    }

    @Override
    public void clickR(Player clicker) {


    }
}
