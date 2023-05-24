package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnInsert, btnEdit, btnDelete, btnSearch, btnAll;
        final EditText etName, etAge, etEdit, etDName, etSearch;

        btnInsert = findViewById(R.id.btnInsert);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        btnSearch = findViewById(R.id.btnSearch);
        btnAll = findViewById(R.id.btnAll);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etEdit = findViewById(R.id.etEdit);
        etDName = findViewById(R.id.etDName);
        etSearch = findViewById(R.id.etSearch);

        final DataManager dm = new DataManager(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.insert(etName.getText().toString(),
                etAge.getText().toString());

                Toast.makeText(MainActivity.this,
                        etName.getText().toString() + " has been inserted!",
                        Toast.LENGTH_SHORT).show();
            }
        });


        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dm.showData(dm.selectAll());
                Toast.makeText(MainActivity.this, data,
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dm.showData(dm.searchName(etSearch.getText().toString()));
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppData editData;
                editData = dm.editName(dm.searchName(etEdit.getText().toString()));
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);

                intent.putExtra("NAME", editData.getName().toString());
                intent.putExtra("AGE", editData.getAge().toString());
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dm.delete(etDName.getText().toString());
                Toast.makeText(MainActivity.this, etDName.getText().toString() + " has been deleted!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
