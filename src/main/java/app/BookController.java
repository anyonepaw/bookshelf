package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        model.addAttribute("bookshelf", bookService.list());
        return "bookshelf";
    }

    @PostMapping("/bookshelf/create")
    public void create(HttpServletRequest httpServletRequest) {
        String title = httpServletRequest.getParameter("title");
        String description = httpServletRequest.getParameter("description");
        String author = httpServletRequest.getParameter("author");
        String isbn = httpServletRequest.getParameter("isbn");
        String year = httpServletRequest.getParameter("year");
        System.out.println(title);
    }



}
