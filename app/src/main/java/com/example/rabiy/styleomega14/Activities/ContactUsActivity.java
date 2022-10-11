package com.example.rabiy.styleomega14.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rabiy.styleomega14.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactUsActivity extends AppCompatActivity {

    private TextView tvstoreEmail;
    private TextView tvstorePhone;
    private TextView tvstoreLocation;
    private ImageButton imgbtnEmail;
    private ImageButton imgbtnCall;
    private ImageButton imgbtnLocate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.itemtoolbarContact);
        toolbar.setTitle("Contact Us");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        tvstoreEmail=findViewById(R.id.email_tv);
        final String storeEmail=tvstoreEmail.getText().toString();
        imgbtnEmail=findViewById(R.id.email_imgbtn);
        imgbtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{storeEmail});
                i.putExtra(Intent.EXTRA_SUBJECT, "Customer Feedback");
                i.putExtra(Intent.EXTRA_TEXT   , "Start typing your feedback to the store here");
                try {
                    startActivity(Intent.createChooser(i, "Send mail through..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(ContactUsActivity.this,
                            "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvstorePhone=findViewById(R.id.phoneNo_tv);
        final String storePhone=tvstorePhone.getText().toString();
        imgbtnCall=findViewById(R.id.call_imgbtn);
        imgbtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:" + storePhone.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        tvstoreLocation=findViewById(R.id.location_tv);
        final String storeLocation=tvstoreLocation.getText().toString();
        imgbtnLocate=findViewById(R.id.locate_imgbtn);
        imgbtnLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+storeLocation);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });



    }
}
