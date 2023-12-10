package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv1;

import cx.myhome.u1lowisland.komugipvp.ServiceLocator;
import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerAttack;
import cx.myhome.u1lowisland.komugipvp.items.ISwordPlayerKill;
import cx.myhome.u1lowisland.komugipvp.items.datas.BaseItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv2.Test;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv2.Typhoon;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv2.UnchikuSword;
import org.bukkit.entity.Player;

public class FarmSword extends BaseItem {

    public FarmSword(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.FarmSword.getItemStack(), EXPbyItem.LV1);
        super.upgradeItems.add(new UnchikuSword(kgPlayer));
        super.upgradeItems.add(new Typhoon(kgPlayer));
    }

}
