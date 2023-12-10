package cx.myhome.u1lowisland.komugipvp.game;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public enum WheatEnum {
    WHEAT(
            ChatColor.YELLOW + "麦",
            new ArrayList<String>(Arrays.asList("美しい、小麦！")),
            Material.WHEAT,
            new HashMap<>());

    private ItemStack itemStack;

    private WheatEnum(String name, List<String> lore, Material material, Map<Enchantment,Integer> enchantmentIntegerMap) {
        ItemStack sword = new ItemStack(material);
        ItemMeta itemMeta = sword.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        sword.setItemMeta(itemMeta);
        sword.addEnchantments(enchantmentIntegerMap);
        this.itemStack = sword;
    }

    public ItemStack getItemStack(){
        return this.itemStack;
    }
}
