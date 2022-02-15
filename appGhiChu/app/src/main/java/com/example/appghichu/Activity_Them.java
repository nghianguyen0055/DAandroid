package com.example.appghichu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.appghichu.Database.NoidungDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Activity_Them extends AppCompatActivity {
    private TextInputEditText txtTieude;
    private EditText txtnoidung;
    private Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them);
        addview();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strTieude = txtTieude.getText().toString().trim();
                String strnoidung = txtnoidung.getText().toString().trim();
                Noidung noidung = new Noidung(strTieude, strnoidung);
//                Intent intent = new Intent();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("objND", noidung);
//                intent.putExtras(bundle);
                NoidungDatabase.getInstance(Activity_Them.this).noiDungDAO().InsertND(noidung);
                txtTieude.setText("");
                txtnoidung.setText("");
                AnBanPhim();
                finish();
            }
        });
    }

    private void addview() {
        txtTieude = findViewById(R.id.TextTD);
        txtnoidung = findViewById(R.id.TextND);
        btnThem = findViewById(R.id.buttonThem);
    }
    private void AnBanPhim(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}