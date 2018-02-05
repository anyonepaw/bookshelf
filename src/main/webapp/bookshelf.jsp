<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <title> hello!</title>
</head>
<body>

<table border="2">
    <tbody>
    <c:forEach items="${book_shelf}" var="it">
        <tr title="BookShelf">
            <td title="ID">${it.ID}</td>
            <td title="TITLE">${it.TITLE}</td>
            <td title="DESCRIPTION">${it.DESCRIPTION}</td>
            <td title="AUTHOR">${it.AUTHOR}</td>
            <td title="ISBN">${it.ISBN}</td>
            <td title="YEAR">${it.PRINT_YEAR}</td>
            <td title="READ_ALREADY">${it.READ_ALREADY}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
i'm a bookshelf


<a href="/bookshelf/create"> Edit</a>

</body>


</html>