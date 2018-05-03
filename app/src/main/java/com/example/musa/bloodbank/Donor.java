package com.example.musa.bloodbank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Musa on 4/28/2018.
 */

public class Donor {

    private int donorID;
    private String donorName;
    private String donorEmail;
    private String donorContact;
    private String donorAge;
    private String donorCity;
    private String donorUserName;
    private String donorUserPass;
    private  String donorGroup;

    private String details;


    private  static List<Donor> donors = new ArrayList<>();

    public Donor() {
    }

    public Donor(int donorID, String donorName, String donorEmail, String donorContact, String donorAge, String donorCity, String donorUserName, String donorUserPass, String donorGroup) {
        this.donorID = donorID;
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorContact = donorContact;
        this.donorAge = donorAge;
        this.donorCity = donorCity;
        this.donorUserName = donorUserName;
        this.donorUserPass = donorUserPass;
        this.donorGroup = donorGroup;
    }

    public Donor(int donorID, String donorName, String donorEmail, String donorContact, String donorAge, String donorCity, String donorGroup) {
        this.donorID = donorID;
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorContact = donorContact;
        this.donorAge = donorAge;
        this.donorCity = donorCity;
        this.donorUserName = donorUserName;
        this.donorUserPass = donorUserPass;
        this.donorGroup = donorGroup;
    }

    public Donor(String donorName, String donorEmail, String donorContact, String donorAge, String donorCity, String donorUserName, String donorUserPass, String donorGroup) {
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.donorContact = donorContact;
        this.donorAge = donorAge;
        this.donorCity = donorCity;
        this.donorUserName = donorUserName;
        this.donorUserPass = donorUserPass;
        this.donorGroup = donorGroup;
    }

    public Donor(String donorName, String donorContact, String donorCity) {
        this.donorName = donorName;
        this.donorContact = donorContact;
        this.donorCity = donorCity;
    }

    public Donor(String donorUserName, String donorUserPass) {
        this.donorUserName = donorUserName;
        this.donorUserPass = donorUserPass;
    }

    public Donor(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public int getDonorID() {
        return donorID;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public String getDonorContact() {
        return donorContact;
    }

    public String getDonorAge() {
        return donorAge;
    }

    public String getDonorCity() {
        return donorCity;
    }

    public String getDonorUserName() {
        return donorUserName;
    }

    public String getDonorUserPass() {
        return donorUserPass;
    }

    public String getDonorGroup() {
        return donorGroup;
    }

    public static void addDonor(Donor donor){
        donors.add(donor);
    }

    public static List<Donor> getDonors(){
        return donors;
    }
}
