package cx.myhome.u1lowisland.komugipvp.map;

import cx.myhome.u1lowisland.komugipvp.CustomConfig;
import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class SelectMapEditMenu extends Menu {

    public SelectMapEditMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "マップを編集";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

        Player player = playerMenuUtility.getOwner();

        ItemStack currentItem = e.getCurrentItem();

        if(currentItem == null || currentItem.equals(Material.AIR))return;

        if (currentItem.isSimilar(MenuItemEnum.CLOSE.getItemStack())) {
            player.closeInventory();
        } else if (currentItem.isSimilar(MenuItemEnum.BACK.getItemStack())) {
            MenuManager.openMenu(MainMenu.class, player);
        } else if (getIcons().contains(currentItem)){
            playerMenuUtility.setData(PMUEnum.EDITMAPNAME, currentItem.getItemMeta().getDisplayName());
            playerMenuUtility.setData(PMUEnum.ICON, currentItem);
            MenuManager.openMenu(RespawnRegisterMenu.class, player);
        }

    }

    @Override
    public void setMenuItems() {
        inventory.setItem(0,MenuItemEnum.BACK.getItemStack());
        inventory.setItem(8,MenuItemEnum.CLOSE.getItemStack());

        int i = 9;
        for(ItemStack icon : getIcons()){
            inventory.setItem(i,icon);
            i++;
        }

        setFillerGlass();
    }

    private ArrayList<ItemStack> getIcons(){
        ArrayList<ItemStack> icons = new ArrayList<>();

        CustomConfig mapConfig = ServiceLocator.getInstance().getMapConfig();

        for(String mapName : mapConfig.getConfig().getConfigurationSection("").getKeys(false)){
            GameMap gameMap = (GameMap) ServiceLocator.getInstance().getMapConfig().getConfig().get(mapName);
            icons.add(gameMap.getIcon());
        }

        return icons;
    }
}
