package NoelSpring.Bookstore.Service;

import NoelSpring.Bookstore.Entity.Book;
import NoelSpring.Bookstore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public Book updateBook(Book book) {
       return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(long id) {
         bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return bookRepository.findAll(pageable);
    }

}
