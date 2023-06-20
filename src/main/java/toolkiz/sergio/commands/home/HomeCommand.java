package toolkiz.sergio.commands.home;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage();
            return true;
        }

        Player player = (Player) sender;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Đẽ wey zìa nhè"));

        return true;
    }
}
