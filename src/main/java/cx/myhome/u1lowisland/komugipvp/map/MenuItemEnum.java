package cx.myhome.u1lowisland.komugipvp.map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public enum MenuItemEnum {
    BACK(
            ChatColor.RESET + "もどる",
            new ArrayList<String>(Arrays.asList("一つ前のメニューに戻れるんだね")),
            Material.CROSSBOW,
            new HashMap<>()),
    CLOSE(
            ChatColor.RESET + "閉じる",
            new ArrayList<String>(Arrays.asList("メニューを閉じることができるんだね")),
            Material.REDSTONE_TORCH,
            new HashMap<>()),
    MAPSETTING(
            ChatColor.GREEN + "マップ設定",
            new ArrayList<String>(Arrays.asList("マップのリスポーン地点などの登録が行なえますｗ")),
            Material.ANVIL,
            new HashMap<>()),
    SPAWNINIT(
            ChatColor.GREEN + "登録",
            new ArrayList<String>(Arrays.asList("クリックでリスポーン地点を登録")),
            Material.OAK_BUTTON,
            new HashMap<>()),
    CONFIRMREMOVE(
            ChatColor.RED + "本当に削除しますか？",
            new ArrayList<String>(Arrays.asList("クリックでマップを削除")),
            Material.OAK_BUTTON,
            new HashMap<>()),
    MAPREMOVE(
            ChatColor.RED + "マップ削除",
            new ArrayList<String>(Arrays.asList("マップの削除ができますねｗ")),
            Material.TNT,
            new HashMap<>()),
    PLAY(
            ChatColor.GREEN + "プレイ",
            new ArrayList<String>(Arrays.asList("ゲームをプレイできるんだねぇ")),
            Material.DIAMOND_SWORD,
            new HashMap<>()),
    NEXT(
            ChatColor.GREEN + "次のページ",
            new ArrayList<String>(Arrays.asList("次")),
            Material.DIAMOND_SWORD,
            new HashMap<>()),
    PREVIOUS(
            ChatColor.GREEN + "前のページ",
            new ArrayList<String>(Arrays.asList("前")),
            Material.DIAMOND_SWORD,
            new HashMap<>());

    private ItemStack itemStack;

    private MenuItemEnum(String name, List<String> lore, Material material, Map<Enchantment,Integer> enchantmentIntegerMap) {
        ItemStack sword = new ItemStack(material);
        ItemMeta itemMeta = sword.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        sword.setItemMeta(itemMeta);
        sword.addEnchantments(enchantmentIntegerMap);
        this.itemStack = sword;
    }

    ItemStack getItemStack(){
        return this.itemStack;
    }
}
