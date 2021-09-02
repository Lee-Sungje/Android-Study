package com.sungje365.mvvmexample.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungje365.mvvmexample.R
import com.sungje365.mvvmexample.data.model.Contact
import com.sungje365.mvvmexample.ui.add.view.AddActivity
import com.sungje365.mvvmexample.ui.main.adapter.ContactAdapter
import com.sungje365.mvvmexample.ui.main.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {
    private val contactViewModel: ContactViewModel by lazy {
        ViewModelProvider(this).get(ContactViewModel::class.java)
    }
    private val rvMainContact: RecyclerView by lazy {
        findViewById(R.id.rv_main_contact)
    }
    private val btnMainAdd: Button by lazy {
        findViewById(R.id.btn_main_add)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set contactItemClick & contactItemLongClick lambda
        val adapter = ContactAdapter({ contact ->
            val intent = Intent(this, AddActivity::class.java)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NAME, contact.name)
            intent.putExtra(AddActivity.EXTRA_CONTACT_NUMBER, contact.number)
            intent.putExtra(AddActivity.EXTRA_CONTACT_ID, contact.id)
            startActivity(intent)
        }, { contact ->
            deleteDialog(contact)
        })

        rvMainContact.adapter = adapter
        rvMainContact.layoutManager = LinearLayoutManager(this)
        rvMainContact.setHasFixedSize(true)

        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })

        btnMainAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact) {
        AlertDialog.Builder(this).apply {
            setMessage("Delete selected contact?")
            setNegativeButton("NO") { _, _ -> }
            setNegativeButton("YES") { _, _ -> contactViewModel.delete(contact) }
        }.show()
    }
}