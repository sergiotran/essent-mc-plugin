package toolkiz.sergio;

import org.bukkit.plugin.java.JavaPlugin;
import toolkiz.sergio.commands.home.HomeCommand;
import toolkiz.sergio.commands.home.SetHomeCommand;
import toolkiz.sergio.db.Database;
import toolkiz.sergio.events.PlayerHandlers;
import toolkiz.sergio.repositories.HomeRepository;
import toolkiz.sergio.repositories.PlayerRepository;

import java.sql.SQLException;

public final class Essent extends JavaPlugin {
    @Override
    public void onEnable() {
        // Connect database
        Database database = new Database();
        database.initializeDatabase();


        new PlayerRepository();
        new HomeRepository();

        // Player Events
        new PlayerHandlers(this, database);
        // Commands
        getCommand("home").setExecutor(new HomeCommand());
        getCommand("sethome").setExecutor(new SetHomeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Hello Guys");
    }
}
