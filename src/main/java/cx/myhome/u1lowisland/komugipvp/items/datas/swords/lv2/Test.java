package cx.myhome.u1lowisland.komugipvp.items.datas.swords.lv2;

import cx.myhome.u1lowisland.komugipvp.game.KGPlayer;
import cx.myhome.u1lowisland.komugipvp.items.ISwordClickR;
import cx.myhome.u1lowisland.komugipvp.items.datas.CoolTimeableItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.EXPbyItem;
import cx.myhome.u1lowisland.komugipvp.items.datas.swords.ItemEnum;
import cx.myhome.u1lowisland.komugipvp.library.DrawForm;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import javax.vecmath.Quat4d;
import java.util.ArrayList;

public class Test extends CoolTimeableItem implements ISwordClickR {

    private final double DISTANCE = 13;
    private final double AMOUNT = 8;

    DrawForm df;

    public Test(KGPlayer kgPlayer) {
        super(kgPlayer, ItemEnum.TEST.getItemStack(), EXPbyItem.LV2,1);
        df = new DrawForm();
    }

    @Override
    public void clickR(Player clicker) {

        if(!isCanUse())return;
        startCD();

        ArrayList<Vector> vecs = df.drawCircleLocations(2,360);

        for(Vector vec : vecs){
            Quat4d quat4d = df.loquat(vec, clicker.getEyeLocation(),7);

            Location loc = clicker.getEyeLocation().clone();

            loc.setX(clicker.getEyeLocation().getX()+quat4d.getX());
            loc.setY(clicker.getEyeLocation().getY()+quat4d.getY());
            loc.setZ(clicker.getEyeLocation().getZ()+quat4d.getZ());

            Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
            clicker.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0, 0, 0, 0, dustOptions, true);
        }


    }
}
