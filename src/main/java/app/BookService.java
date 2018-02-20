package app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public List<Map<String, Object>> list(int page) {

        return jdbcTemplate.queryForList("SELECT * FROM book_shelf LIMIT 10 OFFSET ?", page*10);
    }

    public int countPages() {
        String sql = "SELECT count(1) FROM book_shelf";
        int allBooks = jdbcTemplate.queryForObject(sql, Integer.class);
        return (int) Math.ceil(allBooks/10.0);
    }

    public List<Map<String, Object>> search(int offset, String title) {
        return jdbcTemplate.queryForList("SELECT * FROM book_shelf WHERE LOWER(title) LIKE ? LIMIT 10 OFFSET ? ", "%" + title.toLowerCase() + "%");
    }

    public void create(String title, String description, String author, String isbn, int print_year) {
        String sql = "INSERT INTO book_shelf (id, title, description," +
                " author, isbn, print_year, read_already) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, null, title, description, author, isbn, print_year, false);
    }

    public void delete(String id) {
        jdbcTemplate.update("DELETE FROM book_shelf WHERE id =?", id);
    }

    public Map get(int id) {
        try {
            String sql = "SELECT * FROM book_shelf WHERE id=?";
            return jdbcTemplate.queryForMap(sql, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void update(int id, String title, String description, String isbn, int print_year) {
        String sql = "UPDATE book_shelf SET title = ?, description = ?, isbn=?, print_year=?, read_already=? WHERE id=?";
        jdbcTemplate.update(sql, title, description, isbn, print_year, false, id); //ask about read_already

    }


}
