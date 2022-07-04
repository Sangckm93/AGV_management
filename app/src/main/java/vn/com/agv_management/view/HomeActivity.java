package vn.com.agv_management.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import vn.com.agv_management.R;
import vn.com.agv_management.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        homeBinding.textView.setText(bundle.getString("Barcode result"));
    }
}