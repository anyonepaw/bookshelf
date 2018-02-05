package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/bookshelf")
    public String show(Model model) {
        model.addAttribute("bookShelf", bookService.list());
        return "bookshelf";
    }


    @GetMapping("/bookshelf/create")
    public String create() {
        return "create";
    }


    @PostMapping("/bookshelf/create")
    public String create(HttpServletRequest httpServletRequest) {
        String title = httpServletRequest.getParameter("Title");
        String description = httpServletRequest.getParameter("Description");
        String author = httpServletRequest.getParameter("Author");
        String isbn = httpServletRequest.getParameter("ISBN");
        String year = httpServletRequest.getParameter("PrintYear");
        bookService.create(title, description, author, isbn, Integer.parseInt(year));
        return "redirect:/bookshelf";
    }

    @DeleteMapping("/bookshelf/{id}")
    public void delete(@PathVariable("id") int id) {
        System.out.println("hello!");
        bookService.delete(id);

    }

    @GetMapping("/bookshelf/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
       model.addAttribute("book_with_that_id", )
        return "edit";
    }

    @PutMapping("/bookshelf/{id}/edit")
    public String edit(){

    }



}
