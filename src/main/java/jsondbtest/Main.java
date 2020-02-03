package jsondbtest;

import com.github.vincentrussell.json.datagenerator.JsonDataGeneratorException;
import com.github.vincentrussell.json.datagenerator.impl.JsonDataGeneratorImpl;
import com.google.common.io.Resources;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, JsonDataGeneratorException, SQLException, ClassNotFoundException {
        try (var conn = Database.connectDB()) {
            for (int i = 0; i < 100; i++) {
                var stmt = conn.prepareStatement("insert into table1 (name, jsondata) values(?, ?::JSON)");
                stmt.setString(1, "Test data");
                stmt.setString(2, new String(createRandomData()));
                stmt.execute();
            }
        }
    }

    private static byte[] createRandomData() throws IOException, JsonDataGeneratorException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        BufferedOutputStream out = new BufferedOutputStream(byteOut);
        JsonDataGeneratorImpl parser = new JsonDataGeneratorImpl();
        parser.generateTestDataJson(
                Resources.toString(
                        Resources.getResource("generation.json"),
                        StandardCharsets.UTF_8
                ),
                out
        );
        out.flush();
        return byteOut.toByteArray();
    }
}
