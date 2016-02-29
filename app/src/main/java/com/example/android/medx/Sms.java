package com.example.android.medx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Sms extends AppCompatActivity {
    private EditText editText;
    private String chig="9594999190";
    private String vinay="9699973049";
    private String kalpnil="7208370109";
    private Spinner spinner1;
    private String[] templates={"Help Me!!","I need Urgent Help,Please Call me","I'm stuck alone, need your help"};
    //SharedPreferences mPrefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    double lat, longi;
    private EditText phno1,phno2,phno3,phno4;
    private String ph1="",ph2="",ph3="",ph4="";
private boolean flag=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String ph1,ph2,ph3,ph4;



        //getting coordinates
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getSystemService(Context.LOCATION_SERVICE);
        mCurrentLocation=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        lat=mCurrentLocation.getLatitude();
        longi=mCurrentLocation.getLongitude();
        phno1= (EditText) findViewById(R.id.phno1);
        phno2= (EditText) findViewById(R.id.phno2);
        phno3= (EditText) findViewById(R.id.phno3);
        phno4= (EditText) findViewById(R.id.phno4);
        SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();

        ph1 = mPrefs.getString("ph1", "");
        ph2=mPrefs.getString("ph2","");
        ph3 = mPrefs.getString("ph3", "");
        ph4=mPrefs.getString("ph4","");

        phno1.setText(ph1);
        phno2.setText(ph2);
        phno3.setText(ph3);
        phno4.setText(ph4);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> temp_adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,templates);
        temp_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(temp_adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                // Toast.makeText(getApplicationContext(),"You clicked on "+ branch[position],Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0:
                     editText.setText(templates[0]+"\nMy co-ordinates are:("+lat+","+longi+")");
                        break;
                    case 1:
                        editText.setText(templates[1]+"\nMy co-ordinates are:("+lat+","+longi+")");
                        break;
                    case 2:
                        editText.setText(templates[2]+"\nMy co-ordinates are:("+lat+","+longi+")");
                        break;

                    default:editText.setText(templates[1]+"\nMy co-ordinates are:("+lat+","+longi+")");
                        break;
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        editText= (EditText) findViewById(R.id.sms_text);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text=editText.getText().toString();
//                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//                smsIntent.setData(Uri.parse("smsto:"));
//                smsIntent.setType("vnd.android-dir/mms-sms");
//                smsIntent.putExtra("address"  ,chig+";"+kalpnil+";"+vinay );


                String[] phoneNos={chig,kalpnil,vinay};
                String smsMessage="Help me!!";
                //smsIntent.putExtra("sms_body"  , "Test ");

                try {
                    //this is code for sending sms
                    SmsManager smsManager = SmsManager.getDefault();
//                    for(int i=0;i<phoneNos.length;i++) {
//                        //smsManager.sendTextMessage(phoneNos[i], null, smsMessage, null, null);
//                    }

                    if(!(phno1.getText().toString().equals(null))&&(!(phno3.getText().toString().equals("")))){
                        smsManager.sendTextMessage(phno1.getText().toString(), null, smsMessage, null, null);
                    }
                    if(!(phno2.getText().toString().equals(null))&&(!(phno3.getText().toString().equals("")))){
                        smsManager.sendTextMessage(phno2.getText().toString(), null, smsMessage, null, null);
                    }
                    if(!(phno3.getText().toString().equals(null))&&!(phno3.getText().toString().equals(""))){
                        smsManager.sendTextMessage(phno3.getText().toString(), null, smsMessage, null, null);
                    }

                    if(!(phno4.getText().toString().equals(null))&&!(phno3.getText().toString().equals(""))){
                        smsManager.sendTextMessage(phno4.getText().toString(), null, smsMessage, null, null);
                    }
                    //startActivity(smsIntent);
                    //finish();
                    Toast.makeText(Sms.this,"SMS sent",Toast.LENGTH_SHORT).show();
                    Log.i("Finished sending SMS...", "");
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Sms.this,
                            "SMS failed, please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // spinner1 = (Spinner) findViewById(R.id.spinner1);




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        phno1= (EditText) findViewById(R.id.phno1);
        phno2= (EditText) findViewById(R.id.phno2);
        phno3= (EditText) findViewById(R.id.phno3);
        phno4= (EditText) findViewById(R.id.phno4);
        int id = item.getItemId();
        if(id==R.id.action_save){
            SharedPreferences mPrefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor mEditor = mPrefs.edit();

           if(!(phno1.getText().toString().equals(null))){
               mEditor.putString("ph1", phno1.getText().toString()).commit();
           }
            else {
               mEditor.remove("ph1");//its remove name field from your SharedPreferences
               mEditor.commit();
           }
            if(!(phno2.getText().toString().equals(null))){
                mEditor.putString("ph2", phno2.getText().toString()).commit();
            }
            else {
                mEditor.remove("ph2");//its remove name field from your SharedPreferences
                mEditor.commit();
            }
            if(!(phno3.getText().toString().equals(null))){
                mEditor.putString("ph3", phno3.getText().toString()).commit();
            }
            else {
                mEditor.remove("ph3");//its remove name field from your SharedPreferences
                mEditor.commit();
            }
            if(!(phno4.getText().toString().equals(null))){
                mEditor.putString("ph4", phno4.getText().toString()).commit();
            }
            else {
                mEditor.remove("ph4");//its remove name field from your SharedPreferences
                mEditor.commit();
            }

        }


      /*  String ph1,ph2,ph3,ph4;
        ph1=this.ph1;
        ph2=this.ph2;
        ph3=this.ph3;
        ph4=this.ph4;
        int id = item.getItemId();
if(id==R.id.action_save){
    Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
    flag=true;
    if(phno1.getText().toString().equals(null)){
        phno1.setText("");
    }
    else if(phno1.getText().toString().equals(null)){
        phno1.setText("");
    }
    else if(phno1.getText().toString().equals(null)){
        phno1.setText("");
    }
    else if(phno1.getText().toString().equals(null)){
        phno1.setText("");
    }
    else {
        Toast.makeText(this, "Saving", Toast.LENGTH_SHORT).show();
        ph1 = phno1.getText().toString();
        ph2 = phno2.getText().toString();
        ph3 = phno3.getText().toString();
        ph4 = phno4.getText().toString();
    }
    }*/
        return super.onOptionsItemSelected(item);
    }


}
