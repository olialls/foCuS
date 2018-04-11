package com.example.group5.focus.sql;

/**
 * Created by small on 19/03/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.group5.focus.model.Client;
import com.example.group5.focus.model.User;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "db.db";

    // User table name
    private static final String TABLE_USER = "manager";
    private static final String TABLE_CLIENT = "client";


    // User Table Columns names
    private static final String COLUMN_USER_ID = "ID";
    private static final String COLUMN_USER_NAME = "NAME";
    private static final String COLUMN_USER_USERNAME = "USERNAME";
    private static final String COLUMN_USER_PASSWORD = "PASSWORD";

    private static final String COLUMN_CLIENT_ID = "ID";
    private static final String COLUMN_CLIENT_NAME = "NAME";
    private static final String COLUMN_CLIENT_DOB = "DOB";
    private static final String COLUMN_CLIENT_CURRENCY = "CURRENCY";
    private static final String COLUMN_CLIENT_COUNTRY = "COUNTRY";
    private static final String COLUMN_CLIENT_WEALTH = "WEALTH";
    private static final String COLUMN_CLIENT_COMPANY = "COMPANY";
    private static final String COLUMN_CLIENT_MANAGER = "MANAGER";


//    // create table sql query
//    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
//            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
//            + COLUMN_USER_USERNAME + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";
//
//    // drop table sql query
//    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        //Drop User Table if exist
//        db.execSQL(DROP_USER_TABLE);
//
//        // Create tables again
//        onCreate(db);

//    }

//    /**
//     * This method is to create user record
//     *
//     * @param user
//     */
//    public void addUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getName());
//        values.put(COLUMN_USER_USERNAME, user.getUsername());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//
//        // Inserting Row
//        db.insert(TABLE_USER, null, values);
//        db.close();
//    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_USERNAME,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_username,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }



    public List<Client> getAllClient() {
        // array of columns to fetch
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                COLUMN_CLIENT_ID,
                COLUMN_CLIENT_NAME,
                COLUMN_CLIENT_DOB,
                COLUMN_CLIENT_COUNTRY,
                COLUMN_CLIENT_CURRENCY,
                COLUMN_CLIENT_WEALTH,
                COLUMN_CLIENT_COMPANY,
                COLUMN_CLIENT_MANAGER,
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<Client> ClientList = new ArrayList<Client>();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_username,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_CLIENT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_ID))));
                client.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_NAME)));
                client.setDOB(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_DOB)));
                client.setCountry(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_COUNTRY)));
                client.setCurrency(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_CURRENCY)));
                client.setWealth(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_WEALTH)));
                client.setCompany(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_COMPANY)));
                //client.setManager(replaceManager(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_MANAGER)))));
                client.setManager(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_MANAGER))));
                //client.setManagerName(replaceManager(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_MANAGER)))));
                // Adding user record to list
                ClientList.add(client);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return ClientList;
    }

    public String replaceManager(int manager){
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {COLUMN_USER_NAME};

        String selection = COLUMN_USER_ID + " = ?";

        String[] id = {Integer.toString(manager)};

        Cursor cursor = db.query(TABLE_CLIENT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                id,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String managerName = cursor.getString(1);
        cursor.close();
        db.close();

        return managerName;
    }

    public List<Client> findClients(String id) {
        SQLiteDatabase db = getReadableDatabase();

        List<Client> ClientList = new ArrayList<Client>();

        String[] columns = {
                COLUMN_CLIENT_ID,
                COLUMN_CLIENT_NAME,
                COLUMN_CLIENT_DOB,
                COLUMN_CLIENT_COUNTRY,
                COLUMN_CLIENT_CURRENCY,
                COLUMN_CLIENT_WEALTH,
                COLUMN_CLIENT_COMPANY,
                COLUMN_CLIENT_MANAGER,
        };

        String selection = COLUMN_CLIENT_MANAGER + " = ?";

        String[] selectionArgs = {id};

        Cursor cursor = db.query(TABLE_CLIENT, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        if (cursor.moveToFirst()) {
            do {
                Client client = new Client();
                client.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_ID))));
                client.setName(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_NAME)));
                client.setDOB(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_DOB)));
                client.setCountry(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_COUNTRY)));
                client.setCurrency(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_CURRENCY)));
                client.setWealth(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_WEALTH)));
                client.setCompany(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_COMPANY)));
                client.setManager(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_MANAGER))));
                ClientList.add(client);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return ClientList;
    }

    public List<User> getCurrentUser(String username) {
        // array of columns to fetch
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_USERNAME,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };

        String selection = COLUMN_USER_USERNAME + " = ?";

        String[] selectionArgs = {username};


        List<User> userList = new ArrayList<User>();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_username,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                selection,        //columns for the WHERE clause
                selectionArgs,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(COLUMN_USER_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
//    /**
//     * This method to update user record
//     *
//     * @param user
//     */
//    public void updateUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getName());
//        values.put(COLUMN_USER_USERNAME, user.getUsername());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//
//        // updating row
//        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }

//    /**
//     * This method is to delete user record
//     *
//     * @param user
//     */
//    public void deleteUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // delete user record by id
//        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }

    /**
     * This method to check user exist or not
     *
     * @param username
     * @param password
     * @return true/false
     */
    public boolean checkUser(String username, String password) {

        SQLiteDatabase db = getReadableDatabase();

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        // selection criteria
        String selection = COLUMN_USER_USERNAME + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_username = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


}

