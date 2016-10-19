package com.ivaylok.challenge;

import java.util.Date;

/**
 * Created by smn on 10/19/16.
 */

public class FileModel {

    private String filename;
    private String isFolder;
    private Date modDate;
    private FileType fileType;
    private boolean isOrange;
    private boolean isBlue;

    public FileModel(String filename, String isFolder, FileType fileType, boolean isOrange, boolean isBlue) {
        this.filename = filename;
        this.isFolder = isFolder;
        this.fileType = fileType;
        this.isOrange = isOrange;
        this.isBlue = isBlue;
        modDate = new Date(System.currentTimeMillis());

        switch (fileType) {
            case image: //set image as image icon
                break;
            case pdf: //set image as pdf icon
                break;
            case movie: //set image as movie icon
                break;
            case music: //set image as music icon
                break;
            default:
                //default image
        }


    }

    public enum FileType {
        image,
        pdf,
        movie,
        music;
    }

    public String getFilename() {
        return filename;
    }

    public Date getModDate() {
        return modDate;
    }

    public FileType getFileType() {
        return fileType;
    }

    public boolean isOrange() {
        return isOrange;
    }

    public boolean isBlue() {
        return isBlue;
    }
}
