package com.javaPrintAPI.example;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;



public class PrintServiceMultiplePages {

    public static void print() throws PrinterException, InvalidPasswordException, IOException {
        PrinterJob job = PrinterJob.getPrinterJob();
        String fileName = "label.pdf";
        PDDocument document = new PDDocument();
        File file = new File(fileName);
        PDDocument.load(file);
        for (int i = 0; i < 10; i++) {
            // Creating a blank page
            PDPage blankPage = new PDPage();
            // Adding the blank page to the document
            document.addPage(blankPage);
        }
        PrintService[] printServices = job.lookupPrintServices();
        for (PrintService p : printServices) {
            if (p.getName().equals("DYMO LabelWriter 400")) {
                job.setPrintService(p);
            }
        }
        Book book = new Book();
        book.append(new PDFPrintable(document, Scaling.SCALE_TO_FIT), null, document.getNumberOfPages());
        job.setPageable(book);
        try {
            job.print();
        } catch (PrinterException e) {
            System.out.println(e);

        }
    }

    public static void main(String[] args) {
        try {
            PrintServicePrintableExample.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }

    }

}
