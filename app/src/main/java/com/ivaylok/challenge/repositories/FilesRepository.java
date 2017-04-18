package com.ivaylok.challenge.repositories;

import com.ivaylok.challenge.File;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by smn on 4/14/17.
 */

public interface FilesRepository {

    Single<List<File>> getFiles();
}
