package toolkiz.sergio.repositories;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import toolkiz.sergio.db.Database;
import toolkiz.sergio.models.Home;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeRepository extends BaseRepository {
    public HomeRepository(Database database) {
        super(database);
        initializeDatabase();
    }

    public void setHome(Player player, String coordinate, String name) throws SQLException {
        List<Home> homes = getHomeList(player);

        boolean flag = false;

        for(int i = 0; i < homes.size(); i++) {
            Home home = homes.get(i);

            if(home.getName().equals(name)) {
                flag = true;
                player.sendMessage(ChatColor.RED + "Bạn đã set 1 nơi có tên là '"+name+"' trước đó rồi, hãy chọn tên khác.");
                break;
            }
        }


        if(!flag) {
            this.database.queryVoid("INSERT INTO home (name, coordinate, owner_name) values ('" + name + "', '" + coordinate + "', '" + player.getName() + "')");
        }
    }

    public List<Home> getHomeList(Player player) throws SQLException {
        String playerName = player.getName();
        Object[] res = this.database.query("SELECT * from home where owner_name = '"+ playerName +"'");

        ResultSet homeRes = (ResultSet) res[0];
        Statement statement = (Statement) res[1];

        List<Home> homes = new ArrayList<Home>();

        while(homeRes.next()) {
            String name = homeRes.getString("name");
            String coordinate = homeRes.getString("coordinate");
            String owner_name = homeRes.getString("owner_name");
            Home home = new Home(name, coordinate, owner_name);

            homes.add(home);
        }

        statement.close();

        return homes;
    }

    private void initializeDatabase() {
        try {
            this.database.queryVoid("CREATE TABLE IF NOT EXISTS home(id int PRIMARY KEY AUTO_INCREMENT, name varchar(255) NOT NULL,coordinate varchar(100) NOT NULL, owner_name varchar(255), FOREIGN KEY (owner_name) REFERENCES member(name))");
        } catch (SQLException e) {
            System.out.println("Unable to create Home table");
            e.printStackTrace();
        }
    }


}
