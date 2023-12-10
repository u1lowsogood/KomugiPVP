package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class HomerunBat extends CoolTimeableItem implements ISwordPlayerAttack {

    public HomerunBat(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.HomerunBat.getItemStack(), EXPbyItem.LV4,4*20);
    }

    @Override
    public void attackPlayer(Player attacker, Player victim) {

        super.attackPlayer(attacker, victim);


        if(!isCanUse())return;
        startCD();

        victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.7f, 1f);
        victim.setVelocity(attacker.getEyeLocation().getDirection().normalize().multiply(2).add(new Vector(0,1,0)));

        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.YELLOW, 2);
        victim.getWorld().spawnParticle(Particle.REDSTONE, victim.getEyeLocation(),
                60, 1, 1, 1, 0, dustOptions, true);

        BukkitRunnable typhoon = new BukkitRunnable() {
            int i=0;
            @Override
            public void run() {
                if(i<12){
                    victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_BLAST, 0.8f, 1f);
                    victim.getWorld().spawnParticle(Particle.CRIT, victim.getLocation(),
                            30, 1, 1, 1, 0, null, true);
                    i++;
                }else{
                    this.cancel();
                }
            }
        };

        typhoon.runTaskTimer(ServiceLocator.getInstance().getPlugin(), 0, 5);


    }
}
