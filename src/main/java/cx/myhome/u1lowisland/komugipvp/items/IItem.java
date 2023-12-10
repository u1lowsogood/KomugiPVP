package cx.myhome.u1lowisland.komugipvp.items;

import cx.myhome.u1lowisland.komugipvp.items.datas.BaseItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public interface IItem {
    void openUpgradeMenu(Player player);
    ItemStack getItemStack();
    ArrayList<BaseItem> getUpgradeItems();
}
