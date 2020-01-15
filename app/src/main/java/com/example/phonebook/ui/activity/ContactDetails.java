package com.example.phonebook.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.phonebook.R;
import com.example.phonebook.model.ContactsModel;

public class ContactDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        ImageView profile = findViewById(R.id.img_detail);
        TextView firstName = findViewById(R.id.txt_detail_name);
        TextView lastName = findViewById(R.id.txt_detail_last_name);
        TextView phone = findViewById(R.id.txt_detail_phone_number);
        TextView email = findViewById(R.id.txt_detail_mail);
        TextView address = findViewById(R.id.txt_detail_location);

        Button phoneCall = findViewById(R.id.btn_detail_call);

        Button mailBtn = findViewById(R.id.btn_detail_mail);

        Button locationBtn = findViewById(R.id.btn_detail_location);


        final ContactsModel contactsModel = (ContactsModel) getIntent().getSerializableExtra("ShowDetails");

        final String dialNum = contactsModel.getCountry_code() + contactsModel.getPhone_number();
        Glide.with(this).load(contactsModel.getPicture_path()).apply(RequestOptions.circleCropTransform()).into(profile);
        firstName.setText(contactsModel.getFirst_name());
        lastName.setText(contactsModel.getLast_name());
        phone.setText(dialNum);
        email.setText(contactsModel.getMail_address());
        address.setText(contactsModel.getAddress());


        phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Build.VERSION.SDK_INT < 23) {
                    phoneCall();
                }else {

                    if (ActivityCompat.checkSelfPermission(getBaseContext(),
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        phoneCall();
                    }else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions(ContactDetails.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                9);
                    }
                }
            }
        });

        mailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                if (intent.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(Intent.createChooser(intent, "Email via..."));
                }
            }
        });

        locationBtn.setOnClickListener(new View.OnClickListener() {

            String lat = contactsModel.getLatitude();
            String longitude = contactsModel.getLongitude();
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("geo:"+lat+","+longitude);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
        }
        if(permissionGranted){
            phoneCall();
        }else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:12345678900"));
            this.startActivity(callIntent);
        }else{
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
}
