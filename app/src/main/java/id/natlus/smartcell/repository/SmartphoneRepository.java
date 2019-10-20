package id.natlus.smartcell.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.natlus.smartcell.db.AppDBProvider;
import id.natlus.smartcell.db.AppDatabase;
import id.natlus.smartcell.db.CheckoutDao;
import id.natlus.smartcell.db.CheckoutEntity;
import id.natlus.smartcell.db.PhoneDao;
import id.natlus.smartcell.db.PhoneEntity;
import id.natlus.smartcell.remoteservice.AppServiceProvider;
import id.natlus.smartcell.remoteservice.CheckoutService;
import id.natlus.smartcell.remoteservice.PhoneService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmartphoneRepository {
    private AppDatabase database;
    private PhoneService service;
    private CheckoutService checkoutService;
    private LiveData<List<PhoneEntity>> phoneList;
    private PhoneEntity phoneEntity;
    private LiveData<List<CheckoutEntity>> checkoutList;

    public SmartphoneRepository(Context context) {
        database = AppDBProvider.getInstance(context);
        service = AppServiceProvider.getPhoneService();
        checkoutService = AppServiceProvider.getCheckoutService();
    }

    private boolean isOnline(){
        boolean available = true;
//        try {
//            HttpsURLConnection urlConnection = (HttpsURLConnection) (new URL("https://www.google.com").openConnection());
//            urlConnection.setRequestProperty("User-Agent", "Test");
//            urlConnection.setRequestProperty("Connection", "close");
//            urlConnection.setConnectTimeout(1500);
//            urlConnection.connect();
//            if (urlConnection.getResponseCode() == 200){
//                available = true;
//            }
//            return (urlConnection.getResponseCode() == 200);
//        } catch (IOException e){
//            Log.e("LOG_TAG","Error: Connection Failed!", e);
//        }
        return available;
    }


    public void saveCheckout(CheckoutEntity checkoutEntity){
        if (this.isOnline()){
            saveCheckoutToDb(checkoutEntity);
        } else {
            saveCheckoutToDb(checkoutEntity);
        }
    }

    private void saveCheckoutToDb(CheckoutEntity checkoutEntity) {
//        save contact asyncron
        new SaveCheckoutTask().execute(checkoutEntity);

    }

    private void saveCheckoutToWeb(CheckoutEntity checkoutEntity) {
        Call<CheckoutEntity> checkoutCall = checkoutService.postCheckout(checkoutEntity);

        checkoutCall.enqueue(new Callback<CheckoutEntity>() {
            @Override
            public void onResponse(Call<CheckoutEntity> call, Response<CheckoutEntity> response) {
                getCheckoutListFromWeb();
            }

            @Override
            public void onFailure(Call<CheckoutEntity> call, Throwable t) {

            }
        });
    }

    public LiveData<List<CheckoutEntity>> getCheckoutList(){
        if (isOnline()){
            getCheckoutListFromDB();
        } else {
            getCheckoutListFromDB();
        }
        return checkoutList;
    }

    private void getCheckoutListFromDB() {
        CheckoutDao checkoutDao = database.checkoutDao();

        checkoutList = checkoutDao.getCheckout();
    }

    private void getCheckoutListFromWeb(){
        Call<List<CheckoutEntity>> checkoutListAll = checkoutService.getCheckout();

        checkoutListAll.enqueue(new Callback<List<CheckoutEntity>>() {
            @Override
            public void onResponse(Call<List<CheckoutEntity>> call, Response<List<CheckoutEntity>> response) {
                new RemoveCheckoutTask().execute();
                List<CheckoutEntity> checkoutEntities = response.body();

                CheckoutEntity[] arrCheckout = new CheckoutEntity[checkoutEntities.size()];

                for (int i = 0; i < arrCheckout.length; i++) {
                    arrCheckout[i] = checkoutEntities.get(i);
                }

                new SaveCheckoutTask().execute(arrCheckout);
            }

            @Override
            public void onFailure(Call<List<CheckoutEntity>> call, Throwable t) {
                Log.e("ERROR_GetCheckoutList", t.getMessage());
            }
        });
    }


    public LiveData<List<PhoneEntity>> getPhoneList(){
        if (isOnline()){
            getPhoneListFromDB();
            getPhoneListFromWeb();
        } else {
            getPhoneListFromDB();
        }
        return phoneList;
    }

    private void getPhoneListFromDB() {
        PhoneDao dao = this.database.phoneDao();

        phoneList = dao.getAll();
    }

    private void getPhoneListFromWeb(){
        Call<List<PhoneEntity>> phoneListAll = this.service.getPhones();

        phoneListAll.enqueue(new Callback<List<PhoneEntity>>() {
            @Override
            public void onResponse(Call<List<PhoneEntity>> call, Response<List<PhoneEntity>> response) {
                new RemovePhoneTask().execute();
                List<PhoneEntity> phoneEntities = response.body();

                PhoneEntity[] arrPhone = new PhoneEntity[phoneEntities.size()];

                for (int i = 0; i < arrPhone.length; i++) {
                    arrPhone[i] = phoneEntities.get(i);
                }

                new SavePhoneTask().execute(arrPhone);
            }

            @Override
            public void onFailure(Call<List<PhoneEntity>> call, Throwable t) {
                Log.e("ERROR_GetPhoneList", t.getMessage());
            }
        });
    }

    public PhoneEntity findByType(String type){
        if (isOnline()){
            getPhoneByTypeFromDB(type);
        } else {
            getPhoneByTypeFromDB(type);
        }
        return phoneEntity;
    }

    private void getPhoneByTypeFromWeb(String type) {
        String url = "/smartphone/get_api.php?type=\"" + type + "\"";
        Call<List<PhoneEntity>> phoneByType = this.service.getPhones();

        phoneByType.enqueue(new Callback<List<PhoneEntity>>() {
            @Override
            public void onResponse(Call<List<PhoneEntity>> call, Response<List<PhoneEntity>> response) {
                List<PhoneEntity> phoneEntities = response.body();

                for (int i = 0; i < phoneEntities.size(); i++) {
                    String typeCheck = phoneEntities.get(i).getType();
                    if (type.equals(typeCheck)){
                        phoneEntity = phoneEntities.get(i);
                        break;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<PhoneEntity>> call, Throwable t) {
                Log.e("ERROR_GetPhoneByType", t.getMessage());
            }
        });
    }

    private void getPhoneByTypeFromDB(String type) {
        new GetPhoneByTypeTask().execute(type);
    }

    //    Inner class
    private class SavePhoneTask extends AsyncTask<PhoneEntity, Void, Void> {

        @Override
        protected Void doInBackground(PhoneEntity... phoneEntities) {
            PhoneDao dao = database.phoneDao();

            for (PhoneEntity phoneEntity : phoneEntities) {
                dao.insert(phoneEntity);
            }

            return null;
        }
    }

    private class RemovePhoneTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
//            Remove all
            database.phoneDao().removeAll();
            return null;
        }
    }

    private class SaveCheckoutTask extends AsyncTask<CheckoutEntity, Void, Void> {

        @Override
        protected Void doInBackground(CheckoutEntity... checkoutEntities) {
            CheckoutDao dao = database.checkoutDao();

            for (CheckoutEntity checkoutEntity : checkoutEntities) {
                dao.checkout(checkoutEntity);
            }

            return null;
        }
    }

    private class RemoveCheckoutTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
//            Remove all
            database.checkoutDao().removeAllCheckout();
            return null;
        }
    }

    private class GetPhoneByTypeTask extends AsyncTask<String, Void, PhoneEntity>{
        @Override
        protected PhoneEntity doInBackground(String... strings) {
            PhoneDao dao = database.phoneDao();
            phoneEntity = dao.findByType(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(PhoneEntity phoneEntity) {
            super.onPostExecute(phoneEntity);
        }
    }
}
