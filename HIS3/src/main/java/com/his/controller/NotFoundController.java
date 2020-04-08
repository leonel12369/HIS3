package com.his.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NotFoundController implements ErrorController{

	@RequestMapping("/error")
    public String render404(HttpServletRequest request) {
        // Add model attributes
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());

	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	 return "/views/error/error_404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error500";
	        }
	    }
        return "/error";
    }

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
