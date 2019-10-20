package id.natlus.smartcell.remoteservice;

import java.util.List;

import id.natlus.smartcell.db.CheckoutEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CheckoutService {
    @GET("/SultanKs4/smartphone/checkout")
    Call<List<CheckoutEntity>> getCheckout();

    @POST("/SultanKs4/smartphone/checkout")
    Call<CheckoutEntity> postCheckout(@Body CheckoutEntity checkoutEntity);
}
