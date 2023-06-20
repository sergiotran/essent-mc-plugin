package toolkiz.sergio.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import toolkiz.sergio.Essent;

public class TorchHandler implements Listener {
    public TorchHandler(Essent plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onTorchPlacement_Low(BlockPlaceEvent event) {
        if(event.getBlock().getType() == Material.TORCH) {
            event.getBlock().setType(Material.DIAMOND_BLOCK);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
    public void onTorchPlacement_Normal(BlockPlaceEvent event) {
        Block block = event.getBlock();

        if(block.getType() != Material.TORCH) {
            return;
        }

        Bukkit.getServer().broadcastMessage("Test");
    }
}
