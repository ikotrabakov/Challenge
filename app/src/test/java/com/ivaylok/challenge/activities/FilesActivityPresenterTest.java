package com.ivaylok.challenge.activities;

import com.ivaylok.challenge.File;
import com.ivaylok.challenge.repositories.FilesRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class FilesActivityPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    FilesRepository filesRepository;

    @Mock
    FilesActivityView view;
    FilesActivityPresenter presenter;
    private final List<File> MANY_FILES = Arrays.asList(new File(), new File(), new File());

    @Before
    public void setUp() throws Exception {
        presenter = new FilesActivityPresenter(view, filesRepository);
    }

    @Test
    public void shouldPassFilesToView() {
        when(filesRepository.getFiles()).thenReturn(MANY_FILES);

        presenter.loadFiles();

        verify(view).displayFiles(MANY_FILES);
    }

    @Test
    public void shouldHandleNoFilesFound() {
        when(filesRepository.getFiles()).thenReturn(Collections.EMPTY_LIST);

        presenter.loadFiles();

        verify(view).displayNoFiles();
    }
}