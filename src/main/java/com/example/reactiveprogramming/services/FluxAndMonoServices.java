package com.example.reactiveprogramming.services;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {
    public Flux<String> firstFlux(){

        return Flux.fromIterable(List.of("Apple","Banana")).log();
    }
    public Flux<String> firstFluxMap(){

        return Flux.fromIterable(List.of("Apple","Banana"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> firstFluxFilter(int number){

        return Flux.fromIterable(List.of("Apple","Banana"))
                .filter(s -> s.length()>number)
                .log();
    }

    public Flux<String> firstFluxMapAndFilter(int number){

        return Flux.fromIterable(List.of("Apple","Banana"))
                .filter(s -> s.length()>number)
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> firstFluxFlatMap(){

        return Flux.fromIterable(List.of("Apple","Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> firstFluxFlatMapAsync(){

        return Flux.fromIterable(List.of("Apple","Banana"))
                .flatMap(s -> Flux.just(s.split("")))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                ))
                .log();
    }

    public Flux<String> firstFluxConcatMap(){

        return Flux.fromIterable(List.of("Apple","Banana"))
                .concatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> firstFluxTransform(int number){
        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length()>number);

        return Flux.fromIterable(List.of("Apple","Banana"))
//                .filter(s -> s.length()>number)
                .transform(filterData)
                .log();
    }

    public Flux<String> firstFluxTransformDefaultIfEmpty(int number){
        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length()>number);

        return Flux.fromIterable(List.of("Apple","Banana"))
//                .filter(s -> s.length()>number)
                .transform(filterData)
                .defaultIfEmpty("Default")
                .log();
    }

    public Flux<String> firstFluxTransformSwitchIfEmpty(int number){
        Function<Flux<String>,Flux<String>> filterData =
                data -> data.filter(s -> s.length()>number);

        return Flux.fromIterable(List.of("Apple","Banana"))
//                .filter(s -> s.length()>number)
                .transform(filterData)
                .switchIfEmpty(Flux.just("PineApple","Guava"))
//                .transform(filterData)
                .log();
    }

    public Flux<String> fluxConcat(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple");
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber");
        return Flux.concat(fuitsFlux,veggiesFlux).log();
    }

    public Flux<String> fluxConcatWith(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple");
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber");
        return fuitsFlux.concatWith(veggiesFlux).log();
    }
    public Flux<String> fluxmerge(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple").delayElements(Duration.ofMillis(50));
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber").delayElements(Duration.ofMillis(75));
        return Flux.merge(fuitsFlux,veggiesFlux).log();
    }
    public Flux<String> fluxMergeWith(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple");
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber");
        return fuitsFlux.mergeWith(veggiesFlux).log();
    }

    public Flux<String> fluxMergeSequential(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple").delayElements(Duration.ofMillis(50));
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber").delayElements(Duration.ofMillis(75));
        return Flux.mergeSequential(fuitsFlux,veggiesFlux).log();
    }

    public Flux<String> fluxZip(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple");
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber");
        return Flux.zip(fuitsFlux,veggiesFlux,
                (s, s2) -> s+s2).log();
    }

    public Flux<String> fluxZipWith(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple");
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber");
        return fuitsFlux.zipWith(veggiesFlux, (s, s2) -> s + s2).log();
    }

    public Flux<String> fluxZipTuple(){
        Flux<String> fuitsFlux = Flux.just("Apple", "Pine Apple");
        Flux<String> veggiesFlux = Flux.just("Lemon", "cucumber");
        Flux<String> moreVeggiesFlux = Flux.just("Potato", "Tomato");
        return Flux.zip(fuitsFlux,veggiesFlux,moreVeggiesFlux)
                .map(objects -> objects.getT1()+objects.getT2()+objects.getT3())
                .log();
    }

    public Mono<String> firstMono(){
        return Mono.just("Mango").log();
    }

    public Flux<String> monoConcatWith(){
        Mono<String> fruitsFlux = Mono.just("Apple");
        Mono<String> veggiesFlux = Mono.just("Lemon");
        return fruitsFlux.concatWith(veggiesFlux).log();

    }

    public Mono<List<String>> firstMonoFlatMap(){
        return Mono.just("Mango")
                .flatMap(s -> Mono.just(List.of(s.split(""))))
                .log();
    }

    public Flux<String> firstMonoFlatMapMany(){
        return Mono.just("Mango")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Mono<String> monoZipWith(){
        Mono<String> fuitsFlux = Mono.just("Apple");
        Mono<String> veggiesFlux = Mono.just("Lemon");
        return fuitsFlux.zipWith(veggiesFlux, (s, s2) -> s + s2).log();
    }

    public Flux<String> firstFluxFilterDoOn(int number){

        return Flux.fromIterable(List.of("Apple","Banana"))
                .filter(s -> s.length()>number)
                .doOnNext(s -> System.out.println("s -> "+s))
                .doOnSubscribe(subscription -> System.out.println("subscription.toString() ->"+subscription.toString()))
                .doOnComplete(() -> System.out.println("Completed !! "))
                .log();
    }

    public Flux<String> fruitsFluxOnErrorReturn(){
        return Flux.just("Apple","Mango")
                .concatWith(Flux.error(() -> new RuntimeException("Exception Occured !!")))
                .onErrorReturn("Orange")
                .log();
    }


    public Flux<String> fruitsFluxOnErrorContinue(){
        return Flux.just("Apple","Mango","Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango")) {
                        throw new RuntimeException("Exception generated") ;

                    }else{
                        return s.toUpperCase();
                    }
                })
                .onErrorContinue((throwable, o) ->  {
                    System.out.println("Error due to => "+throwable);
                    System.out.println("Object => "+o);
                })
                .log();
    }

    public Flux<String> fruitsFluxOnErrorMap(){
        return Flux.just("Apple","Mango","Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango")) {
                        throw new RuntimeException("Exception generated") ;

                    }else{
                        return s.toUpperCase();
                    }
                })
                .onErrorMap((throwable) ->  {
                    System.out.println("Error due to => "+throwable);

                    return new IllegalStateException("From onError Map");
                })
                .log();
    }

    public Flux<String> fruitsFluxDoOnError(){
        return Flux.just("Apple","Mango","Orange")
                .map(s -> {
                    if (s.equalsIgnoreCase("Mango")) {
                        throw new RuntimeException("Exception generated") ;

                    }else{
                        return s.toUpperCase();
                    }
                })
                .doOnError(
                        (throwable) ->  {
                            System.out.println("Error due to => "+throwable);
                        }
                        )
                .log();
    }

    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices= new FluxAndMonoServices();
//         fluxAndMonoServices.firstFlux().subscribe(s -> {
//            System.out.println("s -> "+s);
//        });
//
//         fluxAndMonoServices.firstMono().subscribe(s -> {
//             System.out.println("Mono s -> "+s);
//         });
//
//         fluxAndMonoServices.firstFluxFlatMap().subscribe(s -> {
//             System.out.println("Flatmap -> "+s);
//         });

        fluxAndMonoServices.fluxZipTuple().subscribe(s -> {
            System.out.println("Mono Flatmap -> "+s);
        });
    }
}
