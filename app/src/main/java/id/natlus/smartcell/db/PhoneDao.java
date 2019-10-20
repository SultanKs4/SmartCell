package id.natlus.smartcell.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert
    void insert(PhoneEntity phoneEntity);

    @Query("SELECT * FROM phone")
    LiveData<List<PhoneEntity>> getAll();

    @Query("SELECT * FROM phone WHERE type = :type")
    PhoneEntity findByType(String type);

    @Query("DELETE FROM phone")
    void removeAll();
}
