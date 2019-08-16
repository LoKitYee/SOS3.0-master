package com.example.sos;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;


public class rescuefragment extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

     ImageButton call,direction;
  String fire_number,police_number,ambulance_number,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rescue);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fire_number = extras.getString("fire");
            police_number=extras.getString("police");
           ambulance_number=extras.getString("ambulance");
            email=extras.getString("email");
        }

        BottomNavigationView navView = findViewById(R.id.nav_view );
        BottomNavigationView topView = findViewById(R.id.nav_view_top) ;

        navView.setOnNavigationItemSelectedListener(this);
        topView.setOnNavigationItemSelectedListener(this);
        navView.getMenu() .getItem(1).setChecked(true);


        ImageButton btn_ambulance = findViewById(R.id.btn_ambulance);
        btn_ambulance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                ambulance_popup();
            }
        });

        ImageButton btn_police= (ImageButton)findViewById(R.id.btn_police);
        btn_police.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                police_popup();
            }
        });


        ImageButton btn_fire_station= (ImageButton)findViewById(R.id.btn_fireStation);
        btn_fire_station.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                v.playSoundEffect(SoundEffectConstants.CLICK);
                fire_station_popup();
            }
        });






    }


    private void ambulance_popup() {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.ambulancepopup, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

               popupView.getBackground().setAlpha(250);
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0);
                  call= popupView.findViewById(R.id.button_image_call);

                call.setBackgroundResource(0);
                  call.setOnClickListener(new View.OnClickListener(){
                      @Override
                      public void onClick(View v)
                      {

                          if(ambulance_number!=null)
                          {

                              Uri call = Uri.parse("tel:" + ambulance_number);
                              Intent surf = new Intent(Intent.ACTION_DIAL, call);
                              startActivity(surf);
                          }
                          else
                          {
                             Uri call = Uri.parse("tel:" + "999");
                              Intent surf = new Intent(Intent.ACTION_DIAL, call);
                              startActivity(surf);
                          }

                      }
                  });

                 direction = popupView.findViewById(R.id.button_image_direction);
        direction.setBackgroundResource(0);
        direction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospital near me");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }


    private void police_popup() {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.ambulancepopup, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView tv1 = popupView.findViewById(R.id.popupMessage);
        tv1.setText("         Your have chosen Police");
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(popupView, Gravity.CENTER,0,0);
        call= popupView.findViewById(R.id.button_image_call);
        call.setBackgroundResource(0);
        call.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(police_number!=null)
                {

                    Uri call = Uri.parse("tel:" + police_number);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
                else
                {
                    Uri call = Uri.parse("tel:" + "999");
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }


            }
        });

        direction = popupView.findViewById(R.id.button_image_direction);
        direction.setBackgroundResource(0);
        direction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=police station near me");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });


    }
    private void fire_station_popup() {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.ambulancepopup, null);
        final PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView tv1 = popupView.findViewById(R.id.popupMessage);
        tv1.setText("   Your have chosen Fire Station");
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        call = popupView.findViewById(R.id.button_image_call);
        call.setBackgroundResource(0);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fire_number!=null)
                {

                    Uri call = Uri.parse("tel:" + fire_number);
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
                else
                {
                    Uri call = Uri.parse("tel:" + "999");
                    Intent surf = new Intent(Intent.ACTION_DIAL, call);
                    startActivity(surf);
                }
            }
        });
        direction = popupView.findViewById(R.id.button_image_direction);
        direction.setBackgroundResource(0);
        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Fire Station near me");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }




        public void openMainActivity()
        {  Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("email",email);
            startActivity(intent);
        }
        public void openContact()
        {  Intent intent = new Intent(this,contacts.class);
            intent.putExtra("email",email);
            startActivity(intent);
        }
        public void openRescueFragment()
        {  Intent intent = new Intent(this,rescuefragment.class);
            intent.putExtra("email",email);
            startActivity(intent);
        }
    public void openSetting()
    {  Intent intent = new Intent(this,Setting.class);
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
                    openContact();
                    break;
                case R.id.navigation_setting:
                    openSetting();
                    break;

            }
            return true;
        }


}
