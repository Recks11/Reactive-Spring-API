package com.rexijie.reactivefooapi.service;

import com.rexijie.reactivefooapi.model.Foo;
import reactor.core.publisher.Flux;

public interface FooService {
    Flux<Foo> emitElementEverySecond();
}
