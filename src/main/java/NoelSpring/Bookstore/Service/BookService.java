package NoelSpring.Bookstore.Service;

import NoelSpring.Bookstore.Entity.Book;
import NoelSpring.Bookstore.Repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book getBookById(long id);
    Book updateBook(Book book);
    void deleteBookById(long id);
    Page<Book> findPaginated(int pageNo, int pageSize);

}
