package com.guikuki.persistence.model;

import java.util.Arrays;

/**
 * Created by antoniosilvadelpozo on 24/03/14.
 */
public class Photo {

    private byte[] content;
    private String fileName;
    private String contentType;

    public Photo(byte[] content, String fileName, String contentType) {
        this.content = content;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (!Arrays.equals(content, photo.content)) return false;
        if (!contentType.equals(photo.contentType)) return false;
        if (!fileName.equals(photo.fileName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(content);
        result = 31 * result + fileName.hashCode();
        result = 31 * result + contentType.hashCode();
        return result;
    }
}
