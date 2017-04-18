package com.ivaylok.challenge.activities;

import com.ivaylok.challenge.File;

import java.util.List;

/**
 * Created by smn on 4/14/17.
 */

public interface FilesActivityView {

    void displayFiles(List<File> files);

    void displayNoFiles();
}
