package jsondbtest;

import java.sql.SQLException;

public class CreateTable {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        try (var conn = Database.connectDB()) {
            conn.createStatement().execute("create table table1 (id serial, name varchar(60), jsondata jsonb)");
        }
    }
}
