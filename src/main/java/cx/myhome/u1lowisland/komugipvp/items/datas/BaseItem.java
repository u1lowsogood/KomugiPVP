package cx.myhome.u1lowisland.komugipvp.items.datas;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.GameStateEnum;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.IItem;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerKill;
import cx.myhome.u1lowisland.komugipvp.items.UpgradeMenu;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public abstract class BaseItem implements IItem, ISwordPlayerAttack, ISwordPlayerKill {

    ItemStack itemStack;
    public ArrayList<BaseItem> upgradeItems;
    public KGPlayer kgPlayer;

    private EXPbyItem expbyItem;

    Boolean showUpgradeFlag;

    int exp;
    int maxEXP;

    public BaseItem(KGPlayer kgPlayer, ItemStack itemStack, EXPbyItem exPbyItem){
        this.kgPlayer = kgPlayer;
        this.exp = 0;
        this.maxEXP = exPbyItem.getExpToUpgrade();
        this.upgradeItems = new ArrayList<>();
        this.itemStack = itemStack;
        this.showUpgradeFlag = true;
    }

    @Override
    public void attackPlayer(Player attacker, Player victim) {
        addEXP(expbyItem.getExpByAttack());
    }

    @Override
    public void playerKill(Player killer, Player victim) {
        ServiceLocator.getInstance().getGameManager().dropUnstackableWheat(
                victim.getLocation(),expbyItem.getFarmAmountByKill());
    }

    public ArrayList<BaseItem> getUpgradeItems() {
        return upgradeItems;
    }

    public Boolean hasUpgradeBaseItem(ItemStack itemStack){
        for(BaseItem bi : upgradeItems){
            if(itemStack.isSimilar(bi.getItemStack()))return true;
        }
        return false;
    }

    public BaseItem getUpgradeBaseItem(ItemStack itemStack){
        for(BaseItem bi : upgradeItems){
            if(itemStack.isSimilar(bi.getItemStack()))return bi;
        }
        return null;
    }

    public void addEXP(int addExp){
        this.exp += addExp;
        ItemStack itemInMainHand = kgPlayer.getPlayer().getInventory().getItemInMainHand();
        float rnd = new Random().nextFloat();
        kgPlayer.getPlayer().playSound(kgPlayer.getPlayer(),Sound.ENTITY_PLAYER_LEVELUP,0.2f,1f + (rnd/5));
        if(itemInMainHand==null)return;
        if(kgPlayer.hasSimilarItem(itemInMainHand)) {
            showEXP();
        }
    }

    public void showEXP(){
        if(this.upgradeItems.isEmpty())return;
        if(this.exp >= maxEXP){
            kgPlayer.sendActionMessage(ChatColor.AQUA+""+ChatColor.BOLD+""+ChatColor.UNDERLINE+""+buildGage());
            if(!showUpgradeFlag)return;
            kgPlayer.getPlayer().sendMessage(
                    ChatColor.AQUA +
                            "■(Sneak+RClick)「" + itemStack.getItemMeta().getDisplayName()
                            + ChatColor.AQUA + "」アップグレード準備完了！");
            kgPlayer.getPlayer().playSound(kgPlayer.getPlayer(), Sound.BLOCK_NOTE_BLOCK_HARP,1,1);
            this.showUpgradeFlag=false;
        } else {
            kgPlayer.sendActionMessage(ChatColor.AQUA+buildGage());
        }
    }

    public String getAbilityName(){
        return this.getItemStack().getItemMeta().getLore().get(1);
    }

    private String buildGage(){
        int percent = (int) ((double) exp / (double) maxEXP * 100);

        String showEXP = String.format("%d",exp);
        if(exp>maxEXP)showEXP=String.format("%d",maxEXP);;

        String gage = showEXP+"/"+String.format("%d",maxEXP)+" (";

        for(double i=0; i<5; i++){
            if(((double)percent/(double)20)<i+1) {
                gage += "-";
            }else{
                gage += "■";
            }
        }
        gage +=")";
        return gage;
    }

    @Override
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    @Override
    public void openUpgradeMenu(Player player){

        if(ServiceLocator.getInstance().getGameManager().getGameState().equals(GameStateEnum.NOTINGAME))return;
        if(this.exp < maxEXP){
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BASS,1,1f);
            return;
        }

        ItemStack used = player.getInventory().getItemInMainHand();
        KGPlayer kgPlayer = ServiceLocator.getInstance().getGameManager().getKGPlayerMap().get(player);
        if(!kgPlayer.hasSimilarItem(used))return;
        BaseItem usedItem = kgPlayer.getSimilarItem(used);
        if(usedItem.getUpgradeItems().isEmpty())return;

        try {
            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_HARP,1,1f);
            MenuManager.openMenu(UpgradeMenu.class,player);
        } catch (MenuManagerException | MenuManagerNotSetupException ex) {
            ex.printStackTrace();
        }
    }
}
