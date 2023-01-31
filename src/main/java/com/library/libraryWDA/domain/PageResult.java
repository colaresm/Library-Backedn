package com.library.libraryWDA.domain;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResult<T> {

    private final long pageNumber;
    private final long totalElements;
    private final int totalPages;
    private final int elementsPerPage;
    private final List<T> elements;

    public PageResult(Page<T> page) {
        this.pageNumber = page.getNumber();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.elementsPerPage = page.getNumberOfElements();
        this.elements = page.getContent();
    }
}
