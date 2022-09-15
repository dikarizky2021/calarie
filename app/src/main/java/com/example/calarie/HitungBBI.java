package com.example.calarie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HitungBBI extends AppCompatActivity {

    EditText txtTinggi, txtBerat, txtKelamin, txtUsia;
    Button hitung;
    TextView txtHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hitung_bbi);

        txtTinggi=findViewById(R.id.tinggiBadan);
        txtBerat=findViewById(R.id.beratBadan);
        txtKelamin=findViewById(R.id.jenisKelamin);
        txtUsia=findViewById(R.id.usia);
        txtHasil=findViewById(R.id.textHasil);
        hitung = findViewById(R.id.tombolHitung);

        hitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double tinggi=Double.parseDouble(txtTinggi.getText().toString());
                double berat=Double.parseDouble(txtBerat.getText().toString());
                String kelamin=txtKelamin.getText().toString();
                double usia=Double.parseDouble(txtUsia.getText().toString());


                txtHasil.setText( "hasil: "+BBI(tinggi,berat,kelamin,usia));
            }
        });

    }

    private double BBI(double tinggi, double berat, String kelamin, double usia){
        double hasil=0;

        if((kelamin=="Pria")&&(tinggi<=160)){
            hasil=tinggi-100;
        }else if((kelamin=="Wanita")&&(tinggi<=150)){
            hasil=tinggi-100;
        } else if(usia>40){
            hasil=tinggi-100;
        }else{
            hasil=90/100*(tinggi-100);
        }
        return hasil;
    }

}