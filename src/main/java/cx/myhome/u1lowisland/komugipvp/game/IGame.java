package cx.myhome.u1lowisland.komugipvp.game;

import org.bukkit.entity.Player;

import java.util.HashMap;

public interface IGame {
    void gameStart();
    void gameEnd();
    HashMap<Player, KGPlayer> getKGPlayerMap();
    void firstSpawn();
    void sendStartMessage();
    void sendStartTitle();
}
