package com.example.nathaniels.workoutappv01;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class IngredientList extends AppCompatActivity {

    private static final String TAG = "IngredientList";

    private ArrayList<String> exerciseList = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> weightsAndReps = new ArrayList<>();

    public static class Exercise {
        public int weight1;
        public int weight2;
        public int weight3;
        public int reps1;
        public int reps2;
        public int reps3;
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main_page);
        Log.d(TAG, "IngredientList onCreate: started");



        //Grabbing the key of the workout day from RecyclerViewAdapter
        Intent intent = getIntent();
        String key = intent.getStringExtra(RecyclerViewAdapter.KEY);
        System.out.println("THIS IS THE KEY: " + key);
        //Connect to firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("workoutDay");
        DatabaseReference child = myRef.child(key).child("exercise");


        child.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Exercise exercise = dataSnapshot.getValue(Exercise.class);
                String key = dataSnapshot.getKey();
                System.out.println(key);



                if (exercise.reps1 >= 0) {
                    exerciseList.add(key);
                    ArrayList<Integer> tempList = new ArrayList<>();
                    tempList.add(exercise.weight1);
                    tempList.add(exercise.reps1);
                    tempList.add(exercise.weight2);
                    tempList.add(exercise.reps2);
                    tempList.add(exercise.weight3);
                    tempList.add(exercise.reps3);
                    weightsAndReps.add(tempList);

                    System.out.println("-------------HEY--------------");
                    for (int i = 0; i < exerciseList.size(); i++) {
                        System.out.println("Exercise: " + exerciseList.get(i));
                        for (int j = 0; j < weightsAndReps.get(i).size(); j++) {
                            System.out.println("Num: " + weightsAndReps.get(i).get(j));
                        }

                    }
                    System.out.println("-------------HEY--------------");
                }



            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview for IngredientList");
        RecyclerView recyclerView = findViewById(R.id.recycler_view_workouts);
        ExerciseRecyclerViewAdapter adapter = new ExerciseRecyclerViewAdapter(this, weightsAndReps, exerciseList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
