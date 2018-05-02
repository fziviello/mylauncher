package com.fziviello.mylauncher.listaApp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.fziviello.mylauncher.R;

import java.util.ArrayList;
import java.util.List;

public class ListaApp extends Activity {

    private PackageManager manager;
    private List<InfoApp> apps;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_app);

        list = (ListView)findViewById(R.id.id_lista_app);

        manager = getPackageManager();
        apps = new ArrayList<InfoApp>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);

        for(ResolveInfo ri:availableActivities)
        {
            InfoApp app = new InfoApp();
            app.titolo = (String) ri.loadLabel(manager);
            app.pkg = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }

        ArrayAdapter<InfoApp> adapter = new ArrayAdapter<InfoApp>(this, R.layout.list_item, apps) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView == null){
                    convertView = getLayoutInflater().inflate(R.layout.list_item, null);
                }

                ImageView imgIcona = (ImageView)convertView.findViewById(R.id.id_img_icona);
                imgIcona.setImageDrawable(apps.get(position).icon);

                TextView txt_Titolo = (TextView)convertView.findViewById(R.id.id_txt_titolo);
                txt_Titolo.setText(apps.get(position).titolo);

                TextView txtPkg = (TextView)convertView.findViewById(R.id.id_txt_pkg);
                txtPkg.setText(apps.get(position).pkg);

                return convertView;
            }
        };

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos,
                                    long id) {
                Intent i = manager.getLaunchIntentForPackage(apps.get(pos).titolo.toString());
                ListaApp.this.startActivity(i);
            }
        });
    }

}