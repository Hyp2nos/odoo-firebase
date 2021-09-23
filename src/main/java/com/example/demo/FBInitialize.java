package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import com.google.firebase.FirebaseApp;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;


public class FBInitialize {
	
	 
	 public void Connect_To_Firebase() throws IOException, FirebaseAuthException {  
		    ClassLoader classLoader = TestApplication.class.getClassLoader();
			
			File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
			
			FileInputStream serviceAccount = new FileInputStream(file.getAbsoluteFile());
			
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl("https://authentification-c0606-default-rtdb.firebaseio.com")
					  .build();

			 if(FirebaseApp.getApps().isEmpty()) {
	             FirebaseApp.initializeApp(options);
	         }				
			 
			 UserRecord userRecord = FirebaseAuth.getInstance().getUser("KOumy4trtXObXUFKV3qEueXud433");
			// See the UserRecord reference doc for the contents of userRecord.
			System.out.println("Successfully fetched user data: " + userRecord.getUid());

	}
	 public void get_Montant() throws InterruptedException, ExecutionException {
		       Firestore db = FirestoreClient.getFirestore(); 
		       ApiFuture<QuerySnapshot> query = db.collection("Budget").get();
			   QuerySnapshot querySnapshot = query.get();
			   List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			   for (QueryDocumentSnapshot document : documents) {
			   System.out.println("User: " + document.getId());
			   System.out.println("name: " + document.getString("name"));
			   System.out.println("Categorie: " + document.getString("categorie"));
			   System.out.println("montant: " + document.getString("montant"));
				  //name = document.getString("last");
				}
		}

	 
	 
}
