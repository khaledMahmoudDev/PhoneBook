package com.example.phonebook.ui;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.db.ContactDAO;
import com.example.phonebook.db.ContatcsDB;
import com.example.phonebook.model.ContactsModel;
import com.example.phonebook.model.CountriesModel;
import com.example.phonebook.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactsReposatory {

    private ContactDAO contactDAO;
    private LiveData<List<ContactsModel>> allContacts;
    private MutableLiveData<List<CountriesModel>> allCountries;

    public ContactsReposatory(final Application application) {
        allCountries = new MutableLiveData<>();

        setNetwork();
        setDB(application);
    }



    private void setNetwork()
    {
        RetrofitClient.getInstance().getApi().getAllCountries().enqueue(new Callback<List<CountriesModel>>() {
            @Override
            public void onResponse(Call<List<CountriesModel>> call, Response<List<CountriesModel>> response) {
                if (response.body() != null)
                {
                    allCountries.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<CountriesModel>> call, Throwable t) {

            }
        });
    }

    private void setDB(Application application)
    {
        ContatcsDB contatcsDB = ContatcsDB.getInstance(application);
        contactDAO = contatcsDB.getContactDAO();
        allContacts = contactDAO.getAllContacts();

    }

    public LiveData<List<CountriesModel>> getAllCountries()
    {
        return allCountries;

    }

    public void insertContact(ContactsModel model) {

        new InsertContactAsyncTask(contactDAO).execute(model);
    }

    public void updateContact(ContactsModel model) {

        new UpdateContactAsynctask(contactDAO).execute(model);

    }

    public void deleteContact(ContactsModel model) {

        new DeleteContactAsyncTask(contactDAO).execute(model);

    }

    public LiveData<List<ContactsModel>> getAllContact() {
        return allContacts;
    }

    private static class InsertContactAsyncTask extends AsyncTask<ContactsModel, Void, Void> {

        private ContactDAO contactDAO;

        public InsertContactAsyncTask(ContactDAO contactDAO) {
            this.contactDAO = contactDAO;
        }

        @Override
        protected Void doInBackground(ContactsModel... contactsModels) {
            contactDAO.insert(contactsModels[0]);
            return null;
        }
    }

    private static class UpdateContactAsynctask extends AsyncTask<ContactsModel, Void, Void> {

        private ContactDAO contactDAO;

        public UpdateContactAsynctask(ContactDAO contactDAO) {
            this.contactDAO = contactDAO;
        }

        @Override
        protected Void doInBackground(ContactsModel... contactsModels) {
            contactDAO.update(contactsModels[0]);
            return null;
        }
    }

    private static class DeleteContactAsyncTask extends AsyncTask<ContactsModel, Void, Void> {

        private ContactDAO contactDAO;

        public DeleteContactAsyncTask(ContactDAO contactDAO) {
            this.contactDAO = contactDAO;
        }

        @Override
        protected Void doInBackground(ContactsModel... contactsModels) {
            contactDAO.delete(contactsModels[0]);
            return null;
        }
    }


}
