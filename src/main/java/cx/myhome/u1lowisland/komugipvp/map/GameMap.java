package cx.myhome.u1lowisland.komugipvp.map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GameMap implements ConfigurationSerializable {

    private String mapName;
    private ItemStack icon;
    private ArrayList<Location> spawnLocs;

    public GameMap(String mapName, ItemStack icon){
        this.mapName = mapName;
        this.icon = icon;
        spawnLocs = new ArrayList<>();
    }

    public void setSpawnLoc(ArrayList<Location> spawnLocs){
        this.spawnLocs = spawnLocs;
    }

    public void setSpawnLoc(int index, Location loc){
        spawnLocs.set(index, loc);
    }

    public void addSpawnLoc(Location loc){
        spawnLocs.add(loc);
    }

    public String getMapName(){
        return this.mapName;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public ArrayList<Location> getSpawnLocs() {
        return spawnLocs;
    }

    public Location getRandomSpawnLocation(){
        int rnd = new Random().nextInt(spawnLocs.size());
        return spawnLocs.get(rnd);
    }

    @Override
    public Map<String, Object> serialize() {

        HashMap<String,Object> map=new HashMap<String,Object>();

        map.put("mapName", mapName);
        map.put("icon", icon);

        /*if(!spawnLocs.isEmpty()) {
            int i = 0;
            for (Location loc : spawnLocs) {
                SerializableLocation serializableLocation = new SerializableLocation(loc);
                map.put("spawn"+i,serializableLocation);
                i++;
            }
        }*/
        if(!spawnLocs.isEmpty()) {
            List<SerializableLocation> serializableLocations = new ArrayList<>();

            for(Location loc : spawnLocs) serializableLocations.add(new SerializableLocation(loc));

            map.put("locations", serializableLocations);
        }

        return map;
    }

    public static GameMap deserialize(Map<String,Object> map){

        ItemStack icon = (ItemStack) map.get("icon");
        String mapName = map.get("mapName").toString();

        GameMap gameMap = new GameMap(mapName,icon);

        List<SerializableLocation> serializableLocations = (List<SerializableLocation>) map.get("locations");

        for(SerializableLocation sloc : serializableLocations)gameMap.addSpawnLoc(sloc.getLocation());

        /*for(int i=0; i<15; i++) {
            if (map.get("spawn"+i) == null) continue;
            SerializableLocation serializableLocation = (SerializableLocation) map.get("spawn"+i);
            gameMap.setSpawnLoc(i, serializableLocation.getLocation());
        }*/

        return gameMap;
    }
}
