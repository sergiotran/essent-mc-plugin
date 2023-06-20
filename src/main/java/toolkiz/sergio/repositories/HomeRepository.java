package toolkiz.sergio.repositories;

import org.bukkit.entity.Player;
import toolkiz.sergio.db.Database;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeRepository {
    private Statement statement;

    public HomeRepository() {
        this.init();
    }
    private void init() {
        try {
            this.statement = new Database().getConnection().createStatement();
            this.statement.execute("CREATE TABLE IF NOT EXISTS home(name varchar(255) primary key NOT NULL,coordinate nvarchar(100) NOT NULL, owner_name varchar(255), FOREIGN KEY (owner_name) REFERENCES member(name));");
//            this.statement.execute("CREATE TABLE IF NOT EXISTS home_owner(\n" +
//                    "\tid int primary key not null auto_increment,\n" +
//                    "\towner_name varchar(255),\n" +
//                    "    home_name varchar(255),\n" +
//                    "\tconstraint FK_home_homeowner foreign key (home_name) references home(name),\n" +
//                    "    constraint FK_member_homeowner foreign key (owner_name) references member(name)\n" +
//                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHome(Player player, String coordinate, String name) throws SQLException {
        this.statement.execute("INSERT INTO home (name, coordinate, owner_name) values ('"+name+"', '"+coordinate+"', '"+player.getName()+"')");
    }
}
