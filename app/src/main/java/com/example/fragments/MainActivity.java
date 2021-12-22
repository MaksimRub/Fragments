package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    Button toCurrentTask,toFinishTask,toRepeatTask;
    FragmentManager fm;
    FragmentTransaction ft;
    Fragment fragment_current,fragment_finish,fragment_repeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toCurrentTask = findViewById(R.id.to_current_task);
        toFinishTask = findViewById(R.id.to_finish_task);
        toRepeatTask=findViewById(R.id.to_repeat_task);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragment_current = fm.findFragmentById(R.id.fragment_container);
        if (fragment_current == null) {
            fragment_current = new CurrentTaskFragment();
            ft.add(R.id.fragment_container, fragment_current);
            ft.commit();
        }
        toFinishTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fragment_finish == null) {
                    fragment_finish = new FinishTaskFragment();
                }
                fm.beginTransaction().replace(R.id.fragment_container, fragment_finish).commit();
            }
        });
        toCurrentTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.fragment_container, fragment_current).commit();
            }
        });
        toRepeatTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment_repeat==null){
                    fragment_repeat=new RepeatTaskFragment();
                }
                fm.beginTransaction().replace(R.id.fragment_container, fragment_repeat).commit();
            }
        });



        //TODO написать возврат к первому фрагменту yes
        //TODO добавить в код третий фрвгмент "повторяющиеся задачи" yes
        //TODO придумать способ хранения введенных пользователем задач


    }


}