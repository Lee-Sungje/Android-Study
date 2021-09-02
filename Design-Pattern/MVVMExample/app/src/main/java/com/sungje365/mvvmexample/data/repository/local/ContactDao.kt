package com.sungje365.mvvmexample.data.repository.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sungje365.mvvmexample.data.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}