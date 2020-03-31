package com.his.controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.his.model.HisAtenciones;
import com.his.pdf.ExcelServiceImpl;
import com.his.pdf.PdfService;
import com.his.service.AtencionesService;
import com.his.service.IGeneroService;
import com.his.service.MesService;
import com.his.service.TipoDocService;
import com.his.service.UbigeoService;

@Controller
@RequestMapping("/atenciones")
public class AtencionesController {

	@Autowired
	private IGeneroService generoService;
	
	@Autowired
	private UbigeoService ubigeoService;
	
	@Autowired
	private TipoDocService TipoDocService; 
	
	@Autowired
	private MesService mesService;
	
	@Autowired
	private AtencionesService atencionesService; 

	
	@Autowired
	private com.his.pdf.ExcelService excelService;  
	
	@Autowired
	private PdfService pdfService;
	
	@GetMapping("/listar")
	public String listarCombo(Model model) {
		model.addAttribute("titulo","listado");
		//model.addAttribute("genero",generoService.listarTodo());
		model.addAttribute("departamentos",ubigeoService.listarDepartamento());
		model.addAttribute("mes",mesService.listarTodo());
		model.addAttribute("tipoDoc",TipoDocService.listarTodo());
		return "views/Atenciones/lista_busqueda";
	} 
	
	/*@RequestMapping(value="/listarDatos", method=RequestMethod.POST, consumes={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<HisAtenciones> listarDatos(@RequestBody Map<String,Object> json){
		System.out.println(json);
		String departamento=(String)json.get("departamento");
		String provincia=(String)json.get("provincia");
		String distrito=(String)json.get("distrito");
		String tipoDoc=(String)json.get("tipoDoc");
		String mes=(String) json.get("mes");
		String check_error=(String) json.get("check_error");
		/*System.out.println("departamento: "+departamento+" provincia: "+provincia+" distrito: "+distrito+" tipoDoc: "+tipoDoc
				+" mes: "+mes+" check_error: "+check_error);*/
	/*List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
		String idUbigeo=ListIdUbigeo.get(0);
		return atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes);
	}*/
		
