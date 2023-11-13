<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguyenhuugiang19072004
  Date: 10/21/23
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/892d14366e.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Play&amp;display=swap" rel="stylesheet">
</head>
<style>
    body {
        background-color: #e0e0e0;
    }

    .headImg {
        margin-right: 20px;
    }

    .headRight {
        text-align: right;
        padding-right: 10px;
    }

    #header {
        position: fixed;
    }

    #fullLeft {
        display: flex;
    }

    #footer {
        bottom: 0;
        left: 0;
        right: 0;
        position: fixed;
    }

    a {
        text-decoration: none;
    }

    h3 {
        font-family: initial;
    }

    span {
        font-family: initial;;
    }

    .dropdown {
        max-width: 25em;
        position: relative;
        width: 100%;
    }

    .dropdown-btn {
        background: #b2b2b2;
        font-size: 18px;
        width: 100%;
        border: none;
        color: #fff;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0.7em 0.5em;
        border-radius: 0.5em;
        cursor: pointer;
    }

    .arrow {
        border-left: 5px solid transparent;
        border-right: 5px solid transparent;
        border-top: 6px solid #fff;
        transition: transform ease-in-out 0.3s;
    }

    .dropdown-content {
        list-style: none;
        position: absolute;
        top: 3.2em;
        width: 100%;
        visibility: hidden;
        overflow: hidden;
    }

    .dropdown-content li {
        background: #2f3238;
        border-radius: 0.5em;
        position: relative;
        left: 100%;
        transition: 0.5s;
        transition-delay: calc(60ms * var(--delay));
    }

    .dropdown-btn:focus + .dropdown-content li {
        left: 0;
    }

    .dropdown-btn:focus + .dropdown-content {
        visibility: visible;
    }

    .dropdown-btn:focus > .arrow {
        transform: rotate(180deg);
    }

    .dropdown-content li:hover {
        background: #1d1f24;
    }

    .dropdown-content li a {
        display: block;
        padding: 0.7em 0.5em;
        color: #fff;
        margin: 0.1em 0;
        text-decoration: none;
    }

    .title {
        margin-top: 3.9%;
        background-color: #b2b2b2;
        height: 35%;
    }

    .titleRight {
        float: right;
    }

    .sidebar {
        height: 100%;
        position: fixed;
        right: 0;
        background-color: #000;
        overflow-x: hidden;
        transition: 0.5s;
        padding-top: 50px;
    }

    .sidebar a {
        padding: 14px 8px 5px 30px;
        text-decoration: none;
        font-size: 23px;
        color: white;
        display: block;
        transition: 0.3s;
    }

    .sidebar a:hover {
        color: #f1f1f1;
    }

    .sidebar .closebtn {
        position: absolute;
        top: 0;
        right: 25px;
        font-size: 36px;
        margin-left: 50px;

    }

    #main {
        transition: margin-left .5s;
        padding: 16px;
        float: left;
    }

    /* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
    @media screen and (max-height: 400px) {
        .sidebar {
            padding-top: 15px;
        }

        .sidebar a {
            font-size: 10px;
        }
    }

    #text {
        font-size: 33px;
    }

    #rightTitle {
        display: flex;
        flex-direction: row-reverse;
    }

    .circleMember {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        overflow: hidden;
        margin-right: 8px;
    }

    .circleMember img {
        width: 100%;
        height: 100%;
        object-fit: cover;
    }

    .member {
        display: flex;
        align-items: center;
    }

    .title {
        cursor: pointer;
    }

    .formAdd {
        display: none;
        width: 260px;
        position: fixed;
        background-color: #fff;
        overflow-x: hidden;
        padding-top: 30px;
        margin: 18px;
        border-radius: 7px;
        top: 23%;
        left: 50%;
    }

    .formAdd a {
        padding: 8px 8px 8px 32px;
        text-decoration: none;
        font-size: 25px;
        color: #818181;
        display: block;
    }

    .formAdd .closebtn {
        position: absolute;
        top: 0;
        right: 0px;
        font-size: 36px;
        margin-top: -25px;
    }

    .formDelete {
        display: none;
        width: 260px;
        position: fixed;
        background-color: #fff;
        overflow-x: hidden;
        padding-top: 30px;
        margin: 18px;
        border-radius: 7px;
        top: 23%;
        left: 50%;
    }

    .formDelete a {
        padding: 8px 8px 8px 32px;
        text-decoration: none;
        font-size: 25px;
        color: #818181;
        display: block;
    }

    .formDelete .closebtn {
        position: absolute;
        top: 0;
        right: 0px;
        font-size: 36px;
        margin-top: -25px;
    }

    .boxDelete {
        width: 260px;
        height: 45px;
        border-radius: 7px;
        background-color: white;
        margin-top: 10px;
        margin-left: 15px;
    }

    .boxAdd {
        width: 260px;
        height: 45px;
        border-radius: 7px;
        background-color: white;
        margin-top: 10px;
        margin-left: 15px;
    }

    .columnContent {
        width: 260px;
        height: 680px;
        border-radius: 7px;
        border: 1px solid black;
        background-color: white;
        float: left;
        margin-top: 10px;
        margin-left: 15px;
        overflow: auto;
    }

    .contentTable {
        border: 1px solid;
        background: darkgray;
        display: flex;
        flex-direction: column-reverse;
        justify-content: center;
        border-radius: 5px;
        margin-top: 8px;
        font-size: 20px;
        height: auto;
        padding-left: 5px;
        word-wrap: break-word;
    }

</style>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id="header">
            <div class="container-fluid">
                <div class="headImg">
                    <img src="https://logos-world.net/wp-content/uploads/2021/02/Trello-Logo.png"
                         width="130px" height="60px">
                </div>
                <div class="headRight">
          <span class="group-text">
            <div class="collapse navbar-collapse">
              <i class="fa-solid fa-bell" style="color: #ffffff;font-size: 34px; margin-right: 10px"></i>
              <div class="dropdown">
                <button class="dropdown-btn" aria-haspopup="menu">
                  <span><c:out value="${user.name}"/></span>
                  <span class="arrow"></span>
                  </button>
                    <ul class="dropdown-content" role="menu">
                      <li style="--delay: 3;"><a href="#">Setting</a></li>
                      <li style="--delay: 1;"><a href="/homeUser?action=editUser&id=${user.id}">Edit</a></li>
                      <li style="--delay: 2;"><a href="/updatePassword?login=updatePassword&id=${user.id}">Change PassWord</a></li>
                      <li style="--delay: 4;"><a href="login.jsp">Logout</a></li>
                    </ul>
              </div>
            </div>
          </span>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="title">
            <div id="nameTable">
                <form id="edit" action="/addUserToTable" method="post">
                    <input name="nameUpdate" type="text" class="title" id="title" value="${tables.name}"
                           style="border: none">
                    <input name="action" value="editNameTable" type="hidden">
                    <input name="idTable" value="${tables.id}" type="hidden">
                    <input type="submit" class="button" id="buttonEdit" style="display: none ; border: none "
                           hidden="hidden">
                </form>
                <script>
                    const inputElement = document.getElementById("title");
                    const inputValue = document.getElementById("buttonEdit")


                    inputElement.addEventListener("dblclick", function () {
                        inputElement.disabled = false;
                        inputElement.focus();
                    });

                    inputElement.addEventListener("keyup", function (event) {
                        if (event.keyCode === 13) {
                            inputElement.disabled = true;
                            editName();
                        }
                    });

                    inputElement.addEventListener("blur", function () {
                        inputElement.disabled = true;
                        editName();
                    });

                    function editName() {
                        document.getElementById("submit").click();
                    }

                    input.addEventListener('input', resizeInput);
                    resizeInput.call(input);

                    function resizeInput() {
                        this.style.width = this.value.length + "ch";
                    }
                </script>
            </div>
            <div class="titleRight">
                <div id="mySidebar" class="sidebar">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
                    <h2 style="text-align: center;color: white">Menu</h2>
                    <hr style="color:white;">
                    <a href="/addUserToTable?action=addUserToTable&id=${groups.id}&idTable=${tables.id}">Add member</a>
                    <a href="/addUserToTable?action=showUserToTable&idTable=${tables.id}&idUser=${user.id}">Member</a>
                    <c:if test="${member.role.equals('Admin') && member.idTable eq tables.id}">
                        <a onclick="showConfirmation()" style="color: white">Delete table</a>
                    </c:if>
                    <c:if test="${member.idUser != memberToGroup.idUser}">
                        <a href="/addUserToTable?action=joinTable&id=${user.id}&idGroup=${groups.id}&idTable=${tables.id}">
                            Join table</a>
                    </c:if>
                </div>
                <span id="rightTitle">
                    <div id="main">
                        <span onclick="openNav()"><i class="fa-solid fa-bars"
                                                     style="color: #000000;font-size: 28px"></i></span>
                    </div>
                    <div class="member">
                        <c:forEach var="userToTable" items="${userToTable}">
                            <div class="circleMember">
                                <img src="${userToTable.avatar}" alt="Avatar">
                            </div>
                        </c:forEach>
                    </div>
                </span>
            </div>
        </div>
    </div>

    <div class="row">
        <div id="formAdd" class="formAdd">
            <form method="post" action="/column?action=addColumn&idTable=${tables.id}">
                <a href="javascript:void(0)" class="closebtn" onclick="closeForm()">&times;</a>
                <div class="mb-3">
                    <input type="text" class="form-control" name="name" placeholder="Enter a list title">
                    <button type="submit" class="btn btn-primary" style="margin-top: 5px">Add Column</button>
                </div>
            </form>
        </div>

        <c:forEach items="${listColumn}" var="listColumn">
            <c:if test="${tables.id eq listColumn.idTable}">
                <div id="formDelete" class="formDelete">
                    <a href="/column?action=delete&id=${listColumn.id}">
                        <span class="closebtn" onclick="closeFormDelete()">&times;</span>
                        <input type="submit" class="btn btn-primary" value="Delete">
                    </a>
                </div>
                <div class="columnContent">
                    <div class="contentTable">
                        <span>${listColumn.name}</span>
                        <div onclick="openFormDelete()">
                            <i class="fa-solid fa-ellipsis-vertical" style="color: #000000;"></i>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:forEach>
        <div class="boxAdd" onclick="openForm()">
            <span style="font-size: 20px">+ Add list</span>
        </div>
    </div>

</div>
<script>

    function openFormDelete() {
        document.getElementById("formDelete").style.display = "block";
    }

    function closeFormDelete() {
        document.getElementById("formDelete").style.display = "none";
    }

    function openForm() {
        document.getElementById("formAdd").style.display = "block";
    }

    function closeForm() {
        document.getElementById("formAdd").style.display = "none";
    }

    function showConfirmation() {
        var result = confirm("Are you sure you want to remove this table ?");
        if (result) {
            window.location.href = "/addUserToTable?action=deleteTable&idTable=${tables.id}&id=${userOfTable.id}";
        }
    }

    function openNav() {
        document.getElementById("mySidebar").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidebar").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    }

    const inputElement = document.getElementById("title");
    const inputValue = document.getElementById("button")


    inputElement.addEventListener("dblclick", function () {
        inputElement.disabled = false;
        inputElement.focus();
    });

    inputElement.addEventListener("keyup", function (event) {
        if (event.keyCode === 13) {
            inputElement.disabled = true;
            inputValue.onclick;
        }
    });

    inputElement.addEventListener("blur", function () {
        inputElement.disabled = true;
        inputValue.onclick;
    });


    input.addEventListener('input', resizeInput);
    resizeInput.call(input);

    function resizeInput() {
        this.style.width = this.value.length + "ch";
    }
</script>
</body>
</html>