package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Button btnEdit;
    EditText etAge;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnEdit = findViewById(R.id.btnEdit);
        tvName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);

        final DataManager dm = new DataManager(this);

        Intent intent = getIntent();

        tvName.setText(intent.getStringExtra("NAME"));
        etAge.setText(intent.getStringExtra("AGE"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.update(tvName.getText().toString(), etAge.getText().toString());
                Toast.makeText(Main2Activity.this, tvName.getText().toString() + " has been updated!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
