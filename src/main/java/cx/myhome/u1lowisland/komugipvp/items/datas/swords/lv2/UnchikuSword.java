package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv2;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerKill;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3.Collector;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv3.Rendakun;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class UnchikuSword extends CoolTimeableItem implements ISwordPlayerAttack, ISwordPlayerKill {

    private final ArrayList<String> unchikus = new ArrayList<String>(
            Arrays.asList(
                    "サドルの語源は相手を「操作取る」ことから来ている",
                    "左右の金玉が潰れると、子供を作れなくなってしまう（金玉が３つあれば例外だが……）",
                    "サドルの語源は相手を「操作取る」ことからきている",
                    "うんちとおしっこを同時に出せるのは日本人だけ",
                    "黒人の心臓を移植されると肌が黒くなる場合がある",
                    "血液型占いはGHQが流布したガセ",
                    "男でも潮は吹ける",
                    "サメのペニスは二本ある",
                    "首相官邸は伊藤博文がホームレスになるのを防ぐために作られた",
                    "東京駅は水に浮かんでいる",
                    "トウモロコシの粒の数は必ず偶数",
                    "この世界は仮想現実である",
                    "この世界は3分前に作られた",
                    "実は地球は平面である",
                    "童貞の死亡率は100%",
                    "日本人は古代イスラエル人の子孫である",
                    "天皇は人間である",
                    "聖徳太子はキリスト教徒",
                    "紀元前のコンドームは豚･羊の膀胱や腸で作られていた",
                    "ポルチオ開発は4000年前の中国から行われていた",
                    "猫は人間に家畜化されている",
                    "人間に自由意志はない",
                    "人間は小麦の奴隷である",
                    "トマトには毒があると考えられていた",
                    "安い風俗で交尾すると性病になる",
                    "人間は右乳首より左乳首の方が感じやすい",
                    "ゴッホのひまわりは13枚ある",
                    "ダビデ像はポルノ扱いされている",
                    "天国と地獄は存在せず、死の先にあるのは無である",
                    "ソクラテスは筋骨隆々でレスリングをやりこんでいた",
                    "ネコのウンコで作るコーヒーがある",
                    "ゴキブリに洗剤をかけると死ぬ",
                    "30分間、笑い続けると死ぬ",
                    "ダヴィンチはディルドを作っていた",
                    "牛の胃は6つある",
                    "日本式のカレーはイギリス式",
                    "ショタのションベンで煮込んだ卵料理がある",
                    "吸血鬼は蹄鉄に弱い",
                    "膣は10mまで伸びる",
                    "男でも母乳が出る",
                    "インドやミャンマーには「アナル族」という部族があり、アナル語が使用されている",
                    "日本の稲作は朝鮮由来ではない",
                    "平将門公に敬称を付けないと祟られる",
                    "鳥居はイスラエル由来",
                    "古墳は鍵の穴の形ではなく、壺の形",
                    "古代エジプトでは、脳髄は鼻水を作り出す不要な器官であると考えられていた",
                    "魂の質量は21グラムであることはガセ",
                    "お天道様は天照大御神のことである",
                    "タコの脳は9個ある",
                    "ゴリラの血液型はB型",
                    "日本が「いただきます」を使うようになったのは軍国主義教育が関係している",
                    "本能寺は冷暖房完備だった",
                    "初詣は明治以降に鉄道会社が広めたものなん",
                    "座敷童の正体は金持ちの家の隠し子である",
                    "北海道にイノシシはいない"
                    ));

    private final int HEALTHDEPRIVEAMOUNT=2;

    public UnchikuSword(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.UnchikuSword.getItemStack(), EXPbyItem.LV2,200);
        super.upgradeItems.add(new Collector(kgPlayer));
        super.upgradeItems.add(new Rendakun(kgPlayer));
    }

    @Override
    public void attackPlayer(Player attacker, Player victim) {

        super.attackPlayer(attacker, victim);

        if(!isCanUse())return;

        safetyHeal(attacker,HEALTHDEPRIVEAMOUNT);

        attacker.playSound(attacker, Sound.ENTITY_GENERIC_DRINK,1f,0.8f);
        victim.playSound(victim, Sound.ENTITY_GENERIC_DRINK,1f,0.8f);

        Vector veca = attacker.getEyeLocation().getDirection().normalize().multiply(0.7);
        attacker.spawnParticle(Particle.HEART,attacker.getEyeLocation().add(veca),20,0.4,0.4,0.4);

        victim.damage(HEALTHDEPRIVEAMOUNT);
        Vector vecv = victim.getEyeLocation().getDirection().normalize().multiply(0.7);
        victim.spawnParticle(Particle.DAMAGE_INDICATOR,victim.getEyeLocation().add(vecv),20,0.4,0.4,0.4);

        String selif = unchikus.get(new Random().nextInt(unchikus.size()));
        Server server = ServiceLocator.getInstance().getPlugin().getServer();

        server.broadcastMessage(ChatColor.AQUA + victim.getDisplayName()+ "「"+selif+"んだって～！」");
        server.broadcastMessage(ChatColor.AQUA + attacker.getDisplayName()+ "「へぇ～！」");

        startCD();
    }


    private void safetyHeal(Player player, int amount){
        if(player.getHealth()+amount >20){
            player.setHealth(20);
            return;
        }else{
            player.setHealth(player.getHealth()+amount);
        return;
        }
    }
}
