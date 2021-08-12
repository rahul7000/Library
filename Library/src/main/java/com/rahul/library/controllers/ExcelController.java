package com.rahul.library.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rahul.library.entities.Author;
import com.rahul.library.services.LibraryService;
import com.rahul.library.services.helpers.ExcelHelper;

@RestController
public class ExcelController {

	 @Autowired
	    private LibraryService service;
	     
	     
	    @GetMapping("/users/export/excel")
	    public void exportToExcel(HttpServletResponse response) throws IOException {
	        response.setContentType("application/octet-stream");
	        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	        String currentDateTime = dateFormatter.format(new Date());
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=authors_" + currentDateTime + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Author> listAuthors = service.getAuthors();
	         
	        ExcelHelper excelHelper = new ExcelHelper(listAuthors);
	         
	        excelHelper.export(response);    
	    }  
}
