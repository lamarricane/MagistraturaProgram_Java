package lab_6.jdbc.executor;

import lab_6.jdbc.ApplicationDataSource;

import lombok.SneakyThrows;
import java.sql.Statement;

public class SongInsert {
    private static final String SQL_INSERT = "INSERT INTO public.\"Song\"(id, album_id, name, duration) VALUES(nextval('song_pk'), 8, 'SOOONG', 160);";

    @SneakyThrows
    public static int songInsert() {
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        int set = statement.executeUpdate(SQL_INSERT);
        statement.close();
        return set;
    }
}

