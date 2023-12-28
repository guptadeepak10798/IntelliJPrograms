package com.example.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();
    
    @Test
    void firstFlux() {
        Flux<String> stringFlux = fluxAndMonoServices.firstFlux();
        StepVerifier.create(stringFlux)
                .expectNext("Apple","Banana")
                .verifyComplete();
    }

    @Test
    void firstMono() {
        Mono<String> stringMono = fluxAndMonoServices.firstMono();
        StepVerifier.create(stringMono)
                .expectNext("Mango")
                .verifyComplete();

    }

    @Test
    void firstFluxMap() {
        Flux<String> firstFluxMap = fluxAndMonoServices.firstFluxMap();
        StepVerifier.create(firstFluxMap)
                .expectNext("APPLE","BANANA")
                .verifyComplete();
    }

    @Test
    void firstFluxFilter() {
        Flux<String> firstFluxMap = fluxAndMonoServices.firstFluxFilter(5);
        StepVerifier.create(firstFluxMap)
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void firstFluxMapAndFilter() {
        Flux<String> firstFluxMap = fluxAndMonoServices.firstFluxMapAndFilter(5);
        StepVerifier.create(firstFluxMap)
                .expectNext("BANANA")
                .verifyComplete();

    }

    @Test
    void firstFluxFlatMap() {
        Flux<String> firstFluxMap = fluxAndMonoServices.firstFluxFlatMap();
        StepVerifier.create(firstFluxMap)
                .expectNextCount(11)
                .verifyComplete();
    }

    @Test
    void firstFluxFlatMapAsync() {
        Flux<String> firstFluxMap = fluxAndMonoServices.firstFluxFlatMapAsync();
        StepVerifier.create(firstFluxMap)
                .expectNextCount(11)
                .verifyComplete();
    }

    @Test
    void firstMonoFlatMap() {
        Mono<List<String>> listMono =
                fluxAndMonoServices.firstMonoFlatMap();
        StepVerifier.create(listMono)
//                .expectNext(List.of("M", "a", "n", "g", "o"))
                .expectNextCount(1)
                .verifyComplete();


    }

    @Test
    void firstFluxConcatMap() {
//        fluxAndMonoServices.firstFluxConcatMap();
        StepVerifier.create(fluxAndMonoServices.firstFluxConcatMap())
                .expectNextCount(11)
                .verifyComplete();

    }

    @Test
    void firstMonoFlatMapMany() {
//        fluxAndMonoServices.firstMonoFlatMapMany();
        StepVerifier.create(fluxAndMonoServices.firstMonoFlatMapMany())
                .expectNextCount(5)
                .verifyComplete()
                ;
    }

    @Test
    void firstFluxTransform() {

        StepVerifier.create(fluxAndMonoServices.firstFluxTransform(5))
                .expectNext("Banana")
                .verifyComplete();
    }

    @Test
    void firstFluxTransformDefaultIfEmpty() {
        StepVerifier.create(fluxAndMonoServices.firstFluxTransformDefaultIfEmpty(10))
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void firstFluxTransformSwitchIfEmpty() {
        StepVerifier.create(fluxAndMonoServices.firstFluxTransformSwitchIfEmpty(8))
                .expectNext("PineApple","Guava")
                .verifyComplete();
    }

    @Test
    void fluxConcat() {

        Flux<String> stringFlux = fluxAndMonoServices.fluxConcat();
        StepVerifier.create(stringFlux)
                .expectNext("Apple", "Pine Apple","Lemon", "cucumber")
                .verifyComplete()
                ;

    }

    @Test
    void fluxConcatWith() {

        Flux<String> stringFlux = fluxAndMonoServices.fluxConcatWith();

        StepVerifier.create(stringFlux)
                .expectNext("Apple", "Pine Apple","Lemon", "cucumber")
                .verifyComplete()
        ;
    }

    @Test
    void monoConcatWith() {
        Flux<String> stringFlux = fluxAndMonoServices.monoConcatWith();
        StepVerifier.create(stringFlux)
                .expectNext("Apple","Lemon")
                .verifyComplete();
    }

    @Test
    void fluxmerge() {
        Flux<String> fluxmerge = fluxAndMonoServices.fluxmerge();
        StepVerifier.create(fluxmerge)
                .expectNext("Apple","Lemon","Pine Apple", "cucumber")
                .verifyComplete();


    }

    @Test
    void fluxMergeWith() {
        Flux<String> stringFlux = fluxAndMonoServices.fluxMergeWith();
        StepVerifier.create(stringFlux)
                .expectNext("Apple", "Pine Apple","Lemon", "cucumber")
                .verifyComplete()
        ;
    }

    @Test
    void fluxMergeSequential() {
        Flux<String> stringFlux = fluxAndMonoServices.fluxMergeSequential();
        StepVerifier.create(stringFlux)
                .expectNext("Apple", "Pine Apple","Lemon", "cucumber")
                .verifyComplete()
        ;

    }

    @Test
    void fluxZip() {

        Flux<String> stringFlux = fluxAndMonoServices.fluxZip();
        StepVerifier.create(stringFlux)
                .expectNext("AppleLemon","Pine Applecucumber")
                .verifyComplete();

    }
}