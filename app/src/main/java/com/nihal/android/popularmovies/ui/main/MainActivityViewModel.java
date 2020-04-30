
package com.nihal.android.popularmovies.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.nihal.android.popularmovies.data.MovieDataSourceFactory;
import com.nihal.android.popularmovies.data.MovieEntry;
import com.nihal.android.popularmovies.data.MovieRepository;
import com.nihal.android.popularmovies.model.Movie;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.nihal.android.popularmovies.utilities.Constant.INITIAL_LOAD_SIZE_HINT;
import static com.nihal.android.popularmovies.utilities.Constant.NUMBER_OF_FIXED_THREADS_FIVE;
import static com.nihal.android.popularmovies.utilities.Constant.PAGE_SIZE;
import static com.nihal.android.popularmovies.utilities.Constant.PREFETCH_DISTANCE;

/**
 * {@link ViewModel} for MainActivity
 */
public class MainActivityViewModel extends ViewModel {

    private final MovieRepository mRepository;

    private LiveData<PagedList<Movie>> mMoviePagedList;
    private LiveData<List<MovieEntry>> mFavoriteMovies;
    private String mSortCriteria;

    public MainActivityViewModel(MovieRepository repository, String sortCriteria) {
        mRepository = repository;
        mSortCriteria = sortCriteria;
        init(sortCriteria);
    }

    /**
     * Initialize the paged list
     */
    private void init(String sortCriteria) {
        Executor executor = Executors.newFixedThreadPool(NUMBER_OF_FIXED_THREADS_FIVE);

        // Create a MovieDataSourceFactory providing DataSource generations
        MovieDataSourceFactory movieDataFactory = new MovieDataSourceFactory(sortCriteria);

        // Configures how a PagedList loads content from the MovieDataSource
        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                // Size hint for initial load of PagedList
                .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
                // Size of each page loaded by the PagedList
                .setPageSize(PAGE_SIZE)
                // Prefetch distance which defines how far ahead to load
                .setPrefetchDistance(PREFETCH_DISTANCE)
                .build();

        // The LivePagedListBuilder class is used to get a LiveData object of type PagedList
        mMoviePagedList = new LivePagedListBuilder<>(movieDataFactory, config)
                .setFetchExecutor(executor)
                .build();
    }

    /**
     * Returns LiveData of PagedList of movie
     */
    public LiveData<PagedList<Movie>> getMoviePagedList() {
        return mMoviePagedList;
    }

    /**
     * Set the LiveData of PagedList of movie to clear the old list and reload
     *
     * @param sortCriteria The sort order of the movies by popular, top rated, now playing,
     *                     upcoming, and favorites
     */
    public void setMoviePagedList(String sortCriteria) {
        init(sortCriteria);
    }

    /**
     * Returns LiveData of the List of MovieEntries
     */
    public LiveData<List<MovieEntry>> getFavoriteMovies() {
        return mFavoriteMovies;
    }

    /**
     *  Set a new value for the list of MovieEntries
     */
    public void setFavoriteMovies() {
        mFavoriteMovies = mRepository.getFavoriteMovies();
    }
}
