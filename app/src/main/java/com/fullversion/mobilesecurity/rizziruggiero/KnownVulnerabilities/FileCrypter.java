package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities.CoreClass.Inizializer;
import static com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities.CoreClass.TotalFileCipher;

public class FileCrypter extends Activity implements View.OnClickListener {


    String FileName ="";
    String FilePath ="";
    static String MyAppPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_crypter);
        MyAppPath = "/data/data/com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities/files/";
        TextView feed = findViewById(R.id.FeedbacktextView);
        Intent i = getIntent();
        FileName = i.getExtras().getString("FileName");
        FilePath = i.getExtras().getString("FilePath");
        //boolean permReset = i.getExtras().getBoolean("ResetPermission");
        MyAppPath = MyAppPath.concat(FileName);
        TextView desc = findViewById(R.id.Description);
        desc.setText(i.getExtras().getString("Description"));
        try {
            String ActualFile = Inizializer(getApplicationContext(),FileName,MyAppPath,FilePath);
            feed.setText("\n"+getResources().getString(R.string.thisisyourfile)
            +":\n\n"+ActualFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        Button encbutton = findViewById(R.id.EncButton);
        Button decbutton = findViewById(R.id.DecButton);
        Button showbutton = findViewById(R.id.SHOWbutton);
        encbutton.setOnClickListener(this);
        decbutton.setOnClickListener(this);
        showbutton.setOnClickListener(this);




    }

    @SuppressLint("SdCardPath")
    public void onClick(View v) {

        TextView feed = findViewById(R.id.FeedbacktextView);
        EditText pass = findViewById(R.id.PasseditText);
        String password = String.valueOf(pass.getText());
        switch (v.getId()) {

            case  R.id.EncButton: {
                try {
                    String encryptedString = TotalFileCipher(password,FilePath,getApplicationContext(),FileName,MyAppPath,0);
                    feed.setText("\n"+getResources().getString(R.string.encdone)
                            +"\n\n"+encryptedString);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }




                break;
            }

            case R.id.DecButton: {
                try {
                    String decryptedString = TotalFileCipher(password,FilePath,getApplicationContext(),FileName,MyAppPath,1);
                    feed.setText("\n"+getResources().getString(R.string.decdone)+"\n\n"+decryptedString);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                break;
            }

            case R.id.SHOWbutton: {
                Button showbutton = findViewById(R.id.SHOWbutton);
                showbutton.setVisibility(View.GONE);
                feed.setVisibility(View.VISIBLE);

            }

        }
    }



}
