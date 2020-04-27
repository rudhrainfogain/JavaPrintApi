package com.javaPrintAPI.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.print.PrintService;

class Test implements Printable {

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        String s;
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        FileReader fr;
        try {
            fr = new FileReader("file.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((s = br.readLine()) != null) {
                g2d.drawString(s, 100, 100);
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return PAGE_EXISTS;
    }
}


public class PrintServicePrintableExample {

    public static void print() throws PrinterException {
        PrinterJob job = PrinterJob.getPrinterJob();

        PrintService[] printServices = job.lookupPrintServices();
        for (PrintService p : printServices) {
            if (p.getName().equals("DYMO LabelWriter 400")) {
                job.setPrintService(p);
            }
        }
        job.setPrintable(new Test(), new PageFormat());
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
