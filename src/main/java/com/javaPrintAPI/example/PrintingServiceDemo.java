package com.javaPrintAPI.example;

import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class PrintingServiceDemo
{
    public static void main(String[] args) throws PrintException, IOException
    {

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
        PrintRequestAttributeSet attrib = new HashPrintRequestAttributeSet();
        PrintService selectedPrintService = ServiceUI.printDialog(null, 150, 150, printServices,
                defaultPrintService, null, attrib);
        if (selectedPrintService != null)
            System.out.println("selected printer:" + selectedPrintService.getName());
        else
            System.out.println("selection cancelled");
        DocPrintJob job = defaultPrintService.createPrintJob();
        FileInputStream fis = new FileInputStream("C:\\Users\\Naincy.gupta\\Desktop\\Device Tester With Dymo Label Button.PNG");
        Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
        job.print(doc, attrib);
    }
}