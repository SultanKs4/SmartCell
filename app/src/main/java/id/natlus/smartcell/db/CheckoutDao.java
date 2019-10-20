package id.natlus.smartcell.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CheckoutDao {
    @Insert
    void checkout(CheckoutEntity checkoutEntity);

    @Query("SELECT * FROM checkout")
    LiveData<List<CheckoutEntity>> getCheckout();

    @Query("DELETE FROM checkout")
    void removeAllCheckout();
}
