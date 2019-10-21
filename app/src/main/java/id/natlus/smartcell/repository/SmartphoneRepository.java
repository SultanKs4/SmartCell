package id.natlus.smartcell.repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.natlus.smartcell.db.AppDBProvider;
import id.natlus.smartcell.db.AppDatabase;
import id.natlus.smartcell.db.PhoneDao;
import id.natlus.smartcell.db.PhoneEntity;
import id.natlus.smartcell.remoteservice.AppServiceProvider;
import id.natlus.smartcell.remoteservice.PhoneService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmartphoneRepository {
    private AppDatabase database;
    private PhoneService service;
    private LiveData<List<PhoneEntity>> phoneList;

    public SmartphoneRepository(Context context) {
        database = AppDBProvider.getInstance(context);
        service = AppServiceProvider.getPhoneService();
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
}
