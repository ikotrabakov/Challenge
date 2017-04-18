package com.ivaylok.challenge.activities;

import com.ivaylok.challenge.File;
import com.ivaylok.challenge.repositories.FilesRepository;

import java.util.List;

import io.reactivex.functions.Consumer;

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

        filesRepository.getFiles()
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(List<File> fileList) throws Exception {
                        if(fileList.isEmpty()) {
                            view.displayNoFiles();
                        } else {
                            view.displayFiles(fileList);
                        }
                    }
                });
    }
}
