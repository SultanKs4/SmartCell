package id.natlus.smartcell.remoteservice;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppServiceProvider {
    private static final String BASE_URL = "https://my-json-server.typicode.com";

    private static PhoneService phoneService;
    private static CheckoutService checkoutService;

    public static CheckoutService getCheckoutService() {
        if (checkoutService == null) {

            HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor();
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            if (!httpClient.interceptors().contains(httpLogging))
                httpClient.addInterceptor(httpLogging);

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(AppServiceProvider.BASE_URL);
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.client(httpClient.build());

            Retrofit retrofit = builder.build();
            checkoutService = retrofit.create(CheckoutService.class);
        }
        return checkoutService;
    }

    public static PhoneService getPhoneService() {
        if (phoneService == null) {
            HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor();
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            if (!httpClient.interceptors().contains(httpLogging))
                httpClient.addInterceptor(httpLogging);

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(AppServiceProvider.BASE_URL);
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.client(httpClient.build());

            Retrofit retrofit = builder.build();
            phoneService = retrofit.create(PhoneService.class);
        }
        return phoneService;
    }
}
