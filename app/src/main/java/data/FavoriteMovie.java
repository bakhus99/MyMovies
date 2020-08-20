package data;

import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "favoriteMovies")
public class FavoriteMovie extends Movie {
    public FavoriteMovie(int uniqueId,int id, int voteCount, String title, String originalTitle, String overView, String bigPosterPath, String posterPath, String backdropPath, double voteAverage, String releaseDate) {
        super(uniqueId,id, voteCount, title, originalTitle, overView, bigPosterPath, posterPath, backdropPath, voteAverage, releaseDate);
    }
@Ignore
    public  FavoriteMovie(Movie movie){
        super(movie.getUniqueId(),movie.getId(),movie.getVoteCount(),movie.getTitle(),movie.getOriginalTitle(),movie.getOverView(),movie.getBigPosterPath(),movie.getPosterPath(),movie.getBackdropPath(),movie.getVoteAverage(),movie.getReleaseDate());
    }

}
