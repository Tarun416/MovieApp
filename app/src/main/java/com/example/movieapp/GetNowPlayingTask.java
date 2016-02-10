package com.example.movieapp;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;

/**
 * Created by rahul on 10/02/16.
 */
public class GetNowPlayingTask extends AsyncTask<String,Void,List<MovieDb>> {

    private NowPlayingReceiver receiver;

    public GetNowPlayingTask(NowPlayingReceiver receiver)
    {
         this.receiver=receiver;
    }

    @Override
    protected List<MovieDb> doInBackground(String... params) {
        if (params[0].isEmpty()){
            Log.e("d", "No API key");
            return null;
        }
        Log.d("dd",params[0]);
        TmdbMovies movies = new TmdbApi(params[0]).getMovies();
        MovieResultsPage nowPlaying = movies.getNowPlayingMovies("", 1);
        return nowPlaying.getResults();
    }

    @Override
    protected void onPreExecute() {
       // super.onPreExecute();

    }

    @Override
    protected void onPostExecute(List<MovieDb> movieDbs) {
        super.onPostExecute(movieDbs);
        receiver.receiveNowPlaying(movieDbs);
    }

    interface NowPlayingReceiver
    {
        void receiveNowPlaying(List<MovieDb> nowPlaying);
    }






}
