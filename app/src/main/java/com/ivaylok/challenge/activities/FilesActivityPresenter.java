package com.ivaylok.challenge.activities;

import com.ivaylok.challenge.File;
import com.ivaylok.challenge.repositories.FilesRepository;

import java.util.List;

/**
 * Created by smn on 4/14/17.
 */

class FilesActivityPresenter {

    private FilesActivityView view;
    private FilesRepository filesRepository;

    public FilesActivityPresenter(FilesActivityView view, FilesRepository repository) {
        this.view = view;
        this.filesRepository = repository;
    }

    public void loadFiles() {
        List<File> fileList = filesRepository.getFiles();
        if(fileList.isEmpty()) {
            view.displayNoFiles();
        } else {
            view.displayFiles(fileList);
        }
    }
}
