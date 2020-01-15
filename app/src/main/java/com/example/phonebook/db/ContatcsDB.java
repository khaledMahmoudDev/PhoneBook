package com.example.phonebook.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.phonebook.model.ContactsModel;


@Database(entities = {ContactsModel.class}, version = 1,exportSchema = false)
public abstract class ContatcsDB extends RoomDatabase {


    private static ContatcsDB instance;

    public abstract ContactDAO getContactDAO();


    public static synchronized ContatcsDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , ContatcsDB.class,"contacts_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
