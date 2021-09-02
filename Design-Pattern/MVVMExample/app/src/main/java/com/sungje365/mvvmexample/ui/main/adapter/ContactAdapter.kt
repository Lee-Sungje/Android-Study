package com.sungje365.mvvmexample.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungje365.mvvmexample.R
import com.sungje365.mvvmexample.data.model.Contact

class ContactAdapter(
    val contactItemClick: (Contact) -> Unit,
    val contactItemLongClick: (Contact) -> Unit
): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    private var contacts: List<Contact> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_item_name)
        private val tvNumber = itemView.findViewById<TextView>(R.id.tv_item_number)
        private val tvInitial = itemView.findViewById<TextView>(R.id.tv_item_initial)

        fun bind(contact: Contact) {
            tvName.text = contact.name
            tvNumber.text = contact.number
            tvInitial.text = contact.initial.toString()

            itemView.setOnClickListener {
                contactItemClick(contact)
            }

            itemView.setOnLongClickListener {
                contactItemLongClick(contact)
                true
            }
        }
    }

    fun setContacts(contacts: List<Contact>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}