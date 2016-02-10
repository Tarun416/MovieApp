package com.example.movieapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.model.MovieDb;



/**
 * Created by rahul on 09/02/16.
 */
public class MovieGridFragment extends Fragment implements GetNowPlayingTask.NowPlayingReceiver {
    
    private RecyclerView mRecyclerView;
    // ArrayAdapter<String> adapter;
   private MovieGridAdapter movieGridAdapter;
    private GridLayoutManager layoutManager;
    private static final int MIN_COLUMN_WIDTH=200;
    ArrayList<MovieDb>  nowPlaying;
    
    

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //  return super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_movie_grid, container, false);


        String api=getActivity().getString(R.string.mdbApiKey);

        GetNowPlayingTask movieTask=new GetNowPlayingTask(this);
        movieTask.execute(api);

        try {
            movieTask.wait();
        } catch (Exception e){
            Log.e("ddd", e.toString());
        }

        mRecyclerView =(RecyclerView) root.findViewById(R.id.rv);

       // Integer [] array= new Integer[] { R.drawable.avatar, R.drawable.erased,R.drawable.i,R.drawable.gamer,R.drawable.harry,R.drawable.ride};

        // adapter=new ArrayAdapter<String>(getActivity(),R.layout.movie,R.id.poster,array);

        movieGridAdapter=new MovieGridAdapter(getActivity());


        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int columns = (int)dpWidth/MIN_COLUMN_WIDTH;
        columns = Math.max(columns, 2);


        layoutManager=new GridLayoutManager(getContext(),columns);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setAdapter(movieGridAdapter);







        return root;

        
        
        
        
        
        




       /* String apikey=getActivity().getString(R.string.mdbApiKey);

        GetNowPlayingTask movieTask=new GetNowPlayingTask(this.);

        movieTask.execute(apikey);
        try {
            movieTask.wait();
        } catch (Exception e){
            Log.e("dd", e.toString());

        }

        return root;

    }

    @Override
    public void receiveNowPlaying(List<MovieDb> nowPlaying) {
        this.nowPlaying = new ArrayList<MovieDb>(nowPlaying);
      //  this.movieGridAdapter.movies = this.nowPlaying;
        //this.movieGridAdapter.notifyDataSetChanged();
        //mRecyclerView.scrollToPosition(scrollPosition);
    }*/

    }



    @Override
    public void receiveNowPlaying(List<MovieDb> nowPlaying) {
        this.nowPlaying = new ArrayList<MovieDb>(nowPlaying);
        this.movieGridAdapter.movies = this.nowPlaying;
        this.movieGridAdapter.notifyDataSetChanged();
     //   mRecyclerView.scrollToPosition(scrollPosition);
    }
}
