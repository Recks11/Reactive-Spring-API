package com.rexijie.reactivefooapi.api.v1;

import com.rexijie.reactivefooapi.model.Foo;
import com.rexijie.reactivefooapi.service.FooService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AppRouter {

    private FooService fooService;

    //Autowire foo service
    public AppRouter(FooService fooService) {
        this.fooService = fooService;
    }

    @Bean
    public RouterFunction<ServerResponse> appRoutes() {
        return nest(path("/foo"),
                route(GET("/stream"), req -> this.respondEverySecond()));
    }

    // Could be put in a handler class
    public Mono<ServerResponse> respondEverySecond() {
        return ServerResponse
                .status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(fooService.emitElementEverySecond(), Foo.class);
    }
}
