package NoelSpring.Bookstore.Controller;


import NoelSpring.Bookstore.Entity.Book;
import NoelSpring.Bookstore.Service.BookServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/bookstore")
    public String showBooks(Model model){
       showPaginated(1,model);
        return "books";
    }

    @GetMapping("/home")
    public String getHome(){
        return "home";
    }

    @GetMapping("/bookstore_user")
    public String showBooksToUser(Model model){
        showPaginatedUser(1,model);
        return "books_user";
    }

    @GetMapping("/login")
    public String handleLogin(){
        return "login";
    }

    @GetMapping("/bookstore/add")
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("book",book);

        return "add_book";
    }
    @PostMapping("/bookstore")
    public String saveBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult){
         if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form with error messages
            return "add_book";
        }
        bookService.saveBook(book);
        return "redirect:/bookstore";
    }

    @GetMapping("/bookstore/edit/{id}")
    public String editBook(@PathVariable long id,Model model){

        model.addAttribute("book",bookService.getBookById(id));
        return "edit_book";
    }
    @PostMapping("/bookstore/{id}")
    public String updateBook(@Valid @PathVariable long id, @ModelAttribute Book book, BindingResult bindingResult, Model model){

         if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form with error messages
            return "edit_book";
        }

        Book existingBook = bookService.getBookById(id);

        existingBook.setId(book.getId());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setTitle(book.getTitle());
        existingBook.setGenre(book.getGenre());
        existingBook.setPrice(book.getPrice());

        bookService.updateBook(existingBook);

        return "redirect:/bookstore";
    }

    @GetMapping("bookstore/delete/{id}")
    public String deleteBook(@PathVariable long id){
        bookService.deleteBookById(id);
        return  "redirect:/bookstore";
    }

    @GetMapping("bookstore/{pageNo}")
    public String showPaginated(@PathVariable(value = "pageNo") int pageNo,Model model){
        int pageSize = 6;
        Page<Book> page = bookService.findPaginated(pageNo,pageSize);
        List<Book> listBooks = page.getContent();

        model.addAttribute("totalBooks",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("listBooks",listBooks);
        model.addAttribute("currentPage",pageNo);

        return "books";
    }

    //for bookstore_USER

    @GetMapping("bookstore_user/{pageNo}")
    public String showPaginatedUser(@PathVariable(value = "pageNo") int pageNo,Model model){
        int pageSize = 6;
        Page<Book> page = bookService.findPaginated(pageNo,pageSize);
        List<Book> listBooks = page.getContent();

        model.addAttribute("totalBooks",page.getTotalElements());
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("listBooks",listBooks);
        model.addAttribute("currentPage",pageNo);

        return "books_user";
    }



}
