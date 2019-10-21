package id.natlus.smartcell.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import id.natlus.smartcell.db.PhoneEntity;
import id.natlus.smartcell.repository.SmartphoneRepository;

public class MainViewModel extends AndroidViewModel {
    private SmartphoneRepository smartphoneRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        smartphoneRepository = new SmartphoneRepository(application);
    }

    public LiveData<List<PhoneEntity>> getPhoneList(){
        return smartphoneRepository.getPhoneList();
    }
}
