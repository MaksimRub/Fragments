package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RepeatTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepeatTaskFragment extends Fragment {
    private String str="";
    private String str1="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RepeatTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RepeatTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RepeatTaskFragment newInstance(String param1, String param2) {
        RepeatTaskFragment fragment = new RepeatTaskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_repeat_task, container, false);
        EditText newTask=v.findViewById(R.id.add_task);
        Button addTask=v.findViewById(R.id.new_task);
        EditText repeatTask=v.findViewById(R.id.repeat_task);
        try {
            BufferedReader read_current=new BufferedReader(new InputStreamReader(getActivity().openFileInput("repeatTask")));
            while((str=read_current.readLine())!=null){
                repeatTask.append(str+"\n");
            }
            str1=repeatTask.getText().toString();

            read_current.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                repeatTask.append(newTask.getText().toString()+"\n");
                str1=repeatTask.getText().toString();
            }
        });
        return v;
    }
    @Override
    public void onStop() {
        super.onStop();
        try {

            BufferedWriter write_repeat = new BufferedWriter(new OutputStreamWriter(getActivity().openFileOutput("repeatTask", Context.MODE_PRIVATE)));
            write_repeat.write(str1);
            write_repeat.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}