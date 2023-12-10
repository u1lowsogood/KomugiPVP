package cx.myhome.u1lowisland.komugipvp.game.gamemode.SpecialEvent;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.gamemode.GameMode;
import cx.myhome.u1lowisland.komugipvp.map.GameMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public abstract class SpecialEventGameMode extends GameMode {

    protected SpecialEventEnum specialEventEnum;

    protected SpecialEventGameMode(GameMap gameMap) {
        super(gameMap);
        this.specialEventEnum = SpecialEventEnum.NONE;
    }

    public void sendSpecialEventTitle(String name, String lore){
        for(Player player : playerMap.keySet()){
            player.sendTitle(ChatColor.AQUA+name,lore,20,80,20);
        }
    }

    @EventHandler
    public void calledSpecialEvent(SpecialEventEvent e){

        specialEventEnum = e.getSpecialEventEnum();

        String specialEventName = e.getSpecialEventEnum().getName();
        String specialEventLore = e.getSpecialEventEnum().getLore();

        sendSpecialEventTitle(specialEventName,specialEventLore);

        switch(specialEventEnum){
            case ULTRAFARM:
                super.dropMultiply = 3;
        }
    }
}
