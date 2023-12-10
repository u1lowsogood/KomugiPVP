package cx.myhome.u1lowisland.komugipvp.game;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class GameEvent implements Listener {

    @EventHandler
    public void huwattochakuchi(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (p.getLocation().getBlock().getLocation().add(0, -1, 0).getBlock().getType().equals(Material.SPONGE)) {
                    e.setCancelled(true);
                    p.getWorld().playSound(p.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1.0f);
                }
            }
        }
    }

    @EventHandler
    public void jumpPad(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(e.getAction().equals(Action.PHYSICAL) &&
                e.getClickedBlock().getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE) &&
                e.getClickedBlock().getLocation().add(0,-1,0).getBlock().getType().equals(Material.IRON_BLOCK)){

            double vecX = player.getEyeLocation().getDirection().normalize().getX();
            double vecZ = player.getEyeLocation().getDirection().normalize().getZ();
            Vector vec = new Vector(vecX,0.6,vecZ);

            player.setVelocity(vec.multiply(1.6));
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1, 1.0f);
            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,30,3));
            player.getWorld().spawnParticle(Particle.CLOUD,player.getLocation(),20,0.3,0.3,0.3,0);
        }
    }

    @EventHandler
    public void speedPad(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(e.getAction().equals(Action.PHYSICAL) &&
                e.getClickedBlock().getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE) &&
                e.getClickedBlock().getLocation().add(0,-1,0).getBlock().getType().equals(Material.DIAMOND_BLOCK)){

            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,35,3));
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1, 1.0f);
            player.getWorld().spawnParticle(Particle.CLOUD,player.getLocation(),20,0.3,0.3,0.3,0);
        }
    }

    @EventHandler
    public void flowPad(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if(e.getAction().equals(Action.PHYSICAL) &&
                e.getClickedBlock().getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE) &&
                e.getClickedBlock().getLocation().add(0,-1,0).getBlock().getType().equals(Material.GOLD_BLOCK)){

            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,35,5));
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1, 1.0f);
            player.getWorld().spawnParticle(Particle.CLOUD,player.getLocation(),20,0.3,0.3,0.3,0);
        }
    }

    @EventHandler
    public void elevetor(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            if (e.getPlayer().getLocation().add(0, -1, 0).getBlock().getType().equals(Material.LAPIS_BLOCK)) {
                for (int i = 0; i < 60; i++) {
                    if (e.getPlayer().getLocation().add(0, -1, 0).getBlock().getLocation().add(0, i, 0).getBlock().getType().equals(Material.REDSTONE_BLOCK)) {
                        e.getPlayer().teleport(e.getPlayer().getLocation().add(0, i, 0).getBlock().getLocation().add(0.5, 0, 0.5).setDirection(e.getPlayer().getEyeLocation().getDirection()));
                        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1.0f);
                        break;
                    }
                }
            } else if (e.getPlayer().getLocation().add(0, -1, 0).getBlock().getType().equals(Material.REDSTONE_BLOCK)) {
                for (int i = 0; i < 60; i++) {
                    if (e.getPlayer().getLocation().add(0, -1, 0).getBlock().getLocation().add(0, -i, 0).getBlock().getType().equals(Material.LAPIS_BLOCK)) {
                        e.getPlayer().teleport(e.getPlayer().getLocation().add(0, -i, 0).getBlock().getLocation().add(0.5, 0, 0.5).setDirection(e.getPlayer().getEyeLocation().getDirection()));
                        e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1.0f);
                        break;
                    }
                }
            }
        }
    }

}
