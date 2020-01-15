package com.example.phonebook.ui.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.phonebook.R;
import com.example.phonebook.model.ContactsModel;
import com.example.phonebook.ui.ContactViewModel;
import com.example.phonebook.ui.activity.CountryCode;
import com.example.phonebook.ui.assets.InputFilterMinMax;

public class ContactAddFragment extends Fragment {


    Bundle bundle;
    private int id;

    private ImageView profilemage;
    private TextView countryCode;
    private String _picture_path;
    private String _first_name;
    private String _last_name;
    private String _phone_number;
    private String _country_code;
    private String _mail_address;
    private String _address;
    private String _longitude;
    private String _Latitude;
    private String _date;
    private ContactsModel contactsModel;
    private String avatarUrl = "https://i7.pngguru.com/preview/516/872/30/computer-icons-avatar-user-profile-photographer.jpg";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_contact, container, false);
        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = this.getArguments();
        if (bundle != null) {

            contactsModel = (ContactsModel) bundle.getSerializable("Edit");

        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profilemage = view.findViewById(R.id.profile_image);
        final EditText firstName = view.findViewById(R.id.edtxt_first_name_add_contact);
        final EditText lastName = view.findViewById(R.id.edtxt_last_name_add_contact);
        final EditText phoneNumber = view.findViewById(R.id.edtxt_phone_add_contact);
        countryCode = view.findViewById(R.id.txt_select_country_code_add_contact);
        final EditText mail = view.findViewById(R.id.edtxt_mail_add_contact);
        final EditText address = view.findViewById(R.id.edtxt_address_add_contact);
        final EditText latitude = view.findViewById(R.id.edtxt_latitude_add_contact);
        latitude.setFilters(new InputFilter[]{new InputFilterMinMax(-90 ,90)});

        final EditText longitude = view.findViewById(R.id.edtxt_longitude_add_contact);
        longitude.setFilters(new InputFilter[]{new InputFilterMinMax(-180 ,180)});
        Button save = view.findViewById(R.id.btn_add_now_contact);

        Glide.with(getActivity()).load(avatarUrl).apply(RequestOptions.circleCropTransform()).into(profilemage);


        if (contactsModel != null) {
            id = contactsModel.getContact_id();
            Glide.with(getActivity()).load(contactsModel.getPicture_path()).apply(RequestOptions.circleCropTransform()).into(profilemage);
            firstName.setText(contactsModel.getFirst_name());
            lastName.setText(contactsModel.getLast_name());
            countryCode.setText(contactsModel.getCountry_code());
            phoneNumber.setText(contactsModel.getPhone_number());
            mail.setText(contactsModel.getMail_address());
            address.setText(contactsModel.getAddress());
            latitude.setText(contactsModel.getLatitude());
            longitude.setText(contactsModel.getLongitude());
        }


        profilemage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 5);

            }
        });

        countryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CountryCode.class);
                startActivityForResult(intent, 15);
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                _first_name = firstName.getText().toString();
                _last_name = lastName.getText().toString();
                _phone_number = phoneNumber.getText().toString();
                _country_code = countryCode.getText().toString();
                _mail_address = mail.getText().toString();
                _address = address.getText().toString();
                _longitude = longitude.getText().toString();
                _Latitude = latitude.getText().toString();
                _date = "";

                ContactsModel contactsModel = new ContactsModel(
                        _picture_path,
                        _first_name,
                        _last_name,
                        _phone_number,
                        _country_code,
                        _mail_address,
                        _address,
                        _longitude,
                        _Latitude,
                        _date);

                ContactViewModel contactViewModel = ViewModelProviders.of(getActivity()).get(ContactViewModel.class);

                if (bundle != null)
                {
                    contactsModel.setContact_id(id);
                    contactViewModel.upadteContact(contactsModel);

                }else
                {
                    contactViewModel.insertContact(contactsModel);
                }
                getFragmentManager().beginTransaction().replace(R.id.frame_layout_main_activity, new ContactListFragment()).commit();

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5 && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();


            _picture_path = selectedImage.toString();
            Glide.with(getActivity()).load(selectedImage).apply(RequestOptions.circleCropTransform()).into(profilemage);

        } else if (requestCode == 15 && resultCode == getActivity().RESULT_OK && null != data) {

            Toast.makeText(getActivity(), "code is "+data.getStringExtra("countryCode"), Toast.LENGTH_SHORT).show();
            countryCode.setText(data.getStringExtra("countryCode"));


        }
    }
}
