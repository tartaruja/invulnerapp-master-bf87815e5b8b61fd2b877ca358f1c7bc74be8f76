package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.sufficientlysecure.rootcommands.Shell;
import org.sufficientlysecure.rootcommands.command.SimpleCommand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;


public class FileBrowser extends ListActivity {





    private List<String> item = null;

    private List<String> path = null;

    String root="/";

    private TextView myPath;



    /** Called when the activity is first created. */

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_browser);

        Intent i = getIntent();
        root = i.getExtras().getString("start");
        myPath = (TextView)findViewById(R.id.path);
        String findingProcedure = i.getExtras().getString("findingProcedure");


        Shell shell = null;

            try {
                shell = Shell.startRootShell();
                SimpleCommand command2 = new SimpleCommand("chmod -R 777 " + root);
                shell.add(command2).waitForFinish();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




        getDir(root);

        Alertdialog.createDialog(FileBrowser.this,"Search for files",findingProcedure);

    }



    private void getDir(String dirPath)

    {

        myPath.setText("Location: " + dirPath);



        item = new ArrayList<String>();

        path = new ArrayList<String>();



        File f = new File(dirPath);

        File[] files = f.listFiles();



        if(!dirPath.equals(root))

        {



            item.add(root);

            path.add(root);



            item.add("../");

            path.add(f.getParent());



        }



        for(int i=0; i < files.length; i++)

        {

            File file = files[i];

            path.add(file.getPath());

            if(file.isDirectory())

                item.add(file.getName() + "/");

            else

                item.add(file.getName());

        }



        ArrayAdapter<String> fileList =

                new ArrayAdapter<String>(this, R.layout.row, item);

        setListAdapter(fileList);

    }



    @Override

    protected void onListItemClick(ListView l, View v, int position, long id) {


        final Intent intent = new Intent(this,FileCrypter.class);
        final File file = new File(path.get(position));



        if (file.isDirectory())

        {

            if(true)

                getDir(path.get(position));

            else

            {

                new AlertDialog.Builder(this)


                        .setTitle("[" + file.getName() + "] folder can't be read!")

                        .setPositiveButton("OK",

                                new DialogInterface.OnClickListener() {



                                    @Override

                                    public void onClick(DialogInterface dialog, int which) {

                                        // TODO Auto-generated method stub

                                    }

                                }).show();

            }

        }

        else

        {

            new AlertDialog.Builder(this)

                    .setTitle("[" + file.getName() + "]")

                    .setPositiveButton("SEE THIS FILE",

                            new DialogInterface.OnClickListener() {



                                @Override

                                public void onClick(DialogInterface dialog, int which) {

                                    String FileName =file.getName();
                                    String FilePath = file.getPath();
                                    String Description =getIntent().getExtras().getString("Description");
                                    intent.putExtra("FileName",FileName);
                                    intent.putExtra("FilePath",FilePath);
                                    intent.putExtra("Description",Description);
                                    //intent.putExtra("ResetPermission",true);
                                    startActivity(intent);


                                }

                            }).show();

        }

    }

}