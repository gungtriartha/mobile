package com.example.belajardb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class tambahdata extends AppCompatActivity {
    protected Cursor cursor;

    DatabaseHelper dbhelper;
    Button btnsimpan,btnbatal ;
    EditText edtNIDN,edtNama,edttanggal,edtjk,edtalamat;
    String sqlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tambahdata);

        dbhelper = new DatabaseHelper(this);

        edtalamat = findViewById(R.id.alamat);
        edtjk = findViewById(R.id.jk);
        edtNama = findViewById(R.id.nama);
        edttanggal = findViewById(R.id.tanggal);
        edtNIDN = findViewById(R.id.NIDN);

        btnbatal = findViewById(R.id.btl);
        btnbatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(tambahdata.this, tampilan.class);
                startActivity(j);
            }
        });

        btnsimpan = findViewById(R.id.simpan);
        btnsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();

                sqlText="insert into datadosen (nidn, nama, tanggal, jk, alamat) values('" +
                        edtNIDN.getText().toString() + "','" +
                        edtNama.getText().toString() + "','" +
                        edttanggal.getText().toString() + "','" +
                        edtjk.getText().toString() + "','" +
                        edtalamat.getText().toString() + "')";

                db.execSQL(sqlText);

                Toast.makeText(getApplicationContext(), "Data Tersimpan...", Toast.LENGTH_LONG).show();
                finish();

                Intent i = new Intent( tambahdata.this,tampildata.class);
                startActivity(i);

            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}