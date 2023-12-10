package cx.myhome.u1lowisland.komugipvp.items.datas;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class CoolTimeableItem extends BaseItem{

    private boolean canUse;

    private int timeTicks;
    private final int maxTimeTicks;

    public CoolTimeableItem(KGPlayer kgPlayer, ItemStack itemStack, EXPbyItem expbyItem, int maxTimeTicks) {
        super(kgPlayer, itemStack, expbyItem);
        canUse = true;
        this.maxTimeTicks = maxTimeTicks;
        this.timeTicks =0;
    }

    //maxTime秒後にcanUseをtrueにするだけ
    public void startCD(){

        String ability = itemStack.getItemMeta().getLore().get(1);
        canUse=false;
        BossBar bar = Bukkit.createBossBar(ChatColor.AQUA+ability, BarColor.BLUE, BarStyle.SOLID);
        bar.setProgress(0);
        bar.addPlayer(kgPlayer.getPlayer());
        bar.setVisible(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                timeTicks++;
                bar.setProgress((float) timeTicks /(float)maxTimeTicks);

                if(timeTicks >= maxTimeTicks){
                    showItemReadyMessage();
                    canUse=true;
                    timeTicks =0;
                    bar.removeAll();
                    this.cancel();
                }

            }
        }.runTaskTimer(ServiceLocator.getInstance().getPlugin(), 0,1);
    }

    public void showItemReadyMessage(){
        String effectName = itemStack.getItemMeta().getLore().get(1);
        kgPlayer.getPlayer().sendMessage(ChatColor.AQUA+"■ "+ effectName + " 準備完了！");
        kgPlayer.getPlayer().playSound(kgPlayer.getPlayer(), Sound.BLOCK_NOTE_BLOCK_BELL,1f,1.4f);
    }

    public boolean isCanUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    public int getTimeTicks() {
        return timeTicks;
    }

}
