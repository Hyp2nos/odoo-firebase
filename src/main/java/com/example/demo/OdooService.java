package com.example.demo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import com.google.api.client.util.Base64;

public class OdooService {

	    String url;//= "https://accounting-test.eqima.org";
		String database;//= "odoo14";
		String username;//= "ekisender@gmail.com";
		String password;//= "8b77ab80d813f6c8c906726e60406395e829df16";
	    XmlRpcClient models;
	    int uid;

	    @SuppressWarnings("serial")
    public OdooService(String url, String database, String username, String password) throws MalformedURLException {
	    	
	    	this.url = url;
	    	this.database = database;
	    	this.username = username;
	    	this.password = password;
	    	
	    	this.models = new XmlRpcClient() {{
	 		    setConfig(new XmlRpcClientConfigImpl() {{
	 		        setServerURL(new URL(String.format("%s/xmlrpc/2/object", url)));
	 		       
	 		    }});
	 		}};
	    }
	    
	public void ERP_Connect_Odoo() throws XmlRpcException, MalformedURLException{
        System.out.println("Get database list");
 		System.out.println("Login");
 		System.out.println("--------------");
 		uid = login(url,database,username,password);
 		if (uid >0) {
     			System.out.println("Login Ok");
 		} else {
      			System.out.println("Login Fail");
      			
 		}
 		
 		Object[] tara2 = new Object[]{ 
 			     new Object[] {
 			         } 
 			     }; 
 		
 		Object[] parm = new Object[] {database, uid, password,"res.partner", "search_count",tara2};
 		Integer fact = (Integer)models.execute("execute_kw",parm);
 		System.out.println("nombre de contact: "+fact);
	}
	
	
	@SuppressWarnings("rawtypes")
	public void create_invoice() throws XmlRpcException {
		@SuppressWarnings({ "unchecked", "serial" })
		Object[] invoice_args = new Object[] { new HashMap() {{ 
			                                            put("move_type","out_invoice");
														put("date","2021-09-16");
														put("partner_id",41);
														put("company_id",2);
														put("payment_reference","REF/5555");
														put("journal_id",15);
														put("ref","FAC/2021/09/20.455");
														put("invoice_line_ids", new Object[] {
															new Object[]{
																0,false, new HashMap() {{
																	put("name","Volanaka");
																	put("quantity",11.00);
																	put("price_unit",10);				
																}}
															}
														});
														put("state","draft");
														}} };
		
			Object[] param = new Object[] { database, uid, password,"account.move", "create", invoice_args };
			
			final Integer id = (Integer)models.execute("execute_kw", param);
			System.out.println("Creat........");
			System.out.println("l'id est "+id);
	}
	
	@SuppressWarnings("unchecked")
	public void create_journal() throws XmlRpcException {
		
		@SuppressWarnings({ "rawtypes", "serial" })
		Object[] journal_args = new Object[] { new HashMap() {{     put("name","Achat");																	        
																	put("company_id",2);
																	put("type","purchase");
																	put("code","ETSENAK");

        }} };

		
		Object[] param = new Object[] { database, uid, password,"account.journal", "create", journal_args };
		
		final Integer id = (Integer)models.execute("execute_kw", param);
 		System.out.println("Creat........");
 		System.out.println("l'id est "+id);
	}
	
	@SuppressWarnings("unchecked")
	public void create_compagny() throws XmlRpcException {
		@SuppressWarnings({ "rawtypes", "serial" })
		Object[] compagny_args = new Object[] { new HashMap() {{    put("name","TSENAKOU");																	        
																	put("street","Mahazo");
																	put("city","Antananarivo");
																	put("phone","034444444");
																	put("email","Tsena@gmail.com");
																	put("website","tesnakou.com");
        }} };
		Object[] para = new Object[] { database, uid, password,"res.company", "create", compagny_args };

        final Integer id = (Integer)models.execute("execute_kw", para);
        System.out.println("Creat........");
        System.out.println("l'id est "+id);
	}

