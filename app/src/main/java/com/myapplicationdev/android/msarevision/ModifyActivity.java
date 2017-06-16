package com.myapplicationdev.android.msarevision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyActivity extends AppCompatActivity {

    TextView tvId;
    Button btnUpdate, btnCancel, btnDelete;
    EditText etName, etHeight;
    RadioGroup rgGender;
    RadioButton rbFemale, rbMale;
    Contact existingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etName = (EditText) findViewById(R.id.etName);
        rgGender = (RadioGroup) findViewById(R.id.rgGender);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbMale = (RadioButton) findViewById(R.id.rbMale);
        tvId = (TextView) findViewById(R.id.tvId);

        Intent i = getIntent();
        existingData = (Contact) i.getSerializableExtra("data");
        tvId.setText(existingData.getId()+"");
        etHeight.setText(existingData.getHeight()+"");
        etName.setText(existingData.getName());

        if (existingData.getGender().equalsIgnoreCase("Female")){
            rbFemale.setChecked(true);
        } else {
            rbMale.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = etName.getText().toString();
                String str_height = etHeight.getText().toString();

                if (name.length() <1 || str_height.length() < 1){
                    Toast.makeText(ModifyActivity.this, "Please input all values", Toast.LENGTH_SHORT).show();
                } else {
                    double height = Double.parseDouble(str_height);
                    int rbSelected = rgGender.getCheckedRadioButtonId();
                    RadioButton rb = (RadioButton) findViewById(rbSelected);
                    String gender = rb.getText().toString();

                    existingData.setName(name);
                    existingData.setHeight(height);
                    existingData.setGender(gender);

                    DBHelper dbh = new DBHelper(ModifyActivity.this);
                    dbh.updateContact(existingData);
                    dbh.close();

                    setResult(Activity.RESULT_OK);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteContact(existingData.getId());
                dbh.close();
                setResult(Activity.RESULT_OK);
                finish();
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
