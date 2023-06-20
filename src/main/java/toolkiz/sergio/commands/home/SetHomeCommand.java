package toolkiz.sergio.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Không thực hiện được lệnh này");
            return true;
        }

        Player player = (Player) sender;

        player.sendMessage("Đã set nơi này làm nhà");

        return true;
    }
}
