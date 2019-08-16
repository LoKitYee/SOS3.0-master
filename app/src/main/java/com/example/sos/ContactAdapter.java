package com.example.sos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sos.modal.Contact;
import com.example.sos.sql.DatabaseHelper;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactItem> {
    private List<Contact> list;
    private DatabaseHelper databaseHelper;

    public ContactAdapter(List<Contact> list)
    {
        this.list = list;
    }

    public ContactItem onCreateViewHolder(ViewGroup viewGroup, int i) {
        databaseHelper = new DatabaseHelper(viewGroup.getContext());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_list_item, viewGroup, false);
        return new ContactItem(view);
    }

    public void onBindViewHolder(ContactItem contactItem, int i) {
        Contact ci = list.get(i);
        contactItem.name.setText(list.get(i).getContactName());
        contactItem.number.setText(list.get(i).getContactNumber());
        //contactItem.email.setText(list.get(i).getContactEmail());
        contactItem.c = ci;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactItem extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener
    {
        public TextView name, number, email;
        public Contact c;
        public View v;

        public ContactItem(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewName);
            number = (TextView) itemView.findViewById(R.id.textViewNumber);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "Clicked!", Toast.LENGTH_LONG).show();
        }

        @Override
        public boolean onLongClick(final View v) {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete?")
                    .setMessage("Would you really like to delete this entry?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int rowIndex) {
                            DatabaseHelper databaseHelper;
                            databaseHelper = new DatabaseHelper(v.getContext());
                            int i = list.indexOf(c);
                            databaseHelper.deleteContact(c);
                            list.remove(c);
                            notifyItemRemoved(i);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        }
    }

}
