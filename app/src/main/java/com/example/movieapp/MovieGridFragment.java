package com.example.movieapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class MovieGridFragment extends Fragment implements NowPlayingReceiver {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);

       View root= inflater.inflate(R.layout.fragment_movie_grid,container,false);

        String apikey=getActivity().getString(R.string.mdbApiKey);

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
    }

}
