package app;


import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.util.HashMap;


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
    public String create(Model model) {
        HashMap<Object, Object> book = new HashMap<>();
        book.put("title", "world");
        book.put("description", "hello");
        book.put("author", "pushkin");
        book.put("isbn", "titlelo");
        book.put("print_year", "2007");

        model.addAttribute("book", book);
        model.addAttribute("actionUrl", "/bookshelf/create");
        return "update";
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

    @GetMapping("/bookshelf/update/{id}")
    public String update(@PathVariable("id") int id, Model model, HttpServletResponse resp) {
        if (bookService.get(id) != null) {
            model.addAttribute("book", bookService.get(id));
            model.addAttribute("actionUrl", "/bookshelf/update/" + id);
            return "update";
        }
        resp.setStatus(404);
        return null;
    }

    @PostMapping("/bookshelf/update/{id}")
    public String update(HttpServletRequest httpServletRequest, @ModelAttribute("member") Member member) {
        String title = httpServletRequest.getParameter("Title");
        String description = httpServletRequest.getParameter("Description");
        String author = httpServletRequest.getParameter("Author");
        String isbn = httpServletRequest.getParameter("ISBN");
        String year = httpServletRequest.getParameter("PrintYear");

        return "redirect:/bookshelf";
    }


}
