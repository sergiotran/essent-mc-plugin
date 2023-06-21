package toolkiz.sergio;

import org.bukkit.plugin.java.JavaPlugin;
import toolkiz.sergio.commands.home.HomeCommand;
import toolkiz.sergio.commands.home.SetHomeCommand;
import toolkiz.sergio.db.Database;
import toolkiz.sergio.events.PlayerHandlers;

public final class Essent extends JavaPlugin {
    private Database database;

    private void InitHomeCommands() {
        getCommand("home").setExecutor(new HomeCommand(this.database));
        getCommand("sethome").setExecutor(new SetHomeCommand(this.database));
    }
    @Override
    public void onEnable() {
        // Connect database
        this.database = new Database();
        // Commands
        this.InitHomeCommands();
        // Player Events
        new PlayerHandlers(this, database);
    }

    @Override
    public void onDisable() {
//        this.database.disconnectDatabase();
    }
}
