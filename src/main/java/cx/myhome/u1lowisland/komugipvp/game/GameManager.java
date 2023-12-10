package cx.myhome.u1lowisland.komugipvp.game;

import cx.myhome.u1lowisland.komugipvp.game.gamemode.FFA.FreeForAll;
import cx.myhome.u1lowisland.komugipvp.map.GameMap;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.*;

public class GameManager {

    private GameStateEnum gameStateEnum;
    IGame game;

    public void gameStart(GameMap gameMap, GameModeEnum gameModeEnum){

        switch(gameModeEnum) {
            case FFA:
                this.game = new FreeForAll(gameMap);
                break;
            case TDM:
                break;
        }

        game.gameStart();
    }

    public HashMap<Player, KGPlayer> getKGPlayerMap(){
        return game.getKGPlayerMap();
    }

    public boolean isInGame(Player player){
        if(getKGPlayerMap().keySet().contains(player)){
            return true;
        }
        return false;
    }

    public void gameEnd(){
        game.gameEnd();
    }

    public void setGameState(GameStateEnum gameStateEnum){
        this.gameStateEnum = gameStateEnum;
    }

    public GameStateEnum getGameState(){
        return this.gameStateEnum;
    }

    public void dropUnstackableWheat(Location loc, int amount){

        for( int i=0; i<amount; i++) {

            ItemStack is = WheatEnum.WHEAT.getItemStack();
            ItemMeta im = is.getItemMeta();
            List<String> lore = im.getLore();
            lore.set(0,UUID.randomUUID().toString());
            im.setLore(lore);
            is.setItemMeta(im);

            Item coin = loc.getWorld().dropItem(loc, is);
            Random rand = new Random();

            double x = ((rand.nextDouble()*2)-1)/6;
            double z = ((rand.nextDouble()*2)-1)/6;
            Vector vec = new Vector(x, 0.4, z);

            coin.setVelocity(vec);
            coin.setCustomNameVisible(true);
            int rnd = new Random().nextInt(ChatColor.values().length);

            ArrayList<String> mugiArray = new ArrayList<String>(Arrays.asList("菱","妻","夌","笑","奏","葵","萎"));

            String mugi = "麦";

            int mugint = new Random().nextInt(100);

            if(mugint == 19){
                mugi = mugiArray.get(new Random().nextInt(mugiArray.size()));
            }

            coin.setCustomName(ChatColor.GOLD + mugi);
            coin.setPickupDelay(10);
        }

        loc.getWorld().spawnParticle(Particle.COMPOSTER,loc,20,0.3,0.3,0.3,0);

    }

    public void shootFireWorks(
            Location loc, FireworkEffect.Type fireworkEffect,
            Color color, Color fadeColor, Boolean flicker, Boolean trail, int power) {

        Firework firework = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        FireworkEffect.Builder effect = FireworkEffect.builder();

        effect.with(fireworkEffect);
        effect.withColor(color);
        effect.withFade(fadeColor);
        effect.flicker(flicker);
        effect.trail(trail);
        meta.addEffect(effect.build());
        meta.setPower(power);

        firework.setFireworkMeta(meta);
    }

}
