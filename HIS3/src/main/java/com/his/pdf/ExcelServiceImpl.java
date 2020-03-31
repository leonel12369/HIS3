package com.his.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.his.model.HisAtenciones;

@Service("excel")
public class ExcelServiceImpl implements ExcelService{

	@Override
	public ByteArrayInputStream exportAllData(List<HisAtenciones> atenciones) {
		// TODO Auto-generated method stub
		String [] columns= {"Tipo Dcoumento","Numero de Doc","Financiador","id","Institucion","Establecimiento","Departamento","Provincia","Distrito","Micro Red","direccion"};
		Workbook workbook =new HSSFWorkbook();
		ByteArrayOutputStream stream =new ByteArrayOutputStream();
		
		Sheet sheet =workbook.createSheet("Atenciones");
		
		Row row=sheet.createRow(0);
		
		for(int i=0;i<columns.length;i++) {
			Cell cell =row.createCell(i);
			cell.setCellValue(columns[i]);
			
		}
		int initRow =1;
		for(HisAtenciones i: atenciones) {
			row=sheet.createRow(initRow);
			row.createCell(0).setCellValue(i.getTipoDoc());
			row.createCell(1).setCellValue(i.getNroDoc());
			row.createCell(2).setCellValue(i.getFinanciador());
			row.createCell(3).setCellValue(i.getMIpress().getCodigoUnico());
			row.createCell(4).setCellValue(i.getMIpress().getInstitucion());
			row.createCell(5).setCellValue(i.getMIpress().getNombreEstablecimiento());
			row.createCell(6).setCellValue(i.getMIpress().getDepartamento());
			row.createCell(7).setCellValue(i.getMIpress().getProvincia());
			row.createCell(8).setCellValue(i.getMIpress().getDistrito());
			row.createCell(9).setCellValue(i.getMIpress().getMicrorred());
			row.createCell(10).setCellValue(i.getMIpress().getDireccion());
			
			
			initRow++;
		}
		try {
			workbook.write(stream);
			workbook.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return new ByteArrayInputStream(stream.toByteArray());
	}

	
}
