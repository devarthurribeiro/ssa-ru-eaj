package util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur Ribeiro
 */
public class Report {
    Document report = new Document();
    public static final String DEST = "/home/arthur/pdfTemp.pdf";
    private String title;
    private ArrayList<PdfPTable> tables = new ArrayList<>();
    private List<String> headers = new ArrayList<String>();

    public Report() {
        try {
            PdfWriter.getInstance(report, new FileOutputStream(DEST));
            report.open();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void addTable(PdfPTable table) {
        tables.add(table);
    }
    public PdfPTable newTable(int ncol) {
        PdfPTable table = new PdfPTable(ncol);
        return table;
    }

    public void AddParagraph(String text) throws DocumentException {
        headers.add(text);
    }

    public void createPdf(String dest) throws DocumentException, FileNotFoundException {
        Font bigfont = new Font(Font.FontFamily.HELVETICA, 24);
        //Document report = new Document();
        PdfWriter.getInstance(report, new FileOutputStream(dest));
        report.open();
        Paragraph titulo = new Paragraph(this.title, bigfont);
        titulo.setAlignment(Element.ALIGN_CENTER);
        report.add(titulo);
        report.add( Chunk.NEWLINE );
        for (String s: headers) {
            Paragraph p = new Paragraph(s);
            report.add(p);
        }
        report.add( Chunk.NEWLINE );
        report.add(new Paragraph(""));
        for (PdfPTable t: tables) {
            report.add(t);
            report.add( Chunk.NEWLINE );
            report.add(new Paragraph("Total de registros: "+ (t.getRows().size()-1)));
        }
        report.close();
    }
    public void open() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", "-c", "sensible-browser " + DEST);
            processBuilder.start();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void generate() {
        try {
            File file = new File(DEST);
            file.getParentFile().mkdirs();
            try {
                createPdf(DEST);
            }catch (DocumentException ex) {
                System.out.println(ex);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
