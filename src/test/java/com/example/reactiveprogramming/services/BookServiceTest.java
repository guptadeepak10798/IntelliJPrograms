package com.example.reactiveprogramming.services;

import com.example.reactiveprogramming.domain.Book;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookInfoService bookInfoService
            = new BookInfoService();

    private ReviewService reviewService
            = new ReviewService();

    private BookService bookService
            = new BookService(bookInfoService,reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();
        StepVerifier.create(books)
//                .expectNextCount(3)
                .assertNext(book -> {
                    assertEquals("Book One",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Two",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());
                })
                .assertNext(book -> {
                    assertEquals("Book Three",book.getBookInfo().getTitle());
                    assertEquals(2,book.getReviews().size());
                })
                .verifyComplete();

    }
}