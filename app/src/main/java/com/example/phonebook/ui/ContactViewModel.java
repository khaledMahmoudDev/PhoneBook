package com.example.phonebook.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.phonebook.model.ContactsModel;
import com.example.phonebook.model.CountriesModel;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {


    private ContactsReposatory repository;
    private LiveData<List<ContactsModel>> allContacts;
    private LiveData<List<CountriesModel>> allCountries;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactsReposatory(application);
        allContacts = repository.getAllContact();
        allCountries = repository.getAllCountries();
    }

    public void insertContact(ContactsModel contactsModel) {

        repository.insertContact(contactsModel);
    }

    public void deleteContact(ContactsModel contactsModel) {

        repository.deleteContact(contactsModel);
    }

    public void upadteContact(ContactsModel contactsModel) {
        repository.updateContact(contactsModel);

    }

    public LiveData<List<ContactsModel>> getAllContact() {
        return allContacts;
    }

    public LiveData<List<CountriesModel>> getAllCountries() {
        return allCountries;

    }

}
