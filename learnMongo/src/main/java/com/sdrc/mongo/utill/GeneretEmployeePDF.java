package com.sdrc.mongo.utill;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sdrc.mongo.domains.Employee;

public class GeneretEmployeePDF {

	public File cretePdf(Employee emp) {
		Document document = new Document();
		FileOutputStream fileOut = null;
		File file = null;
		try {
			 file = new File("testPdf.pdf");
			 fileOut = new FileOutputStream(file);
			
			PdfWriter writer = PdfWriter.getInstance(document, fileOut);
	        document.open();
	        PdfPTable ptable = new PdfPTable(4);
	        ptable.setWidthPercentage(100);
	        ptable.setSpacingAfter(10f);
	        ptable.setSpacingBefore(10f);
	        
	        float[] columnWidths = {1f, 1f, 1f,1f};
	        ptable.setWidths(columnWidths);
	        
	        PdfPCell cell1 = new PdfPCell(new Paragraph("Name"));
	        cell1.setBorderColor(BaseColor.BLUE);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        PdfPCell cell2 = new PdfPCell(new Paragraph("Name"));
	        cell2.setBorderColor(BaseColor.BLUE);
	        cell2.setPaddingLeft(10);
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        PdfPCell cell3 = new PdfPCell(new Paragraph("Name"));
	        cell3.setBorderColor(BaseColor.BLUE);
	        cell3.setPaddingLeft(10);
	        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        PdfPCell cell4 = new PdfPCell(new Paragraph("Name"));
	        cell4.setBorderColor(BaseColor.BLUE);
	        cell4.setPaddingLeft(10);
	        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        
	        ptable.addCell(cell1);
	        ptable.addCell(cell2);
	        ptable.addCell(cell3);
	        ptable.addCell(cell4);
	        
	        document.add(ptable);
	        
	        
	        document.close();
	        writer.close();
	        
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}
}
