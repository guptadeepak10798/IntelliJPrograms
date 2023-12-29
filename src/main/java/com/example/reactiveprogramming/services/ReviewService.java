package com.example.reactiveprogramming.services;

import com.example.reactiveprogramming.domain.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {
    public Flux<Review> getReviews(long bookId)
    {

        List<Review> reviews = List.of(
                new Review(1, bookId, 5.0, "Good Book"),
                new Review(2, bookId, 4.5, "Avg Book")
        );
        return Flux.fromIterable(reviews);
    }
}
