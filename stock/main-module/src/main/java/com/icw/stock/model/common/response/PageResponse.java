package com.icw.stock.model.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class PageResponse<T> {

    @JsonProperty("total")
    private long totalCount = 0;                        // 전체 개수
    @JsonProperty("size")
    private long returnCount = 0;                       // 리턴 개수
    @JsonProperty("page")
    private long returnPage = 0;                        // 리턴 페이지

    private List<T> contents = new ArrayList<>();       // 내용

    public PageResponse() {
        super();
    }

    public PageResponse(List<T> contents, long size, int page) {
        super();
        this.returnPage = page;
        this.contents = contents;
        this.returnCount = size;
        this.totalCount = this.contents.size();
    }

}
