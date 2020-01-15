
package com.example.phonebook.model;


import java.util.ArrayList;

public class CountriesModel {

    private String name;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    ArrayList < Object > callingCodes = new ArrayList < Object > ();
    private String capital;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Object> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(ArrayList<Object> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    ArrayList< Object > topLevelDomain = new ArrayList < Object > ();
    private String alpha2Code;
    private String alpha3Code;
    ArrayList < Object > altSpellings = new ArrayList < Object > ();
    private String region;
    private String subregion;
    private float population;
    ArrayList < Object > latlng = new ArrayList < Object > ();
    private String demonym;
    private float area;
    private float gini;
    ArrayList < Object > timezones = new ArrayList < Object > ();
    ArrayList < Object > borders = new ArrayList < Object > ();
    private String nativeName;
    private String numericCode;
    ArrayList < Object > currencies = new ArrayList < Object > ();
    ArrayList < Object > languages = new ArrayList < Object > ();
    Translations TranslationsObject;
    private String flag;
    ArrayList < Object > regionalBlocs = new ArrayList < Object > ();
    private String cioc;


}
