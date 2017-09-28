namespace java.swift info.einverne.spring.swift
namespace java info.einverne.spring.swift

enum BookType {
    BOOK = 0,
    NEWS_PAPER = 1
}

struct Book {
    1: string isbn;
    2: string title;
    3: string author;
    4: i32 page;
    5: list<string> keyword;
    6: BookType bookType;
}

service BookService {
    void ping();
    void createBook(1: Book book);
}