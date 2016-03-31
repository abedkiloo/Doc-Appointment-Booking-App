package com.unifysoftech.abedx.medicare;

/**
 * Created by abedx on 2/22/2016.
 */
public class RowItem {
    private String County_Name;
    private String C_Status;
    private int County_Pics;
    private String Main_Sites;
    public RowItem(String CountyName,int CountyPics,String CStatus,String MainSites)
    {
        this.County_Name=CountyName;
        this.County_Pics=CountyPics;
        this.C_Status=CStatus;
        this.Main_Sites=MainSites;
    }
    public String getCounty_Name() {
        return County_Name;
    }

    public void setCounty_Name(String county_Name) {
        County_Name = county_Name;
    }

    public String getC_Status() {
        return C_Status;
    }

    public void setC_Status(String c_Status) {
        C_Status = c_Status;
    }

    public int getCounty_Pics() {
        return County_Pics;
    }

    public void setCounty_Pics(int county_Pics) {
        County_Pics = county_Pics;
    }

    public String getMain_Sites() {
        return Main_Sites;
    }

    public void setMain_Sites(String main_Sites) {
        Main_Sites = main_Sites;
    }


}
