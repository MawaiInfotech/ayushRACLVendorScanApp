package com.mawai.raclvendorscanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navController = Navigation.findNavController(MainActivity.this, R.id.main);
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController);
        NavigationUI.setupWithNavController(toolbar, navController);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE},

                    101);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Permission Not granted", Toast.LENGTH_LONG).show();
            }
        }
    }
}