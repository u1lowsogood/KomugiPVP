package cx.myhome.u1lowisland.komugipvp.items.datas;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public enum ArmorEnum {
    lv1_1(
            ChatColor.DARK_GREEN + "麦わら帽",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"処方せんの入力中につき",
                    ChatColor.GRAY+"お問い合わせ等に関しましては",
                    ChatColor.GRAY+"ご遠慮下さいますよう",
                    ChatColor.GRAY+"ご協力お願い致します。",
                    ChatColor.GRAY+"入力が遅くなったり",
                    ChatColor.GRAY+"入力ミスにつながり",
                    ChatColor.GRAY+"患者様にご迷惑をおかけしてしまいます。",
                    ChatColor.GRAY+"尚、お薬に関するご質問は",
                    ChatColor.GRAY+"お気軽におたずね下さい。"
            )),
            Material.LEATHER_HELMET,
            new HashMap<>()),
    lv1_2(
            ChatColor.DARK_GREEN + "Tシャツ",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"南米のスラム街で作られた",
                    ChatColor.GRAY+"安物のTシャツ",
                    ChatColor.GRAY+"麻薬の密輸入に使われており",
                    ChatColor.GRAY+"生地の間はゴワゴワしてる"
            )),
            Material.LEATHER_CHESTPLATE,
            new HashMap<>()),
    lv1_3(
            ChatColor.DARK_GREEN + "ももひき",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"すごい食い込む すごい",
                    ChatColor.GRAY+"※食い込みすぎて死亡した事例あり"
            )),
            Material.LEATHER_LEGGINGS,
            new HashMap<>()),
    lv1_4(
            ChatColor.DARK_GREEN + "デニール５００",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"中古品",
                    ChatColor.GRAY+"デニール値は高ければ高いほど",
                    ChatColor.GRAY+"偏差値も上がってく"
            )),
            Material.LEATHER_BOOTS,
            new HashMap<>()),
    lv2_1(
            ChatColor.DARK_GREEN + "成金ヘルメット",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"純金の成金ヘルメット",
                    ChatColor.GRAY+"つけていればどんな蛮行も許される"
            )),
            Material.GOLDEN_HELMET,
            new HashMap<>()),
    lv2_2(
            ChatColor.DARK_GREEN + "守銭奴アーマー",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"非常に守銭奴なアーマー",
                    ChatColor.GRAY+"貧乏人が借金をして買った"
            )
            ),
            Material.GOLDEN_CHESTPLATE,
            new HashMap<>()),
    lv2_3(
            ChatColor.DARK_GREEN + "皇族レギンス",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"聖徳太子時代から受け継がれた",
                    ChatColor.GRAY+"皇族専用のレギンス")
            ),
            Material.GOLDEN_LEGGINGS,
            new HashMap<>()),
    lv2_4(
            ChatColor.DARK_GREEN + "セレブ靴",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"セレブが使うからセレブ靴なのであって",
                    ChatColor.GRAY+"一般人が使ってもセレブにはならないぞｗ")
            ),
            Material.GOLDEN_BOOTS,
            new HashMap<>()),
    lv3_1(
            ChatColor.DARK_GREEN + "アルミホイルヘルメット",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"電波が！電波が！！"
            )),
            Material.CHAINMAIL_HELMET,
            new HashMap<>()),
    lv3_2(
            ChatColor.DARK_GREEN + "半透明チェストプレート",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"すごい♡♡♡♡♡♡♡")
            ),
            Material.CHAINMAIL_CHESTPLATE,
            new HashMap<>()),
    lv3_3(
            ChatColor.DARK_GREEN + "鋼鉄ニーソックス",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"あ")
            ),
            Material.CHAINMAIL_LEGGINGS,
            new HashMap<>()),
    lv3_4(
            ChatColor.DARK_GREEN + "バーサーカーブーツ",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"あｗ")
            ),
            Material.CHAINMAIL_BOOTS,
            new HashMap<>()),
    lv4_1(
            ChatColor.DARK_GREEN + "SWATヘルメット",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"アイテム名")
            ),
            Material.IRON_HELMET,
            new HashMap<>()),
    lv4_2(
            ChatColor.DARK_GREEN + "防弾チョッキ",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"考えるの")
            ),
            Material.IRON_CHESTPLATE,
            new HashMap<>()),
    lv4_3(
            ChatColor.DARK_GREEN + "ヘビーレギンス",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"あきた")
            ),
            Material.IRON_LEGGINGS,
            new HashMap<>()),
    lv4_4(
            ChatColor.DARK_GREEN + "プレートスチールキャップ",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"最近")
            ),
            Material.IRON_BOOTS,
            new HashMap<>()),
    lv5_1(
            ChatColor.DARK_GREEN + "超・成金ヘルメット",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+" あんまり")
            ),
            Material.DIAMOND_HELMET,
            new HashMap<>()),
    lv5_2(
            ChatColor.DARK_GREEN + "超・守銭奴アーマー",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"楽しいこと")
            ),
            Material.DIAMOND_CHESTPLATE,
            new HashMap<>()),
    lv5_3(
            ChatColor.DARK_GREEN + "超・皇族レギンス",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"ないから")
            ),
            Material.DIAMOND_LEGGINGS,
            new HashMap<>()),
    lv5_4(
            ChatColor.DARK_GREEN + "超・セレブ靴",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"つまらん")
            ),
            Material.DIAMOND_BOOTS,
            new HashMap<>()),
    lv6_1(
            ChatColor.DARK_GREEN + "デス・エンドオブ・ヘルファイア・ヘルメット",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"誰か１億円くれ～～～")
            ),
            Material.NETHERITE_HELMET,
            new HashMap<>()),
    lv6_2(
            ChatColor.DARK_GREEN + "超高重機動殺戮敵防御装甲",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"なければ")
            ),
            Material.NETHERITE_CHESTPLATE,
            new HashMap<>()),
    lv6_3(
            ChatColor.DARK_GREEN + "～終わりを齎す者～",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"３０００円くらいでも")
            ),
            Material.NETHERITE_LEGGINGS,
            new HashMap<>()),
    lv6_4(
            ChatColor.DARK_GREEN + "狂戦士の俊足ブーツ",
            new ArrayList<String>(Arrays.asList(
                    "",
                    ChatColor.GRAY+"助かりますｗ")
            ),
            Material.NETHERITE_BOOTS,
            new HashMap<>());


    private ItemStack armor;

    private ArmorEnum(String name, List<String> lore, Material material, Map<Enchantment,Integer> enchantmentIntegerMap) {
        ItemStack sword = new ItemStack(material);
        ItemMeta itemMeta = sword.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        sword.setItemMeta(itemMeta);
        sword.addEnchantments(enchantmentIntegerMap);
        this.armor = sword;
    }

    public ItemStack getItemStack(){
        return this.armor;
    }
}
