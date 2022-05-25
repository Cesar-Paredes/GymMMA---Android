package com.example.mma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


//CESAR PAREDES /////////////////////////////////////////////////
public class DeleteAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        Button deleteBtn = (Button) findViewById(R.id.deleteFinal);
        Button homeBtn = findViewById(R.id.homeBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteAccount.this,HomeActivity.class);
                startActivity(intent);

            }
        });



        deleteBtn.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                                             FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                                             DatabaseReference databaseReference = firebaseDatabase.getReference("Users").child(currentUser.getUid());
                                             ;


                                             databaseReference.addValueEventListener(new ValueEventListener() {
                                                 @Override
                                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                     if (snapshot.exists()) {
                                                         //remove user in auth
                                                         snapshot.getRef().removeValue();

                                                         //remove user in realDatabase
                                                         currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                             @Override
                                                             public void onComplete(@NonNull Task<Void> task) {
                                                                 Intent intent = new Intent(DeleteAccount.this, MainActivity.class);
                                                                 startActivity(intent);
                                                             }
                                                         });
                                                     }
                                                 }

                                                 @Override
                                                 public void onCancelled(@NonNull DatabaseError error) {

                                                 }
                                             });
                                         }
        });
    }
}



//                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//
//
//                                Intent intent = new Intent(DeleteAccount.this,MainActivity.class);
//                                startActivity(intent);
//                            }
//                        });
//
//                    }
//                });
//            }
//        });











