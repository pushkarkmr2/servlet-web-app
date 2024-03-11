<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Edit Books</title>
</head>
<body>

<h3>Update Book</h3>
    <form action="BookServlet" method="post">
    	Book Id : <input type="number" value="${book.id}" disabled="disabled"><br>
    	Book Title : <input type="text" name ="btitle" value="${book.bookTitle}" ><br>
    	Author : <input type="text" name ="bauthor" value="${book.author}" ><br>
    	Genre: <input type="text" name ="bgenre" value="${book.genre}"><br>
    	Book Copies: <input type="number" name ="bcopies" value="${book.bookCopies}"><br>
    	<input type="hidden" name ="bid" value="${book.id}">
    	<input type="hidden" name ="action" value="update"><br>
    	<button type="submit">Update</button>
	</form>


</body>
</html>
