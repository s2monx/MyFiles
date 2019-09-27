package com.example.myfiles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        final EditText etTenfile = findViewById(R.id.etTenfile);
        final EditText etNoidung = findViewById(R.id.etNoidung);

        ////////////Xu ly spinner
        final ArrayList<String> listfile = new ArrayList<>();
        listfile.add("Hello");
        listfile.add("Goodbye");
        listfile.add("Aloha");
        listfile.add("Bonjour");
        final Spinner spFile = findViewById(R.id.spFile);
        ArrayAdapter<String> adapterFile = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_single_choice, listfile);
        spFile.setAdapter(adapterFile);
        spFile.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etTenfile.setText(listfile.get(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //btn Xoa trang
        Button btnNhapmoi = findViewById(R.id.btnNhapmoi);
        btnNhapmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etTenfile.setText("");
                etNoidung.setText("");
                etTenfile.requestFocus();
            }
        });
        //btn Luu
        Button btnLuu = findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenfile = etTenfile.getText().toString();
                listfile.add(tenfile);

                try {
                    //Luu vao bo nho trong
//                    FileOutputStream fout = openFileOutput(tenfile, Context.MODE_PRIVATE);
//                    fout.write(etNoidung.getText().toString().getBytes());
//                    fout.close();

                    //
//                    SharedPreferences preferences = getApplicationContext().getSharedPreferences(tenfile,0);
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("noidung", etNoidung.getText().toString());
//                    editor.commit();


                    SharedPreferences preferences = getApplicationContext().getSharedPreferences(tenfile, 0);
                    etNoidung.setText(preferences.getString("Content", null));


                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Loi luu file", Toast.LENGTH_SHORT).show();

                }




            }
        });
        //btn Mo file
        Button btnMo = findViewById(R.id.btnMo);
        btnMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenfile = etTenfile.getText().toString();
                StringBuffer buffer = new StringBuffer();
                String line = null;
                try{
                    InputStream fin = openFileInput(tenfile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    while((line = br.readLine())!=null){
                        buffer.append(line).append("\n");
                    }
                    etNoidung.setText(buffer.toString());
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Loi input", Toast.LENGTH_SHORT).show();
                }
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
