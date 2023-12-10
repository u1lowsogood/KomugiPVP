package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4;

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

public class Exculibar extends CoolTimeableItem implements ISwordPlayerAttack {

    public Exculibar(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.Exculibar.getItemStack(), EXPbyItem.LV4,4*20);
    }

    @Override
    public void attackPlayer(Player attacker, Player victim) {

        super.attackPlayer(attacker, victim);

        if(!isCanUse())return;
        startCD();
        victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_GLOW_SQUID_HURT, 1f, 1f);
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 2);
        victim.getWorld().spawnParticle(Particle.REDSTONE, victim.getEyeLocation(),
                70, 1, 1, 1, 0, dustOptions, true);
        if(victim.getHealth()<6) {
            victim.setHealth(0);
        }else{
            victim.setHealth(victim.getHealth() - 5);
        }
        attacker.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,40,1));
    }
}
