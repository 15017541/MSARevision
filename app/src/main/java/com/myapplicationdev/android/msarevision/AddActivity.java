package com.myapplicationdev.android.msarevision;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText etName, etHeight;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etName = (EditText) findViewById(R.id.etName);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String str_height = etHeight.getText().toString();

                if (name.length() <1 || str_height.length() < 1){
                    Toast.makeText(AddActivity.this, "Please input all values", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbh = new DBHelper(AddActivity.this);
                    double height = Double.parseDouble(str_height);
                    int rbSelected = rgGender.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton) findViewById(rbSelected);
                    String gender = rb.getText().toString();

                    dbh.insertContact(name, gender, height);
                    dbh.close();

                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
