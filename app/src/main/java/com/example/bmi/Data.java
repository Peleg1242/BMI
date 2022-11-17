package com.example.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Data extends AppCompatActivity {
    Intent i;
    TextView mishkal, gova,bmit,status;
    double govaa,weightt,bmi;
    String statuss;
    Button b,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        gova = (TextView) findViewById(R.id.height);
        mishkal = (TextView) findViewById(R.id.weight);
        bmit = (TextView) findViewById(R.id.bmit);
        status = (TextView) findViewById(R.id.status);
        b = (Button) findViewById(R.id.b);
        b2 = (Button) findViewById(R.id.b2);
        i=getIntent();
        govaa = i.getDoubleExtra("gov", 0);
        weightt = i.getDoubleExtra("wei", 0);
        bmi = i.getDoubleExtra("bmii", 0);
        statuss = i.getStringExtra("status");

        String govas = Double.toString(govaa);
        String mishkals = Double.toString(weightt);
        String bmis = Double.toString(bmi);
        gova.setText(govas);
        mishkal.setText(mishkals);
        bmit.setText(bmis);
        status.setText(statuss);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(Data.this, Implict.class);
                j.putExtra("bmii", bmis);
                startActivity(j);
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