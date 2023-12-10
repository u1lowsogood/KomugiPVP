package cx.myhome.u1lowisland.komugipvp.game.gamemode;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.GameStateEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class GameTimer extends BukkitRunnable {

    protected final int time_seconds;
    protected int counter;

    public GameTimer(int time_seconds){
        this.time_seconds = time_seconds;
        counter = time_seconds;

        Plugin plugin = ServiceLocator.getInstance().getPlugin();
        this.runTaskTimer(plugin,0,20);
    }

    @Override
    public void run() {

        if(ServiceLocator.getInstance().getGameManager().getGameState().equals(GameStateEnum.NOTINGAME))this.cancel();

        if(counter<5){
            Bukkit.broadcastMessage(ChatColor.YELLOW + "残り時間： " + counter);
        }else if(counter%30==0) {
            Bukkit.broadcastMessage(ChatColor.YELLOW + "残り時間： " + counter);
        }

        if(counter<=0){
            ServiceLocator.getInstance().getGameManager().gameEnd();
            this.cancel();
        }
        counter--;
    }
}
