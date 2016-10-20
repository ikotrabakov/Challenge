package com.ivaylok.challenge;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        mFiles = getFiles();
    }

    public List<FileModel> getFiles() { return mFiles = mDatabase.getAllData(); }


    public void addFilesToDatabase(FileModel model) {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String mDate = simpleDateFormat.format(date);
        model.setModDate(mDate);
        mDatabase.insertFile(model);
    }


}
