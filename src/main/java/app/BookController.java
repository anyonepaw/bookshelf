package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/bookshelf")
    public String show(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        model.addAttribute("bookShelf", bookService.list(page));
        model.addAttribute("allPages", bookService.countPages());
        model.addAttribute("page", page);
        return "bookshelf";
    }


    @GetMapping("/bookshelf/create")
    public String create(Model model) {
        Book book = new Book(null,"world","hello","pushkin","titlelo",2007, false);
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
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/bookshelf/update/{id}")
    public String update(@PathVariable("id") Long id, Model model, HttpServletResponse resp) {
      Book book = bookService.get(id);
      if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("actionUrl", "/bookshelf/update/" + id);
            return "update";
        }
        resp.setStatus(404);
        return null;
    }

    @PostMapping("/bookshelf/update/{id}")
    public String update(HttpServletRequest httpServletRequest, @PathVariable("id") Long id) {

        String title = httpServletRequest.getParameter("Title");
        String description = httpServletRequest.getParameter("Description");
        String isbn = httpServletRequest.getParameter("ISBN");
        String year = httpServletRequest.getParameter("PrintYear");

        bookService.update(id, title, description, isbn, Integer.parseInt(year));
        return "redirect:/bookshelf";
    }


    @GetMapping("/bookshelf/search")
    public String search(HttpServletRequest httpServletRequest, Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page) {
        String title = httpServletRequest.getParameter("Query");
        model.addAttribute("bookShelf", bookService.search(page, title));
        model.addAttribute("allPages", bookService.countPages(title));
        model.addAttribute("page", page);
        model.addAttribute("query", title);
        return "bookshelf";
    }


}
