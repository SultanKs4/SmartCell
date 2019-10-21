package id.natlus.smartcell.remoteservice;

import java.util.List;

import id.natlus.smartcell.db.PhoneEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PhoneService {
    @GET("/SultanKs4/smartcell/data")
    Call<List<PhoneEntity>> getPhones();
}
