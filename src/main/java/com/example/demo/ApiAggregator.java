package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.FirebaseDatabase;

public class ApiAggregator {
	
	 public void Connect_To_Firebase_Aggergator() throws IOException, FirebaseAuthException {  
		    ClassLoader classLoader = TestApplication.class.getClassLoader();
			
			File file = new File(Objects.requireNonNull(classLoader.getResource("apiservice.json")).getFile());
			
			FileInputStream serviceAccount = new FileInputStream(file.getAbsoluteFile());
			
			@SuppressWarnings("deprecation")
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl("https://apiaggregator-31a72-default-rtdb.firebaseio.com")
					  .build();

			 if(FirebaseApp.getApps().isEmpty()) {
				 
	             FirebaseApp.initializeApp(options);
	         }				
			 
	}
	 
	 public int Auth(String mail) {
		 
		    UserRecord userRecord = null;
		    int test = 0;
			try {
				userRecord = FirebaseAuth.getInstance().getUserByEmail(mail);
			} catch (FirebaseAuthException e) {
			    
		       test=-1;
			}
			// See the UserRecord reference doc for the contents of userRecord.
		 
			if(test>=0) {
				return 1;
			}
			else {
				return 0;
			}
	 }

}
