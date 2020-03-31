package com.his.pdf;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.InputStreamResource;

import com.his.model.HisAtenciones;

public interface PdfService {

	InputStreamResource pdfGenerate(List<HisAtenciones> data);
	
	ByteArrayInputStream exportpdf(List<HisAtenciones> atenciones);
	
}
