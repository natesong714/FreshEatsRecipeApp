package com.example.nathaniels.workoutappv01;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder>{

    //typed as logt used for debugging
    private static final String TAG = "ExerciseRecyclerViewAdp";

    private ArrayList<ArrayList<Integer>> mWeightsAndReps = new ArrayList<>();
    private ArrayList<String> mExercises = new ArrayList<>();
    private Context mContext;
    private int width;



    public ExerciseRecyclerViewAdapter(Context context, ArrayList<ArrayList<Integer>> weightsAndReps, ArrayList<String> exercises) {
        this.mWeightsAndReps = weightsAndReps;
        this.mExercises = exercises;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //Responsible for inflating the view
        //Not super important to understand how it works
        //Same for every recycler adapter view you make
        //Recycling the view holders
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_exercise_name, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //Important method that changes based on what your layouts are

        //Used to see how many items get loaded
        Log.d(TAG, "onBindViewHolder: called.");


        holder.exerciseName.setText(mExercises.get(position));
        holder.weight1.setHint((mWeightsAndReps.get(position).get(0)).toString());
        holder.rep1.setHint((mWeightsAndReps.get(position).get(1)).toString());
        holder.weight2.setHint((mWeightsAndReps.get(position).get(2)).toString());
        holder.rep2.setHint((mWeightsAndReps.get(position).get(3)).toString());
        holder.weight3.setHint((mWeightsAndReps.get(position).get(4)).toString());
        holder.rep3.setHint((mWeightsAndReps.get(position).get(5)).toString());

        //Assign size based on screen sizes
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        width = dm.widthPixels;
        holder.exerciseName.setWidth(width * 1/3);
        holder.weight1.setWidth(width * 1/15);
        holder.rep1.setWidth(width * 1/15);
        holder.weight2.setWidth(width * 1/15);
        holder.rep2.setWidth(width * 1/15);
        holder.weight3.setWidth(width * 1/15);
        holder.rep3.setWidth(width * 1/15);
        holder.weight4.setWidth(width * 1/15);
        holder.rep4.setWidth(width * 1/15);
        holder.weight5.setWidth(width * 1/15);
        holder.rep5.setWidth(width * 1/15);

        //Allows the toast to pop up
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(position));
                Toast.makeText(mContext, mImageNames.get(position), Toast.LENGTH_SHORT).show();

                 */
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    //Holds the view(widget) in memory of each individual entry
    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText exerciseName;
        EditText weight1;
        EditText rep1;
        EditText weight2;
        EditText rep2;
        EditText weight3;
        EditText rep3;
        EditText weight4;
        EditText rep4;
        EditText weight5;
        EditText rep5;
        LinearLayout linearLayout;
        //Constructor for ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Attaching image to the IDs
            exerciseName = itemView.findViewById(R.id.exerciseName);
            weight1 = itemView.findViewById(R.id.weight1);
            rep1 = itemView.findViewById(R.id.rep1);
            weight2 = itemView.findViewById(R.id.weight2);
            rep2 = itemView.findViewById(R.id.rep2);
            weight3 = itemView.findViewById(R.id.weight3);
            rep3 = itemView.findViewById(R.id.rep3);
            weight4 = itemView.findViewById(R.id.weight4);
            rep4 = itemView.findViewById(R.id.rep4);
            weight5 = itemView.findViewById(R.id.weight5);
            rep5 = itemView.findViewById(R.id.rep5);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
