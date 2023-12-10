package cx.myhome.u1lowisland.komugipvp.game.gamemode.SpecialEvent;

import cx.myhome.u1lowisland.komugipvp.game.gamemode.GameTimer;
import org.bukkit.Bukkit;

public abstract class SpecialEventGameTimer extends GameTimer {

    public SpecialEventGameTimer(int time_seconds) {
        super(time_seconds);
    }

    @Override
    public void run() {
        super.run();

        if(counter == (time_seconds/2)){
            SpecialEventEvent event = new SpecialEventEvent();
            Bukkit.getServer().getPluginManager().callEvent(event);
        }

    }
}
