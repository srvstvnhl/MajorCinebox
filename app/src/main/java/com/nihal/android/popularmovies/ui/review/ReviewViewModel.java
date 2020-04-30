

package com.nihal.android.popularmovies.ui.review;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.nihal.android.popularmovies.data.MovieRepository;
import com.nihal.android.popularmovies.model.ReviewResponse;

/**
 * {@link ViewModel} for ReviewFragment
 */
public class ReviewViewModel extends ViewModel {
    private final MovieRepository mRepository;
    private final LiveData<ReviewResponse> mReviewResponse;

    public ReviewViewModel (MovieRepository repository, int movieId) {
        mRepository = repository;
        mReviewResponse = mRepository.getReviewResponse(movieId);
    }

    public LiveData<ReviewResponse> getReviewResponse() {
        return mReviewResponse;
    }
}
