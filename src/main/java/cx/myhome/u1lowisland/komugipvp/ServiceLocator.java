package cx.myhome.u1lowisland.komugipvp;

import cx.myhome.u1lowisland.komugipvp.game.GameManager;
import org.bukkit.plugin.Plugin;

public class ServiceLocator {

    private static ServiceLocator instance;

    private GameManager gm;
    private Plugin plugin;
    private CustomConfig mapConfig;

    public static ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    public void setPlugin(Plugin plugin){
        this.plugin = plugin;
    }

    public Plugin getPlugin(){
        return this.plugin;
    }

    public void setMapConfig(CustomConfig mapConfig) {
        this.mapConfig = mapConfig;
    }

    public CustomConfig getMapConfig(){
        return this.mapConfig;
    }

    public GameManager getGameManager() {
        return gm;
    }

    public void setGameManager(GameManager gm) {
        this.gm = gm;
    }
}
