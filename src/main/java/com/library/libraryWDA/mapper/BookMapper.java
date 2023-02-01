package com.library.libraryWDA.mapper;

import com.library.libraryWDA.dto.book.BookCreateRequest;
import com.library.libraryWDA.dto.book.BookListItemResponse;
import com.library.libraryWDA.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toModel(BookCreateRequest bookCreateRequest);

    BookListItemResponse toBookListItemResponse (Book book);
}
