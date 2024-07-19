package com.example.belajardb;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class editdata extends AppCompatActivity {
    protected Cursor cursor;
    DatabaseHelper dbHelper;
    EditText edtNIM,edtnama,edtjk,edtalamat,edttgl;
    Button btnsmpn;
    String sqlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editdata);

        dbHelper = new DatabaseHelper(this);

        edtalamat=findViewById(R.id.alamat2);
        edtNIM=findViewById(R.id.NIM2);
        edtnama=findViewById(R.id.nama2);
        edtjk=findViewById(R.id.jk2);
        edttgl=findViewById(R.id.tanggal2);

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


        btnsmpn = findViewById(R.id.simpan2);
        btnsmpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                sqlText = "update datadosen set nama='" +
                        edtnama.getText().toString() + "', tanggal='" +
                        edttgl.getText().toString() + "',jk='" +
                        edtjk.getText().toString() + "', alamat='" +
                        edtalamat.getText().toString() +"' where nidn='" +
                        edtNIM.getText().toString() + "'";

                db.execSQL(sqlText);

                Toast.makeText(getApplicationContext(), "perubahan Data Tersimpan...", Toast.LENGTH_LONG).show();
                finish();

                Intent i = new Intent(editdata.this, tampildata.class);
                startActivity(i);

            }

        });
    }
}