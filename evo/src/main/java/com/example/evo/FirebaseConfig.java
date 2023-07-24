package com.example.evo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        File file = new File("evo/src/main/resources/evo03-c9619-firebase-adminsdk-th2n1-877957cc49.json");
        FileInputStream serviceAccount = new FileInputStream(file);
        System.out.println("Directorio actual: " + System.getProperty("user.dir"));


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://evo03-c9619.firebaseio.com") // Reemplaza "YOUR_DATABASE_URL" con la URL de tu base de datos de Firebase
                .build();

        System.out.println("Conexion exitosa....");
        return FirebaseApp.initializeApp(options);
    }
}

