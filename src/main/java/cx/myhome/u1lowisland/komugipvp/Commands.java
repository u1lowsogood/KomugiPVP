package cx.myhome.u1lowisland.komugipvp;

import cx.myhome.u1lowisland.komugipvp.game.GameStateEnum;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.map.GameMap;
import cx.myhome.u1lowisland.komugipvp.map.MainMenu;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Commands implements CommandExecutor {

    private boolean hasAir(Player player){
        return player.getInventory().getItemInMainHand() == null ||
                player.getInventory().getItemInMainHand().getType().equals(Material.AIR);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("mapregist")) {
            if(args.length != 1){player.sendMessage("引数の数が間違っとるんよ");return false;}
            if(hasAir(player)) {player.sendMessage("なにかアイテムを手に持って");return false;}

            String mapName = args[0];

            ItemStack icon = new ItemStack(player.getInventory().getItemInMainHand().getType());
            ItemMeta itemMeta = icon.getItemMeta();
            itemMeta.setDisplayName(mapName);
            icon.setItemMeta(itemMeta);

            GameMap gameMap = new GameMap(mapName, icon);

            FileConfiguration mapFileConfig = ServiceLocator.getInstance().getMapConfig().getConfig();
            mapFileConfig.set(mapName,gameMap);

            ServiceLocator.getInstance().getMapConfig().saveConfig();

            player.getInventory().setItemInMainHand(icon);

            player.sendMessage("新マップ" + args[0] + "を登録しました！");
            player.sendMessage("マップには /playmenu でスポーン地点を登録できます。");

            return true;

        }else if(command.getName().equalsIgnoreCase("playmenu")){

            try {
                MenuManager.openMenu(MainMenu.class, player);
            } catch (MenuManagerException | MenuManagerNotSetupException e) {
                e.printStackTrace();
            }

            return true;

        }else if(command.getName().equalsIgnoreCase("soundcheck")){

            try {
                MenuManager.openMenu(SoundCheckMenu.class,player);
            } catch (MenuManagerException e) {
                e.printStackTrace();
            } catch (MenuManagerNotSetupException e) {
                e.printStackTrace();
            }

        }else if(command.getName().equalsIgnoreCase("getswords")){

            for(ItemEnum se : ItemEnum.values()){
                player.getInventory().addItem(se.getItemStack());
            }

            if(ServiceLocator.getInstance().getGameManager().getGameState().equals(GameStateEnum.NOTINGAME)){
                Bukkit.broadcastMessage("DEBUG: not in game");
                return true;
            }

        }else if(command.getName().equalsIgnoreCase("gameabort")){

            Bukkit.broadcastMessage("The game was aborted");
            ServiceLocator.getInstance().getGameManager().gameEnd();

        }else if(command.getName().equalsIgnoreCase("getbigwheat")){

            if(ServiceLocator.getInstance().getGameManager().getGameState().equals(GameStateEnum.NOTINGAME)){
                Bukkit.broadcastMessage("DEBUG: not in game");
                return true;
            }

            ServiceLocator.getInstance().getGameManager().dropUnstackableWheat(
                    player.getLocation(), 60);
            ItemStack is = player.getInventory().getItemInMainHand();
            KGPlayer kgp = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(player);
            if(kgp.hasSimilarItem(is)){
                kgp.getSimilarItem(is).addEXP(1500);
            }
            return true;

        }else if(command.getName().equalsIgnoreCase("earnkill")){

            if(ServiceLocator.getInstance().getGameManager().getGameState().equals(GameStateEnum.NOTINGAME)){
                Bukkit.broadcastMessage("DEBUG: not in game");
                return true;
            }

            ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(player).addKillCount(1);
            ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(player).updateArmor();
            return true;

        }
        return false;
    }
}