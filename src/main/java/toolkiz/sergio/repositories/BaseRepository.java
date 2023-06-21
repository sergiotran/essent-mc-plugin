package toolkiz.sergio.repositories;

import toolkiz.sergio.db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseRepository {
    protected Database database;
    public BaseRepository(Database database) {
        this.database = database;
    }
}
