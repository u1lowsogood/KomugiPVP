package cx.myhome.u1lowisland.komugipvp.items.datas.swords;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public enum EXPbyItem {

    LV1(500,10,100),
    LV2(700,15,140),
    LV3(1200,20,180),
    LV4(0,0,100);

    private int expToUpgrade, expByAttack, expByKill;

    private EXPbyItem(int expToUpgrade, int expByAttack, int expByKill) {
        this.expToUpgrade = expToUpgrade;
        this.expByAttack = expByAttack;
        this.expByKill = expByKill;
    }

    public int getExpByAttack(){
        return this.expByAttack;
    }

    public int getFarmAmountByKill(){
        return this.expByAttack/5;
    }

    public int getExpToUpgrade(){
        return this.expToUpgrade;
    }
}
