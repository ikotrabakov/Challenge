package com.ivaylok.challenge;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smn on 10/19/16.
 */

public class FileController {

    private static FileController sControler;
    private List<FileModel> mFiles;

    public static FileController get(Context context) {
        if(sControler == null) {
            sControler = new FileController(context);
        }
        return sControler;
    }

    private FileController(Context context){
        mFiles = new ArrayList<>();
        populateWithFiles();
    }

    private void populateWithFiles() {
        FileModel model = new FileModel("Game of Thrones", "isFolder", FileModel.FileType.image, false, true);

        for (int i = 0; i < 20; i++) {
            mFiles.add(model);
        }
//        FileModel model1 = new FileModel("Foo Fighters", "isFolder", FileModel.FileType.music, true, false);
//        mFiles.add(model1);
    }

    public List<FileModel> getFiles() { return mFiles; }


}
