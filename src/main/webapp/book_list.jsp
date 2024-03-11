<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0" />
    <title>Library Management</title>
    <link
      href="css/bootstrap.min.css"
      rel="stylesheet"
      type="text/css" />
  </head>
  <body>
    <div class="d-block px-3 py-2 text-center text-bold skippy purple-200">
      <a
        href="#"
        class="text-white text-decoration-none">
        Library Management
      </a>
      <tr><td><span style="font-weight:bold"><h2>Library Management</h2></span> </td></tr>
    </div>
    <div class="container">
      <table class="table table-success table-striped">
			  
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Book Id</th>
            <th scope="col">Book Title</th>
            <th scope="col">Author</th>
            <th scope="col">Genre</th>
            <th scope="col">Book Copies</th>
            
          </tr>
        </thead>
        <tbody>
          <c:forEach
            var="book"
            items="${books}"
            varStatus="theCount">
            <tr>
              <th scope="row">${theCount.count}</th>
              <td><c:out value="${book.id}" /></td>
              <td><c:out value="${book.bookTitle}" /></td>
              <td><c:out value="${book.author}" /></td>
              <td><c:out value="${book.genre}" /></td>
              <td><c:out value="${book.bookCopies}" /></td>
              <td>
			  	<form action="BookServlet" method="post">
			  	  <input type="hidden" name ="id" value="${book.id}">
			  	  <input type="hidden" name ="action" value="edit">
			  	  <button type="submit">Edit</button>
			  	</form>
		      </td>
			  <td>
			  	<form action="BookServlet" method="post">
			  	  <input type="hidden" name ="id" value="${book.id}">
			  	  <input type="hidden" name ="action" value="delete">
			  	  <button type="submit">Delete</button>
			  	</form>
		      </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <div align="center">
    	<!-- <form action="BookServlet" method="post">
    		 <input type="hidden" name ="action" value="add">
			 <td><span style="font-weight:bold">Add Book in Library :</span> <button type="submit">Add Book</button> </td>
		</form> -->
		<tr><td><span style="font-weight:bold">Add Book in Library :</span> <button onclick="window.location='add_book.jsp'">Add Book</button> </td></tr>
    </div>
    
  </body>
</html>
