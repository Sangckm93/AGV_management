package vn.com.agv_management.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import vn.com.agv_management.R;
import vn.com.agv_management.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding homeBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(homeBinding.getRoot());

        setSupportActionBar(homeBinding.toolbar);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Toast.makeText(HomeActivity.this, bundle.getString("Barcode_result"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}