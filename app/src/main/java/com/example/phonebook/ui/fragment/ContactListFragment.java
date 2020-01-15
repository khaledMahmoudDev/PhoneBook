package com.example.phonebook.ui.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebook.R;
import com.example.phonebook.model.ContactsModel;
import com.example.phonebook.ui.activity.ContactDetails;
import com.example.phonebook.ui.adapter.ContactListAdapter;
import com.example.phonebook.ui.ContactViewModel;

import java.util.List;

public class ContactListFragment extends Fragment {
    ContactViewModel contactViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_contact, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_contact);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);


        final ContactListAdapter listAdapter = new ContactListAdapter();
        recyclerView.setAdapter(listAdapter);



        contactViewModel = ViewModelProviders.of(getActivity()).get(ContactViewModel.class);

        contactViewModel.getAllContact().observe(getViewLifecycleOwner(), new Observer<List<ContactsModel>>() {
            @Override
            public void onChanged(List<ContactsModel> contactsModels) {

                listAdapter.setContact(contactsModels);
            }
        });

        listAdapter.setContactListClickListener(new ContactListAdapter.ContactListClickListener() {
            @Override
            public void onContactItemClickListenerCallBack(ContactsModel item) {

                Intent intent = new Intent(getActivity() , ContactDetails.class);
                intent.putExtra("ShowDetails",item);
                startActivity(intent);
            }

            @Override
            public void onContactItemCallBtnClickListenerCallBack(ContactsModel item) {
                String phoneNumber = item.getCountry_code()+item.getPhone_number();

                if (Build.VERSION.SDK_INT < 23) {
                    phoneCall();
                }else {

                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                        phoneCall();
                    }else {
                        final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                        //Asking request Permissions
                        ActivityCompat.requestPermissions(getActivity(), PERMISSIONS_STORAGE, 9);
                    }
                }


            }

            @Override
            public void onContactItemEditBtnClickListenerCallBack(ContactsModel item) {
                Bundle arg = new Bundle();
                arg.putSerializable("Edit",item);
                Fragment fragment = new ContactAddFragment();
                fragment.setArguments(arg);

                getFragmentManager().beginTransaction().replace(R.id.frame_layout_main_activity,fragment).addToBackStack("Edit").commit();

            }

            @Override
            public void onContactItemDeleteBtnClickListenerCallBack(final ContactsModel item) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("Do you  want to delete "+ item.getFirst_name());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                contactViewModel.deleteContact(item);
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                contactViewModel.deleteContact(item);

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
            Toast.makeText(getActivity(), "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:12345678900"));
            getActivity().startActivity(callIntent);
        }else{
            Toast.makeText(getActivity(), "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
}
