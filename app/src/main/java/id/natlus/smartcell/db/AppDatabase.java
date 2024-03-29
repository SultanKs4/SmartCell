package id.natlus.smartcell.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PhoneEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PhoneDao phoneDao();
}
