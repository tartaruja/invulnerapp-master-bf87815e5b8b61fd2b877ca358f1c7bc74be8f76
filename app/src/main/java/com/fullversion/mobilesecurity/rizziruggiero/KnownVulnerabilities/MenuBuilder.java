package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MenuBuilder extends Activity implements View.OnClickListener {



    /*  OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO OBSOLETO
    class seed{
        String appname;
        int[] buttonID;
        String[] vulnerableFiles;

        seed(String name,int[] id,String[] files){
            appname=name;
            buttonID=id;
            vulnerableFiles=files;
        }
    }
    public seed[] seeds ={new seed("chrome", new int[]{R.id.LoginDataButton,R.id.HistoryButton,R.id.CookiesButton}, new String[]{"Login Data","History","Cookies"}),
            new seed("tumblr", new int[]{R.id.journalButton}, new String[]{"Tumblr journal"}),
            new seed("evernote", new int[]{R.id.NoteBrowserButton}, new String[]{"Notes"}),
            new seed("stalkeame", new int[]{R.id.stalkaryabutton}, new String[]{"my account info"}),
            new seed("whatsapp", new int[]{R.id.whatsappprefsbutton,R.id.msgstoredbbutton}, new String[]{"whatsapp user prefs","text backup"}),
            new seed("standard browser", new int[]{R.id.standardbrowserauthfactors}, new String[]{"used auth factors"}),
            new seed("instagram", new int[]{R.id.instadirectbutton}, new String[]{"direct chat massages"}),
            new seed("facebook", new int[]{R.id.facebooksavedelementsbutton}, new String[]{"saved elements"})
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_builder);
        DBhandler dbhand = new DBhandler(getApplicationContext(),null);
        String appID =getIntent().getExtras().getString("appID");
        String iconName = "lil"+appID;
        TextView versionTXT = findViewById(R.id.appVersion);
        VulnerableApp thisapp = dbhand.getAppInfo(getIntent().getExtras().getInt("appKEY"));
        String version = getResources().getString(R.string.version)+":\n" + thisapp.VERSION;
        versionTXT.setText(version);
        ImageView iconView = findViewById(R.id.imageViewICONAPP);
        Resources resources = getResources();
        int resourceId = resources.getIdentifier(iconName, "drawable",
                getPackageName());

        iconView.setImageResource(resourceId);
        buildMenu(appID,dbhand);
    }

    public void onClick(View v) {
        Intent intent;
        DBhandler dbhand = new DBhandler(getApplicationContext(),null);
        dbhand.inizializer(getApplicationContext());
        VulnerableFile file = dbhand.getFileInfo(v.getId());
        if(file.MULTI.equalsIgnoreCase("yes")){
            intent = new Intent(this, FileBrowser.class);
            intent.putExtra("start",file.PATH);
            intent.putExtra("Description",file.DESC);
            intent.putExtra("findingProcedure",file.FIND);
            startActivity(intent);
        }
        else{
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName",file.NAME);
            intent.putExtra("FilePath",file.PATH);
            intent.putExtra("Description",file.DESC);
            intent.putExtra("ResetPermission",false);

            startActivity(intent);

        }

        /*
        switch (v.getId()) {

        //creare qui dentro il case per i nuovi file sensibili

        //-------------------------------------------------------------------------------
        case R.id.LoginDataButton:
            //Uno dei bottoni per i file di chrome. UTILE ESEMPIO DI BOTTONE PER FILE SINGOLO
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","Login Data");
            intent.putExtra("FilePath","/data/data/com.android.chrome/app_chrome/Default/Login Data");
            intent.putExtra("Description","Login data contains all the passwords and username you saved with Google Chrome");

            startActivity(intent);
            break;

        case R.id.HistoryButton:

            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","History");
            intent.putExtra("FilePath","/data/data/com.android.chrome/app_chrome/Default/History");
            intent.putExtra("Description","History contains all the web pages you visited with Google Chrome");

            startActivity(intent);
            break;

        case R.id.CookiesButton:

            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","Cookies");
            intent.putExtra("FilePath","/data/data/com.android.chrome/app_chrome/Default/Cookies");
            intent.putExtra("Description","COOKIES ARE USELESS");
            startActivity(intent);
            break;

        case R.id.NoteBrowserButton:
            //bottone per le note di evernote. UTILE ESEMPIO DI INTENTI PER IL FILE BROWSER
            intent = new Intent(this, FileBrowser.class);
            intent.putExtra("start","/data/data/com.evernote/files/");
            startActivity(intent);
            break;

        case R.id.journalButton:
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","Tumblr.sqlite-journal");
            intent.putExtra("FilePath","/data/data/com.tumblr/databases/Tumblr.sqlite-journal");
            intent.putExtra("Description","contains informations about your last visited tumblr blogs");

            startActivity(intent);
            break;

        case R.id.stalkaryabutton:
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","ARYA.Droid.xml");
            intent.putExtra("FilePath","/data/data/es.softwareknights.stalkeame/shared_prefs/ARYA.Droid.xml");
            intent.putExtra("Description","contains many information about the account you logged in through the stalkeame app");

            startActivity(intent);
            break;

        case R.id.whatsappprefsbutton:
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","com.whatsapp_preferences.xml");
            intent.putExtra("FilePath","/data/data/com.whatsapp/shared_prefs/com.whatsapp_preferences.xml");
            intent.putExtra("Description","at some point contains your email used for the backup and your phone number");

            startActivity(intent);
            break;

        case R.id.msgstoredbbutton:
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","msgstore.db");
            intent.putExtra("FilePath","/data/data/com.whatsapp/databases/msgstore.db");
            intent.putExtra("Description","contains a backup of your conversation and some infos about groupchat and contacts");

            startActivity(intent);
            break;


        case R.id.standardbrowserauthfactors:
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","http_auth.db");
            intent.putExtra("FilePath","/data/data/com.android.browser/databases/http_auth.db");
            intent.putExtra("Description","contains passwords and ids used to log while using standard android browser");

            startActivity(intent);
            break;

        case R.id.instadirectbutton:
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","direct.db");
            intent.putExtra("FilePath","/data/data/com.instagram.android/databases/direct.db");
            intent.putExtra("Description","contains direct chat massages backup");

            startActivity(intent);
            break;

        case R.id.facebooksavedelementsbutton:
            intent = new Intent(this, FileCrypter.class);
            intent.putExtra("FileName","saved2.db");
            intent.putExtra("FilePath","/data/data/com.facebook.katana/databases/saved2.db");
            intent.putExtra("Description","contains infos about your saved elements");

            startActivity(intent);
            break;
        //-------------------------------------------------------------------------------
    }*/

}



    private void buildMenu(String apptosearch,DBhandler dbhand){
        LinearLayout layout = (LinearLayout) findViewById(R.id.listapps);
        Button button;
        boolean found = false;
        int h=0;
        List<VulnerableFile> allVulnerableFiles = dbhand.getAllFiles(apptosearch);


        for(int i=0;i<allVulnerableFiles.size();i++) {

                if(allVulnerableFiles.get(i).APPNAME.equalsIgnoreCase(apptosearch)){
                    button = new Button(getApplicationContext());
                    button.setId(allVulnerableFiles.get(i).ID);
                    button.setText(allVulnerableFiles.get(i).NAME);
                    button.setBackground(getDrawable(R.drawable.mybutton));
                    button.setOnClickListener(this);
                    layout.addView(button);}

        }
    }
}
