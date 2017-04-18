package com.ivaylok.challenge.repositories.impl;

import android.content.Context;

import com.ivaylok.challenge.DatabaseHelper;
import com.ivaylok.challenge.File;
import com.ivaylok.challenge.repositories.FilesRepository;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

import static com.ivaylok.challenge.DatabaseHelper.SELECT_ALL_DATA_QUERY;

/**
 * Created by smn on 4/14/17.
 */

public class DatabaseFilesRepository implements FilesRepository {

    private final DatabaseHelper databaseHelper;

    public DatabaseFilesRepository(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }

    @Override
    public Single<List<File>> getFiles() {
        return Single.fromCallable(new Callable<List<File>>() {
            @Override
            public List<File> call() throws Exception {
                return databaseHelper.getDataWithQuery(SELECT_ALL_DATA_QUERY);
            }
        });
    }
}
