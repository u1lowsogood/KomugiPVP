package cx.myhome.u1lowisland.komugipvp.items.datas.swords;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public enum ItemEnum {
    FarmSword(
            ChatColor.DARK_GREEN + "農夫の剣",
            new ArrayList<String>(Arrays.asList("",
                    ChatColor.AQUA+"【特殊効果：なし】",
                    ChatColor.GRAY+"一般的な剣",
                    ChatColor.GRAY+"小作が自害に使ったとされている")),
            Material.WOODEN_SWORD,
            new HashMap<>()),
    Typhoon(
            ChatColor.DARK_AQUA + "タイフーン",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：突風】",
                    ChatColor.GRAY+"右クリックで突風を解き放ち",
                    ChatColor.GRAY+"巻き込んだ相手を大きく打ち上げる",
                    ChatColor.GRAY+"落下ダメージが入るので嬉しい",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"7秒")),
            Material.STONE_SWORD,
            new HashMap<>()),
    UnchikuSword(
            ChatColor.DARK_AQUA + "蘊蓄剣",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：蘊蓄吸収】",
                    ChatColor.GRAY+"攻撃が相手の海馬から蘊蓄を吸収する。",
                    ChatColor.GRAY+"吸収された相手は追加でHPを2失い",
                    ChatColor.GRAY+"自身はHPを2回復する。",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"10秒")),
            Material.STONE_SWORD,
            new HashMap<Enchantment,Integer>()),
    HomerunBat(
            ChatColor.DARK_AQUA + "ホームランバット",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：蘊蓄吸収】",
                    ChatColor.GRAY+"攻撃が相手の海馬から蘊蓄を吸収する。",
                    ChatColor.GRAY+"吸収された相手は追加でHPを1失い",
                    ChatColor.GRAY+"自身はHPを1回復する。",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"5秒")),
            Material.STONE_SWORD,
            new HashMap<Enchantment,Integer>()),
    Hiraishin(
            ChatColor.DARK_RED + "避雷針",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：落雷】",
                    ChatColor.GRAY+"攻撃が雷を落としダメージを与え、",
                    ChatColor.GRAY+"さらに2秒間炎上させる",
                    ChatColor.GRAY+"右クリックで遠距離にも落とせる",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"6秒")),
            Material.IRON_SWORD,
            new HashMap<Enchantment,Integer>()),
    Collector(
            ChatColor.DARK_RED + "コレクター",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：死と税金】",
                    ChatColor.GRAY+"攻撃時、相手の体力が5以下だった場合",
                    ChatColor.GRAY+"とどめを刺し、大量の小麦をゲットする！")
            ),
            Material.IRON_SWORD,
            new HashMap<Enchantment,Integer>()),
    Rendakun(
            ChatColor.DARK_RED + "自動連打君",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：3回攻撃】",
                    ChatColor.GRAY+"素早く3回攻撃を繰り出し",
                    ChatColor.GRAY+"各打撃ごとにノックバックと1ダメージを与える",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"3秒")
            ),
            Material.IRON_SWORD,
            new HashMap<Enchantment,Integer>()),
    DeathCrawler(
            ChatColor.DARK_RED + "デスクローラー",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：キャスター付きテーブル】",
                    ChatColor.GRAY+"デスクのローラーであり",
                    ChatColor.GRAY+"デス・クローラーではない",
                    ChatColor.GRAY+"右クリックで発動。",
                    ChatColor.GRAY+"1.5秒の詠唱後、視線方向にテレポートする。",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"15秒")
            ),
            Material.IRON_SWORD,
            new HashMap<Enchantment,Integer>()),
    Exculibar(
            ChatColor.AQUA+""+ChatColor.BOLD +"エクスカリバー",
            new ArrayList<String>(Arrays.asList(
                    "",
            ChatColor.AQUA+"【特殊効果：英雄譚】",
            ChatColor.GRAY+"伝説の力を呼び覚まし、",
            ChatColor.GRAY+"攻撃が追加で5のダメージを与える。",
            ChatColor.GRAY+"なお、このダメージは防具を貫通する。",
            ChatColor.GRAY+"その後、自身は2秒間移動速度が上昇する",
                    "",
            ChatColor.AQUA+"【クールタイム】",
            ChatColor.GRAY+"4秒")),
    Material.DIAMOND_SWORD,
            new HashMap<Enchantment,Integer>()),
    Hirai_shin(
            ChatColor.AQUA+""+ChatColor.BOLD +"平井 真",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：霹靂】",
                    ChatColor.GRAY+"右クリックで指定した地点から周囲3マスの",
                    ChatColor.GRAY+"全プレイヤーに連続して雷を落とす。",
                    ChatColor.GRAY+"0.5秒ごと、合計20回の雷撃が発生し",
                    ChatColor.GRAY+"1回の雷撃につき2のダメージが入る",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"20秒")),
            Material.DIAMOND_SWORD,
            new HashMap<Enchantment,Integer>()),
    AirStriker(
            ChatColor.AQUA+""+ChatColor.BOLD +"空爆剣",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：空爆要請】",
                    ChatColor.GRAY+"右クリックで指定した地点を中心として",
                    ChatColor.GRAY+"計５発の空爆を要請する",
                    ChatColor.GRAY+"爆風は相手を打ち上げるようにノックバックさせ",
                    ChatColor.GRAY+"4秒間炎上させる。自分はダメージを受けない",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"10秒")),
            Material.DIAMOND_SWORD,
            new HashMap<Enchantment,Integer>()),
    EDENSVICE(
            ChatColor.AQUA+""+ChatColor.BOLD +"エデンス ヴァイス",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：ドッキリ！おもしろテレポート君】",
                    ChatColor.GRAY+"右クリックで発動。",
                    ChatColor.GRAY+"0.5秒の詠唱後、視線方向にテレポートする。",
                    ChatColor.GRAY+"テレポート後は3.5秒間、攻撃力が上昇し、",
                    ChatColor.GRAY+"再発動で元の位置に戻る事ができる",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"20秒")),
            Material.DIAMOND_SWORD,
            new HashMap<Enchantment,Integer>()),
    TEST(
            ChatColor.AQUA+""+ChatColor.BOLD +"テスト",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.AQUA+"【特殊効果：ドッキリ！おもしろテレポート君】",
                    ChatColor.GRAY+"右クリックで発動。",
                    ChatColor.GRAY+"0.5秒の詠唱後、視線方向にテレポートする。",
                    ChatColor.GRAY+"テレポート後は3.5秒間、攻撃力が上昇し、",
                    ChatColor.GRAY+"再発動で元の位置に戻る事ができる",
                    "",
                    ChatColor.AQUA+"【クールタイム】",
                    ChatColor.GRAY+"20秒")),
            Material.DIAMOND_SWORD,
            new HashMap<Enchantment,Integer>()),;


    private ItemStack sword;

    private ItemEnum(String name, List<String> lore, Material material, Map<Enchantment,Integer> enchantmentIntegerMap) {
        ItemStack sword = new ItemStack(material);
        ItemMeta itemMeta = sword.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        sword.setItemMeta(itemMeta);
        sword.addEnchantments(enchantmentIntegerMap);
        this.sword = sword;
    }

    public ItemStack getItemStack(){
        return this.sword;
    }
}
