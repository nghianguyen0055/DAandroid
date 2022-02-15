package com.example.appghichu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appghichu.Database.NoidungDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txttimkiem;
    private ImageView imgtimkiem;
    private RecyclerView rcvDS;
    private FloatingActionButton btnThem;

    private NoDungAdapter noDungAdapter;
    private List<Noidung> mListNoiDung;

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK){
                        loaddata();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addview();
        noDungAdapter = new NoDungAdapter(new NoDungAdapter.Iclick() {
            @Override
            public void updateNoiDung(Noidung noidung) {
                clickUpdate(noidung);
            }

            @Override
            public void DeleteNoiDung(Noidung noidung) {
                LongclickXoa(noidung);
            }
        });
        mListNoiDung = new ArrayList<>();
        noDungAdapter.setdata(mListNoiDung);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvDS.setLayoutManager(linearLayoutManager);
        rcvDS.setAdapter(noDungAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Them.class);
                startActivity(intent);
            }
        });
        txttimkiem.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){//khi click vào edt search thì bàn phím hiện icon search
                    //search
                    SearchNoiDung();
                }
                return false;
            }
        });
        imgtimkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchNoiDung();
            }
        });

        loaddata();
    }

    private void SearchNoiDung() {
        String key = txttimkiem.getText().toString().trim();
        mListNoiDung = new ArrayList<>();
        mListNoiDung = NoidungDatabase.getInstance(MainActivity.this).noiDungDAO().searchND(key);
        noDungAdapter.setdata(mListNoiDung);
        AnBanPhim();
    }

    private void LongclickXoa(Noidung noidung) {
        new AlertDialog.Builder(this)
                .setTitle("Xóa Ghi Chú")
                .setMessage("Bạn Có Chắc Muốn Xóa?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Delete
                        NoidungDatabase.getInstance(MainActivity.this).noiDungDAO().DeleteNd(noidung);
                        Toast.makeText(MainActivity.this, "Xóa Thành Công!", Toast.LENGTH_LONG).show();
                        loaddata();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void addview() {
        txttimkiem = findViewById(R.id.TimKiem);
        imgtimkiem = findViewById(R.id.Imghinh);
        rcvDS = findViewById(R.id.rcvdanhsach);
        btnThem = findViewById(R.id.floatThem);
    }
//    private void adduser(){
//        if(getIntent().getExtras() != null){
//           Noidung mnoidung = (Noidung) getIntent().getExtras().get("objND");
//            NoidungDatabase.getInstance(this).noiDungDAO().InsertND(mnoidung);
//        }
//    }
    private void loaddata(){
        mListNoiDung = NoidungDatabase.getInstance(this).noiDungDAO().getListND();
        noDungAdapter.setdata(mListNoiDung);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loaddata();
    }
    private void clickUpdate(Noidung noidung){
        Intent intent = new Intent(MainActivity.this, Activity_sua.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objNDsua", noidung);
        intent.putExtras(bundle);
        mActivityResultLauncher.launch(intent);
    }
    private void AnBanPhim(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}