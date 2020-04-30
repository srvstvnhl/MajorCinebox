
package com.nihal.android.popularmovies.data;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.nihal.android.popularmovies.model.Movie;

/**
 * The MovieDataSourceFactory is responsible for creating a DataSource.
 */
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Movie> {

    private MutableLiveData<MovieDataSource> mPostLiveData;
    private MovieDataSource mMovieDataSource;
    private String mSortBy;

    public MovieDataSourceFactory(String sortBy) {
        mPostLiveData = new MutableLiveData<>();
        mSortBy = sortBy;
    }

    @Override
    public DataSource<Integer, Movie> create() {
        mMovieDataSource = new MovieDataSource(mSortBy);

        // Keep reference to the data source with a MutableLiveData reference
        mPostLiveData = new MutableLiveData<>();
        mPostLiveData.postValue(mMovieDataSource);

        return mMovieDataSource;
    }

    public MutableLiveData<MovieDataSource> getPostLiveData() {
        return mPostLiveData;
    }
}
