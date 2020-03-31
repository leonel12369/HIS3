package com.his.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.his.model.HisAtenciones;
import com.itextpdf.html2pdf.HtmlConverter;




import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service("pdfService")
public class PdfServiceImpl implements PdfService{

	@Autowired
	private TemplateEngine templateEngine;
	
	@Override
	public InputStreamResource pdfGenerate(List<HisAtenciones> data) {
		// TODO Auto-generated method stub
		Context context =new Context();
		context.setVariable("lista", data);
		System.out.println(context);
		
		String html =templateEngine.process("views/pdf/pdf", context);
		
		try {
			HtmlConverter.convertToPdf(html, new FileOutputStream("target/test.pdf"));
			return new InputStreamResource(new FileInputStream("target/test.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
			return null;
		}
		
	}

	@Override
	public ByteArrayInputStream exportpdf(List<HisAtenciones> atenciones) {
		// TODO Auto-generated method stub
		Document document = new Document();
		ByteArrayOutputStream stream =new ByteArrayOutputStream();
		 PdfPTable table = new PdfPTable(3);
         table.setWidthPercentage(60);
         try {
			table.setWidths(new int[]{1, 3, 3});
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

         Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

         PdfPCell hcell;
         hcell = new PdfPCell(new Phrase("Id", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(hcell);

         hcell = new PdfPCell(new Phrase("Name", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(hcell);

         hcell = new PdfPCell(new Phrase("Population", headFont));
         hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
         table.addCell(hcell);
         
		try
	      {
	         PdfWriter.getInstance(document, stream);
	         document.open();
	         document.add(table);
	         document.close();
	      } catch (DocumentException e)
	      {
	         e.printStackTrace();
	      } 
		return new ByteArrayInputStream(stream.toByteArray());
	}

	
}
