package com.baha.mymovies.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baha.mymovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import data.Movie;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private OnPosterClickListener onPosterClickListener;
    private OnReachOnEndListener onReachOnEndListener;

    public MovieAdapter() {
        movies = new ArrayList<>();
    }


    public  interface OnPosterClickListener {
        void onPosterClick(int position);
    }

    public void setOnReachOnEndListener(OnReachOnEndListener onReachOnEndListener) {
        this.onReachOnEndListener = onReachOnEndListener;
    }

    public interface OnReachOnEndListener {
        void onReachEnd();
    }

    public void setOnPosterClickListener(OnPosterClickListener onPosterClickListener) {
        this.onPosterClickListener = onPosterClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder moviesViewHolder, int i) {
        if (movies.size()>=20 &&  i > movies.size() - 4 && onReachOnEndListener != null) {
            onReachOnEndListener.onReachEnd();
        }
        Movie movie = movies.get(i);
        Picasso.get().load(movie.getPosterPath()).into(moviesViewHolder.imageViewSmallPoster);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewSmallPoster;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewSmallPoster = itemView.findViewById(R.id.imageViewSmallPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPosterClickListener != null) {
                        onPosterClickListener.onPosterClick(getAdapterPosition());
                    }

                }
            });
        }
    }

    public void clear(){
        this.movies.clear();
        notifyDataSetChanged();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void addMovies(List<Movie> movies) {
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