	@GetMapping("/listarDatos")
	public String listarDatos(Model model,@RequestParam(value="departamento")String departamento,@RequestParam(value="provincia")String provincia,
			@RequestParam(value="distrito")String distrito,@RequestParam(value="tipoDoc")String tipoDoc,
			@RequestParam(value="anio")String anio,
			@RequestParam(value="mes")String mes,@RequestParam(value="check_error")String check_error){
		 departamento=departamento.replace('_', ' ');
		 provincia=provincia.replace('_', ' ');
		 distrito=distrito.replace('_', ' ');
		 tipoDoc=tipoDoc.replace('_', ' ');
		 anio=anio.replace('_', ' ');
		 mes=mes.replace('_', ' ');
		
		System.out.println("departamento: "+departamento+" provincia: "+provincia+" distrito: "+distrito+" tipoDoc: "+tipoDoc
				+" mes: "+mes+" anio: "+ anio+" check_error: "+check_error);
		//System.out.println("check_error"+check_error.getClass().getName());
		String longitud="";

		if(check_error.equals("true")) {
			switch(tipoDoc) {
			case "CE":
				longitud="12";
				break;
			case "CNV":
				longitud="10";
				break;
			case "DIE":
				longitud="8";
				break;
			case "DNI":
				longitud="8";
				break;
			case "PASS":
				longitud="12";
				break;
			}
			String ubigeo="";
			if(distrito.equals("Escoja una opcion")) {
				ubigeo= (String)ubigeoService.extraerUbigeoValidado(departamento, provincia);
				System.out.println("ubigeo "+ubigeo);
				model.addAttribute("lista", atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud));
				System.out.println(atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud).size());

			}
			else {
				List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
				String idUbigeo=ListIdUbigeo.get(0);
				model.addAttribute("lista",atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud));
				System.out.println(atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud).size());

			}	
		}
		else {
			List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
			String idUbigeo=ListIdUbigeo.get(0);
			model.addAttribute("lista",atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio));
			System.out.println(atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio).size());

		}
		
	
		return "views/Atenciones/resultados";
	}
	
	
	/*@GetMapping("/exportarDatos")
	public ResponseEntity<InputStreamResource> exportExcel(@RequestParam(value="departamento")String departamento,@RequestParam(value="provincia")String provincia,
			@RequestParam(value="distrito")String distrito,@RequestParam(value="tipoDoc")String tipoDoc,
			@RequestParam(value="anio")String anio,
			@RequestParam(value="mes")String mes,@RequestParam(value="check_error")String check_error,
			@RequestParam(value="documento")String documento) throws Exception {
		
		 departamento=departamento.replace('_', ' ');
		 provincia=provincia.replace('_', ' ');
		 distrito=distrito.replace('_', ' ');
		 tipoDoc=tipoDoc.replace('_', ' ');
		 anio=anio.replace('_', ' ');
		 mes=mes.replace('_', ' ');
		
		System.out.println("departamento: "+departamento+" provincia: "+provincia+" distrito: "+distrito+" tipoDoc: "+tipoDoc
				+" mes: "+mes+" anio: "+ anio+" check_error: "+check_error+ " documento: "+documento);
		//System.out.println("check_error"+check_error.getClass().getName());
		String longitud="";
		List<HisAtenciones> lista=null;
		if(check_error.equals("true")) {
			switch(tipoDoc) {
			case "CE":
				longitud="12";
				break;
			case "CNV":
				longitud="10";
				break;
			case "DIE":
				longitud="8";
				break;
			case "DNI":
				longitud="8";
				break;
			case "PASS":
				longitud="12";
				break;
			}
			String ubigeo="";
			if(distrito.equals("Escoja una opcion")) {
				ubigeo= (String)ubigeoService.extraerUbigeoValidado(departamento, provincia);
				System.out.println("ubigeo "+ubigeo);
				lista=atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud);
				System.out.println(atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud).size());

			}
			else {
				List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
				String idUbigeo=ListIdUbigeo.get(0);
				lista=atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud);
				System.out.println(atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud).size());

			}	
		}
		else {
			List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
			String idUbigeo=ListIdUbigeo.get(0);
			lista = atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio);
			System.out.println(atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio).size());

		}
		
		
		ByteArrayInputStream stream =excelService.exportAllData(lista);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=Atenciones.xls");
		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
	}*/

	 /*@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> exportPdf(
			@RequestParam(value="departamento")String departamento,@RequestParam(value="provincia")String provincia,
			@RequestParam(value="distrito")String distrito,@RequestParam(value="tipoDoc")String tipoDoc,
			@RequestParam(value="anio")String anio,
			@RequestParam(value="mes")String mes,@RequestParam(value="check_error")String check_error) {
		

		 departamento=departamento.replace('_', ' ');
		 provincia=provincia.replace('_', ' ');
		 distrito=distrito.replace('_', ' ');
		 tipoDoc=tipoDoc.replace('_', ' ');
		 anio=anio.replace('_', ' ');
		 mes=mes.replace('_', ' ');
		
		System.out.println("departamento: "+departamento+" provincia: "+provincia+" distrito: "+distrito+" tipoDoc: "+tipoDoc
				+" mes: "+mes+" anio: "+ anio+" check_error: "+check_error);
		//System.out.println("check_error"+check_error.getClass().getName());
		String longitud="";
		List<HisAtenciones> lista=null;
		if(check_error.equals("true")) {
			switch(tipoDoc) {
			case "CE":
				longitud="12";
				break;
			case "CNV":
				longitud="10";
				break;
			case "DIE":
				longitud="8";
				break;
			case "DNI":
				longitud="8";
				break;
			case "PASS":
				longitud="12";
				break;
			}
			String ubigeo="";
			if(distrito.equals("Escoja una opcion")) {
				ubigeo= (String)ubigeoService.extraerUbigeoValidado(departamento, provincia);
				System.out.println("ubigeo "+ubigeo);
				lista=atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud);
				System.out.println(atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud).size());

			}
			else {
				List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
				String idUbigeo=ListIdUbigeo.get(0);
				lista=atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud);
				System.out.println(atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud).size());

			}	
		}
		else {
			List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
			String idUbigeo=ListIdUbigeo.get(0);
			lista = atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio);
			System.out.println(atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio).size());

		}
		
		InputStreamResource resource =pdfService.pdfGenerate(lista);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=Atenciones.pdf");
		return ResponseEntity.ok().headers(headers).body(resource);
	}*/
	
	
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/exportarDatos", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> exportExcel(@RequestParam(value="departamento")String departamento,@RequestParam(value="provincia")String provincia,
			@RequestParam(value="distrito")String distrito,@RequestParam(value="tipoDoc")String tipoDoc,
			@RequestParam(value="anio")String anio,
			@RequestParam(value="mes")String mes,@RequestParam(value="check_error")String check_error,
			@RequestParam(value="documento")String documento) throws Exception {
		
		 departamento=departamento.replace('_', ' ');
		 provincia=provincia.replace('_', ' ');
		 distrito=distrito.replace('_', ' ');
		 tipoDoc=tipoDoc.replace('_', ' ');
		 anio=anio.replace('_', ' ');
		 mes=mes.replace('_', ' ');
		
		System.out.println("departamento: "+departamento+" provincia: "+provincia+" distrito: "+distrito+" tipoDoc: "+tipoDoc
				+" mes: "+mes+" anio: "+ anio+" check_error: "+check_error+ " documento: "+documento);
		//System.out.println("check_error"+check_error.getClass().getName());
		String longitud="";
		List<HisAtenciones> lista=null;
		if(check_error.equals("true")) {
			switch(tipoDoc) {
			case "CE":
				longitud="12";
				break;
			case "CNV":
				longitud="10";
				break;
			case "DIE":
				longitud="8";
				break;
			case "DNI":
				longitud="8";
				break;
			case "PASS":
				longitud="12";
				break;
			}
			String ubigeo="";
			if(distrito.equals("Escoja una opcion")) {
				ubigeo= (String)ubigeoService.extraerUbigeoValidado(departamento, provincia);
				System.out.println("ubigeo "+ubigeo);
				lista=atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud);
				System.out.println(atencionesService.listarAtencionesErroneasSinDistrito(ubigeo, tipoDoc, longitud).size());

			}
			else {
				List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
				String idUbigeo=ListIdUbigeo.get(0);
				lista=atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud);
				System.out.println(atencionesService.listarAtencionesErroneasConDistrito(idUbigeo, tipoDoc, longitud).size());

			}	
		}
		else {
			List<String> ListIdUbigeo=ubigeoService.extraerIdUbigeo(departamento, provincia, distrito);
			String idUbigeo=ListIdUbigeo.get(0);
			lista = atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio);
			System.out.println(atencionesService.listarAtencionesFiltrados(idUbigeo, tipoDoc, mes,anio).size());

		}
		
		if(documento.equals("excel")) {
			ByteArrayInputStream stream =excelService.exportAllData(lista);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "attachment; filename=Atenciones.xls");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		}
		else{
			InputStreamResource resource =pdfService.pdfGenerate(lista);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=Atenciones.pdf");
			return ResponseEntity.ok().headers(headers).body(resource);
		}
		///// 			Metodo 2
		/*else {
			ByteArrayInputStream stream =pdfService.exportpdf(lista);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=Atenciones.pdf");
			return ResponseEntity.ok().headers(headers).body(new InputStreamResource(stream));
		}*/
	}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
