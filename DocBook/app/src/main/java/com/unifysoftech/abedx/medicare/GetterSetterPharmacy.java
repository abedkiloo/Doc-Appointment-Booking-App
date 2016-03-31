package com.unifysoftech.abedx.medicare;

/**
 * Created by abedx on 3/24/2016.
 */
public class GetterSetterPharmacy {
    public String getPharmacyID() {
        return PharmacyID;
    }

    public void setPharmacyID(String pharmacyID) {
        PharmacyID = pharmacyID;
    }

    public String getPharmacyPhoneNumber() {
        return PharmacyPhoneNumber;
    }

    public void setPharmacyPhoneNumber(String pharmacyPhoneNumber) {
        PharmacyPhoneNumber = pharmacyPhoneNumber;
    }

    public String getPharmacyName() {
        return PharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        PharmacyName = pharmacyName;
    }

    public String getPharmacyCounty() {
        return PharmacyCounty;
    }

    public void setPharmacyCounty(String pharmacyCounty) {
        PharmacyCounty = pharmacyCounty;
    }

    public String PharmacyID;
    public String PharmacyName;
    public String PharmacyPhoneNumber;
    public String PharmacyCounty;
}
