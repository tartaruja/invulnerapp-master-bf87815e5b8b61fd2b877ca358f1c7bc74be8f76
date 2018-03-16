package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;

import android.content.Context;
import android.util.Log;

import org.sufficientlysecure.rootcommands.Shell;
import org.sufficientlysecure.rootcommands.command.SimpleCommand;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.concurrent.TimeoutException;

/**
 * Created by hp01 on 29/11/2017.
 */

public class CoreClass {

    public static String Inizializer(Context context,String FileName,String MyAppPath,String FilePath,boolean permissionReset) throws IOException, TimeoutException {

        writeToFile("",context,FileName);
        Shell shell = null;
        shell = Shell.startRootShell();
        SimpleCommand command2 = new SimpleCommand("cat \""+FilePath+"\"");
        SimpleCommand command3 = new SimpleCommand("rm \""+MyAppPath+"\"");
        shell.add(command2).waitForFinish();
        shell.add(command3).waitForFinish();

        if(permissionReset){
            shell = Shell.startRootShell();
            SimpleCommand command4 = new SimpleCommand("chmod -R 744 data/");
            shell.add(command4).waitForFinish();
        }


        return command2.getOutput();
    }

    public static String Inizializer(Context context,String FileName,String MyAppPath,String FilePath)
            throws IOException, TimeoutException {

        writeToFile("",context,FileName);
        Shell shell = null;
        shell = Shell.startRootShell();
        SimpleCommand command2 = new SimpleCommand("cat \""+FilePath+"\"");
        SimpleCommand command3 = new SimpleCommand("rm \""+MyAppPath+"\"");
        shell.add(command2).waitForFinish();
        shell.add(command3).waitForFinish();

        return command2.getOutput();
    }

    public static String TotalFileCipher(String pass,String FilePath,Context context,
                                         String FileName,String MyAppPath,int CipherMode)
            throws IOException, TimeoutException {

        Shell shell = null;
        shell = Shell.startRootShell();
        SimpleCommand command2 = new SimpleCommand("cat \""+FilePath+"\"");
        shell.add(command2).waitForFinish();
        String stuffToEncrypt = command2.getOutput();
        String cryptedString = CipherSetAndRun(stuffToEncrypt,pass,CipherMode);
        writeToFile(cryptedString,context,FileName);

        SimpleCommand command4 = new SimpleCommand("mv \""+MyAppPath+"\" \""+FilePath+"\"");
        shell.add(command4).waitForFinish();
        return cryptedString;
    }


    private static String CipherSetAndRun(String stuffToCrypt, String pass, int mode){
        switch (mode){
            case 0: return encryption(stuffToCrypt,pass);
            case 1: return decryption(stuffToCrypt,pass);
            default: return "something went wrong!";
        }

    }


    private static void writeToFile(String data, Context context, String FileName) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(FileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public static String encryption(String strNormalText, String password){
        String seedValue = password;
        String normalTextEnc="";
        try {
            normalTextEnc = AESHelper.encrypt(seedValue, strNormalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalTextEnc;
    }
    public static String decryption(String strEncryptedText, String password){
        String seedValue = password;
        String strDecryptedText="";
        try {
            strDecryptedText = AESHelper.decrypt(seedValue, strEncryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }


}
