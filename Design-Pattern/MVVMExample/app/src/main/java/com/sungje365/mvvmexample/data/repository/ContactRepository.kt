package com.sungje365.mvvmexample.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.sungje365.mvvmexample.data.model.Contact
import com.sungje365.mvvmexample.data.repository.local.ContactDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactRepository(application: Application) {

    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            contactDao.insert(contact)
        }
    }

    fun delete(contact: Contact) {
        CoroutineScope(Dispatchers.IO).launch {
            contactDao.delete(contact)
        }
    }
}