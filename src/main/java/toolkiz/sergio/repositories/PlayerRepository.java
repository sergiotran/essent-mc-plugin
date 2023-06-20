package toolkiz.sergio.repositories;

import toolkiz.sergio.db.Database;
import toolkiz.sergio.models.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {
    private Statement statement;

    public PlayerRepository(Statement statement) {
        this.statement = statement;
        this.init();
    }
    private void init() {
        try {
            this.statement = new Database().getConnection().createStatement();
            this.statement.execute("CREATE TABLE IF NOT EXISTS member(name varchar(255) NOT NULL, money INT default 0);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Member> getMembers() {
        List<Member> members = new ArrayList<Member>();
        try {
            ResultSet statement = this.statement.executeQuery("SELECT * from member");
            while(statement.next()) {
                Member member = convertStatementToMember(statement);
                members.add(member);
            }
            return members;
        } catch (SQLException e) {
            return members;
        }
    }

    public Member getMemberByName(String playerName) {
        try {
            ResultSet statement = this.statement.executeQuery("select * from member where name = '" + playerName + "'");

            if(statement.next()) {
                return convertStatementToMember(statement);
            }

            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean isServerMember(String playerName) {
        return getMemberByName(playerName) != null;
    }

    private Member convertStatementToMember(ResultSet resultSet) {
        try {
            String name = resultSet.getString("name");
            int money = resultSet.getInt("money");

            return new Member(name, money);
        } catch (SQLException e) {
            return null;
        }
    }
}
