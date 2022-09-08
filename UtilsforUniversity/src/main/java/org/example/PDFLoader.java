package org.example;


import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class PDFLoader {
    File file;
    int pages;
    String name;
    public PDFLoader(File file) throws IOException {
        this.file=file;
        name=file.getName();
        PDDocument pdDocument=PDDocument.load(file);
        pages=pdDocument.getNumberOfPages();
        pdDocument.close();
    }
    public boolean openPDF(){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.OPEN)) {
            try {
                desktop.open(file);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

    }

}
