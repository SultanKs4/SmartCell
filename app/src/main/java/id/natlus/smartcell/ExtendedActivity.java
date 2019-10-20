package id.natlus.smartcell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import id.natlus.smartcell.db.CheckoutEntity;
import id.natlus.smartcell.db.PhoneEntity;

public class ExtendedActivity extends AppCompatActivity {
    public static final String Key_RegisterActivity = "Key_RegisterActivity";
    PhoneEntity phone;

    TextView resultType, resultPrice, resultDetail, resultFullDetailDisplay, resultFullDetailBrand, resultFullDetailHardware;
    ImageView resultImageUrl;
    String fullDetailDisplay, fullDetailHardware;
    EditText editTextName, editTextAddress, editTextPhone, editTextEmail;
    Spinner paymentSpinner;

    private void initComponents(){
        resultType = findViewById(R.id.option_typeText);
        resultDetail = findViewById(R.id.option_detailText);
        resultPrice = findViewById(R.id.option_textPrice);
        resultImageUrl = findViewById(R.id.option_imgTop);
        resultFullDetailDisplay = findViewById(R.id.option_FullDetailDisplayText);
        resultFullDetailBrand = findViewById(R.id.option_FullDetailBrandText);
        resultFullDetailHardware = findViewById(R.id.option_FullDetailHardwareText);
        editTextName = findViewById(R.id.option_nameTextEdit);
        editTextAddress = findViewById(R.id.option_addressTextEdit);
        editTextPhone = findViewById(R.id.option_numberPhoneTextEdit);
        editTextEmail = findViewById(R.id.option_EmailAddressTextEdit);
        paymentSpinner = findViewById(R.id.option_spinnerPayment);

        phone = getIntent().getParcelableExtra(Key_RegisterActivity);
    }

    private void initSpec(){
        fullDetailDisplay = phone.getDisplay_size() + "\n" + phone.getDisplay_ratio() + "\n" + phone.getDisplay_resolution();
        fullDetailHardware = phone.getHardware_proc() + "\n" + phone.getHardware_camera() + "\n" + phone.getHardware_battery();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended);

        initComponents();
        initSpec();

        resultType.setText(phone.getType());
        resultDetail.setText(phone.getDetail());
        resultPrice.setText(phone.getPrice());
        resultFullDetailBrand.setText(phone.getBrand());
        resultFullDetailDisplay.setText(fullDetailDisplay);
        resultFullDetailHardware.setText(fullDetailHardware);
        Picasso.get().load(phone.getImage()).placeholder(R.color.colorWhite).into(resultImageUrl);
    }
}
