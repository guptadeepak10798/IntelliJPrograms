package com.example.reactiveprogramming.services;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotAndColdStreamsTest {

    @Test
    public void coldStreamTest() {
        Flux<Integer> numbers = Flux.range(1, 10);
        numbers.subscribe(integer -> {
            System.out.println("subscriber1 = " + integer);
        });
        numbers.subscribe(integer -> {
            System.out.println("subscriber2 = " + integer);
        });


    }

    @Test
    @SneakyThrows
    public void hotStreamTest() {
        Flux<Integer> numbers = Flux.range(1, 10)
                .delayElements(Duration.ofMillis(1000));
        ConnectableFlux<Integer> publisher = numbers.publish();
        publisher.connect();

        publisher.subscribe(integer ->
                System.out.println("subscriber1 = " + integer)
        );


        Thread.sleep(40000);


        publisher.subscribe(integer ->
                System.out.println("subscriber2 = " + integer)
        );

        Thread.sleep(10000);
    }
}
