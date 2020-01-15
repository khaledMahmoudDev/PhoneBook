package com.example.phonebook.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.phonebook.R;
import com.example.phonebook.model.ContactsModel;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListViewHolder> {

    List<ContactsModel> modelList = new ArrayList<>();
    Context context;


    private ContactListClickListener contactListClickListener;

    public void setContactListClickListener(ContactListClickListener contactListClickListener) {
        this.contactListClickListener = contactListClickListener;
    }


    @NonNull
    @Override
    public ContactListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contatc_element, parent, false);
        this.context = parent.getContext();
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListViewHolder holder, final int position) {
        ContactsModel contactsModel = modelList.get(position);


        Glide.with(context).load(contactsModel.getPicture_path()).apply(RequestOptions.circleCropTransform()).into(holder.profilePicture);
        holder.firstName.setText(contactsModel.getFirst_name());


        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactListClickListener != null && position != RecyclerView.NO_POSITION) {
                    contactListClickListener.onContactItemCallBtnClickListenerCallBack(modelList.get(position));
                }

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactListClickListener != null && position != RecyclerView.NO_POSITION) {
                    contactListClickListener.onContactItemEditBtnClickListenerCallBack(modelList.get(position));
                }

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contactListClickListener != null && position != RecyclerView.NO_POSITION) {
                    contactListClickListener.onContactItemDeleteBtnClickListenerCallBack(modelList.get(position));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void setContact(List<ContactsModel> contacts) {
        this.modelList = contacts;
        notifyDataSetChanged();
    }

    class ContactListViewHolder extends RecyclerView.ViewHolder {

        ImageView profilePicture;
        TextView firstName;
        Button call;
        Button edit;
        Button delete;


        public ContactListViewHolder(@NonNull View itemView) {
            super(itemView);

            profilePicture = itemView.findViewById(R.id.img_row_element);
            firstName = itemView.findViewById(R.id.txt_row_element);
            call = itemView.findViewById(R.id.btn_call_row_element);
            edit = itemView.findViewById(R.id.btn_edit_row_element);
            delete = itemView.findViewById(R.id.btn_delete_row_element);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (contactListClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        contactListClickListener.onContactItemClickListenerCallBack(modelList.get(getAdapterPosition()));
                    }
                }
            });

        }

    }

    public interface ContactListClickListener {

        void onContactItemClickListenerCallBack(ContactsModel item);

        void onContactItemCallBtnClickListenerCallBack(ContactsModel item);

        void onContactItemEditBtnClickListenerCallBack(ContactsModel item);

        void onContactItemDeleteBtnClickListenerCallBack(ContactsModel item);

    }


}
