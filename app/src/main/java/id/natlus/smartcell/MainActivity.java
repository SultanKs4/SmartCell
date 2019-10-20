package id.natlus.smartcell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;

import java.util.List;

import id.natlus.smartcell.adapters.PhoneAdapter;
import id.natlus.smartcell.db.PhoneEntity;
import id.natlus.smartcell.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity implements PhoneAdapter.OnPhoneClickListener {
    public static final String Key_RegisterActivity = "Key_RegisterActivity";
    public RecyclerView recyclerView;
    public PhoneAdapter phoneAdapter;
    private MainViewModel mainViewModel;
    private ImageButton refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        phoneAdapter = new PhoneAdapter(this);
        phoneAdapter.setListener(this);
        recyclerView = findViewById(R.id.rvPhone);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(phoneAdapter);
        refreshBtn = findViewById(R.id.main_btn_refresh);

        mainViewModel.getPhoneList().observe(this, phoneEntities -> phoneAdapter.setPhoneList(phoneEntities));
    }

    @Override
    public void onClick(View view, int position) {
        PhoneEntity phoneEntity = phoneAdapter.getPhoneEntityList().get(position);
        Intent i = new Intent(MainActivity.this, ExtendedActivity.class);
        i.putExtra(Key_RegisterActivity, phoneEntity);
        startActivity(i);
    }

    public void refresh(View view) {
        mainViewModel.getPhoneList().observe(this, new Observer<List<PhoneEntity>>() {
            @Override
            public void onChanged(List<PhoneEntity> phoneEntities) {
                RotateAnimation rotate = new RotateAnimation(0,360,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(1000);
                rotate.setRepeatCount(Animation.INFINITE);
                rotate.setRepeatMode(Animation.INFINITE);
                rotate.setInterpolator(new LinearInterpolator());
                new CountDownTimer(1000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        phoneAdapter.setPhoneList(phoneEntities);
                        refreshBtn.startAnimation(rotate);
                    }

                    @Override
                    public void onFinish() {
                        refreshBtn.clearAnimation();
                    }
                }.start();
            }
        });
    }
}
