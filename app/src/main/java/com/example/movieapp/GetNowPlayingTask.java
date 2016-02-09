package com.example.movieapp;

import android.os.AsyncTask;

import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by rahul on 09/02/16.
 */
public class GetNowPlayingTask extends AsyncTask<String,Void,List<MovieDb>> {

    private NowPlayingReceiver receiver;

    public GetNowPlayingTask(NowPlayingReceiver receiver)
    {
        this.receiver=receiver;
    }


    @Override
    protected List<MovieDb> doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(List<MovieDb> movieDbList) {
        //super.onPostExecute(movieDbList);
        receiver.receiveNowPlaying(movieDbList);
    }

    interface NowPlayingReceiver
    {
        void receiveNowPlaying(List<MovieDb> nowPlaying);
    }
}
