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
</head>
<style>
    body {
        background-color: #f8f9fa;
    }

    #header {
        position: fixed;
        background-color: #2f2f2f;
        height: 50px;
    }

    .headImg {
        margin-right: 20px;
    }

    .headRight {
        text-align: right;
        padding-right: 10px;
    }

    a {
        text-decoration: none;
        color: black;
    }

    span {
        font-size: 23px;
    }

    /* Style for dropdown content */
    .dropdown-content {
        display: none; /* Hidden by default */
        position: absolute;
        background-color: #000000;
        min-width: 85px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        z-index: 1;
    }

    .dropdown-content a {
        font-size: 15px;
        color: white;
    }

    /* Style for each content div */
    .dropdown-content div {
        padding: 10px 5px;
        font-size: 16px;
        text-align: center;
    }

    /* Highlight dropdown options on hover */
    .dropdown-content div:hover {
        background-color: #cecece;
    }

    /* Show the dropdown menu on hover */
    .dropdown:hover .dropdown-content {
        display: block;
    }

    .title {
        margin-top: 2.6%;
        background-color: #b2b2b2;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .titleRight {
        float: right;
    }

    .sidenav {
        display: none;
        height: 36%;
        width: 222px;
        position: fixed;
        z-index: 1;
        right: 0;
        background-color: #8c8c8c;
        overflow-x: hidden;
        transition: 0.5s;
        padding-top: 60px;
        margin-top: -13px;
    }

    .sidenav a {
        padding: 8px 8px 8px 32px;
        text-decoration: none;
        font-size: 25px;
        color: #ffffff;
        display: block;
        transition: 0.3s;
    }

    .sidenav a:hover {
        color: #f1f1f1;
    }

    .sidenav .closebtn {
        position: absolute;
        top: 0;
        right: 25px;
        font-size: 36px;
        margin-left: 50px;
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
        background-color: #e9ecef;
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
        right: 12px;
        font-size: 36px;
        margin-top: -12px;
    }

    .formDelete {
        display: none;
        width: 260px;
        position: fixed;
        background-color: #e9ecef;
        overflow-x: hidden;
        padding-top: 24px;
        border-radius: 7px;
        top: 20%;
        left: 40%;
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

    .boxAdd {
        width: 260px;
        height: 45px;
        border-radius: 7px;
        background-color: #e9ecef;
        margin-top: 10px;
        margin-left: 15px;
    }

    .columnContent {
        width: 260px;
        height: 820px;
        border-radius: 7px;
        border: 1px solid black;
        background-color: #e9ecef;
        float: left;
        margin-top: 10px;
        margin-left: 15px;
        overflow: auto;
    }

    .contentTable {
        border: 1px solid;
        background: darkgray;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-radius: 5px;
        margin-top: 8px;
        font-size: 20px;
        height: auto;
        padding-left: 5px;
        word-wrap: break-word;
    }

    .nav {
        color: #000000;
        margin-top: 1px;
        margin-left: 10px;
        cursor: pointer;
    }

    #nameTable {
        margin-top: 5px;
        border: none;
        font-size: 20px;
    }
</style>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-dark" id="header">
            <div class="container-fluid">
                <div class="headImg">
                    <a href="/homeUser">
                        <img src="https://logos-world.net/wp-content/uploads/2021/02/Trello-Logo.png"
                             width="100" height="50">
                    </a>
                </div>

                <div class="headRight">
                    <span class="group-text">
                    <div class="collapse navbar-collapse">
                      <i class="fa-solid fa-bell" style="color: #ffffff; margin-right: 15px"></i>
                        <span class="dropdown">
                            <button style="font-size: 15px" class="btn btn-light"><c:out value="${user.name}"/></button>
                            <div class="dropdown-content">
                                <div><a href="#">Setting</a></div>
                                <div><a href="/homeUser?action=editUser&id=${user.id}">Edit</a></div>
                                <div><a href="/updatePassword?login=updatePassword&id=${user.id}">Change PassWord</a></div>
                                <div><a href="login.jsp">Logout</a></div>
                            </div>
                        </span>
                    </div>
                    </span>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="title">
            <div id="nameTable">
                <c:choose>
                    <c:when test="${(roleUser.role).equals('Admin')}">
                        <form id="edit" action="/addUserToTable" method="post">
                            <input name="nameUpdate" type="text" class="title" id="title" value="${tables.name}"
                                   style="border: none">
                            <input name="action" value="editNameTable" type="hidden">
                            <input name="idTable" value="${tables.id}" type="hidden">
                            <input type="submit" class="button" id="buttonEdit" style="display: none ; border: none "
                                   hidden="hidden">
                        </form>
                    </c:when>
                    <c:when test="${(roleUser.role).equals('User')}">
                        <label>${tables.name}</label>
                    </c:when>
                </c:choose>
            </div>
            <div class="titleRight">
                <div id="setting" class="sidenav">
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
                    <div class="nav">
                     <span onclick="openNav()">
                         <i class="fa-solid fa-bars" style="font-size: 35px"></i>
                     </span>
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
                    <form id="editColumn" action="/addUserToTable" method="post">

                        <div class="contentTable">
                            <input name="nameColumnUpdate" type="text" class="title" id="titleColumn" value="${listColumn.name}"
                                   style="border: none">
                            <div onclick="openFormDelete()">
                                <i class="fa-solid fa-ellipsis-vertical" style="color: #000000;"></i>
                            </div>
                        </div>
                        <input name="action" value="editNameColumn" type="hidden">
                        <input name="idColumn" value="${listColumn.id}" type="hidden">
                        <input type="submit" class="button" id="buttonColumn" style="display: none ; border: none "
                               hidden="hidden">

                    </form>
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
        document.getElementById("setting").style.display = "block";
    }

    function closeNav() {
        document.getElementById("setting").style.display = "none";
    }

    const inputElement = document.getElementById("title");
    const inputValue = document.getElementById("buttonEdit")
    const inputColumn = document.getElementById("buttonColumn")


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
    function editColumn(){
        document.getElementById("submit").click();
    }

    input.addEventListener('input', resizeInput);
    resizeInput.call(input);

    function resizeInput() {
        this.style.width = this.value.length + "ch";
    }
</script>
</body>
</html>
