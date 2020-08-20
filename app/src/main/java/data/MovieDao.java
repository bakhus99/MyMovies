package data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM favoriteMovies")
    LiveData<List<FavoriteMovie>> getAllFavMovies();

    @Query("SELECT * FROM movies")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT * FROM movies WHERE id == :movieId")
    Movie getMovieById(int movieId);

    @Query("DELETE FROM movies")
    void deleteAllMovies();

    @Insert
    void insertMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

    @Query("SELECT * FROM favoriteMovies WHERE id == :movieId")
    FavoriteMovie getFavMovieById(int movieId);

    @Insert
    void insertFavMovie(FavoriteMovie movie);

    @Delete
    void deleteFavMovie(FavoriteMovie movie);

}
