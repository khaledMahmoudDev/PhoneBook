package com.example.phonebook.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.phonebook.model.ContactsModel;

import java.util.List;

@Dao
public interface ContactDAO {

    @Insert
    void insert(ContactsModel model);

    @Update
    void update(ContactsModel model);

    @Delete
    void delete(ContactsModel model);

    @Query("SELECT * FROM contacts_table ORDER BY first_name,last_name ASC")
    LiveData<List<ContactsModel>> getAllContacts();

}
