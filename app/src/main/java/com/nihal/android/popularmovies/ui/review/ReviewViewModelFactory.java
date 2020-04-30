
package com.nihal.android.popularmovies.ui.review;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nihal.android.popularmovies.data.MovieRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link MovieRepository} and the movie ID
 */
public class ReviewViewModelFactory extends ViewModelProvider.NewInstanceFactory  {

    private final MovieRepository mRepository;
    private final int mMovieId;

    public ReviewViewModelFactory(MovieRepository repository, int movieId) {
        this.mRepository = repository;
        this.mMovieId = movieId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ReviewViewModel(mRepository, mMovieId);
    }
}
