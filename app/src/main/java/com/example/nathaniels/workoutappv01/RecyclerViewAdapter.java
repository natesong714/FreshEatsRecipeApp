package com.example.nathaniels.workoutappv01;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    public static final String KEY = "com.example.myfirstapp.MESSAGE";

    //typed as logt used for debugging
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();
    private Context mContext;
    Button deleteBtn;

    public RecyclerViewAdapter(Context context, ArrayList<String> imageNames, ArrayList<String> images, ArrayList<String> keys) {
        this.mImageNames = imageNames;
        this.mImages = images;
        this.mContext = context;
        this.mKeys = keys;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //Responsible for inflating the view
        //Not super important to understand how it works
        //Same for every recycler adapter view you make
        //Recycling the view holders
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //Important method that changes based on what your layouts are

        //Used to see how many items get loaded
        Log.d(TAG, "onBindViewHolder: called.");

        //Get the images
        Glide.with(mContext)
                .asBitmap() //We are going to be getting Bitmaps
                .load(mImages.get(position)) //Where we reference image URLs (resource)
                .into(holder.image); //Where we reference our image widget

        holder.imageName.setText(mImageNames.get(position));



        //Allows the toast to pop up
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, IngredientList.class);
                //Pass in the key of the workout day
                intent.putExtra(KEY, mKeys.get(position));
                mContext.startActivity(intent);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DeleteRecipeActivity.class);
                intent.putExtra(KEY, mKeys.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    //Holds the view(widget) in memory of each individual entry
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button deleteBtn;
        CircleImageView image;
        TextView imageName;
        RelativeLayout parentLayout;
        //Constructor for ViewHolder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Attaching image to the IDs
            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
