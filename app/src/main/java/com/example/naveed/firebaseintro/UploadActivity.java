package com.example.naveed.firebaseintro;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Naveed on 23/08/2017.
 */

public class UploadActivity extends AppCompatActivity {

    private Button upload , back ,next;
    private EditText name;
    private ImageView img;

    ArrayList<String> path;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    private int pos;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_activity);

        upload = (Button)findViewById(R.id.upload);
        back = (Button)findViewById(R.id.back);
        next = (Button)findViewById(R.id.next);
        img = (ImageView)findViewById(R.id.img);
        name = (EditText)findViewById(R.id.name);
        mAuth  = FirebaseAuth.getInstance();

        path = new ArrayList<>();
        progressDialog = new ProgressDialog(UploadActivity.this);
        mStorageRef = FirebaseStorage.getInstance().getReference();
    }
//    private void checkFilePermissions() {
//        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
//            int permissionCheck = UploadActivity.this.checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
//            permissionCheck += UploadActivity.this.checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
//            if (permissionCheck != 0) {
//                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1001); //Any number
//            }
//        }else{
//           // Log.d(TAG, "checkBTPermissions: No need to check permissions. SDK version < LOLLIPOP.");
//        }
//    }
//    private void addFilePaths(){
//       // Log.d(TAG, "addFilePaths: Adding file paths.");
//        String path = System.getenv("EXTERNAL_STORAGE");
//        path.add(path+"/Pictures/Portal/image1.jpg");
//        path.add(path+"/Pictures/Portal/image2.jpg");
//        path.add(path+"/Pictures/Portal/image3.jpg");
//        loadImageFromStorage();
//    }
//    private void loadImageFromStorage()
//    {
//        try{
//            String path = pathArray.get(array_position);
//            File f=new File(path, "");
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            image.setImageBitmap(b);
//        }catch (FileNotFoundException e){
//            Log.e(TAG, "loadImageFromStorage: FileNotFoundException: " + e.getMessage() );
//        }
//
//    }
}
