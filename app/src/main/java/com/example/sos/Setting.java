package com.example.sos;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Set;

import static java.lang.Integer.parseInt;

public class Setting extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

 String fire_Service_number,ambulance_service_number,police_service_number,Emergency_message,email;



    private static final String  ambulance_KEY = "am", police_KEY="po",fire_Key="fi", message_key="me";
     boolean isChecked_siren=false,ischecked_location=false;
    private  EditText ambulance_edittext, police,fire,message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            email=extras.getString("email");
        }

        final SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editor = prefs.edit();
        final CheckBox checkBox_siren = (CheckBox)findViewById(R.id.check_play_siren);

        if(prefs.contains("checked") && prefs.getBoolean("checked",false) == true) {
            checkBox_siren.setChecked(true);
                isChecked_siren= true;
        }else {
            checkBox_siren.setChecked(false);
            isChecked_siren= false;
        }

        checkBox_siren.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_siren.isChecked()) {
                    editor.putBoolean("checked", true);
                    isChecked_siren= true;
                    editor.apply();
                }else{
                    editor.putBoolean("checked", false);
                    editor.apply();
                    isChecked_siren= false;
                }
            }
        });

        final CheckBox checkBox_location = (CheckBox)findViewById(R.id.check_send_location);


        if(prefs.contains("checked_location") && prefs.getBoolean("checked_location",false) == true) {
            checkBox_location.setChecked(true);
            ischecked_location=true;
        }else {
            checkBox_location.setChecked(false);
            ischecked_location=false;
        }

        checkBox_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox_location.isChecked()) {
                    editor.putBoolean("checked_location", true);
                    ischecked_location=true;
                    editor.apply();
                }else{
                    editor.putBoolean("checked_location", false);
                    ischecked_location=false;
                    editor.apply();
                }
            }
        });





      ambulance_edittext= (EditText)findViewById(R.id.ambulance_phone_number);
      ambulance_edittext.setText(prefs.getString(ambulance_KEY, "999"));
      ambulance_service_number=ambulance_edittext.getText().toString();

        ambulance_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(ambulance_KEY, s.toString()).commit();
            }
        });





       police= (EditText)findViewById(R.id.police_phone_number);
        police.setText(prefs.getString(police_KEY, "999"));
        police_service_number=police.getText().toString();

        police.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(police_KEY, s.toString()).commit();
            }
        });



        fire= (EditText)findViewById(R.id.fire_phone_number);
        fire.setText(prefs.getString(fire_Key, "999"));
        fire_Service_number=fire.getText().toString();

        fire.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(fire_Key, s.toString()).commit();
            }
        });




        message= (EditText)findViewById(R.id.SOS_Message);
        message.setText(prefs.getString(message_key, "Please help me!"));
        Emergency_message=message.getText().toString();

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                prefs.edit().putString(message_key, s.toString()).commit();
            }
        });

        Button btnLogout = (Button)findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView topView = findViewById(R.id.nav_view_top) ;
        topView.setOnNavigationItemSelectedListener(this);

        topView.getMenu() .getItem(2).setChecked(true);



    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ambulance_KEY, ambulance_edittext.getText().toString());
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ambulance_edittext.setText(savedInstanceState.getString(ambulance_KEY));
    }


    public void openMainActivity()
    {  Intent intent = new Intent(this,MainActivity.class);
     intent.putExtra("fire",fire_Service_number);
       intent.putExtra("ambulance",ambulance_service_number);
       intent.putExtra("police",police_service_number);
       intent.putExtra("message",Emergency_message);
        intent.putExtra("check_siren",isChecked_siren);
        intent.putExtra("check_location",ischecked_location);
        intent.putExtra("email",email);
        startActivity(intent);

    }


    public void openContect()
    {  Intent intent = new Intent(this,contacts.class);
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
                openContect();
                break;


        }
        return true;
    }
}
