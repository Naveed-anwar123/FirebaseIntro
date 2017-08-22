package com.example.naveed.firebaseintro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Naveed on 23/08/2017.
 */

public class SaveActivity extends AppCompatActivity {


    private String userId;
    private EditText fullname , uni;
    private Button save;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database;   // to get firebase accesss
    DatabaseReference myRef ;  // to get reference of firebase database
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_activity);

        fullname = (EditText)findViewById(R.id.fullname);
        uni = (EditText)findViewById(R.id.uni);
        save = (Button)findViewById(R.id.save);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();        // get database
        // db =(TextView) findViewById(R.id.db);

        FirebaseUser users = mAuth.getCurrentUser();    // current signed in user
        userId = users.getUid();                      // Id of signed in user
        myRef= database.getReference();                   //A reference to databse

        mAuthListener = new FirebaseAuth.AuthStateListener() {   //It is auth listner means user signed in and then quite app and again resumem then he will remain signed in.
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                  //  Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                 //   toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                   // Log.d(TAG, "onAuthStateChanged:signed_out");
                  //  toastMessage("Successfully signed out.");
                }
                // ...
            }
        };

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = fullname.getText().toString();
                String Uni = uni.getText().toString();
                User u = new User(fname,Uni);
                if(!fname.equals("") && !Uni.equals(""))
                {
                    myRef.child("users").child(userId).setValue(u);
                }
            }
        });


    }
}
