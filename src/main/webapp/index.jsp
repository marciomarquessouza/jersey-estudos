<html>
<head>
    <title>Hello Example</title>
</head>

<body>
    <h2>Name and surname form example</h2>

    <form method="post" action="<%=request.getContextPath()%>/hello">

        Name: <input type="text" name="name" />
        Last name: <input type="text" name="surname" />

        <input type="submit" name="Send" />

    </form>

</body>
</html>
