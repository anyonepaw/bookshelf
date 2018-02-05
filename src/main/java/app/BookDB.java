package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDB implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final BookService bookService;

    @Autowired
    public BookDB(JdbcTemplate jdbcTemplate, BookService bookService) {
        this.jdbcTemplate = jdbcTemplate;
        this.bookService = bookService;
    }

    /*
    * Данные, которые должны быть в таблице:
    • id – идентификатор книги в БД;
    • title – название книги. Можно использовать тип VARCHAR(100);
    • description – краткое описание о чем книга. Можно использовать тип VARCHAR(255);
    • author – фамилия и имя автора. Можно использовать тип VARCHAR(100);
    • isbn – ISBN книги. Можно использовать тип VARCHAR(20);
    • printYear – в каком году напечатана книга (INT);
    • readAlready – читал ли кто-то эту книгу. Это булево поле.
    * */

    @Override
    public void run(String... args) throws Exception {
        createShelf();
    }

    private void createShelf() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS book_shelf");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS book_shelf (" +
                "id INTEGER AUTO_INCREMENT NOT NULL, " +
                "title VARCHAR(100) NOT NULL," +
                "description VARCHAR(255) NOT NULL," +
                "author VARCHAR(100) NOT NULL," +
                "isbn VARCHAR(20) NOT NULL, " +
                "print_year INTEGER NOT NULL ," +
                "read_already BOOLEAN NOT NULL" +
                ")");

        bookService.create("fairyTales", "6 fairytales from great Russian writer", "Pushkin A.",
                "4566856788", 2013);
        bookService.create("Gone_with_the_wind", "6 fairytales from great Russian writer", "Pushkin A.",
                "4566856788", 2013);

    }


}