    @SuppressWarnings("unchecked")
	public void create_attache_file_to_an_invoice() throws XmlRpcException {
		
		@SuppressWarnings({ "rawtypes", "serial" })
		Object[] attache_args = new Object[] { new HashMap() {{     put("name","Attache d'un facture");																	        
																	put("type","url");
																	put("url","https://firebasestorage.googleapis.com/v0/b/finup-261.appspot.com/o/12dxd9nFeTWXsO2E2yAl%2FTransaction%2Frevenus%2F1621520529279.jpg?alt=media&token=808e4115-f26a-4704-8215-d673f3522ca0");
																	put("res_model","account.move");
																	put("res_id",18);
																	put("res_name","FACTU/2021/09/0005 (FAC/2021/09/167)");
																	put("company_id",2);
																	put("public",true);
																	put("description","L'image du facture");

        }} };

		
		Object[] param = new Object[] { database, uid, password, "ir.attachment", "create", attache_args };
		
		final Integer id = (Integer)models.execute("execute_kw", param);
 		System.out.println("Creat........");
 		System.out.println("l'id est "+id);
	}
    
    public void create_contact() throws XmlRpcException {
    	
    	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
		Object[] attache_args = new Object[] { new HashMap() {{     put("name","Fifaliana");																	        
																	put("type","contact");
																	put("company_type","person");
																	put("street","Andraisoro");
																	put("vat","TVAFRE");
																	put("function","");
																	put("phone","03444");
																	put("email","fifa@gmail.com");

        }} };

		
		Object[] param = new Object[] { database, uid, password, "res.partner", "create", attache_args };
		
		final Integer id = (Integer)models.execute("execute_kw", param);
 		System.out.println("Creat........");
 		System.out.println("l'id est "+id);
    	
    }
	
    
    public void get_invoice_pdf() throws XmlRpcException, MalformedURLException {
    	
    	Object[] args = new Object[] {
    			new Object[] {
    					new Object[] {"move_type", "=", "out_invoice"},
    					new Object[] {"state", "=", "open"},
    					new Object[] {"name","=","FAC/2021/09/0001" }
    			}
    	};
    	
    	Object[] invoice_search = new Object[] {database, uid, password,"account.move", "search",args};
    	
    	@SuppressWarnings("unused")
		final Object[] invoice_ids = (Object[])models.execute("execute_kw", invoice_search);
    	final XmlRpcClientConfigImpl report_config = new XmlRpcClientConfigImpl();
    	report_config.setServerURL(
    	    new URL(String.format("%s/xmlrpc/2/report", url)));
    	
    	Object[] pdf_args = new Object[] {   
    			 database, uid, password,
     	        "account.report_invoice",
     	        invoice_ids
    	};
    	
    	
    	@SuppressWarnings("unchecked")
		final Map<String, Object> resultat = (Map<String, Object>)models.execute(
    	    report_config, "render_report", pdf_args);
    	@SuppressWarnings("unused")
		final byte[] report_data = DatatypeConverter.parseBase64Binary(
    	    (String)resultat.get("result"));
    	System.out.println("image upload");
    	//File file = new File("records/quotation_order.pdf");
        //FileUtils.writeByteArrayToFile(file, report_data);
    	
    }

    
    public void create_pdf_invoice() {
    	
    	
    }
    
    // login 
	static int login(String url, String db, String login, String password) throws XmlRpcException, MalformedURLException {
    		XmlRpcClient client = new XmlRpcClient();
    		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
    		config.setEnabledForExtensions(true);
    		config.setServerURL(new URL(url+"/xmlrpc/2/common"));
    		client.setConfig(config);
    		Object[] params = new Object[] {db,login,password};
    		Object uid = client.execute("login", params);
    		if (uid instanceof Integer)
        			return (int) uid;
    		return -1;
	}
}
