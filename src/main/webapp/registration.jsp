<!DOCTYPE html>
<html>
<head>
    <style>
        input[type=text], input[type=password], select {
            width: 15%;
            padding: 8px 5px;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 7px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 15%;
            background-color: #21474f;
            color: white;
            padding: 8px 1px;
            border: none;
            border-radius: 7px;
            cursor: pointer;
        }

        .registration {
            margin-top: 10px;
            margin-left: 10px;
        }
    </style>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="registration">
    <form action="Registration" method="post">
        <input type="text" name="name" placeholder="username" minlength="4" maxlength="13"
               required="required"><br> <br>
        <input type="text" name="password" placeholder="password" minlength="4" maxlength="13"
               required="required"><br> <br>
        <input type="submit" value="registration">
    </form>
</div>
</body>
</html>
