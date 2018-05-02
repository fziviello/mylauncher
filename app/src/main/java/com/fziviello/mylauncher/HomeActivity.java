package com.fziviello.mylauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.fziviello.mylauncher.listaApp.ListaApp;

public class HomeActivity extends Activity
{

    private Button btnLista;
    private Button btnSettings;
    private PackageManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        btnLista = findViewById(R.id.id_btn_lista);
        btnSettings = findViewById(R.id.id_btn_settings);

        manager = getPackageManager();

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListaApp.class);
                startActivity(i);
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = manager.getLaunchIntentForPackage("com.android.settings");
                startActivity(i);
            }
        });

    }
}
