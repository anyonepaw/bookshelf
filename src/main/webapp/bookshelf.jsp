<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
    <title>bookshelf</title>
</head>
<body>

<table border="2">
    <tbody>
    <c:forEach items="${bookShelf}" var="it">
        <tr title="BookShelf">
            <td title="ID">${it.ID}</td>
            <td title="TITLE">${it.TITLE}</td>
            <td title="DESCRIPTION">${it.DESCRIPTION}</td>
            <td title="AUTHOR">${it.AUTHOR}</td>
            <td title="ISBN">${it.ISBN}</td>
            <td title="YEAR">${it.PRINT_YEAR}</td>
            <td title="READ_ALREADY">${it.READ_ALREADY}</td>
            <td><a onclick="deleteIt(${it.ID})">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
i'm a bookshelf

<script src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>

<script>
   function deleteIt(id) {
       $.ajax({
           url: '/bookshelf/' + id,
           type: 'DELETE',
           success: function(result) {

               window.location = "/bookshelf"
           },
           error: function() {
               alert('Whooops... Smth went wrong!');
           }
       });
   }
</script>

<a href="/bookshelf/create">Create</a>

</body>


</html>