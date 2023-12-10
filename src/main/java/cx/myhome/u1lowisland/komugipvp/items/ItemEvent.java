package cx.myhome.u1lowisland.komugipvp.items;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.datas.BaseItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemEvent implements Listener {

    @EventHandler
    public void onPlayer(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){

            Player attacker = (Player) e.getDamager();
            Player victim = (Player) e.getEntity();

            ItemStack used = attacker.getInventory().getItemInMainHand();

            if(!isInGame(attacker,used))return;

            KGPlayer kgAttacker = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(attacker);

            BaseItem baseItem = kgAttacker.getSimilarItem(used);
            if(baseItem instanceof ISwordPlayerAttack) {
                ((ISwordPlayerAttack) baseItem).attackPlayer(attacker, victim);
            }
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent e) {

        Player attacker = e.getPlayer();
        ItemStack used = e.getItem();

        if(!isInGame(attacker,used))return;

        KGPlayer kgAttacker = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(attacker);
        BaseItem usedItem = kgAttacker.getSimilarItem(used);

        if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) &&
        attacker.isSneaking()){
            usedItem.openUpgradeMenu(attacker);
            return;
        }

        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if(usedItem instanceof ISwordClickL) {
                ((ISwordClickL) usedItem).clickL(attacker);
            }
        }else if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(usedItem instanceof ISwordClickR) {
                ((ISwordClickR) usedItem).clickR(attacker);
            }
        }
    }

    @EventHandler
    public void onPlayerKill(PlayerDeathEvent e) {

        Player victim = e.getEntity();
        if(victim.getKiller() == null)return;

        Player killer = victim.getKiller();
        ItemStack used = killer.getInventory().getItemInMainHand();

        if(!isInGame(killer,used))return;

        KGPlayer kgKiller = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(killer);
        BaseItem usedItem = kgKiller.getSimilarItem(used);

        if(usedItem instanceof ISwordPlayerKill) {
            ((ISwordPlayerKill) usedItem).playerKill(killer,victim);
        }
    }

    public boolean isInGame(Player player){

        if(ServiceLocator.getInstance().getGameManager().getKGPlayerMap().containsKey(player)){
            return true;
        }

        return false;
    }

    public boolean isInGame(Player user, ItemStack used){

        if(used==null)return false;
        if(!ServiceLocator.getInstance().getGameManager().isInGame(user))return false;

        KGPlayer kgAttacker = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(user);

        if(!kgAttacker.hasSimilarItem(used))return false;

        return true;
    }
}
