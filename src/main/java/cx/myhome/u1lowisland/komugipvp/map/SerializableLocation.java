package cx.myhome.u1lowisland.komugipvp.map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

public class SerializableLocation implements ConfigurationSerializable {

    private Location loc=null;

    public SerializableLocation(Location loc){
        this.loc=loc;
    }

    public Location getLocation(){
        return loc;
    }

    public Map<String,Object> serialize(){

        // マップを作る
        Map<String,Object> map=new HashMap<String,Object>();

        // この座標のワールド名をマップに保存
        map.put("world",loc.getWorld().getName());

        // この座標のX,Y,Z軸の値をマップに保存
        map.put("x",loc.getX());
        map.put("y",loc.getY());
        map.put("z",loc.getZ());

        // この座標の角度の値をマップに保存
        map.put("yaw",loc.getYaw());
        map.put("pitch",loc.getPitch());

        // データを保存したマップを返す（ここで返したマップの中身がconfig.ymlなどに保存される）
        return map;
    }


    public static SerializableLocation deserialize(Map<String,Object> map){

        // config.ymlなどから読み込まれたマップが引数に渡されるので、このマップを元に座標を作成（復元）する

        // 保存されているワールド名を元にワールドを取得
        World world= Bukkit.getWorld(map.get("world").toString());

        // 保存されているX,Y,Z軸の値を少数として読み込む
        double x=Double.parseDouble(map.get("x").toString());
        double y=Double.parseDouble(map.get("y").toString());
        double z=Double.parseDouble(map.get("z").toString());

        // 保存されている角度の値を少数として読み込む
        float yaw=Float.parseFloat(map.get("yaw").toString());
        float pitch=Float.parseFloat(map.get("pitch").toString());

        // Locationクラスのコンストラクタに読み込んだ6つの引数を渡してオブジェクトを作成
        Location loc=new Location(world,x,y,z,yaw,pitch);

        // 作成したLocationオブジェクトをSerializableLocationでラップして返す
        return new SerializableLocation(loc);
    }
}