package com.example.naveed.firebaseintro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Naveed on 22/08/2017.
 */

public class AddActivity extends AppCompatActivity {


    private Button add ;
    FirebaseDatabase database;   // to get firebase accesss
    DatabaseReference myRef ;  // to get reference of firebase database
    FirebaseAuth mAuth;       // authenticaion Like laravel Auth
    FirebaseAuth.AuthStateListener mAuthListener; // Authentication Listener
    TextView db;
    String userId ;
     ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_firebase);
        listView =(ListView)findViewById(R.id.list);
        mAuth = FirebaseAuth.getInstance();               //get Object of authentication
        database = FirebaseDatabase.getInstance();        // get database
       // db =(TextView) findViewById(R.id.db);

        FirebaseUser users = mAuth.getCurrentUser();    // current signed in user
        userId = users.getUid();                      // Id of signed in user
        myRef= database.getReference();                   //A reference to databse
        Toast.makeText(getBaseContext(), "REF"+myRef , Toast.LENGTH_LONG).show();

        mAuthListener =
                new FirebaseAuth.AuthStateListener() {   //It is auth listner means user signed in and then quite app and again resumem then he will remain signed in.
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                   // Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                    //toastMessage("Successfully signed out.");
                }
                // ...
            }
        };
        add= (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    myRef.child("Last").setValue("Nsb");
            }
        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
                //db.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private void showData(DataSnapshot dataSnapshot) {
        //Toast.makeText(getBaseContext() , "Fullname : " +dataSnapshot.getChildrenCount()  ,Toast.LENGTH_LONG).show();
        for(DataSnapshot d: dataSnapshot.getChildren())
        {

          //  User u= d.getValue(User.class);
            //String s= d.child(userId).getValue(String.class);
            //Toast.makeText(getBaseContext() , "Fullname : " + d.child(userId) ,Toast.LENGTH_LONG).show();
          //  Toast.makeText(getBaseContext() , "Fullname : " +u  ,Toast.LENGTH_LONG).show();
            User u = new User();
          u.setFullname(d.child(userId).getValue(User.class).getFullname());
            u.setUni(d.child(userId).getValue(User.class).getUni());
            //u.setFullname("a");
            //u.setUni("u");

          //  u.setFullname(d.child(d.getKey()).getValue(String.class));
           // u.setUni(d.child(d.getKey()).getValue(String.class));

            ArrayList<String> ar = new ArrayList<>();
//
            ar.add(u.getFullname());
            ar.add(u.getUni());
//
//
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ar);
            listView.setAdapter(adapter);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
