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
    #text{
        font-size: 33px;
    }
    #rightTitle{
        display: flex;
        flex-direction: row-reverse;
    }
    .circleMember{
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
    .member{
        display: flex;
        align-items: center;
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
                <div style="width: 600px">
                    <form class="d-flex">
                        <input class="form-control" type="search" placeholder="Search">
                        <button style="margin-left: 10px" class="btn btn-outline-secondary" type="submit">Search</button>
                    </form>
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
            <span id="text">${member.name}</span>
            <div class="titleRight">
                <div id="mySidebar" class="sidebar">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
                    <h2 style="text-align: center;color: white">Menu</h2>
                    <hr style="color:white;">
                    <a href="/addUserToTable?action=addUserToTable&id=${groups.id}&idTable=${tables.id}">Add member</a>
                    <a href="#">Services</a>
                    <a href="#">Clients</a>
                    <a href="#">Contact</a>

                    <button onclick="showConfirmation()" class="btn btn-secondary" style="width: 200px">
                        <a style="text-decoration: none;color: white"
                           href="homeUser?action=deleteTable&idTable=${tables.id}&groupId=${groups.id}">Delete</a>
                    </button>


                    <script>
                        function showConfirmation() {
                            var result = confirm("Are you sure you want to remove this table from the group?");
                            if (result) {
                                window.location.href = "/homeUser?action=deleteTable&id=${tables.id}&groupId=${groups.id}";
                            }
                        }
                    </script>

                </div>
                <span id="rightTitle">
          <div id="main">
          <span onclick="openNav()"><i class="fa-solid fa-bars" style="color: #000000;font-size: 28px"></i></span>
        </div>
        <div class="member">
            <c:forEach var="member" items="${member}">
          <div class="circleMember">
            <img src="${member.avatar}" alt="Avatar">
          </div>
            </c:forEach>
        </div>
      </span>
            </div>
        </div>
    </div>
    <div class="bg-light py-2" id="footer">
        <div class="container text-center">
            <p class="text-muted mb-0 py-1">© 2023 Trello Group 7.</p>
        </div>
    </div>
</div>
<script>
    function openNav() {
        document.getElementById("mySidebar").style.width = "250px";
        document.getElementById("main").style.marginLeft = "250px";
    }

    function closeNav() {
        document.getElementById("mySidebar").style.width = "0";
        document.getElementById("main").style.marginLeft = "0";
    }
</script>
</body>
</html>