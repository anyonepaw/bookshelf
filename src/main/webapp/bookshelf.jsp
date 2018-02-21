<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html>

<html>
<head>
    <title>bookshelf</title>

</head>
<body>

<br>
<br>


<form action="/bookshelf/search">
    <input name="Query"/>
    <input type="submit">
</form>

<br>
<br>

<table border="1">
    <tbody>
    <%--@elvariable id="bookShelf" type="java.util.List"--%>
    <c:forEach items="${bookShelf}" var="it">
      <%--@elvariable id="it" type="app.Book"--%>
        <tr title="BookShelf">
            <td title="ID">${it.id}</td>
            <td title="TITLE">${it.title}</td>
            <td title="DESCRIPTION">${it.description}</td>
            <td title="AUTHOR">${it.author}</td>
            <td title="ISBN">${it.isbn}</td>
            <td title="YEAR">${it.printYear}</td>
            <td title="READ_ALREADY">${it.readAlready}</td>
            <td>
                <button><a onclick="deleteIt(${it.id})">Delete</a></button>
            </td>
            <td>
                <button><a href="/bookshelf/update/${it.id}">Update</a></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>

<div>

    <button onclick="prevPage()" id="prev"  ${ page==0 ? "disabled" : "" }> &lt;</button>
    <input type="text" onchange="onChange(this)" value="${page+1} ">

    ${allPages}
    <button onclick="nextPage()" id="next" ${ page==(allPages-1) ? "disabled" : "" }> &gt;</button>


</div>

<br>
<a href="/bookshelf/create">
    <button>Create</button>
</a>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>

<script>
    function deleteIt(id) {
        $.ajax({
            url: '/bookshelf/' + id,
            type: 'DELETE',
            success: function (result) {
                window.location.reload()
            },
            error: function () {
                alert('Whooops... Smth went wrong!');
            }
        });
    }
</script>

<script>

    var page =  ${page};

    function nextPage() {
        window.location = window.location.pathname + "?page=" + (page + 1)
    }

    function prevPage() {
        window.location = window.location.pathname + "?page=" + (page - 1)
    }

</script>

<script>

    var allPages = ${allPages};

    function onChange(input) {
        var pageValue = input.value-1;
        if (pageValue > 0 || pageValue < allPages) {
            window.location = window.location.pathname + "?page=" + (pageValue);
        } else {
            return alert("There's no page with such a number!");
        }
    }

</script>


</body>


</html>
