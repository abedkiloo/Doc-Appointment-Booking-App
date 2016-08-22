package com.unifysoftech.abedx.medicare;

/**
 * Created by abedx on 3/24/2016.
 */
public class GetterSetterDoctor {
    public String getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(String doctorId) {
        DoctorId = doctorId;
    }

    public String getDocName() {
        return DocName;
    }

    public void setDocName(String docName) {
        DocName = docName;
    }

    public String getDocOtherDetails() {
        return DocOtherDetails;
    }

    public void setDocOtherDetails(String docOtherDetails) {
        DocOtherDetails = docOtherDetails;
    }

    public String getDocHospitals() {
        return DocHospitals;
    }

    public void setDocHospitals(String docHospitals) {
        DocHospitals = docHospitals;
    }
    public String getDISEASEID() {
        return DISEASEID;
    }

    public void setDISEASEID(String DISEASEID) {
        this.DISEASEID = DISEASEID;
    }


    public String getDISEASESpecalisation() {
        return DISEASESpecalisation;
    }

    public void setDISEASESpecalisation(String DISEASESpecalisation) {
        this.DISEASESpecalisation = DISEASESpecalisation;
    }

    public String DoctorId;
    public String DocName;
    public String DocOtherDetails;
    public String DocHospitals;
    public String DISEASEID;
    public String DISEASESpecalisation;


}
