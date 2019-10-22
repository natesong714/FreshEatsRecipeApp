package com.example.nathaniels.workoutappv01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;

public class DeleteRecipeActivity extends Activity {
    public static final String DELETE_KEY  = "com.example.myfirstapp.DELETE_KEY";
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        key = intent.getStringExtra(RecyclerViewAdapter.KEY);

        setContentView(R.layout.delete_recipe_pop_up);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .9));
    }

    public void deleteRecipe(View view) {
        //Button b = (Button)findViewById(R.id.button3);
        Intent intent = new Intent(this, RecipeMainPage.class);
        intent.putExtra(DELETE_KEY, key);
        startActivity(intent);
    }

    public void backToPreviousScreen(View view) {
        //Button b = (Button)findViewById(R.id.button3);
        Intent intent = new Intent(this, RecipeMainPage.class);
        startActivity(intent);
    }




}
