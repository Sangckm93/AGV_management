package vn.com.agv_management;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import vn.com.agv_management.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, Scanner add dependency");

        mainBinding.btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Init intent Integrator
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        MainActivity.this
                );
                // Set prompt text
                intentIntegrator.setPrompt("For Flash use volume up key");
                // Set beep
                intentIntegrator.setBeepEnabled(true);
                // Locked Screen rotate
                intentIntegrator.setOrientationLocked(true);
                // Set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                // Init scan
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Init intent Result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        // Check condition
        if (intentResult.getContents() != null){
            // When result content is not null
            // Init alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            // Set Titile
            builder.setTitle("Result");
            // Set Message
            builder.setMessage(intentResult.getContents());
            // Set Possitive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.show();

        }else{
            Toast.makeText(this, "You did not scan anything", Toast.LENGTH_SHORT).show();
        }
    }
}