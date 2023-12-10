package cx.myhome.u1lowisland.komugipvp.map;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MainMenu extends Menu {

    public MainMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "マップメニュー";
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
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

        Player player = playerMenuUtility.getOwner();

        ItemStack currentItem = e.getCurrentItem();

        if (currentItem.isSimilar(MenuItemEnum.CLOSE.getItemStack())) {
            player.closeInventory();
        } else if (currentItem.isSimilar(MenuItemEnum.MAPSETTING.getItemStack())) {
            MenuManager.openMenu(SelectMapEditMenu.class, player);
        } else if (currentItem.isSimilar(MenuItemEnum.PLAY.getItemStack())){
            MenuManager.openMenu(SelectMapPlayMenu.class, player);
        }

    }

    @Override
    public void setMenuItems() {
        inventory.setItem(8, MenuItemEnum.CLOSE.getItemStack());
        inventory.setItem(11, MenuItemEnum.MAPSETTING.getItemStack());
        inventory.setItem(13, MenuItemEnum.PLAY.getItemStack());
        inventory.setItem(15, MenuItemEnum.MAPREMOVE.getItemStack());
        setFillerGlass();
        return;
    }
}
