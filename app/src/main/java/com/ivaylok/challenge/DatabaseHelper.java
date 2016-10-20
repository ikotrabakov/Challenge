package com.ivaylok.challenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smn on 10/19/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "File.db";
    private static final String TABLE_NAME = "file_table";

    private static final String _ID = "i_d";
    private static final String NAME = "name";
    private static final String FOLDER = "folder";
    private static final String DATE = "date";
    private static final String TYPE = "type";
    private static final String ORANGE = "orange";
    private static final String BLUE = "blue";
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public static String ACTION_FOLDER = "";

    public static final String SELECT_ALL_FOLDERS_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + TYPE + "='folder'";

    public static final String SELECT_ALL_DATA_QUERY = "SELECT * FROM " + TABLE_NAME;

    public static final String SELECT_ALL_FILES_IN_FOLDER = "SELECT * FROM " + TABLE_NAME + " WHERE " + FOLDER + "='" + ACTION_FOLDER+  "'";

    private static DatabaseHelper mDbHelper;

    public static synchronized DatabaseHelper getInstance(Context context) {

        if (mDbHelper == null) {
            mDbHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return mDbHelper;
    }

    private DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_FILE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" +
                _ID + " INTEGER PRIMARY KEY ," +
                NAME + " TEXT," +
                FOLDER + " TEXT," +
                DATE + " DATE," +
                TYPE + " TEXT," +
                ORANGE + " BOOLEAN," +
                BLUE + " BOOLEAN" +
                ")";
        sqLiteDatabase.execSQL(CREATE_FILE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if(i != i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

            onCreate(sqLiteDatabase);
        }

    }

    public void insertFile(FileModel fileModel) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        try {
            ContentValues values = new ContentValues();
            values.put(NAME, fileModel.getFilename());
            values.put(FOLDER, fileModel.getIsFolder());
            values.put(DATE, fileModel.getModDate());
            values.put(TYPE, fileModel.getFileType().toString());
            values.put(ORANGE, fileModel.isOrange());
            values.put(BLUE, fileModel.isBlue());

            db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            Log.d(TAG, "Error while trying to add post to database");
        } finally {

            db.endTransaction();
        }
    }

    public List<FileModel> getAllData(String query) {

        List<FileModel> modelList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    boolean orange, blue;

                    FileModel fileModel = new FileModel();
                    fileModel.setFilename(cursor.getString(cursor.getColumnIndex(NAME)));
                    fileModel.setIsFolder(cursor.getString(cursor.getColumnIndex(FOLDER)));
                    fileModel.setModDate(cursor.getString(cursor.getColumnIndex(DATE)));
                    fileModel.setFileType(cursor.getString(cursor.getColumnIndex(TYPE)));
                    orange = cursor.getInt(cursor.getColumnIndex(ORANGE)) > 0;
                    fileModel.setOrange(orange);
                    blue = cursor.getInt(cursor.getColumnIndex(BLUE)) > 0;
                    fileModel.setBlue(blue);
                    Log.d(TAG, "getAllData: " + fileModel.toString());

                    modelList.add(fileModel);

                } while (cursor.moveToNext());
            }
        }  catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return modelList;

    }

    void deleteRow(String name) {
        SQLiteDatabase db = getWritableDatabase();


        try {
            db.beginTransaction();
            db.execSQL("delete from " + TABLE_NAME + " where name ='" + name + "'");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.d(TAG, "Error while trying to delete file");
        } finally {
            db.endTransaction();
        }


    }
}
