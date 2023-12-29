package com.example.reactiveprogramming.services;

import com.example.reactiveprogramming.domain.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {
    public Flux<BookInfo> getBooks(){
        var books = List.of(
                new BookInfo(1, "Book One", "Author One", "123456789"),
                new BookInfo(2, "Book Two", "Author Two", "987654321"),
                new BookInfo(3, "Book Three", "Author Three", "753951852")
        );
        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId){
       var book = new BookInfo(bookId,"Book One", "Author One", "123456789") ;
       return Mono.just(book);
    }
}
