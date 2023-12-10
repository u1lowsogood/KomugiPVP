package cx.myhome.u1lowisland.komugipvp.game.gamemode.SpecialEvent;

import cx.myhome.u1lowisland.komugipvp.game.GameManager;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.Random;

public class SpecialEventEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    SpecialEventEnum specialEventEnum = SpecialEventEnum.NONE;

    public SpecialEventEvent() {
        while(this.specialEventEnum==SpecialEventEnum.NONE) {
            int pick = new Random().nextInt(SpecialEventEnum.values().length);
            this.specialEventEnum = SpecialEventEnum.values()[pick];
        }
    }

    public SpecialEventEnum getSpecialEventEnum(){
        return this.specialEventEnum;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}