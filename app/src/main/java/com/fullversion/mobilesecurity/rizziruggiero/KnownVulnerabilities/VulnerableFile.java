package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;

/**
 * Created by hp01 on 26/01/2018.
 */

public class VulnerableFile {
    int ID;
    String NAME;
    String PATH;
    String APPNAME;
    String DESC;
    String MULTI;
    String FIND;

    public VulnerableFile(int i, String n, String p, String a, String d, String m,String f) {
        ID = i;
        NAME = n;
        PATH = p;
        APPNAME = a;
        DESC = d;
        MULTI = m;
        FIND =f;
    }

    public VulnerableFile() {
    }

    public void setID(int id){
        ID=id;
    }

    public void setNAME(String name){
        NAME=name;
    }
    public void setPATH(String path){
        PATH=path;
    }
    public void setAPPNAME(String appname){
        APPNAME=appname;
    }
    public void setDESC(String desc){
        DESC=desc;
    }
    public void setFIND(String find){
        FIND=find;
    }
    public void setMULTI(String multi){
        MULTI=multi;
    }
}
