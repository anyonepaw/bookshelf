package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    public List<Map<String, Object>> list(){
        return jdbcTemplate.queryForList("SELECT * FROM book_shelf");
    }

    public void create(String title, String description, String author, String isbn, int print_year){
        String sql = "INSERT INTO book_shelf (id, title, description," +
                " author, isbn, print_year, read_already) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, null, title, description, author, isbn, print_year, false);
    }
}
