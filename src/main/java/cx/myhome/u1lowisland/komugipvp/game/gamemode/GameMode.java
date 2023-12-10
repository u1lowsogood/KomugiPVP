package cx.myhome.u1lowisland.komugipvp.game.gamemode;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.GameStateEnum;
import cx.myhome.u1lowisland.komugipvp.game.IGame;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ItemEvent;
import cx.myhome.u1lowisland.komugipvp.items.datas.BaseItem;
import cx.myhome.u1lowisland.komugipvp.map.GameMap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public abstract class GameMode implements IGame, Listener {

    private final int FARMEXP = 5;

    protected final int WHEATAMOUNT = 1;
    protected final int HAYAMOUNT = 5;
    protected int dropMultiply = 1;

    protected final int WHEATTIME = 500;
    protected final int HAYTIME = 1000;

    GameMap gameMap;
    protected HashMap<Player, KGPlayer> playerMap = new HashMap<>();

    ArrayList<Material> breakableBlocks = new ArrayList<>();

    protected ArrayList<String[]> startMessages = new ArrayList<>();
    protected String startTitle;
    protected String startSubTitle;

    ItemEvent itemEvent;

    protected int wholeScore;

    protected GameMode(GameMap gameMap){
        this.gameMap = gameMap;
        this.itemEvent = new ItemEvent();
        breakableBlocks.add(Material.WHEAT);
        breakableBlocks.add(Material.HAY_BLOCK);
    }

    @Override
    public HashMap<Player, KGPlayer> getKGPlayerMap(){
        return this.playerMap;
    }

    @Override
    public void gameStart(){
        registerEvent();

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getDisplayName().equals("GreenappleRabbit") || player.getDisplayName().equals("souta02"))continue;
            playerMap.put(player, new KGPlayer(player));
            playerMap.get(player).initCondition();
        }

        firstSpawn();
        sendStartMessage();
        sendStartTitle();

        ServiceLocator.getInstance().getGameManager().setGameState(GameStateEnum.INGAME);
    }

    @Override
    public void gameEnd() {
        unregisterEvent();
        ServiceLocator.getInstance().getGameManager().setGameState(GameStateEnum.NOTINGAME);
    }

    @Override
    public void sendStartMessage(){
        Bukkit.broadcastMessage(ChatColor.YELLOW + "■■■■■■■■ゲーム開始■■■■■■■■");
        showRandomMessages(startMessages);
        Bukkit.broadcastMessage(ChatColor.YELLOW + "■■■■■■■■■■■■■■■■■■■■■");
    }

    public void showRandomMessages(ArrayList<String[]> msgs){
        Collections.shuffle(msgs);
        String messages[] = msgs.get(0);
        for(int i=0; i<messages.length; i++){
            Bukkit.broadcastMessage(ChatColor.YELLOW + "" + messages[i]);
        }
    }

    @Override
    public void sendStartTitle(){
        for(Player player : playerMap.keySet()){
            player.sendTitle(ChatColor.AQUA+this.startTitle,ChatColor.YELLOW+this.startSubTitle,20,80,20);
        }
    }

    @Override
    public void firstSpawn(){
        ArrayList<Location> locs = gameMap.getSpawnLocs();

        int i=0;
        for(Player player : playerMap.keySet()){
            Location loc = locs.get(0);
            if(i < locs.size())loc = locs.get(i);
            player.teleport(loc);

            ServiceLocator.getInstance().getGameManager().shootFireWorks(
                    loc, FireworkEffect.Type.BALL_LARGE,Color.RED,Color.BLUE,true,true,1);
            i++;
        }
    }

    private void registerEvent(){
        Plugin plugin = ServiceLocator.getInstance().getPlugin();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getServer().getPluginManager().registerEvents(itemEvent, plugin);
    }

    private void unregisterEvent() {
        HandlerList.unregisterAll(this);
        HandlerList.unregisterAll(itemEvent);
    }

    protected void replaceWheat(Player player, Location loc){
        ServiceLocator.getInstance().getGameManager().dropUnstackableWheat(loc,WHEATAMOUNT*dropMultiply);
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,20,1));

        new BukkitRunnable() {
            @Override
            public void run() {
                loc.getWorld().playSound(loc,Sound.BLOCK_GRASS_BREAK,1,1);
                loc.getBlock().setType(Material.WHEAT);

                Ageable ageable = (Ageable) loc.getBlock().getBlockData();
                ageable.setAge(ageable.getMaximumAge());
                loc.getBlock().setBlockData(ageable);
            }
        }.runTaskLater(ServiceLocator.getInstance().getPlugin(), WHEATTIME);
    }

    protected void replaceHay(Player player, Location loc){
        Random rand = new Random();
        int additional = rand.nextInt(4);

        ServiceLocator.getInstance().getGameManager().dropUnstackableWheat(loc,(HAYAMOUNT+additional)*dropMultiply);
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,40,1));

        new BukkitRunnable() {
            @Override
            public void run() {
                loc.getWorld().playSound(loc,Sound.BLOCK_GRASS_BREAK,1,1);
                loc.getBlock().setType(Material.HAY_BLOCK);

            }
        }.runTaskLater(ServiceLocator.getInstance().getPlugin(), HAYTIME);
    }

    @EventHandler
    public void farmKomugi(BlockBreakEvent e){

        Block block = e.getBlock();
        Location loc = block.getLocation().add(0.5,0,0.5);

        if(!isInMap(e.getPlayer()))return;

        if(block.getType().equals(Material.WHEAT)){
            e.setDropItems(false);
            replaceWheat(e.getPlayer(),loc);
        }else if(block.getType().equals(Material.HAY_BLOCK)){
            e.setDropItems(false);
            replaceHay(e.getPlayer(),loc);
        }
    }

    @EventHandler
    public void farmEXP(EntityPickupItemEvent e){

        if(!(e.getEntity() instanceof Player))return;

        Player player = (Player) e.getEntity();
        if(!isInMap(player))return;

        KGPlayer kgPlayer = playerMap.get(player);

        if(e.getItem().getItemStack().getType().equals(Material.WHEAT)){
            for(BaseItem baseItem : kgPlayer.getBaseItems()){
                baseItem.addEXP(FARMEXP);
            }

            int foodLevel = player.getFoodLevel();
            player.setFoodLevel(foodLevel+1 > 20 ? 20 : foodLevel+1);

            new BukkitRunnable() {
                @Override
                public void run() {
                    player.getInventory().remove(Material.WHEAT);
                }
            }.runTaskLater(ServiceLocator.getInstance().getPlugin(), 8);
        }
    }

    @EventHandler
    public void toggleHotbar(PlayerItemHeldEvent e){

        Player player = e.getPlayer();

        if(!isInMap(e.getPlayer()))return;

        KGPlayer kgPlayer = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(player);

        int newSlot = e.getNewSlot();

        if(player.getInventory().getItem(newSlot) == null) {
            player.setExp(0);
            return;
        };

        ItemStack newSlotItem = player.getInventory().getItem(newSlot);

        if(kgPlayer.hasSimilarItem(newSlotItem)) {
            BaseItem bi = kgPlayer.getSimilarItem(newSlotItem);
            bi.showEXP();
        }
    }

    /*@EventHandler(priority = EventPriority.LOW)
    public void playerKill(PlayerDeathEvent e){

        Player victim = e.getEntity();
        Player killer = victim.getKiller();

        if(!isInMap(killer))return;
        if(!isInMap(victim))return;

        KGPlayer kgVictim = playerMap.get(victim);
        KGPlayer kgKiller = playerMap.get(killer);

        kgKiller.addKillCount(1);
        kgKiller.updateArmor();
    }*/

    @EventHandler(priority = EventPriority.NORMAL)
    public void playerKill(PlayerDeathEvent e){

        String vicISName = "鬼滅の刃";

        Player victim = e.getEntity();
        if(!isInMap(victim))return;
        KGPlayer kgVictim = playerMap.get(victim);

        ItemStack victimIS = victim.getInventory().getItemInMainHand();

        //プレイヤーが手にアイテムを持ってた場合アイテム名で上書き（なかったら鬼滅の刃）
        if(victimIS!=null && victimIS.getItemMeta()!=null){
            vicISName = victimIS.getItemMeta().getDisplayName();
        }

        //プレイヤーを殺した人間がいない＝自殺だった場合
        if(e.getEntity().getKiller()==null){
            e.setDeathMessage(
                    ChatColor.YELLOW+"■ "+victim.getDisplayName()+
                            " ("+kgVictim.getKillCount()+") "+
                            "["+vicISName+ChatColor.YELLOW+ "]" +
                            " <-✙- "+"自害");
            return;
        }

        //プレイヤーを殺した人間がいた場合
        Player killer = victim.getKiller();
        if(!isInMap(killer))return;
        KGPlayer kgKiller = playerMap.get(killer);

        //キルカウントを追加し、装備を更新
        kgKiller.addKillCount(1);
        kgKiller.updateArmor();

        String killISName="グー";
        ItemStack killerIS = killer.getInventory().getItemInMainHand();
        if(killerIS!=null && killerIS.getItemMeta()!=null){
            killISName = killerIS.getItemMeta().getDisplayName();
        }

        vicISName = "チョキ";
        if(victimIS!=null && victimIS.getItemMeta()!=null){
            vicISName = victimIS.getItemMeta().getDisplayName();
        }


        e.setDeathMessage(
                ChatColor.YELLOW+"■ "+killer.getDisplayName()+
                        " ("+kgKiller.getKillCount()+") " +
                        "["+ killISName + ChatColor.YELLOW+"]" +
                        " -✙-> "+
                        victim.getDisplayName()+
                        " ("+kgVictim.getKillCount()+") "+
                        "["+ vicISName + ChatColor.YELLOW+"]");
    }

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent e){

        Player player = e.getPlayer();
        if(!isInMap(player))return;

        KGPlayer kgReviver = playerMap.get(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                player.setLevel(kgReviver.getKillCount());
                player.setExp(((float)kgReviver.getKillCount()/(float)wholeScore));
            }
        }.runTaskLater(ServiceLocator.getInstance().getPlugin(), 20);

        ServiceLocator.getInstance().getGameManager().shootFireWorks(
                e.getRespawnLocation(), FireworkEffect.Type.BALL_LARGE,Color.RED,Color.BLUE,true,true,1);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerDeath(PlayerDeathEvent e){

        Player victim = e.getEntity();

        if(!isInMap(victim))return;

        e.setKeepInventory(true);
        e.getDrops().clear();
        e.setDroppedExp(0);

        Location spawnLoc = gameMap.getRandomSpawnLocation();
        victim.setBedSpawnLocation(spawnLoc,true);
    }

    @EventHandler
    public void rejectDurabity(PlayerItemDamageEvent e){
        if(!isInMap(e.getPlayer()))return;
        e.setCancelled(true);
    }

    @EventHandler
    public void rejectDrop(PlayerDropItemEvent e){
        if(!isInMap(e.getPlayer()))return;
        e.setCancelled(true);
    }

    @EventHandler
    public void rejectBreak(BlockBreakEvent e){
        if(!isInMap(e.getPlayer()))return;
        if(breakableBlocks.contains(e.getBlock().getType()))return;
        e.setCancelled(true);
    }

    @EventHandler
    public void rejectFireworkDamage(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Firework)e.setCancelled(true);
    }

    @EventHandler
    public void rejectDestroyFarmLand(PlayerInteractEvent e)
    {
        if(e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND)
            e.setCancelled(true);
    }

    public boolean isInMap(Player player){

        if(ServiceLocator.getInstance().getGameManager().getKGPlayerMap().containsKey(player)){
            return true;
        }

        return false;
    }

}
