package cx.myhome.u1lowisland.komugipvp.game.gamemode.FFA;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayerComparator;
import cx.myhome.u1lowisland.komugipvp.game.gamemode.SpecialEvent.SpecialEventEnum;
import cx.myhome.u1lowisland.komugipvp.game.gamemode.SpecialEvent.SpecialEventEvent;
import cx.myhome.u1lowisland.komugipvp.game.gamemode.SpecialEvent.SpecialEventGameMode;
import cx.myhome.u1lowisland.komugipvp.map.GameMap;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class FreeForAll extends SpecialEventGameMode {

    private final int KILLLIMIT = 15;
    private final int GAMETIME_SECONDS = 210;

    private FFATimer gameTimer;
    private KGPlayer winner;

    private String gameEndReason;

    public FreeForAll(GameMap gameMap){
        super(gameMap);
        super.startMessages.add(
                new String[]{
                        "突然変異した小麦！",
                        "一度摂取したら一生快楽の虜に！",
                        "その小麦には、多量の麻薬的成分が含まれていた！",
                        "俺の小麦を奪おうとする怪物たちを殺せ！"
                });
        super.startMessages.add(
                new String[]{
                        "ライ麦畑で",
                        "皆殺し"
                });
        super.startMessages.add(
                new String[]{
                        "２０５８年、エネルギー不足となった世界……",
                        "小麦から抽出されるエネルギーを巡り、世界各国のエージェントが争いを起こす！"
                });
        super.startMessages.add(
                new String[]{
                        "命は、限りがあるからこそ美しい。",
                        "",
                        "300年に1度しか生えない、食べると不老不死になる小麦！",
                        "万人に備わったシステム「死」を克服すべく集った愚かな人類！",
                        "不死の力を得るのは誰だ！？"
                });
        super.startMessages.add(
                new String[]{
                        "突如誘拐されたプレイヤー達……",
                        "目を覚ますと、恐ろしいデスゲームに参加させられていた！",
                        "黒幕は農林水産省！野望を打ち砕く為に戦え！"
                });
        gameEndReason=ChatColor.AQUA+"時間切れ";
        super.wholeScore= KILLLIMIT;
        super.startTitle = ChatColor.GOLD+gameMap.getMapName();
        super.startSubTitle = "FFA: 先に" + KILLLIMIT + "キルしたものが勝利！";
    }

    @Override
    public void gameStart() {
        super.gameStart();
        gameTimer = new FFATimer(GAMETIME_SECONDS);
    }

    @Override
    public void gameEnd() {

        ArrayList<KGPlayer> kgPlayers = new ArrayList<>(playerMap.values());
        kgPlayers.sort(new KGPlayerComparator());

        if(winner == null){
            setWinner(kgPlayers.get(0));
        }

        Location spawnLoc = null;
        for(Player player : playerMap.keySet()){
            spawnLoc = player.getWorld().getSpawnLocation();
            if(player.isDead()){
                Location finalSpawnLoc = spawnLoc;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.spigot().respawn();
                        player.teleport(finalSpawnLoc);
                    }
                }.runTaskLater(ServiceLocator.getInstance().getPlugin(), 5);
            }
            player.teleport(spawnLoc);
            player.setGameMode(org.bukkit.GameMode.CREATIVE);
            player.sendTitle(gameEndReason,
                    ChatColor.AQUA+winner.getPlayer().getDisplayName()+"の勝利！",20,80,20);
        }

        ServiceLocator.getInstance().getGameManager().shootFireWorks(
                spawnLoc, FireworkEffect.Type.BALL_LARGE, Color.RED,Color.BLUE,true,true,2);


        Bukkit.broadcastMessage(ChatColor.YELLOW + "■■■■■■■■ゲーム終了■■■■■■■■");
        Bukkit.broadcastMessage("");
        int i=0;
        for(KGPlayer kgPlayer : kgPlayers){
            Bukkit.broadcastMessage(ChatColor.YELLOW+"・ "+(i+1)+"位 "+kgPlayer.getPlayer().getDisplayName()+" ("+
                    kgPlayer.getKillCount() + "キル)");
            i++;
        }
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(ChatColor.YELLOW + "■■■■■■■■■■■■■■■■■■■■■");

        super.gameEnd();
    }

    public void setWinner(KGPlayer winner){
        this.winner = winner;
    }

    public void setGameEndReason(String gameEndReason) {
        this.gameEndReason = gameEndReason;
    }

    @EventHandler
    public void playerKill(PlayerDeathEvent e){

        super.playerKill(e);

        if(e.getEntity().getKiller()==null)return;
        if(!(e.getEntity().getKiller() instanceof Player))return;

        Player victim = e.getEntity();
        Player killer = victim.getKiller();

        if(!(isInMap(killer) && isInMap(victim)))return;

        KGPlayer kgKiller = playerMap.get(killer);
        killer.setLevel(kgKiller.getKillCount());
        killer.setExp(((float)kgKiller.getKillCount()/(float) KILLLIMIT));

        if(kgKiller.getKillCount() >= KILLLIMIT){
            setGameEndReason(ChatColor.AQUA+""+ KILLLIMIT +"キルに到達");
            setWinner(kgKiller);
            this.gameEnd();
            return;
        }
    }



}
