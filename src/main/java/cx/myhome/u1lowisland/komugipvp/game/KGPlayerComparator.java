package cx.myhome.u1lowisland.komugipvp.game;

import java.util.Comparator;

public class KGPlayerComparator implements Comparator<KGPlayer> {
    @Override
    public int compare(KGPlayer o1, KGPlayer o2) {
        if(o1.getKillCount() == o2.getKillCount()) {
            return 0;
        }else if(o1.getKillCount() < o2.getKillCount()) {
            return 1;
        }
        return -1;
    }
}
