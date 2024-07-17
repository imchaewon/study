package com.example.webflux.context;

import com.example.webflux.utils.Logger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Ex9 {
    public static final String HEADER_NAME_AUTH_TOKEN = "authToken";

    public static void main(String[] args) {
        Book book = new Book("abcd-1111-3333-2809", "Reactor's Bible", "Kevin");
        Mono<String> mono = postBook(Mono.just(book))
                .contextWrite(Context.of(HEADER_NAME_AUTH_TOKEN, "eyJhbGjE5sdAfj.Vs6Kv4"));

        mono.subscribe(Logger::onNext);
    }

    private static Mono<String> postBook(Mono<Book> book) {
        return book.zipWith(Mono.deferContextual(contextView -> Mono.just(contextView.get(HEADER_NAME_AUTH_TOKEN))))
                .flatMap(e -> Mono.just(e)) // 실제로는 여기서 외부 API 서버로 HTTP POST request를 전송한다고 가정
                .flatMap(e ->
                        Mono.just(String.format("POST the book(%s, %s) with token: %s",
                                e.getT1().getBookName(),
                                e.getT1().getAuthor(),
                                e.getT2())));
    }

    @Getter
    @AllArgsConstructor
    static class Book{
        private String isbn;
        private String bookName;
        private String author;
    }
}