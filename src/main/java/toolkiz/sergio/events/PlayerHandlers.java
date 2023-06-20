package toolkiz.sergio.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import toolkiz.sergio.Essent;
import toolkiz.sergio.db.Database;
import toolkiz.sergio.repositories.PlayerRepository;
import java.sql.SQLException;

public class PlayerHandlers implements Listener {
    private final PlayerRepository repository;
    public PlayerHandlers(Essent plugin, Database database) {
        try {
            repository = new PlayerRepository(database.getConnection().createStatement());
            Bukkit.getPluginManager().registerEvents(this, plugin);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onPlayerMovement(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        boolean isServerMember = repository.isServerMember(player.getName());

        player.setFlySpeed(isServerMember ? 0.2f : 0);
        player.setWalkSpeed(isServerMember ? 0.2f : 0);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(repository.isServerMember(player.getName())) {
            clearPlayerPotionEffect(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&lChào sục viên &f&l" + player.getName()));
        } else {
            player.kickPlayer(ChatColor.translateAlternateColorCodes('&', "Mày không phải là sục viên &f&lok!!"));
        }
        event.setJoinMessage(null);
    }

    private void clearPlayerPotionEffect(Player player) {
        for(PotionEffect effect:player.getActivePotionEffects()){
            player.removePotionEffect(effect.getType());
        }
    }
}
