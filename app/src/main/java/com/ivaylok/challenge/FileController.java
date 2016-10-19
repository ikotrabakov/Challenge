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
        addFilesToDatabase();
        populateWithFiles();
    }

    private void populateWithFiles() {

        mFiles = mDatabase.getAllData();

    }

    public List<FileModel> getFiles() { return mFiles; }


    private void addFilesToDatabase() {

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
        String mDate = simpleDateFormat.format(date);
//        mDatabase.inserFile(new FileModel("Foo Fighters", "isFolder", mDate, FileModel.FileType.music, true, true));
//        mDatabase.inserFile(new FileModel("Lord of The Rings", "isFolder", mDate, FileModel.FileType.movie, false, true));
        mDatabase.inserFile(new FileModel("Image", "isFolder", mDate, FileModel.FileType.image, true, false));
    }


}
