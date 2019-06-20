package info.einverne.spring.swift;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Book")
public final class Book
{
    public Book() {
    }

    private String isbn;

    @ThriftField(value=1, name="isbn", requiredness=Requiredness.NONE)
    public String getIsbn() { return isbn; }

    @ThriftField
    public void setIsbn(final String isbn) { this.isbn = isbn; }

    private String title;

    @ThriftField(value=2, name="title", requiredness=Requiredness.NONE)
    public String getTitle() { return title; }

    @ThriftField
    public void setTitle(final String title) { this.title = title; }

    private String author;

    @ThriftField(value=3, name="author", requiredness=Requiredness.NONE)
    public String getAuthor() { return author; }

    @ThriftField
    public void setAuthor(final String author) { this.author = author; }

    private int page;

    @ThriftField(value=4, name="page", requiredness=Requiredness.NONE)
    public int getPage() { return page; }

    @ThriftField
    public void setPage(final int page) { this.page = page; }

    private List<String> keyword;

    @ThriftField(value=5, name="keyword", requiredness=Requiredness.NONE)
    public List<String> getKeyword() { return keyword; }

    @ThriftField
    public void setKeyword(final List<String> keyword) { this.keyword = keyword; }

    private BookType bookType;

    @ThriftField(value=6, name="bookType", requiredness=Requiredness.NONE)
    public BookType getBookType() { return bookType; }

    @ThriftField
    public void setBookType(final BookType bookType) { this.bookType = bookType; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("isbn", isbn)
            .add("title", title)
            .add("author", author)
            .add("page", page)
            .add("keyword", keyword)
            .add("bookType", bookType)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book other = (Book)o;

        return
            Objects.equals(isbn, other.isbn) &&
            Objects.equals(title, other.title) &&
            Objects.equals(author, other.author) &&
            Objects.equals(page, other.page) &&
            Objects.equals(keyword, other.keyword) &&
            Objects.equals(bookType, other.bookType);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            isbn,
            title,
            author,
            page,
            keyword,
            bookType
        });
    }
}
