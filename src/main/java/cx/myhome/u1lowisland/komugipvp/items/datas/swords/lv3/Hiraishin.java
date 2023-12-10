package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3;

import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordClickR;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.AirStriker;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv4.Hirai_shin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class Hiraishin extends CoolTimeableItem implements ISwordPlayerAttack, ISwordClickR {

    public Hiraishin(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.Hiraishin.getItemStack(), EXPbyItem.LV3,6*20);
        super.upgradeItems.add(new Hirai_shin(kgPlayer));
        super.upgradeItems.add(new AirStriker(kgPlayer));
    }

    @Override
    public void attackPlayer(Player attacker, Player victim) {

        super.attackPlayer(attacker, victim);
        if(!isCanUse())return;
        startCD();
        victim.getWorld().strikeLightningEffect(victim.getLocation());
        victim.damage(2);
        victim.setFireTicks(40);
    }

    @Override
    public void clickR(Player clicker) {

        if(!isCanUse())return;

        BlockIterator it = new BlockIterator(clicker, 20);

        while (true) {

            if(!it.hasNext()){
                clicker.playSound(clicker, Sound.BLOCK_NOTE_BLOCK_BASS,1,1f);
                break;
            }

            Block block = it.next();
            Location loc = block.getLocation();

            if ( block.getType() != Material.AIR ) {

                loc.getWorld().strikeLightningEffect(loc);
                loc.getWorld().spawnParticle(Particle.LAVA, loc,
                        30, 0.4, 1.8, 0.4, 0, null, true);

                startCD();

                for (Entity victim : loc.getWorld().getNearbyEntities(
                        loc,2,2,2,(e) -> e.getType() == EntityType.PLAYER)) {

                    if(victim.equals(clicker))return;

                    ((Player) victim).damage(2);
                    victim.setFireTicks(40);
                }
                break;
            }
        }
    }
}
