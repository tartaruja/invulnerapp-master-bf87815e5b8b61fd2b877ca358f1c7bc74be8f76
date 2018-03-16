package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp01 on 25/01/2018.
 */

public class DBhandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "VulnerabilitiesDB.db";
    public static final String TABLE_APPS = "apps";
    public static final String TABLE_FILES = "files";

    public static final String COLUMN_APPID = "app_id";
    public static final String COLUMN_APPNAME = "app_name";
    public static final String COLUMN_APPPACKAGE = "app_package";
    public static final String COLUMN_APPVERSION = "app_version";
    public static final String COLUMN_APPTHUMB = "app_THUMB";
    public static final String COLUMN_FILEID = "file_id";
    public static final String COLUMN_FILEDESC = "file_desc";
    public static final String COLUMN_FILEPATH = "file_path";
    public static final String COLUMN_FILENAME = "file_name";
    public static final String COLUMN_MULTI = "multiple_files";
    public static final String COLUMN_FILEAPPNAME = "file_app_name";
    public static final String COLUMN_FILEFIND = "file_find";

    public DBhandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME READ ME
// basta inserire l'app e relativi file nella forma mostrata, da qui vuiene costruito il database, e il resto del codice lo sfrutta utilizzando i metodi di questa classe
        VulnerableApp[] APPS;

        VulnerableFile[] FILES;

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void inizializer(Context context){
        APPS = new VulnerableApp[]{new VulnerableApp(1, "chrome", "com.android.chrome", "64.0.3282.137", R.drawable.chrome),
                new VulnerableApp(2, "evernote", "com.evernote", "7.16.1", R.drawable.evernote),
                new VulnerableApp(3, "tumblr", "com.tumblr", "10.0.0.00", R.drawable.tumblr),
                new VulnerableApp(4, "stalkeame", "es.softwareknights.stalkeame", "5", R.drawable.stalkaeme),
                new VulnerableApp(5, "whatsapp", "com.whatsapp", "2.18.46", R.drawable.whatsapp),
                new VulnerableApp(6, "browser", "com.android.browser", "6.0-1482200770", R.drawable.browser),
                new VulnerableApp(7, "instagram", "com.instagram.android", "31.0.0.10.94", R.drawable.insta),
                new VulnerableApp(8, "facebook", "com.facebook.katana", "158.0.0.38.95", R.drawable.face),
                new VulnerableApp(9, "sarahah", "com.sarahah.app", "2.1.4", R.drawable.sara),
                new VulnerableApp(10, "reddit", "com.reddit.frontpage", "2.22.9", R.drawable.redit),
                new VulnerableApp(11, "unfollowers", "com.dann.unfollowers", "1.0.2", R.drawable.unfollow),
                new VulnerableApp(12, "messanger", "com.facebook.orca", "152.0.0.17.94", R.drawable.mesenger),
                new VulnerableApp(13, "line", "jp.naver.line.android", "8.1.1", R.drawable.line),
                new VulnerableApp(14, "ask", "com.askfm", "4.9.5", R.drawable.ask),
                new VulnerableApp(15, "skype", "com.skype.raider", "8.15.0.4", R.drawable.skype)};

        FILES = new VulnerableFile[]{new VulnerableFile(0, "Login Data", "/data/data/com.android.chrome/app_chrome/Default/Login Data", "chrome", context.getString(R.string.descLoginData), "no", ""),
                new VulnerableFile(1, "notes", "/data/data/com.evernote/files/", "evernote", context.getString(R.string.descEverNotes), "yes", context.getString(R.string.notesHelper)),
                new VulnerableFile(2, "Tumblr.sqlite-journal", "/data/data/com.tumblr/databases/Tumblr.sqlite-journal", "tumblr", context.getString(R.string.descTumblrJournal), "no", ""),
                new VulnerableFile(3, "ARYA.Droid.xml", "/data/data/es.softwareknights.stalkeame/shared_prefs/ARYA.Droid.xml", "stalkeame", context.getString(R.string.descStalkArya), "no", ""),
                new VulnerableFile(4, "com.whatsapp_preferences.xml", "/data/data/com.whatsapp/shared_prefs/com.whatsapp_preferences.xml", "whatsapp", context.getString(R.string.descWhatPref), "no", ""),
                new VulnerableFile(5, "msgstore.db", "/data/data/com.whatsapp/databases/msgstore.db", "whatsapp", context.getString(R.string.descWhatsMsgs), "no", ""),
                new VulnerableFile(6, "http_auth.db", "/data/data/com.android.browser/databases/http_auth.db", "browser", context.getString(R.string.descBrowAuth), "no", ""),
                new VulnerableFile(7, "direct.db", "/data/data/com.instagram.android/databases/direct.db", "instagram", context.getString(R.string.descInstaDirect), "no", ""),
                new VulnerableFile(8, "saved2.db", "/data/data/com.facebook.katana/databases/saved2.db", "facebook", context.getString(R.string.descFaceSave), "no", ""),
                new VulnerableFile(9, "pref.xml", "/data/data/com.sarahah.app/shared_prefs/pref.xml", "sarahah", context.getString(R.string.descSaraPref), "no", ""),
                new VulnerableFile(10, "subs", "/data/data/com.reddit.frontpage/databases/", "reddit", context.getString(R.string.descRedsubs), "yes", context.getString(R.string.redditHelper)),
                new VulnerableFile(11, "unfollowers.db", "/data/data/com.dann.unfollowers/databases/unfollowers.db", "unfollowers", context.getString(R.string.descUnfollowContacts), "no", ""),
                new VulnerableFile(12, "threads_db2.db", "/data/data/com.facebook.orca/databases/threads_db2.db", "messanger", context.getString(R.string.descMessangerThreads), "no", ""),
                new VulnerableFile(13, "naver_line.db", "/data/data/jp.naver.line.android/databases/naver_line.db", "line", context.getString(R.string.descLineMess), "no", ""),
                new VulnerableFile(14, "mobvista.msdk.db", "/data/data/com.askfm/databases/mobvista.msdk.db", "ask", context.getString(R.string.descAskPacklist), "no", ""),
                new VulnerableFile(15, "RKStorage.db", "/data/data/com.skype.raider/databases/RKStorage.db", "skype", context.getString(R.string.descSkypeAuth), "no", "")};

    }
    public void creatAppTable(SQLiteDatabase db){
        String CREATE_APP_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_APPS + " ("
                + COLUMN_APPID + " INTEGER PRIMARY KEY," + COLUMN_APPNAME
                + " TEXT," + COLUMN_APPPACKAGE + " TEXT," + COLUMN_APPVERSION + " TEXT," + COLUMN_APPTHUMB + " INTEGER" +")";
        db.execSQL(CREATE_APP_TABLE);
    }
    public void creatFileTable(SQLiteDatabase db){
        String CREATE_FILE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_FILES + " ("
                + COLUMN_FILEID + " INTEGER PRIMARY KEY," + COLUMN_FILENAME
                + " TEXT," + COLUMN_FILEPATH + " TEXT," + COLUMN_FILEAPPNAME + " TEXT," + COLUMN_FILEDESC + " TEXT," +
                COLUMN_MULTI + " TEXT," + COLUMN_FILEFIND + " TEXT" + ")";
        db.execSQL(CREATE_FILE_TABLE);
    }
    public void fillAppTAble(){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<APPS.length;i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_APPID, APPS[i].ID);
            values.put(COLUMN_APPNAME, APPS[i].NAME);
            values.put(COLUMN_APPPACKAGE, APPS[i].PACK);
            values.put(COLUMN_APPVERSION, APPS[i].VERSION);
            values.put(COLUMN_APPTHUMB, APPS[i].THUMB);
            db.insert(TABLE_APPS, null, values);
        }
        db.close();
    }
    public void fillFileTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i=0;i<FILES.length;i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_FILEID, FILES[i].ID);
            values.put(COLUMN_FILENAME, FILES[i].NAME);
            values.put(COLUMN_FILEPATH, FILES[i].PATH);
            values.put(COLUMN_FILEAPPNAME, FILES[i].APPNAME);
            values.put(COLUMN_FILEDESC, FILES[i].DESC);
            values.put(COLUMN_MULTI, FILES[i].MULTI);
            values.put(COLUMN_FILEFIND, FILES[i].FIND);



            db.insert(TABLE_FILES, null, values);
        }
        db.close();
    }

    public VulnerableApp getAppInfo(int idapp){

        String selectQuery = "SELECT  * FROM " + TABLE_APPS + " WHERE "+COLUMN_APPID+"="+idapp;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        VulnerableApp app = new VulnerableApp();
        if (cursor.moveToFirst()) {

                app.setID(Integer.parseInt(cursor.getString(0)));
                app.setNAME(cursor.getString(1));
                app.setPACK(cursor.getString(2));
                app.setVERSION(cursor.getString(3));
                app.setTHUMB(cursor.getInt(4));
        }
        return app;

        /* NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_APPS, new String[] { COLUMN_APPID, COLUMN_APPNAME, COLUMN_APPPACKAGE }, COLUMN_APPID + "="+idapp, new String[] { String.valueOf(idapp) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        VulnerableApp app=new VulnerableApp(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2));

        return app;*/
    }

    public VulnerableFile getFileInfo(int idFile){
        String selectQuery = "SELECT  * FROM " + TABLE_FILES + " WHERE "+ COLUMN_FILEID + "=" + idFile;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        VulnerableFile file = new VulnerableFile();
        if (cursor.moveToFirst()) {
                file.setID(Integer.parseInt(cursor.getString(0)));
                file.setNAME(cursor.getString(1));
                file.setPATH(cursor.getString(2));
                file.setAPPNAME(cursor.getString(3));
                file.setDESC(cursor.getString(4));
            file.setMULTI(cursor.getString(5));
            file.setFIND(cursor.getString(6));
        }

        return file;

        /* NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING NOT WORKING
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FILES, new String[] { COLUMN_FILEID, COLUMN_FILENAME, COLUMN_FILEPATH,COLUMN_FILEAPPNAME,COLUMN_FILEDESC,COLUMN_MULTI }, COLUMN_FILEID + "="+idFile, new String[] { String.valueOf(idFile) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        VulnerableFile file=new VulnerableFile(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

        return file;*/
    }

    public List<VulnerableApp> getAllApps(){
        List<VulnerableApp> allApps= new ArrayList<VulnerableApp>();
        String selectQuery = "SELECT  * FROM " + TABLE_APPS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                VulnerableApp app = new VulnerableApp();
                app.setID(Integer.parseInt(cursor.getString(0)));
                app.setNAME(cursor.getString(1));
                app.setPACK(cursor.getString(2));
                app.setVERSION(cursor.getString(3));
                app.setTHUMB(cursor.getInt(4));
                allApps.add(app);
            } while (cursor.moveToNext());
        }
        return allApps;
    }

    public List<VulnerableFile> getAllFiles(String appToSearch){
        List<VulnerableFile> allFile= new ArrayList<VulnerableFile>();
        String selectQuery = "SELECT  * FROM " + TABLE_FILES + " WHERE "+ COLUMN_FILEAPPNAME + "='" + appToSearch + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                VulnerableFile file = new VulnerableFile();
                file.setID(Integer.parseInt(cursor.getString(0)));
                file.setNAME(cursor.getString(1));
                file.setPATH(cursor.getString(2));
                file.setAPPNAME(cursor.getString(3));
                file.setDESC(cursor.getString(4));
                file.setMULTI(cursor.getString(5));
                file.setFIND(cursor.getString(6));
                allFile.add(file);
            } while (cursor.moveToNext());
        }

        return allFile;
    }



}
