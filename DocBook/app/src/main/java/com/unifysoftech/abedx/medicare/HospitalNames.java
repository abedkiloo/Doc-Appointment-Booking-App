package com.unifysoftech.abedx.medicare;

/**
 * Created by abedx on 3/16/2016.
 */
public class HospitalNames {
    public String HosptalName;
    public String HospitalID;

    public HospitalNames() {
    }

    public HospitalNames(String G_HospitalName) {

        this.HosptalName = G_HospitalName;
    }

    public String getHosptalName() {
        return HosptalName;
    }

    public void setHosptalName(String hosptalName) {
        HosptalName = hosptalName;
    }


    public String getHospitalID() {
        return HospitalID;
    }

    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
    }

}
