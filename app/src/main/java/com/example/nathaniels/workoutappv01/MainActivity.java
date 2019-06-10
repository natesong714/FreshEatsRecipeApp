package com.example.nathaniels.workoutappv01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToRecipeMainPage(View view) {
        Intent intent = new Intent(this, RecipeMainPage.class);
        startActivity(intent);
    }


}


