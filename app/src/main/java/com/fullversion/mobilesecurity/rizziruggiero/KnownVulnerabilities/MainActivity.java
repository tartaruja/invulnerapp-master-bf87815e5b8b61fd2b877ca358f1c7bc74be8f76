package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import org.sufficientlysecure.rootcommands.Shell;
import org.sufficientlysecure.rootcommands.command.SimpleCommand;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class MainActivity extends Activity implements View.OnClickListener {

    //Fun Fact: 1.188 righe totali
    //Per aggiungere una nuova app:
    //inserire nel relativo array le informazione dell'applicazione
    // creare il case nel click listener
    // e poi passare alla classe MenuBuilder

    //-------------------------------------------------------------------------------
    /*
    String[] vulnerableAppsPack ={"com.android.chrome","com.evernote","com.tumblr","es.softwareknights.stalkeame","com.whatsapp","com.android.browser","com.instagram.android","com.facebook.katana"};
    String[] vulnerableAppsNames={"chrome","evernote","tumblr","stalkeame","whatsapp","standard browser","instagram","facebook"};
    int[] vulnerableAppsIds ={R.id.chromebutton,R.id.evernotebutton,R.id.tublrbutton,R.id.stalkamebutton,R.id.whatsappbutton,R.id.standardbrowser,R.id.instabutton,R.id.facebookbutton};
    */
    //-------------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Shell shell = null;
        try {
            shell = Shell.startRootShell();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DBhandler dbhand = new DBhandler(getApplicationContext(),null);
        dbhand.inizializer(getApplicationContext());
        SQLiteDatabase myDB = dbhand.getReadableDatabase();
        dbhand.creatAppTable(myDB);
        dbhand.creatFileTable(myDB);
        dbhand.fillFileTable();
        dbhand.fillAppTAble();
        String installedApps = getInstalledApps();
        buildList(installedApps,dbhand);

    }

    @Override
    public void onClick(View v) {

        DBhandler dbhand = new DBhandler(getApplicationContext(),null);
        Intent intent = new Intent(this,MenuBuilder.class);
        VulnerableApp chosenApp = dbhand.getAppInfo(v.getId());
        intent.putExtra("appID",chosenApp.NAME);
        intent.putExtra("appKEY",chosenApp.ID);
        intent.putExtra("appPACK",chosenApp.PACK);
        startActivity(intent);

        /* OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO
        switch (v.getId()) {
            case R.id.chromebutton:
                intent.putExtra("appID","chrome");


                startActivity(intent);
                break;

            case R.id.evernotebutton:
                intent.putExtra("appID","evernote");


                startActivity(intent);
                break;

            case R.id.tublrbutton:
                intent.putExtra("appID","tumblr");


                startActivity(intent);
                break;

            case R.id.stalkamebutton:
                intent.putExtra("appID","stalkeame");


                startActivity(intent);
                break;

            case R.id.whatsappbutton:
                intent.putExtra("appID","whatsapp");


                startActivity(intent);
                break;

            case R.id.standardbrowser:
                intent.putExtra("appID","standard browser");


                startActivity(intent);
                break;

            case R.id.instabutton:
                intent.putExtra("appID","instagram");


                startActivity(intent);
                break;

            case R.id.facebookbutton:
                intent.putExtra("appID","facebook");


                startActivity(intent);
                break;
            //-------------------------------------------------------------------------------

        }*/

    }

    private String getInstalledApps() {
        // start root shell
        Shell shell = null;
        try {
            shell = Shell.startRootShell();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleCommand command = new SimpleCommand("pm list packages");
        try {
            shell.add(command).waitForFinish();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String installedApps = command.getOutput();
        return installedApps;
    }

    private void buildList(String installedApps,DBhandler dbhand){
        LinearLayout layout = (LinearLayout) findViewById(R.id.listapps);
        ImageButton button;
        List<VulnerableApp> allVulnerableApps = dbhand.getAllApps();


        for(int i=0;i<allVulnerableApps.size();i++) {
            if (installedApps.contains(allVulnerableApps.get(i).NAME)) {
                button = new ImageButton(getApplicationContext());
                button.setId(allVulnerableApps.get(i).ID);
                button.setImageResource(allVulnerableApps.get(i).THUMB);
                button.setBackground(null);
                button.setOnClickListener(this);
                layout.addView(button);
            }
        }
    }
}
