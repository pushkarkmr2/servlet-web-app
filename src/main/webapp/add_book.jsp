<html>
<head>
	<title>Add Books</title>
</head>
<body>
<h2>Please Enter Book Details:</h2>

    <form action="BookServlet" method="post">
    	<input type="number" name ="bid" placeholder="Book Id" required="required"><br>
    	<input type="text" name ="btitle" placeholder="Book Title" required="required"><br>
    	<input type="text" name ="bauthor" placeholder="Author" required="required"><br>
    	<input type="text" name ="bgenre" placeholder="Genre" required="required"><br>
    	<input type="number" name ="bcopies" placeholder="Book Copies" required="required"><br>
    	<input type="hidden" name ="action" value="add">
    	<button type="submit">Add</button>
	</form>
</body>
</html>
