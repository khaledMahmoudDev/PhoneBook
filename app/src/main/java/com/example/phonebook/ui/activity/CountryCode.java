package com.example.phonebook.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.phonebook.R;
import com.example.phonebook.model.ContactsModel;
import com.example.phonebook.model.CountriesModel;
import com.example.phonebook.ui.ContactViewModel;
import com.example.phonebook.ui.adapter.CountryCodeAdapter;

import java.util.List;

public class CountryCode extends AppCompatActivity {
    ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_code);

        RecyclerView countriesRecycle = findViewById(R.id.countries_recycler_view);
        countriesRecycle.setLayoutManager(new LinearLayoutManager(this));
        countriesRecycle.setHasFixedSize(true);
        final CountryCodeAdapter codeAdapter = new CountryCodeAdapter();
        countriesRecycle.setAdapter(codeAdapter);

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        contactViewModel.getAllCountries().observe(this, new Observer<List<CountriesModel>>() {
            @Override
            public void onChanged(List<CountriesModel> countriesModels) {
                codeAdapter.setContact(countriesModels);
            }
        });

        codeAdapter.setCountriesListClickListener(new CountryCodeAdapter.CountriesListClickListener() {
            @Override
            public void onCountriesItemClickListenerCallBack(CountriesModel item) {
                Intent data = new Intent();
                data.putExtra("countryCode", item.getCallingCodes().get(0).hashCode());
                setResult(RESULT_OK, data);
                finish();
            }
        });



    }
}
