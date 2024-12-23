package com.example.exam1;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.example.exam1.ClassItem;



public class AddClassActivity extends  AppCompatActivity{
    private static final String EXTRA_NEW_CLASS_ITEM = "new_class_item";

    private EditText editTextClassName;
    private EditText editTextClassTime;
    private Button buttonAddClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        editTextClassName = findViewById(R.id.edit_text_class_name);
        editTextClassTime = findViewById(R.id.edit_text_class_time);
        buttonAddClass = findViewById(R.id.button_add_class);

        buttonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClass();
            }
        });
    }

    private  void addClass() {
        String className = editTextClassName.getText().toString();
        String classTime = editTextClassTime.getText().toString();

        if (!className.isEmpty() && !classTime.isEmpty()) {
            ClassItem newClassItem = new ClassItem(className, classTime);

            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_NEW_CLASS_ITEM, newClassItem);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        } else {
            // Обработка ошибок, например, показ Toast
        }
    }
}
