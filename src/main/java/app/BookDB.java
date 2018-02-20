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

        bookService.create("Письмо к женщине", "Письмо к женщине", "Есенин С.А.", "4566856788", 2013);
        bookService.create("Золотой теленок", "Золотой теленок", "Ильф И., Петров Е.", "4566856788", 2013);
        bookService.create("Путями Каина", "Путями Каина", "Волошин М.А.", "4566856788", 2013);
        bookService.create("Золотой теленок", "Золотой теленок", "Ильф И., Петров Е.", "4566856788", 2013);
        bookService.create("Работа актера над собой", "Работа актера над собой", "Станиславский К.С.", "4566856788", 2013);
        bookService.create("Кристин, дочь Лавранса. Хозяйка", "Кристин, дочь Лавранса. Хозяйка", "Унсет С.", "4566856788", 2013);
        bookService.create("Образы Италии", "Образы Италии", "Муратов П.П.", "4566856788", 2013);
        bookService.create("Всеобщая история, обработанная 'Сатириконом'", "Всеобщая история, обработанная 'Сатириконом'", "Аверченко А.Т.", "4566856788", 2013);
        bookService.create("Так говорил Заратустра", "Так говорил Заратустра", "Ницше Ф.", "4566856788", 2013);
        bookService.create("Моя жизнь в искусстве", "Моя жизнь в искусстве", "Станиславский К.С.", "4566856788", 2013);
        bookService.create("Консуэло", "Консуэло", "Санд Ж.", "4566856788", 2013);
        bookService.create("Флейта-позвоночник", "Флейта-позвоночник", "Маяковский В.В.", "4566856788", 2013);
        bookService.create("Стихотворения", "Стихотворения", "Теннисон А.", "4566856788", 2013);
        bookService.create("На заре жизни. Том первый", "На заре жизни. Том первый", "Водовозова Е.Н.", "4566856788", 2013);
        bookService.create("Пер Гюнт", "Пер Гюнт", "Ибсен Г.", "4566856788", 2013);
        bookService.create("Черный человек", "Черный человек", "Есенин С.А.", "4566856788", 2013);
        bookService.create("Мартин Иден", "Мартин Иден", "Лондон Д.", "4566856788", 2013);
        bookService.create("На горах. Книга 1-я", "На горах. Книга 1-я", "Мельников-Печерский П.И.", "4566856788", 2013);
        bookService.create("Стихотворения", "Стихотворения", "Мережковский Д.С.", "4566856788", 2013);
        bookService.create("Скифы", "Скифы", "Блок А.А.", "4566856788", 2013);
        bookService.create("Судебные речи", "Судебные речи", "Плевако Ф.Н.", "4566856788", 2013);
        bookService.create("Стихотворения", "Стихотворения", "Давыдов Д.В.", "4566856788", 2013);
        bookService.create("От Двуглавого Орла к красному знамени", "От Двуглавого Орла к красному знамени", "Краснов П.Н.", "4566856788", 2013);
        bookService.create("Очерки уголовного мира царской России. Книга первая", "Очерки уголовного мира царской России. Книга первая", "Кошко А.Ф.", "4566856788", 2013);
        bookService.create("Пятьдесят лет в строю", "Пятьдесят лет в строю", "История Игнатьев А.А.", "4566856788", 2013);
        bookService.create("О ничтожестве и горестях жизни", "О ничтожестве и горестях жизни", "Шопенгауэр А.", "4566856788", 2013);
        bookService.create("Кипарисовый ларец", "Кипарисовый ларец", "Анненский И.Ф.", "4566856788", 2013);
        bookService.create("В лесах. Книга 1-я. ", "В лесах. Книга 1-я. ", "Мельников-Печерский П.И.", "4566856788", 2013);
        bookService.create("Записки врача", "Записки врача", "Вересаев В.В.", "4566856788", 2013);
        bookService.create("Мизантроп", "Мизантроп", "Мольер Ж.", "4566856788", 2013);
        bookService.create("Мир как воля и представление", "Мир как воля и представление", "Шопенгауэр А.", "4566856788", 2013);
        bookService.create("Госпожа Бовари", "Госпожа Бовари", "Флобер Г.", "4566856788", 2013);
        bookService.create("Фараон и хорал", "Фараон и хорал", "О.Генри", "4566856788", 2013);
        bookService.create("Петербургские трущобы. Том 2.", "Петербургские трущобы. Том 2.", "Крестовский В.В.", "4566856788", 2013);
        bookService.create("Перстень", "Перстень", "Баратынский Е.А.", "4566856788", 2013);
        bookService.create("Христос", "Христос", "Морозов Н.А.", "4566856788", 2013);
        bookService.create("Солнце мертвых", "Солнце мертвых", "Шмелев И.С.", "4566856788", 2013);
        bookService.create("Очерки бурсы", "Очерки бурсы", "Помяловский Н.Г.", "4566856788", 2013);
        bookService.create("Государственность и анархия", "Государственность и анархия", "Бакунин М.А.", "4566856788", 2013);
        bookService.create("Как закалялась сталь", "Как закалялась сталь", "Островский Н.А.", "4566856788", 2013);
        bookService.create("Беда от нежного сердца", "Беда от нежного сердца", "Соллогуб В.А.", "4566856788", 2013);

    }


}
