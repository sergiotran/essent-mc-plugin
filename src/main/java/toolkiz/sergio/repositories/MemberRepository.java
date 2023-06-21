package toolkiz.sergio.repositories;

import toolkiz.sergio.db.Database;
import toolkiz.sergio.models.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository extends BaseRepository {

    public MemberRepository(Database database) {
        super(database);
        initializeDatabase();
    }
    public List<Member> getMembers() {
        List<Member> members = new ArrayList<Member>();
        try {
            Object[] res = this.database.query("SELECT * from member");

            ResultSet memberRes = (ResultSet) res[0];
            Statement statement = (Statement) res[1];

            while(memberRes.next()) {
                Member member = convertStatementToMember(memberRes);
                members.add(member);
            }

            statement.close();

            return members;
        } catch (SQLException e) {
            return members;
        }
    }

    public Member getMemberByName(String playerName) {
        try {
            Object[] res = this.database.query("select * from member where name = '" + playerName + "'");

            ResultSet memberRes = (ResultSet) res[0];
            Statement statement = (Statement) res[1];

            if(memberRes.next()) {
                return convertStatementToMember(memberRes);
            }

            statement.close();

            return null;
        } catch (SQLException e) {
            System.out.println("Unable to get member");
            e.printStackTrace();
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

    private void initializeDatabase() {
        try {
            this.database.queryVoid("CREATE TABLE IF NOT EXISTS member(name varchar(255) primary key NOT NULL, money INT default 0);");
        } catch (SQLException e) {
            System.out.println("Unable to create Member table");
            e.printStackTrace();
        }
    }
}
