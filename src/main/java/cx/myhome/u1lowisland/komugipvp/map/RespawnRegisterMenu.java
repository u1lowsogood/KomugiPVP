package cx.myhome.u1lowisland.komugipvp.map;

import cx.myhome.u1lowisland.komugipvp.CustomConfig;
import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RespawnRegisterMenu extends Menu {

    public RespawnRegisterMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return playerMenuUtility.getData(PMUEnum.EDITMAPNAME, String.class);
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

        if(currentItem == null || currentItem.equals(Material.AIR))return;
        if (currentItem.isSimilar(MenuItemEnum.CLOSE.getItemStack())) {
            player.closeInventory();
        } else if (currentItem.isSimilar(MenuItemEnum.BACK.getItemStack())) {
            MenuManager.openMenu(SelectMapEditMenu.class, player);
        }

        int clicked=e.getSlot();
        if((clicked%9==2||clicked%9==3||clicked%9==4||clicked%9==5||clicked%9==6)&&clicked<27){

            HashMap<Integer,Integer> numMap = new HashMap<>();
            for(int key=0,index=0; key<inventory.getSize(); key++){
                if(!(key%9==2||key%9==3||key%9==4||key%9==5||key%9==6))continue;
                numMap.put(key,index);
                index++;
            }

            Location loc = player.getLocation();

            String mapName = playerMenuUtility.getData(PMUEnum.EDITMAPNAME, String.class);
            GameMap gameMap = (GameMap) ServiceLocator.getInstance().getMapConfig().getConfig().get(mapName);

            if(gameMap.getSpawnLocs().size()>numMap.get(clicked)) {
                gameMap.setSpawnLoc(numMap.get(clicked), loc);
            }else{
                gameMap.addSpawnLoc(loc);
            }

            ItemStack is = new ItemStack(Material.OAK_WOOD);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GREEN + "リスポーン地点設定済み");
            im.setLore(new ArrayList<String>(Arrays.asList(
                    "X: "+loc.getX(),
                    "Y: "+loc.getY(),
                    "Z: "+loc.getZ())));
            is.setItemMeta(im);
            inventory.setItem(clicked,is);

            CustomConfig mapConfig = ServiceLocator.getInstance().getMapConfig();
            mapConfig.getConfig().set(mapName, gameMap);

            mapConfig.saveConfig();

            player.sendMessage("スポーン地点を登録しました！");
        }

        }

    @Override
    public void setMenuItems() {


        Player player = playerMenuUtility.getOwner();

        inventory.setItem(0,MenuItemEnum.BACK.getItemStack());
        inventory.setItem(8,MenuItemEnum.CLOSE.getItemStack());

        String mapName = playerMenuUtility.getData(PMUEnum.EDITMAPNAME, String.class);
        GameMap gameMap = (GameMap) ServiceLocator.getInstance().getMapConfig().getConfig().get(mapName);

        HashMap<Integer,Integer> numMap = new HashMap<>();
        ArrayList<Integer> numList = new ArrayList<>();
        for(int key=0,index=0; key<inventory.getSize(); key++){
            if(!(key%9==2||key%9==3||key%9==4||key%9==5||key%9==6))continue;
            numMap.put(key,index);
            numList.add(key);
            index++;
            inventory.setItem(key,MenuItemEnum.SPAWNINIT.getItemStack());
        }

        ArrayList<Location> locs = gameMap.getSpawnLocs();

        int i=0;
        for(Location loc : locs){
            ItemStack is = new ItemStack(Material.OAK_WOOD);
            ItemMeta im = is.getItemMeta();
            im.setDisplayName(ChatColor.GREEN + "リスポーン地点設定済み");
            im.setLore(new ArrayList<String>(Arrays.asList(
                    "X: "+loc.getX(),
                    "Y: "+loc.getY(),
                    "Z: "+loc.getZ())));
            is.setItemMeta(im);
            inventory.setItem(numList.get(i),is);
            i++;
        }

        setFillerGlass();
    }
}
