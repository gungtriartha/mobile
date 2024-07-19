package com.example.belajardb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//===<<extend SQLiteOpenHelper untuk menjadikan class ini membuka database SQLite>>===
public class DatabaseHelper extends SQLiteOpenHelper {
    //===<<mendeklarasikan nama database dan versinya dalam string>>===
    private static final String database_name="datadosen.db";
    private static final int database_version=1;
    //===<<Membuat koneksi database>>===
    public DatabaseHelper(Context context) {
        super(context,database_name, null, database_version);
    }
    @Override
    //===<<perintah untuk membuat tabel dan memasukkan data awal>>===
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table datadosen (nidn text primary key, nama text null, "
                +
                "tanggal text null, jk text null, alamat text null);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        sql = "INSERT INTO datadosen (nidn, nama, tanggal, jk, alamat) " +
                " VALUES ('0812129001', 'wahyu', '1990-12-12', 'Lakilaki','Denpasar');";
        db.execSQL(sql);
        sql = "INSERT INTO datadosen (nidn, nama, tanggal, jk, alamat) " +
                " VALUES ('0812109001', 'Dewa', '1990-12-10', 'Lakilaki','gianyar');";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int
            arg2) {
        // TODO Auto-generated method stub
    }
}
