package lab_6;

import lab_6.jdbc.executor.SongDelete;
import lab_6.jdbc.executor.SongInsert;
import lab_6.jdbc.executor.SongSelect;
import lab_6.jdbc.executor.SongUpdate;


public class Main {
    public static void main(String[] args) {
        //SongInsert.songInsert();
        //SongDelete.songDelete();
        //SongUpdate.songUpdate();
        SongSelect.viewAll();
    }
}

