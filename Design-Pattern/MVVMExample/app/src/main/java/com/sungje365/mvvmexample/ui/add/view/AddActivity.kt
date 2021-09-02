package com.sungje365.mvvmexample.ui.add.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.sungje365.mvvmexample.R
import com.sungje365.mvvmexample.data.model.Contact
import com.sungje365.mvvmexample.ui.main.viewmodel.ContactViewModel

class AddActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_CONTACT_NAME = "EXTRA_CONTACT_NAME"
        const val EXTRA_CONTACT_NUMBER = "EXTRA_CONTACT_NUMBER"
        const val EXTRA_CONTACT_ID = "EXTRA_CONTACT_ID"
    }

    private val contactViewModel: ContactViewModel by lazy {
        ViewModelProvider(this).get(ContactViewModel::class.java)
    }
    private val etAddName: EditText by lazy {
        findViewById(R.id.et_add_name)
    }
    private val etAddNumber: EditText by lazy {
        findViewById(R.id.et_add_number)
    }
    private val btnAddDone: Button by lazy {
        findViewById(R.id.btn_add_done)
    }
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        // intent null check & get extras
        if (intent != null && intent.hasExtra(EXTRA_CONTACT_NAME) &&
            intent.hasExtra(EXTRA_CONTACT_NUMBER) && intent.hasExtra(EXTRA_CONTACT_ID)) {

            etAddName.text.apply { intent.getStringExtra(EXTRA_CONTACT_NAME) }
            etAddNumber.text.apply { intent.getStringExtra(EXTRA_CONTACT_NUMBER) }
            id = intent.getLongExtra(EXTRA_CONTACT_ID, -1)
        }

        btnAddDone.setOnClickListener {
            val name = etAddName.text.toString().trim()
            val number = etAddNumber.text.toString()

            if (name.isEmpty() || number.isEmpty()) {
                Snackbar.make(
                    findViewById(R.id.layout_add),
                    R.string.add_empty,
                    Snackbar.LENGTH_SHORT
                ).show()
            } else {
                val initial = name.first().uppercaseChar()
                contactViewModel.insert(Contact(id, name, number, initial))
                finish()
            }
        }
    }
}