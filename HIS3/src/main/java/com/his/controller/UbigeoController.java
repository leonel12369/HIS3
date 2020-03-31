package com.his.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.his.service.UbigeoService;

@Controller
@RequestMapping("/ubigeo")

public class UbigeoController {

	@Autowired
	private UbigeoService ubigeoService;
	
	@RequestMapping(value="/cargaProvincia", method=RequestMethod.POST,  consumes={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<String> cargaProvincia(@RequestBody Map<String, Object>  json)throws  IOException{
		String departamento=(String)json.get("nombre");
		//System.out.println(departamento);
		return ubigeoService.listarProvincia(departamento);
	} 
	
	@RequestMapping(value="/cargaDistrito", method=RequestMethod.POST, consumes= {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<String> cargaDistrito(@RequestBody Map<String,Object> json){
		//System.out.println(json);
		String departamento=(String)json.get("departamento");
		String provincia=(String)json.get("provincia");
		return ubigeoService.listarDistrito(departamento, provincia);
		//return null;
	}
	
}

