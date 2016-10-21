package com.ivaylok.challenge;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ivaylok.challenge.DatabaseHelper.SELECT_ALL_DATA_QUERY;

/**
 * Created by smn on 10/19/16.
 */

public class FileController {

    private static FileController sController;
    private List<FileModel> mFiles;
    private DatabaseHelper mDatabase;

    public static FileController get(Context context) {
        if(sController == null) {
            sController = new FileController(context);
        }
        return sController;
    }

    private FileController(Context context){
        mDatabase = DatabaseHelper.getInstance(context);
        mFiles = new ArrayList<>();
        mFiles = getFiles(SELECT_ALL_DATA_QUERY);
    }

    public List<FileModel> getFiles(String query) { return mFiles = mDatabase.getDataWithQuery(query); }

    public List<FileModel> selectAllWhere(String columnName, String operator, String value) {
         return mFiles = mDatabase.getDataWithQuery(DatabaseHelper.SELECT_ALL_DATA_QUERY + " WHERE " + columnName + " " + operator + "'" + value + "'");
    }


    public void addFilesToDatabase(FileModel model) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String mDate = simpleDateFormat.format(date);
        model.setModDate(mDate);
        mDatabase.insertFile(model);
    }


}
