package com.example.reactiveprogramming.services;

import com.example.reactiveprogramming.domain.Book;
import com.example.reactiveprogramming.domain.BookInfo;
import com.example.reactiveprogramming.domain.Review;
import com.example.reactiveprogramming.exception.BookException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
import java.util.List;

@Slf4j
public class BookService {

    private  BookInfoService bookInfoService;
    private  ReviewService reviewService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    public Flux<Book> getBooks() {
        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo,review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is :" + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .log();

    }

    public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo,review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is :" + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retry(3)
                .log();

    }
    public Flux<Book> getBooksRetryWhen() {

        RetryBackoffSpec retryBackoffSpec
                = Retry.backoff(3, Duration.ofMillis(1000))
                .onRetryExhaustedThrow((retryBackoffSpec1, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure()));

//        RetryBackoffSpec retryBackoffSpec1
//                = getRetryBackoffSpec1();

        var allBooks = bookInfoService.getBooks();
        return allBooks
                .flatMap(bookInfo -> {
                    Mono<List<Review>> reviews =
                            reviewService.getReviews(bookInfo.getBookId()).collectList();
                    return reviews
                            .map(review -> new Book(bookInfo,review));
                })
                .onErrorMap(throwable -> {
                    log.error("Exception is :" + throwable);
                    return new BookException("Exception occurred while fetching Books");
                })
                .retryWhen(getRetryBackoffSpec1())
                .log();

    }

    private static RetryBackoffSpec getRetryBackoffSpec1() {
        return Retry.backoff(3, Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((retryBackoffSpec2, retrySignal) ->
                        Exceptions.propagate(retrySignal.failure()));
    }

    public Mono<Book> getBookById(long bookId){

        Mono<BookInfo> bookById = bookInfoService.getBookById(bookId);
        Mono<List<Review>> reviews = reviewService.getReviews(bookId).collectList();

        return bookById.zipWith(reviews,(b,r)->new Book(b,r)).log();
    }

}
