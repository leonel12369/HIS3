package com.his.pdf;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.his.model.HisAtenciones;

public interface ExcelService {

	ByteArrayInputStream exportAllData(List<HisAtenciones> atenciones);
	

}

