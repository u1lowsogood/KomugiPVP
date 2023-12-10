package cx.myhome.u1lowisland.komugipvp.game;

import cx.myhome.u1lowisland.komugipvp.items.datas.ArmorEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.BaseItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv1.FarmSword;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class KGPlayer {

    Player player;

    ArrayList<BaseItem> baseItems;
    ArrayList<ItemStack> armorList = new ArrayList<>();

    private int killCount;
    private int killStreak;

    public KGPlayer(Player player){
        baseItems = new ArrayList<>();
        this.player = player;
        killCount = 0;

        for(ArmorEnum armorEnum : ArmorEnum.values()){
            armorList.add(armorEnum.getItemStack());
        }
    }

    public ArrayList<BaseItem> getBaseItems(){
        return this.baseItems;
    }

    public void initCondition(){
        player.setGameMode(GameMode.SURVIVAL);
        player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL ,20,5,false));
        player.setSaturation(5f);
        player.setFoodLevel(20);
        player.setExp(0f);
        player.setLevel(0);
        player.getInventory().clear();
        giveItem(new FarmSword(this));
    }

    public void sendActionMessage(String message){

        TextComponent component = new TextComponent();
        component.setText(message);

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, component);
    }

    public void giveItem(BaseItem baseItem){
        baseItems.add(baseItem);
        player.getInventory().addItem(baseItem.getItemStack());
    }

    public void addKillCount(int i){
        killCount +=i;
    }

    public int getKillCount(){
        return killCount;
    }

    public Player getPlayer() {
        return player;
    }

    public BaseItem getSimilarItem(ItemStack itemStack){

        for(BaseItem baseItem : getBaseItems()) {
            if (baseItem.getItemStack().isSimilar(itemStack)) {
                return baseItem;
            }
        }
        return null;
    }

    public Boolean hasSimilarItem(ItemStack itemStack){

        for(BaseItem baseItem : getBaseItems()) {
            if (baseItem.getItemStack().isSimilar(itemStack)) {
                return true;
            }
        }
        return false;
    }

    public void updateArmor() {

        int index = getKillCount()-1;

        if(getKillCount() <= armorList.size()) {
            switch (index % 4){
                case 0:
                    player.getInventory().setHelmet(armorList.get(index));
                    break;
                case 1:
                    player.getInventory().setChestplate(armorList.get(index));
                    break;
                case 2:
                    player.getInventory().setLeggings(armorList.get(index));
                    break;
                case 3:
                    player.getInventory().setBoots(armorList.get(index));
                    break;

            }
            player.sendMessage(ChatColor.AQUA + "■ 装備「"+
                    armorList.get(index).getItemMeta().getDisplayName() + ChatColor.AQUA + "」を獲得");
            player.getWorld().playSound(player, Sound.ITEM_ARMOR_EQUIP_IRON,1.4f,1f);
        }
    }

}
