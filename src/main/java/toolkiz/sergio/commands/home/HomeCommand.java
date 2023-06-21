package toolkiz.sergio.commands.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toolkiz.sergio.db.Database;
import toolkiz.sergio.models.Home;
import toolkiz.sergio.repositories.HomeRepository;

import java.sql.SQLException;
import java.util.List;

public class HomeCommand implements CommandExecutor {
    private HomeRepository homeRepository;
    public HomeCommand(Database database) {
        this.homeRepository = new HomeRepository(database);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Không thực hiện được lệnh này");
            return true;
        }

        Player player = (Player) sender;

        try {
            List<Home> homes = homeRepository.getHomeList(player);

            if(args.length == 0) {
                gotoHome(homes.get(0), player);
            } else {
                for(int i = 0; i < homes.size(); i++) {
                    Home home = homes.get(i);
                    if(home.getName().equals(args[0])) {
                        gotoHome(home, player);
                    }
                }
            }

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2Đẽ wey zìa nhè"));
        } catch (SQLException e) {
            player.sendMessage(ChatColor.RED + "Bạn chưa có nhà nào.");
            e.printStackTrace();
        }

        return true;
    }

    private void gotoHome(Home home, Player player) {
        double[] coordinates = home.toDoubleArray();
        Location location = new Location(player.getWorld(), coordinates[0], coordinates[1], coordinates[2], 0, 0);
        player.teleport(location);
    }
}
