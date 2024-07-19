package com.example.belajardb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class tampildata extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DatabaseHelper dbcenter;
    Button btnkmbli;
    public static tampildata da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampildata);

        btnkmbli=findViewById(R.id.bck);
        btnkmbli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(tampildata.this, tampilan.class);
                startActivity(i);
            }
        });

        da = this;
        dbcenter = new DatabaseHelper(this);
        //====<<merefresh list untuk memanggil data terbaru>>==
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        //========<<membuka table, pastikan nama tabel sesuai pada tabel class DatabaseHelper>>===
        cursor = db.rawQuery("SELECT * FROM datadosen", null);
        //===<<proses menyalin isi database ke dalam listview>>===
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = findViewById(R.id.listview1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                daftar));

        ListView01.setSelected(true);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                final String Selection =daftar[i];
                final CharSequence[] dialogitem ={"Lihat Data","edit Data","hapus Data"};
                AlertDialog.Builder builder = new AlertDialog.Builder(tampildata.this);
            builder.setTitle("pilihan");
            builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    switch (item){
                        case 0:
                            Intent i = new Intent(getApplicationContext(), detaildata.class);
                            i.putExtra("nama", Selection);
                            startActivity(i);
                            break;
                        case 1:
                            Intent j = new Intent(getApplicationContext(), editdata.class);
                            j.putExtra("nama", Selection);
                            startActivity(j);
                            break;
                        case 2:
                            SQLiteDatabase db =dbcenter.getWritableDatabase();
                            String sql =" delete from datadosen where nama ='"+ Selection +"'";
                            db.execSQL(sql);
                            RefreshList();

                            Toast.makeText(getApplicationContext(), "Data berhasil Di Hapus...", Toast.LENGTH_LONG).show();
                            finish();
                            break;



                    }
                }
            });
            builder.create().show();
            }
        });


        {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}
