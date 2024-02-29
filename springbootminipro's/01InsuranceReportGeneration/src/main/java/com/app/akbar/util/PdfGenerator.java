package com.app.akbar.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.app.akbar.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {
	
	public void generate(HttpServletResponse response, List<CitizenPlan> records, File file) throws Exception {
		// Creating the Object of Document
				Document document = new Document(PageSize.A4);
				
				// Getting instance of PdfWriter
				PdfWriter.getInstance(document, response.getOutputStream());
				PdfWriter.getInstance(document, new FileOutputStream(file));
				
				// Opening the created document to modify it
				document.open();
				
				// Creating font
				// Setting font style and size
				Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				fontTiltle.setSize(20);
				
				// Creating paragraph
				Paragraph paragraph = new Paragraph("List Of Citizen Plans", fontTiltle);
				
				// Aligning the paragraph in document
				paragraph.setAlignment(Paragraph.ALIGN_CENTER);
				
				// Adding the created paragraph in document
				document.add(paragraph);
				
				// Creating a table of 3 columns
				PdfPTable table = new PdfPTable(8);
				
				// Setting width of table, its columns and spacing
				table.setWidthPercentage(100f);
				table.setWidths(new int[] { 1, 3, 2, 3, 3, 3, 3, 3 });
				table.setSpacingBefore(5);
				
				table.addCell("ID");
				table.addCell("CITIZEN NAME");
				table.addCell("GENDER");
				table.addCell("PLAN NAME");
				table.addCell("PLAN STATUS");
				table.addCell("PLAN START DATE");
				table.addCell("PLAN END DATE");
				table.addCell("BENFIT AMOUNT");
				
				for(CitizenPlan plan : records) {
					table.addCell(String.valueOf(plan.getCitizenId()));
					table.addCell(plan.getCitizenName());
					table.addCell(plan.getGender());
					table.addCell(plan.getPlanName());
					table.addCell(plan.getPlanStatus());
					if(null != plan.getPlanStartDate()) {
						table.addCell(plan.getPlanStartDate()+"");
					} else {
						table.addCell("N/A");
					}
					
					if(null != plan.getPlanEndDate()) {
						table.addCell(plan.getPlanEndDate()+"");
					} else {
						table.addCell("N/A");
					}
					
					if(null != plan.getBenefitAmount()) {
						table.addCell(String.valueOf(plan.getBenefitAmount()));
					} else {
						table.addCell("N/A");
					}
					
				}
				
				// Adding the created table to document
				document.add(table);
				
				// Closing the document
				document.close();
	}
}
