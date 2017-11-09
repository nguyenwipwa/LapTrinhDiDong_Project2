package com.project.com.project2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.project.com.project2.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 04/11/2017.
 */

public class DBSQLlite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "project2";
    private static final String TABLE_NAME = "user";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String NUMBER = "number";
    private static final String ADDRESS = "address";
    private static final String SEX = "sex";

    private Context context;

    public DBSQLlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key, " +
                EMAIL + " TEXT UNIQUE, " +
                NAME + " TEXT, " +
                PASSWORD + " TEXT, " +
                NUMBER + " TEXT," +
                ADDRESS + " TEXT," +
                SEX + " TEXT)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, user.getName());
        values.put(PASSWORD, user.getPassword());
        values.put(NUMBER, user.getNumber());
        values.put(EMAIL, user.getEmail());
        values.put(ADDRESS, user.getAddress());
        values.put(SEX, user.getSex());

        long ma = db.insert(TABLE_NAME, null, values);
        db.close();
        return ma > 0;
    }

    public List<User> getAllStudent() {
        List<User> listUser = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setEmail(cursor.getString(1));
                user.setName(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setNumber(cursor.getString(4));
                user.setAddress(cursor.getString(5));
                listUser.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listUser;
    }

    public User getUser(String email, String password) {
        User user = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE email='" + email + "' and password='" + password+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setName(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setNumber(cursor.getString(4));
            user.setAddress(cursor.getString(5));
        }
        cursor.close();
        db.close();
        return user;
    }

    public void deleteUser(int id) {
        String selectQuery = "DELETE FROM " + TABLE_NAME + " WHERE ID=" + id;
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {id + ""};
//        db.delete(TABLE_NAME, ID+"=?", args);
//        db.close();
        db.execSQL(selectQuery);
    }
}
