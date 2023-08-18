package com.yosfl;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlScriptRunner {

    public static void runScript(DataSource dataSource, String path) throws SQLException, IOException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            reader.close();
            statement.execute(sb.toString());
        }
    }
}
