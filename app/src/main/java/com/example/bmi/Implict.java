package com.example.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Implict extends AppCompatActivity {
    String bmiscore;
    Intent j;
    EditText number,emailad;
    Button sms,email,call,back;
    TextView bmitext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implict);
        j=getIntent();
        bmiscore = j.getStringExtra("bmii");

        sms = (Button) findViewById(R.id.sms);
        email = (Button) findViewById(R.id.email);
        call = (Button) findViewById(R.id.call);
        back = (Button) findViewById(R.id.back);
        number = (EditText) findViewById(R.id.number);
        emailad = (EditText) findViewById(R.id.emailad);
        bmitext = (TextView) findViewById(R.id.bmitext);
        bmitext.setText("Your BMI Score Is: "+bmiscore);
        SmsManager smsManager = SmsManager.getDefault();

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnumber = number.getText().toString();
                if (number.getText().length() != 10) {
                    Toast.makeText(Implict.this, "10 numbers only!", Toast.LENGTH_SHORT).show();
                }
                else{
                    smsManager.sendTextMessage(pnumber, null,"My BMI score is: " + bmiscore, null, null);
                }
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnumber = number.getText().toString();
                if (number.getText().length() != 10) {
                    Toast.makeText(Implict.this, "10 numbers only!", Toast.LENGTH_SHORT).show();
                }
                else{
                     startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + pnumber)));
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailad.getText().length() == 0) {
                    Toast.makeText(Implict.this, "Please enter email address!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String emailadd = emailad.getText().toString();
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { emailadd });
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MY BMI SCORE");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, bmiscore);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("message/rfc822");
                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send Mail"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(Implict.this, "There is no email client installed", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.total,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.exit) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setMessage("Are you sure you want to exit?");
            adb.setTitle("DON'T EXIT!!");
            adb.setCancelable(false);
            adb.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            adb.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finishAndRemoveTask();
                }
            });
            adb.show();
        }
        return super.onOptionsItemSelected(item);
    }
}