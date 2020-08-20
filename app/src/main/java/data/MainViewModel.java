package data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static MovieDatabase database;
    private LiveData<List<Movie>> movies;
    private LiveData<List<FavoriteMovie>> favMovies;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = MovieDatabase.getInstance(getApplication()) ;
        movies = database.movieDao().getAllMovies();
        favMovies = database.movieDao().getAllFavMovies();
    }

    public Movie getMovieById (int id){
        try {
            return new GetMovieTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FavoriteMovie getFavMovieById (int id){
        try {
            return new GetFavMovieTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public LiveData<List<FavoriteMovie>> getFavMovies() {
        return favMovies;
    }

    public void deleteAllMovies() {
        new DeleteMovieTask().execute();
    }
    public void insertMovie(Movie movie) {
        new InsertTask().execute(movie);
    }
    public void deleteMovie(Movie movie) {
        new DeleteTask().execute(movie);
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public void insertFavMovie(FavoriteMovie movie) {
        new InsertFavTask().execute(movie);
    }
    public void deleteFavMovie(FavoriteMovie movie) {
        new DeleteFavTask().execute(movie);
    }

    private static class DeleteFavTask extends AsyncTask<FavoriteMovie, Void,Void>{
        @Override
        protected Void doInBackground(FavoriteMovie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().deleteFavMovie(movies[0]);
            }
            return null;
        }
    }

    private static class InsertFavTask extends AsyncTask<FavoriteMovie, Void,Void>{
        @Override
        protected Void doInBackground(FavoriteMovie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().insertFavMovie(movies[0]);
            }
            return null;
        }
    }

    private static class DeleteTask extends AsyncTask<Movie, Void,Void>{
        @Override
        protected Void doInBackground(Movie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().deleteMovie(movies[0]);
            }
            return null;
        }
    }

    private static class InsertTask extends AsyncTask<Movie, Void,Void>{
        @Override
        protected Void doInBackground(Movie... movies) {
            if (movies != null && movies.length > 0){
                database.movieDao().insertMovie(movies[0]);
            }
            return null;
        }
    }



    private static class DeleteMovieTask extends AsyncTask<Void, Void,Void>{
        @Override
        protected Void doInBackground(Void... integers) {
                 database.movieDao().deleteAllMovies();
            return null;
        }
    }


    private static class GetMovieTask extends AsyncTask<Integer, Void,Movie>{
        @Override
        protected Movie doInBackground(Integer... integers) {
            if (integers !=null && integers.length > 0){
                return database.movieDao().getMovieById(integers[0]);
            }
            return null;
        }
    }

    private static class GetFavMovieTask extends AsyncTask<Integer, Void,FavoriteMovie>{
        @Override
        protected FavoriteMovie doInBackground(Integer... integers) {
            if (integers !=null && integers.length > 0){
                return database.movieDao().getFavMovieById(integers[0]);
            }
            return null;
        }
    }

}
