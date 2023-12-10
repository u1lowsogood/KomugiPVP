package cx.myhome.u1lowisland.komugipvp.game.gamemode.SpecialEvent;

public enum SpecialEventEnum {
    NONE("あｗ","ｗ"),
    HEALTHTRADE("ヘルストレード","体力がランダムに入れ替わる！"),
    SUPERNOCKBACK("スーパーノックバック","攻撃がえぐいノックバックする ！"),
    SUPERSPEED("スーパースピード","超加速する！"),
    DOUBLEDAMAGE("ダブルダメージ","あらゆるダメージが２倍！"),
    SUPERJUMP("スーパージャンプ","超ジャンプする！"),
    ULTRARAPIDSLAYER("ウルトララピッドスレイヤー","ダメージの無敵時間が半減！"),
    ULTRAFARM("ウルトラファーム","ドロップする小麦が３倍！");

    String name;
    String lore;

    public String getName(){
        return this.name;
    }

    public String getLore(){
        return this.lore;
    }

    SpecialEventEnum(String name, String lore){
        this.name=name;
        this.lore=lore;
    }
}
