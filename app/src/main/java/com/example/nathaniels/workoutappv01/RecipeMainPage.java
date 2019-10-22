package com.example.nathaniels.workoutappv01;

import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RecipeMainPage extends AppCompatActivity {
    private static final String TAG = "RecipeMainPage";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();

    public static class WorkoutDay {
        public String name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_main_page);
        Log.d(TAG, "Recipe Main Page - onCreate: started.");


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("workoutDay");

        Intent intent = getIntent();

        //Grabbing the added workout from the AddRecipeActivity page
        String message = intent.getStringExtra(AddRecipeActivity.EXTRA_MESSAGE);
        String deleteKey = intent.getStringExtra(DeleteRecipeActivity.DELETE_KEY);

        Map<String, String> userData = new HashMap<String, String>();
        if (message != null) {
            userData.put("name", message);
            myRef.push().setValue(userData);
        }
        if (deleteKey != null) {
            myRef.child(deleteKey).removeValue();
        }



        FloatingActionButton addRecipeButton = (FloatingActionButton) findViewById(R.id.addButton);
        addRecipeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecipeMainPage.this, AddRecipeActivity.class));
            }
        });




        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                WorkoutDay day = dataSnapshot.getValue(WorkoutDay.class);
                String key = dataSnapshot.getKey();
                //System.out.println("Author: " + day.name);
                if (day.name != null) {
                    mImageUrls.add("data:image/jpeg;base64,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");
                    mNames.add(day.name);
                    mKeys.add(key);
                }

                /*
                for (int i = 0; i < mNames.size(); i++) {
                    System.out.println(mNames.get(i));
                }

                 */
                System.out.println("-----------");
                initRecyclerView();

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




    }


    private void initRecyclerView() {


        Log.d(TAG, "initRecyclerView: init recyclerview for RecipeMainPage");
        RecyclerView recyclerView = findViewById(R.id.recycler_view_workouts);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mKeys);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
