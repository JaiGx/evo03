package com.example.evo;

import com.google.firebase.database.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class FirebaseService {

    public CompletableFuture<List<Object>> readAllDataFromDatabase(String path) {
        CompletableFuture<List<Object>> future = new CompletableFuture<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(path);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Object> dataList = new ArrayList<>();
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    Object data = childSnapshot.getValue();
                    dataList.add(data);
                }
                future.complete(dataList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new RuntimeException("Error al leer desde Firebase: " + databaseError.getMessage()));
            }
        });
        return future;
    }
}
