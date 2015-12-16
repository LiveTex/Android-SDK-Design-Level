package com.designlevelsdk;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.larswerkman.lobsterpicker.LobsterPicker;
import com.larswerkman.lobsterpicker.OnColorListener;

import nit.livetex.livetexsdktestapp.utils.DataKeeper;

public class MainActivity extends AppCompatActivity {

    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#8bc34a")));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Ищу хозяина");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, nit.livetex.livetexsdktestapp.FragmentEnvironment.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_color) {

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Pick a color");
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_color_picker, null, false);
            LobsterPicker picker = (LobsterPicker) view.findViewById(R.id.lobsterpicker);
            picker.addOnColorListener(new OnColorListener() {
                @Override
                public void onColorChanged(int color) {

                }

                @Override
                public void onColorSelected(int color) {
                    MainActivity.this.color = color;;
                }
            });
            builder.setView(view).setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String hexColor = String.format("#%06X", (0xFFFFFF & MainActivity.this.color));
                    DataKeeper.setMainColor(MainActivity.this, Color.parseColor(hexColor));
                }
            })
            .setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
