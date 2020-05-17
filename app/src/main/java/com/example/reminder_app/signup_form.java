package com.example.reminder_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signup_form extends AppCompatActivity implements View.OnClickListener {
    EditText txt_username,txt_email,txt_password,txt_type;


    String token ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_passward);
        txt_email = findViewById(R.id.txt_email);
        txt_type = findViewById(R.id.txt_type);
        findViewById(R.id.button3).setOnClickListener(this);

    }
    private void userSignup(){
        String username = txt_username.getText().toString().trim();
        String email = txt_email.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        String user_type = txt_type.getText().toString().trim();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(signup_form.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(signup_form.this,"Please Enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(signup_form.this,"Please Enter password",Toast.LENGTH_SHORT).show();
        }
        Call<ResponseBody> call =RetrofitClient.getInstance().getApi().register(new RegisterRequest(username,  password,email,user_type));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("MainActivity", String.valueOf(response.code()));
                if(response.isSuccessful()){
                    try {
                        Toast.makeText(signup_form.this,response.body().string(),Toast.LENGTH_LONG).show();


                            Intent hom = new Intent(signup_form.this, reminder.class);
                            startActivity(hom);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(signup_form.this,"sign up failed",Toast.LENGTH_LONG).show();
               }
                // Log.d("signup_form","Response successful");
                // Log.d("MainActivity", String.valueOf(response.code()));
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(signup_form.this,"check the connection",Toast.LENGTH_LONG).show();

            }
        });
    }
            @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
            userSignup();
        }

    }
}
