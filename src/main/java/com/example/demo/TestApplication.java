package com.example.demo;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.firebase.auth.FirebaseAuthException;

@SpringBootApplication
public class TestApplication {
	
	  static String name;


	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException, XmlRpcException, FirebaseAuthException {	
		ApiAggregator api_conn = new  ApiAggregator();
		api_conn.Connect_To_Firebase_Aggergator();			
		SpringApplication.run(TestApplication.class, args);
		
		OdooService Odoo = new OdooService("https://accounting-test.eqima.org", "odoo14", "ekisender@gmail.com", "8b77ab80d813f6c8c906726e60406395e829df16");
		Odoo.ERP_Connect_Odoo();
		Odoo.get_invoice_pdf();
	}		

}
