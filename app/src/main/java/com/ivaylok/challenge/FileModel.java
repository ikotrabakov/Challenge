package com.ivaylok.challenge;

import java.io.Serializable;

/**
 * Created by smn on 10/19/16.
 */

public class FileModel implements Serializable{

    private String filename;
    private String isFolder;
    private String modDate;
    private FileType fileType;
    private boolean isOrange;
    private boolean isBlue;

    public FileModel(){};

    public FileModel(String filename, String isFolder, String modDate, FileType fileType, boolean isOrange, boolean isBlue) {
        this.filename = filename;
        this.isFolder = isFolder;
        this.fileType = fileType;
        this.isOrange = isOrange;
        this.isBlue = isBlue;
        this.modDate = modDate;
//        Date date = new Date(System.currentTimeMillis());
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM dd, yyyy");
//        modDate = simpleDateFormat.format(date);

    }

    public enum FileType {
        image,
        folder,
        movie,
        music;
    }

    public String getFilename() {
        return filename;
    }

    public String getIsFolder() { return isFolder; }

    public String getModDate() {
        return modDate;
    }

    public FileType getFileType() {
        return fileType;
    }

    public String getFileTypeAsString() {
        return String.valueOf(fileType);
    }

    public boolean isOrange() {
        return isOrange;
    }

    public boolean isBlue() {
        return isBlue;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setIsFolder(String isFolder) {
        this.isFolder = isFolder;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public void setFileType(String fileType) {
        this.fileType = FileType.valueOf(fileType);
    }

    public void setOrange(boolean orange) {
        isOrange = orange;
    }

    public void setBlue(boolean blue) {
        isBlue = blue;
    }

    @Override
    public String toString() {
        return "FileModel{" +
                "filename='" + filename + '\'' +
                ", isFolder='" + isFolder + '\'' +
                ", modDate='" + modDate + '\'' +
                ", fileType=" + fileType +
                ", isOrange=" + isOrange +
                ", isBlue=" + isBlue +
                '}';
    }
}
