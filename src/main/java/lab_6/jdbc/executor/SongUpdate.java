package lab_6.jdbc.executor;

import lab_6.jdbc.ApplicationDataSource;

import lombok.SneakyThrows;
import java.sql.Statement;

public class SongUpdate {
    private static final String SQL_UPDATE = "UPDATE public.\"Song\" SET name= 'Update_song', duration = '144', album_id = '8' where id = '8'";

    @SneakyThrows
    public static int songUpdate() {
        Statement statement = ApplicationDataSource.getConnection().createStatement();
        int set = statement.executeUpdate(SQL_UPDATE);
        statement.close();
        return set;
    }
}

