package com.example.calarie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calarie.api.RequestHandler;
import com.example.calarie.api.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    EditText regNama, regTTL, regJK, regUsername, regPassword;
    Button daftar;
    String nama,ttl,jk,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regNama= findViewById(R.id.regNama);
        regTTL = findViewById(R.id.regTTL);
        regJK = findViewById(R.id.regJK);
        regUsername= findViewById(R.id.regUsername);
        regPassword= findViewById(R.id.regPassword);
        daftar= findViewById(R.id.tombolDaftar);

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData();
            }
        });



    }


    private void simpanData(){

        nama=regNama.getText().toString();
        ttl=regTTL.getText().toString();
        jk=regJK.getText().toString();
        username=regUsername.getText().toString();
        password=regPassword.getText().toString();

        if (TextUtils.isEmpty(nama)) {
            regNama.setError("Masukkan Nama terlebih dahulu");
            regNama.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(ttl)) {
            regTTL.setError("Masukkan Tanggal Lahir terlebih dahulu");
            regTTL.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(jk)) {
            regJK.setError("Masukkan Jenis Kelamin terlebih dahulu");
            regJK.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(username)) {
            regUsername.setError("Masukkan Usernmae terlebih dahulu");
            regUsername.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            regPassword.setError("Masukkan Password terlebih dahulu");
            regPassword.requestFocus();
            return;
        }

        class RegisterUser extends AsyncTask<Void, Void, String> {

            // private ProgressBar progressBar;

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("jk", jk);
                params.put("ttl", ttl);
                params.put("nama", nama);
                params.put("password", password);


                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_DAFTAR, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                //hiding the progressbar after completion
//                progressBar.setVisibility(View.GONE);

                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();

                        finish();
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute();

    }




}