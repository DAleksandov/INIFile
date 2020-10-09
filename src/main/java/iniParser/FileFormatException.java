package iniParser;

import java.io.FileNotFoundException;

public class FileFormatException extends Exception{
    public FileFormatException() {
    }

    public FileFormatException(String message) {
        super(message);
    }
}
