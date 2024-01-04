package lab_6.jdbc.executor;

import lab_6.jdbc.ApplicationDataSource;
import lab_6.jdbc.TableGenerator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongSelect {
    private static final String SQL_SELECT = """
            SELECT a.name, MIN(s.duration) AS shortest_duration
            FROM public."Album" a
            INNER JOIN public."Song" s ON a.id = s.album_id
            GROUP BY a.name
            HAVING MIN(s.duration) >= 136;""";

    public static void viewAll() {
        try {
            Statement statement = ApplicationDataSource.getConnection().createStatement();
            //ResultSet set = statement.executeQuery(SQL_SELECT);
            //ResultSet set = statement.executeQuery("SELECT id, name\n" + "\tFROM public.\"Artist\";");
            //ResultSet set = statement.executeQuery("SELECT id, artist_id, name, genre\n" + "\tFROM public.\"Album\";");
            ResultSet set = statement.executeQuery("SELECT id, album_id, name, duration\n" + "\tFROM public.\"Song\";");
            createTable(set);
            statement.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void createTable(ResultSet set) throws SQLException {
        TableGenerator tableGenerator = new TableGenerator();
        List<String> headersList = new ArrayList<>();
        List<List<String>> rowsList = new ArrayList<>();
        ResultSetMetaData data = set.getMetaData();
        int columnsNumber = data.getColumnCount();
        while (set.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {
                headersList.add(data.getColumnName(i));
                row.add(set.getString(i));
            }
            rowsList.add(row);
            break;
        }
        while (set.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {
                row.add(set.getString(i));
            }
            rowsList.add(row);
        }
        System.out.print(tableGenerator.generateTable(headersList, rowsList));
    }
}

