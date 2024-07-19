package com.example.belajardb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class detaildata extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    TextView edtNIM,edtnama,edtjk,edtalamat,edttgl;

    Button btnkmbli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detaildata);

        dbHelper = new DatabaseHelper(this);

        edtalamat=findViewById(R.id.alamat1);
        edtNIM=findViewById(R.id.NIM);
        edtnama=findViewById(R.id.nama1);
        edtjk=findViewById(R.id.jk1);
        edttgl=findViewById(R.id.tanggal1);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM datadosen WHERE nama ='" + getIntent().getStringExtra("nama")+"'",null);
        cursor.moveToFirst();

        {
            cursor.moveToPosition(0);
            edtNIM.setText(cursor.getString(0).toString());
            edtnama.setText(cursor.getString(1).toString());
            edttgl.setText(cursor.getString(2).toString());
            edtjk.setText(cursor.getString(3).toString());
            edtalamat.setText(cursor.getString(4).toString());
        }

        btnkmbli=findViewById(R.id.kembali);
        btnkmbli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(detaildata.this, tampildata.class);
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