package id.natlus.smartcell.db;

import android.content.Context;

import androidx.room.Room;

public class AppDBProvider {
    private static AppDatabase ourInstance;

    public static AppDatabase getInstance(Context context) {
        if (ourInstance == null){
            ourInstance = Room.databaseBuilder(context,
                    AppDatabase.class, "app_database_db").build();
        }
        return ourInstance;
    }

    public static AppDatabase getInstanceInline(Context context) {
        if (ourInstance == null){
            ourInstance = Room.databaseBuilder(context,
                    AppDatabase.class, "app_database_db").allowMainThreadQueries().build();
        }
        return ourInstance;
    }
}
