package cx.myhome.u1lowisland.komugipvp.items;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.GameStateEnum;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.datas.BaseItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class UpgradeMenu extends Menu {

    public UpgradeMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.BOLD +"" + ChatColor.YELLOW + "アップグレード";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e){

        if(ServiceLocator.getInstance().getGameManager().getGameState().equals(GameStateEnum.NOTINGAME))return;

        Player player = playerMenuUtility.getOwner();

        ItemStack currentItem = e.getCurrentItem();
        ItemStack used = player.getInventory().getItemInMainHand();

        if(!ServiceLocator.getInstance().getGameManager().isInGame(player))return;
        KGPlayer kgPlayer = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(player);

        for(ItemEnum bs : ItemEnum.values()){
            if(currentItem.isSimilar(bs.getItemStack())){
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                player.getInventory().setItemInMainHand(currentItem);

                BaseItem nowItem = kgPlayer.getSimilarItem(used);

                int index = kgPlayer.getBaseItems().indexOf(nowItem);

                if(nowItem.hasUpgradeBaseItem(currentItem)) {
                    BaseItem upgradedItem = nowItem.getUpgradeBaseItem(currentItem);
                    kgPlayer.getBaseItems().set(index, upgradedItem);
                    upgradedItem.showEXP();

                    player.sendMessage(ChatColor.AQUA+"■「"+
                            upgradedItem.getItemStack().getItemMeta().getDisplayName() +ChatColor.AQUA+"」にアップグレードしました！");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_HARP,1,1.4f);
                    player.getOpenInventory().close();
                }

                return;
            }
        }
    }

    @Override
    public void setMenuItems() {
        Player player = playerMenuUtility.getOwner();
        ItemStack used = player.getInventory().getItemInMainHand();

        KGPlayer kgPlayer = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(player);

        if(!kgPlayer.hasSimilarItem(used))return;

        BaseItem usedItem = kgPlayer.getSimilarItem(used);

        if(usedItem.getUpgradeItems().size() == 1){
            inventory.setItem(13, usedItem.getUpgradeItems().get(0).getItemStack());
        }else if(usedItem.getUpgradeItems().size() == 2){
            inventory.setItem(10, usedItem.getUpgradeItems().get(0).getItemStack());
            inventory.setItem(16, usedItem.getUpgradeItems().get(1).getItemStack());
        }else if(usedItem.getUpgradeItems().size() == 3){
            inventory.setItem(10, usedItem.getUpgradeItems().get(0).getItemStack());
            inventory.setItem(13, usedItem.getUpgradeItems().get(1).getItemStack());
            inventory.setItem(16, usedItem.getUpgradeItems().get(2).getItemStack());
        }

        for(int i=0; i<27; i++){
            if(inventory.getItem(i) != null) continue;
            if(i%9==0||i%9==1||i%9==2)inventory.setItem(i,new ItemStack(Material.LIME_STAINED_GLASS_PANE));
            if(i%9==3||i%9==4||i%9==5)inventory.setItem(i,new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
            if(i%9==6||i%9==7||i%9==8)inventory.setItem(i,new ItemStack(Material.YELLOW_STAINED_GLASS_PANE));
        }

        return;
    }
}
