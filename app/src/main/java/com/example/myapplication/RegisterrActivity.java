package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MODEL.Admin;
import com.example.myapplication.MODEL.NhanVien;
import com.example.myapplication.MODEL.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.SQLOutput;
import java.util.Scanner;

public class RegisterrActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth
  EditText ed_name, ed_email,ed_password,enterpassword;
  Button btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sog_in);
        mAuth = FirebaseAuth.getInstance();
        anhXaView();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = ed_email.getText().toString();
                String password = ed_password.getText().toString();
                register(email,password);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
    public void register(String email, String pass){
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser userCurent = mAuth.getCurrentUser();
                            String userid = userCurent.getUid();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                           if(email.matches("^nhanvien+\\w+\\@+\\w+\\.+\\w+")){
                                //String id, String name, tring email, String password, String imgURL, int loaiUser, String sdt, String diachi
                                 NhanVien nv = new NhanVien(userid, ed_name.getText().toString(), email,pass,"default",2,"chưa thêm số điện thoại", "chưa thêm địa chỉ");
                                db.collection("Users").document("nhanvien")
                                                .collection("nhanviens")
                                                        .document()
                                                                .set(nv).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegisterrActivity.this, "dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterrActivity.this, MainActivity.class));
                                        }
                                    }
                                });
                            }else {

                            }

                        }
                    }
                });
    }
   private void anhXaView(){
        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        enterpassword = findViewById(R.id.enter_password);
        btn_add = findViewById(R.id.btn_ok);
    }
}