package com.example.phonebook.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.phonebook.R;
import com.example.phonebook.model.ContactsModel;
import com.example.phonebook.model.CountriesModel;
import com.example.phonebook.ui.activity.CountryCode;

import java.util.ArrayList;
import java.util.List;

public class CountryCodeAdapter extends RecyclerView.Adapter<CountryCodeAdapter.CountryCodeViewHolder> {

    List<CountriesModel> countriesModels = new ArrayList<>();
    Context context;
    private CountriesListClickListener countriesListClickListener;

    public void setCountriesListClickListener(CountriesListClickListener countriesListClickListener) {
        this.countriesListClickListener = countriesListClickListener;
    }

    @NonNull
    @Override
    public CountryCodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_country_element,parent,false);
        context = parent.getContext();
        return new CountryCodeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryCodeViewHolder holder, int position) {

        CountriesModel model = countriesModels.get(position);


        Glide.with(context).load(model.getFlag()).apply(RequestOptions.circleCropTransform()).into(holder.flag);
        holder.countrayname.setText(model.getName());
        holder.countrayCapital.setText(model.getCapital());
        holder.countraycode.setText(model.getCallingCodes().get(0).toString());

    }

    @Override
    public int getItemCount() {
        return countriesModels.size();
    }
    public void setContact(List<CountriesModel> countriesModels) {
        this.countriesModels = countriesModels;
        notifyDataSetChanged();
    }

    public class CountryCodeViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView countrayname;
        TextView countrayCapital;
        TextView countraycode;

        public CountryCodeViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.img_row_country);
            countrayname = itemView.findViewById(R.id.txt_row_name_country);
            countrayCapital = itemView.findViewById(R.id.txt_row_name_capital);
            countraycode = itemView.findViewById(R.id.txt_row_code_country);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (countriesListClickListener!= null && getAdapterPosition() != RecyclerView.NO_POSITION)
                    {
                        countriesListClickListener.onCountriesItemClickListenerCallBack(countriesModels.get(getAdapterPosition()));

                    }
                }
            });
        }
    }
    public interface CountriesListClickListener {

        void onCountriesItemClickListenerCallBack(CountriesModel item);
    }
}
