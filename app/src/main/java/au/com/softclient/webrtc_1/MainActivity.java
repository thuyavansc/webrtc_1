package au.com.softclient.webrtc_1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.permissionx.guolindev.PermissionX;

import au.com.softclient.webrtc_1.databinding.ActivityMainBinding;
import au.com.softclient.webrtc_1.repository.MainRepository;
import au.com.softclient.webrtc_1.ui.CallActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding views;

    private MainRepository mainRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(views.getRoot());
        init();
    }

    private void init() {
        mainRepository = MainRepository.getInstance();
        views.enterBtn.setOnClickListener(v -> {
            PermissionX.init(this)
                    .permissions(android.Manifest.permission.CAMERA, android.Manifest.permission.RECORD_AUDIO)
                    .request((allGranted, grantedList, deniedList) -> {
                        if (allGranted) {
                            //login to firebase here

                            mainRepository.login(
                                    views.username.getText().toString(), getApplicationContext(), () -> {
                                        //if success then we want to move to call activity
                                        startActivity(new Intent(MainActivity.this, CallActivity.class));
                                    }
                            );
                        }
                    });


        });
    }
}