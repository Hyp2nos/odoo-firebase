package com.example.demo.controller;

import java.net.MalformedURLException;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.ApiAggregator;
import com.example.demo.OdooService;
import com.google.firebase.auth.FirebaseAuthException;

@Controller
public class MainController {

	@PostMapping("/connect")
	public String login_post(@RequestParam String mail) throws FirebaseAuthException {
		
		ApiAggregator api = new ApiAggregator();
		int token = api.Auth(mail);
		if(token == 1) {			
			return "odoo";
		}
		else {
			return "404";
		}
	}
	
	@GetMapping("/show")
	public String show_Data_From_View(@RequestParam String url ,@RequestParam String username,@RequestParam String database,@RequestParam String apikey) throws MalformedURLException, XmlRpcException {		
		OdooService Odoo = new OdooService(url, database, username, apikey);
		Odoo.ERP_Connect_Odoo();
		Odoo.get_invoice_pdf();
		return "page";
		
		
	}
	
}
