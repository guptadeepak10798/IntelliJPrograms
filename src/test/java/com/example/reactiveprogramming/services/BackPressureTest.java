package com.example.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

public class BackPressureTest {

    @Test
    public void testBackPressure()
    {

        Flux<Integer> numbers = Flux.range(1, 100).log();
//        numbers.subscribe(integer -> System.out.println("integer =>"+integer));
        numbers.subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("value = " + value);
                if (value == 3) {
                    cancel();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("completed !! ");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });
    }

    @Test
    public void testBackPressureDrop()
    {

        Flux<Integer> numbers = Flux.range(1, 100).log();
//        numbers.subscribe(integer -> System.out.println("integer =>"+integer));
        numbers
                .onBackpressureDrop(integer -> {
                    System.out.println("integer = " + integer);
                })
                .subscribe(new BaseSubscriber<Integer>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(3);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("value = " + value);
                if (value == 3) {
                    hookOnCancel();
                }
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("completed !! ");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookOnCancel() {
                super.hookOnCancel();
            }
        });
    }

    @Test
    public void testBackPressureBuffer()
    {

        Flux<Integer> numbers = Flux.range(1, 100).log();
//        numbers.subscribe(integer -> System.out.println("integer =>"+integer));
        numbers
                .onBackpressureBuffer(10,
                        integer -> {
                            System.out.println("Buffered Value = " + integer);
                        })
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("value = " + value);
                        if (value == 3) {
                            hookOnCancel();
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("completed !! ");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        super.hookOnError(throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }

    @Test
    public void testBackPressureError()
    {

        Flux<Integer> numbers = Flux.range(1, 100).log();
//        numbers.subscribe(integer -> System.out.println("integer =>"+integer));
        numbers
                .onBackpressureError()
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(3);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("value = " + value);
                        if (value == 3) {
                            hookOnCancel();
                        }
                    }

                    @Override
                    protected void hookOnComplete() {
                        System.out.println("completed !! ");
                    }

                    @Override
                    protected void hookOnError(Throwable throwable) {
                        System.out.println("throwable = " + throwable);
                    }

                    @Override
                    protected void hookOnCancel() {
                        super.hookOnCancel();
                    }
                });
    }
}
