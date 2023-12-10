package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.datas.BaseItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.AirStriker;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.Exculibar;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Collector extends BaseItem implements ISwordPlayerAttack{

    private final int THRESHOLD = 5;

    public Collector(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.Collector.getItemStack(), EXPbyItem.LV3);
        super.upgradeItems.add(new Exculibar(kgPlayer));
        super.upgradeItems.add(new AirStriker(kgPlayer));
    }

    @Override
    public void attackPlayer(Player attacker, Player victim) {

        super.attackPlayer(attacker, victim);

        if(victim.getHealth()<=THRESHOLD){
            victim.damage(9999);
            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED,1.0f);
            attacker.getPlayer().getWorld().spawnParticle(Particle.REDSTONE, victim.getEyeLocation(), 80, 0.3, 2, 0.3, dustOptions);
            attacker.playSound(attacker, Sound.BLOCK_END_PORTAL_SPAWN,0.6f,1.2f);
            attacker.sendMessage(ChatColor.AQUA+"■"+getAbilityName() + "発動！");
            victim.sendMessage(ChatColor.AQUA +"■"+ getAbilityName()+"により処刑された！");
        }
    }


}
