package toolkiz.sergio.commands.home;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toolkiz.sergio.repositories.HomeRepository;

import java.sql.SQLException;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Không thực hiện được lệnh này");
            return true;
        }

        Player player = (Player) sender;
        HomeRepository homeRepository = new HomeRepository();

        if ( args.length < 1 ) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3- - - - - - - - - - - - - - - - - -"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVui lòng thêm tên cho nhà của bạn"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5VD: &2/sethome &f&l<name_of_home>"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3- - - - - - - - - - - - - - - - - -"));
            return true;
        }

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        try {
            homeRepository.setHome(player, x + " " + y + " " + z, args[0]);
            player.sendMessage("Đã set nơi này làm nhà");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
