Приложение CRUD

Книги на полку можно добавлять (create), 
брать посмотреть (read), заменять на новый выпуск (update), убирать (delete).
Данные, которые должны быть в таблице:
#• id – идентификатор книги в БД;
#• title – название книги. Можно использовать тип VARCHAR(100);
#• description – краткое описание о чем книга. Можно использовать тип VARCHAR(255);
#• author – фамилия и имя автора. Можно использовать тип VARCHAR(100);
#• isbn – ISBN книги. Можно использовать тип VARCHAR(20);
#• printYear – в каком году напечатана книга (INT);
#• readAlready – читал ли кто-то эту книгу. Это булево поле.
Бизнес-требование: при редактировании может быть 2 варианта поведение:
• Книгу прочитали, и тогда изменяется только поле readAlready, 
и только, если оно было false. Значения поля должно стать true.
• Книгу заменили на новое издание. В этом случае должна быть 
возможность изменить title, description, isbn, printYear. 
А поле readAlready нужно выставить в false. 
Поле author должно быть неизменяемым с момента создания книги.
Необходимо реализовать стандартное CRUD приложение, 
которое отображаем список всех книг в базе (с пейджингом по 10 книг на странице).
С возможностью их удаления, редактирования, добавления новых, и поиска по уже существующим.
По какому полю искать – каждый решает для себя сам. Можно ограничиться полем title, 
но согласитесь, удобно просмотреть книги, которые еще не читал, или, которые вышли после 2016 года.
