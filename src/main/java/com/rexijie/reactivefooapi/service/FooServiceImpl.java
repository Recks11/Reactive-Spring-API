package com.rexijie.reactivefooapi.service;

import com.rexijie.reactivefooapi.model.Foo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FooServiceImpl implements FooService {

    //Generate new Foo entity every second
    @Override
    public Flux<Foo> emitElementEverySecond() {
        return Flux.fromStream(Stream.generate(() ->
                new Foo(UUID.randomUUID().toString(), "Foo - "+ new Date())
        )).delayElements(Duration.ofSeconds(1));
    }
}
