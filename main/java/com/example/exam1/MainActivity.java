package com.example.exam1;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exam1.ClassAdapter;
import com.example.exam1.ClassItem;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ClassAdapter classAdapter;
    private List<ClassItem> classList; // Список занятий

    private static final int ADD_CLASS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button buttonAddClass = findViewById(R.id.button_add_class);
        buttonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        AddClassActivity.class);
                startActivity(intent);
            }
        });

        // Инициализация списка занятий
        classList = new ArrayList<>();
        // Здесь можно добавить некоторые начальные данные для списка
        classList.add(new ClassItem("Математика", "10:00 - 11:30"));
        classList.add(new ClassItem("Физика", "12:00 - 13:30"));
        classList.add(new ClassItem("Химия", "14:00 - 15:30"));

        // Инициализация адаптера и привязка его к RecyclerView
        classAdapter = new ClassAdapter(classList);
        recyclerView.setAdapter(classAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_class) {
            openAddClassActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void openAddClassActivity() {
        Intent intent = new Intent(this, AddClassActivity.class);
        startActivityForResult(intent, ADD_CLASS_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CLASS_REQUEST && resultCode == RESULT_OK) {
            ClassItem newClassItem = (ClassItem) data.getSerializableExtra("new_class_item");
            classList.add(newClassItem);
            classAdapter.notifyDataSetChanged();
            Toast.makeText(this, "New class added", Toast.LENGTH_SHORT).show();
        }
    }
}