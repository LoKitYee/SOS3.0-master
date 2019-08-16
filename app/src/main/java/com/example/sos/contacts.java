package com.example.sos;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sos.modal.Contact;
import com.example.sos.modal.User;
import com.example.sos.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class contacts extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private AppCompatActivity activity = contacts.this;
    private RecyclerView recyclerViewContact;
    private List<Contact> listContact;
    private ContactAdapter contactAdapter;
    private DatabaseHelper databaseHelper;
    private TextView textCount;
    private Contact contact;
    String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email=extras.getString("email");
        }

        initViews();
        initObjects();

        BottomNavigationView topView = findViewById(R.id.nav_view_top) ;
        topView.setOnNavigationItemSelectedListener(this);
        topView.getMenu() .getItem(1).setChecked(true);

        textCount = (TextView)findViewById(R.id.textViewCount);
        int contact_counts = databaseHelper.getContactCount(email);
        textCount.setText(String.format("%d",contact_counts));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(contacts.this,add_contact.class);
                intent.putExtra("email",email);
                startActivity(intent);

            }
        });

    }

    private void initViews() {
        recyclerViewContact = (RecyclerView) findViewById(R.id.recyclerViewContact);
    }

    private void initObjects() {
        listContact = new ArrayList<>();
        contactAdapter = new ContactAdapter(listContact);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewContact.setLayoutManager(mLayoutManager);
        recyclerViewContact.setItemAnimator(new DefaultItemAnimator());
        recyclerViewContact.setAdapter(contactAdapter);
        databaseHelper = new DatabaseHelper(activity);


        getDataFromSQLite();
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                listContact.clear();

                listContact.addAll(databaseHelper.getAllContact(email));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                contactAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


    public void openMainActivity()
    {  Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    public void openSetting()
    {  Intent intent = new Intent(this,Setting.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }
    public void openRescueFragment()
    {  Intent intent = new Intent(this,rescuefragment.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.navigation_home :
                openMainActivity();
                break;
            case R.id.navigation_rescue:
                openRescueFragment();
                break;
            case R.id.navigation_contacts:
                break;
            case R.id.navigation_setting:
                openSetting();
                break;

        }
        return true;
    }


}

