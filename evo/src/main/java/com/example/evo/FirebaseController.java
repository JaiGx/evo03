package com.example.evo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class FirebaseController {

    private final FirebaseService firebaseService;

    @Autowired
    public FirebaseController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @GetMapping("/read-all")
    public List<Object> readAllDataFromFirebase() throws ExecutionException, InterruptedException {
        // Definir la ruta completa al nodo "users" dentro de la colección "path"
        String rutaDelNodo = "path/users";
        CompletableFuture<List<Object>> result = firebaseService.readAllDataFromDatabase(rutaDelNodo);
        
        // Esperar hasta que se complete la lectura de datos desde Firebase y luego devolver el resultado
        return result.get();
    }
}
