package com.icw.stock.utils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Operation
@ApiResponses({
		@ApiResponse(responseCode = "200", description = "OK"),
		@ApiResponse(responseCode = "204", description = "NO CONTENT", content = @Content(
				mediaType = "application/json",
				examples = @ExampleObject(value = "{'error': '204 NO CONTENT', 'message': '결과가 존재하지 않습니다.'}")
		)),
		@ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(
				mediaType = "application/json",
				examples = @ExampleObject(value = "{'error': '400 Bad Request', 'message': '잘못된 요청입니다.'}")
		)),
		@ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(
				mediaType = "application/json",
				examples = @ExampleObject(value = "{'error': '404 Not Found', 'message': '요청한 리소스를 찾을 수 없습니다.'}")
		)),
		@ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR", content = @Content(
				mediaType = "application/json",
				examples = @ExampleObject(value = "{'error': '500 Internal Server Error', 'message': '서버에서 오류가 발생했습니다.'}")
		))
})
public @interface SwaggerDefault {
	@AliasFor(annotation = Operation.class, attribute = "summary")
	String title();

	@AliasFor(annotation = Operation.class, attribute = "description")
	String summary() default "";
}

