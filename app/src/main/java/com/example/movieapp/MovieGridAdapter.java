package com.example.movieapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import info.movito.themoviedbapi.model.MovieDb;

/**
 * Created by rahul on 10/02/16.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {
    Context context;

   //private Integer [] array;
    ArrayList<MovieDb> movies;



    public MovieGridAdapter(Context context)
    {
        this.context=context;
        movies=new ArrayList<>();
       // this.array=array1;
    }

    public MovieGridAdapter() {
        super();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // return null;

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie,parent,false);

        ViewHolder vh=new ViewHolder(v,parent.getContext());
        return vh;
    }

   /* @Override
    public long getItemId(int position) {
        return array[position];
    }*/

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Log.d("dsd", Integer.toString(array[position]));
//         holder.poster.setImageResource(array[position]);


        String baseurl= holder.context.getString(R.string.base_URL);
        Log.d("hh",baseurl);
        String size=holder.context.getString(R.string.thumb_size);
        String url=baseurl+size+movies.get(position).getPosterPath();
        Picasso.with(holder.context).load(url).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public ImageView poster;
        public Context context;
         public ViewHolder(View v,Context context)
         {
             super(v);
             this.context=context;
             poster= (ImageView)v.findViewById(R.id.poster);
         }
    }
}
