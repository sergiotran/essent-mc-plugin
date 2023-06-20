package toolkiz.sergio;

import org.bukkit.plugin.java.JavaPlugin;
import toolkiz.sergio.commands.home.HomeCommand;
import toolkiz.sergio.commands.home.SetHomeCommand;
import toolkiz.sergio.db.Database;
import toolkiz.sergio.events.PlayerHandlers;

public final class Essent extends JavaPlugin {
    @Override
    public void onEnable() {
        // Connect database
        Database database = new Database();
        database.initializeDatabase();
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
