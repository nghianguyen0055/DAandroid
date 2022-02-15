package com.example.appghichu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appghichu.Database.NoidungDatabase;
import com.google.android.material.textfield.TextInputEditText;

public class Activity_sua extends AppCompatActivity {
    private TextInputEditText txtTieudesua;
    private EditText txtnoidungsua;
    private Button btnSua;
    private Noidung mNoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua);
        addview();

        mNoiDung = (Noidung) getIntent().getExtras().get("objNDsua");
        if(mNoiDung != null){
            txtTieudesua.setText(mNoiDung.getTieude());
            txtnoidungsua.setText(mNoiDung.getNd());
        }

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateND();
            }
        });
    }

    private void updateND() {
        String strTieuDe = txtTieudesua.getText().toString().trim();
        String strNoiDung = txtnoidungsua.getText().toString().trim();
        if(TextUtils.isEmpty(strTieuDe) || TextUtils.isEmpty(strNoiDung)){
            return;
        }
        //update
        mNoiDung.setTieude(strTieuDe);
        mNoiDung.setNd(strNoiDung);
        NoidungDatabase.getInstance(this).noiDungDAO().UpdateND(mNoiDung);

        Toast.makeText(this, "Update Thành Công!!", Toast.LENGTH_LONG).show();
        Intent intentKQ = new Intent();
        setResult(Activity.RESULT_OK,intentKQ);
        AnBanPhim();
        finish();
    }

    private void addview() {
        txtTieudesua = findViewById(R.id.TextTDSua);
        txtnoidungsua = findViewById(R.id.TextNDSua);
        btnSua = findViewById(R.id.buttonSua);
    }
    private void AnBanPhim(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}