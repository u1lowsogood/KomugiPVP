package cx.myhome.u1lowisland.komugipvp;

import cx.myhome.u1lowisland.komugipvp.game.GameEvent;
import cx.myhome.u1lowisland.komugipvp.game.GameManager;
import cx.myhome.u1lowisland.komugipvp.game.GameStateEnum;
import cx.myhome.u1lowisland.komugipvp.map.GameMap;
import cx.myhome.u1lowisland.komugipvp.map.SerializableLocation;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class KomugiPVP extends JavaPlugin {

    private GameStateEnum gameStateEnum;

    @Override

    public void onEnable() {

        MenuManager.setup(getServer(),this);

        ConfigurationSerialization.registerClass(GameMap.class);
        ConfigurationSerialization.registerClass(SerializableLocation.class);

        ServiceLocator.getInstance().setPlugin(this);

        this.getServer().getPluginManager().registerEvents(new GameEvent(), this);

        CustomConfig mapConfig = new CustomConfig(this,"mapdata.yml");
        mapConfig.saveDefaultConfig();
        ServiceLocator.getInstance().setMapConfig(mapConfig);

        GameManager gameManager = new GameManager();
        ServiceLocator.getInstance().setGameManager(gameManager);
        ServiceLocator.getInstance().getGameManager().setGameState(GameStateEnum.NOTINGAME);

        getCommand("mapregist").setExecutor(new Commands());
        getCommand("playmenu").setExecutor(new Commands());
        getCommand("soundcheck").setExecutor(new Commands());
        getCommand("getswords").setExecutor(new Commands());
        getCommand("gameabort").setExecutor(new Commands());
        getCommand("getbigwheat").setExecutor(new Commands());
        getCommand("earnkill").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
