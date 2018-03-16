package com.fullversion.mobilesecurity.rizziruggiero.KnownVulnerabilities;

/**
 * Created by hp01 on 26/01/2018.
 */

public class VulnerableApp{
    int ID;
    String NAME;
    String PACK;
    String VERSION;
    int THUMB;


    public VulnerableApp(int i,String n,String p,String v,int t){
        ID = i;
        NAME = n;
        PACK = p;
        VERSION = v;
        THUMB=t;
    }

    public VulnerableApp(){
    }

    public void setID(int id){
        ID=id;
    }
    public void setNAME(String name){
        NAME=name;
    }
    public void setPACK(String pack){
        PACK=pack;
    }
    public void setVERSION(String version) { VERSION=version;}
    public void setTHUMB(int t) { THUMB=t;}


}
