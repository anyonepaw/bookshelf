<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>update</title>
    <meta charset="UTF-8">
</head>
<body>

<div>
    <form method="post" action="${actionUrl}">


            <label for="2">Title </label><input name="Title" value="${book.title}" id="2"/><Br>
            <label for="3">Description</label><input name="Description" value="${book.description}" id="3"/><Br>
            <label for="4">Author </label><input name="Author" value="${book.author}" id="4" disabled /><Br>
            <label for="5">ISBN </label><input name="ISBN" value="${book.isbn}" id="5"/><Br>
            <label for="6">PrintYear </label><input name="PrintYear" value="${book.print_year}" id="6"/><Br>

            <input type="submit">



    </form>
</div>
</body>
</html>