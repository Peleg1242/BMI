package com.example.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView t1, t2;
    Button calc,intentb;
    EditText gova, mishkal;
    ImageView image;
    double mishkall;
    double govaa;
    double bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView t1 = (TextView) findViewById(R.id.yes4);
        TextView t2 = (TextView) findViewById(R.id.yes5);
        Button calc = (Button) findViewById(R.id.x);
        EditText gova = (EditText) findViewById(R.id.editText2);
        EditText mishkal = (EditText) findViewById(R.id.editText);
        ImageView image = (ImageView) findViewById(R.id.image1);
        intentb = (Button) findViewById(R.id.intentb);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SEND_SMS} , 1);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE} , 1);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gova.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "enter mishkal!", Toast.LENGTH_SHORT).show();
                } else if (mishkal.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "enter Gova!", Toast.LENGTH_SHORT).show();
                } else {
                    mishkall = Double.parseDouble(mishkal.getText().toString());
                    govaa = Double.parseDouble(gova.getText().toString());
                    bmi = mishkall / (govaa * govaa);
                    String bmi1 = new Double(bmi).toString();
                    t1.setText(bmi1);

                    if (bmi < 18.5) {
                        t2.setText("תת משקל");
                        image.setImageResource(R.drawable.skinny);
                    } else if (bmi > 18.5 && bmi < 24.9) {
                        t2.setText("משקל תקין");
                        image.setImageResource(R.drawable.regular);
                    } else if (bmi > 25 && bmi < 29.9) {
                        t2.setText("משקל עודף");
                        image.setImageResource(R.drawable.regular);
                    } else if (bmi > 30 && bmi < 34.9) {
                        t2.setText("השמנה דרגה1");
                        image.setImageResource(R.drawable.fat);
                    } else if (bmi > 35 && bmi < 39.9) {
                        t2.setText("השמנה דרגה2");
                        image.setImageResource(R.drawable.fat);
                    } else {
                        t2.setText("השמנה דרגה3");
                        image.setImageResource(R.drawable.fat);
                    }
                }
            }
        });
        intentb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Data.class);
                i.putExtra("gov", govaa);
                i.putExtra("wei", mishkall);
                i.putExtra("bmii", bmi);
                i.putExtra("status", t2.getText());
                startActivity(i);
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


