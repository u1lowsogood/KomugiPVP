package cx.myhome.u1lowisland.komugipvp;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SoundCheckMenu extends Menu {

    public SoundCheckMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        int page=0;
        if(playerMenuUtility.getData("MenuIndex")!=null){
            page=(int) playerMenuUtility.getData("MenuIndex");
        }
        return "SoundCheck - "+page;
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

        ItemStack clicked = e.getCurrentItem();
        Player player = playerMenuUtility.getOwner();

        int page=0;
        if(playerMenuUtility.getData("MenuIndex")!=null){
            page=(int) playerMenuUtility.getData("MenuIndex");
        }

        if(clicked.getType().equals(Material.REDSTONE_BLOCK) && page!=0){

            playerMenuUtility.setData("MenuIndex",page-1);
            MenuManager.openMenu(this.getClass(),player);

        }else if(clicked.getType().equals(Material.REDSTONE_ORE)) {

            playerMenuUtility.setData("MenuIndex", page+1);
            MenuManager.openMenu(this.getClass(), player);

        }else if(clicked.getType().equals(Material.REDSTONE_TORCH)){
            player.stopAllSounds();
            Sound sound = Enum.valueOf(Sound.class, e.getCurrentItem().getItemMeta().getDisplayName());
            player.playSound(player, sound, 1, 1);
        }

    }

    @Override
    public void setMenuItems() {

        inventory.setItem(0, new ItemStack(Material.REDSTONE_BLOCK));
        inventory.setItem(8, new ItemStack(Material.REDSTONE_ORE));

        int page=0;
        if(playerMenuUtility.getData("MenuIndex")==null){
            page=0;
        }else{
            page=(int) playerMenuUtility.getData("MenuIndex");
        }

        for(int si=page*54,index=9; index<inventory.getSize(); index++, si++){

            if(index>inventory.getSize()-1)break;
            if(si >= Sound.values().length)break;

            Sound sound = Sound.values()[si];

            ItemStack is = new ItemStack(Material.REDSTONE_TORCH);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(sound.toString());
            is.setItemMeta(im);

            inventory.setItem(index,is);
        }

    }
}
