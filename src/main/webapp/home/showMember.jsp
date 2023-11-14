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

    #boxLeft {
        background-image: url("https://i.pinimg.com/236x/4e/4f/65/4e4f6521b262e4fb9664455012f741fb.jpg");
        max-height: 100%;
        margin-top: 4%;
        height: 100%;
        position: fixed;
        flex: 0 0 auto;
        margin-bottom: 4%;
    }

    #content {
        margin-top: 3%;
        margin-left: 18.5%;
    }

    #footer {
        bottom: 0;
        left: 0;
        right: 0;
        position: fixed;
    }

    .textSpan {
        font-size: 40px;
        margin-left: 14px;
        font-weight: bold;
    }


    .table {
        width: 100%;
        height: 3rem;
        margin-top: 2rem;
    }

    .group {
        width: 100%;
        height: 3rem;
        flex: 1 1 auto;
    }

    .group_name {
        width: 80%;
        height: 3rem;
    }

    .group_add {
        margin-left: 2rem;
        margin-top: 1rem;
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

    #member {
        width: 100%;
        height: 120px;
        border-bottom: solid black 1px;
    }

    .table_all {
        width: 90%;
        margin: 90px auto;
    }

    .name_member {
        float: left;
        width: 60%;
        height: 80px;
        margin-bottom: 35px;
    }

    .add_member {
        width: 40%;
        height: 80px;
        float: left;
        margin-top: 45px;
        margin-bottom: 35px;
    }

    /* menu */
    .menu-wrapper, .menu a {
        width: 100%;
    }

    .menu::after {
        content: '';
        clear: both;
        display: block;
    }

    .menu a {
        display: block;
        padding: 4px;
        -webkit-box-sizing: border-box;
        box-sizing: border-box;
        text-decoration: none;
        font-size: 17px;
    }

    .menu li {
        position: relative;
    }

    .menu > li {
        float: left;
    }

    .menu, .menu ul {
        display: inline-block;
        padding: 0;
        margin: 0;
        list-style-type: none;
        background: white;
    }

    .menu ul li + li {
        border-top: 1px solid #fff;
    }

    .menu ul {
        position: absolute;
        box-shadow: 5px 5px 10px 0 rgba(0, 0, 0, 0.5);
    }

    .menu > li ul, .menu ul ul {
        opacity: 0;
        -webkit-transition: all 0.2s ease-in;
        -moz-transition: all 0.2s ease-in;
        transition: all 0.2s ease-in;
        z-index: -1;
        visibility: hidden;
    }

    .menu > li ul {
        top: 130%;
        left: 0;
    }

    .menu ul ul {
        left: 130%;
        top: 0;
    }

    .menu ul a {
        width: 100px;
    }

    .menu > li:hover > ul {
        top: 100%;
        opacity: 1;
        z-index: 1;
        visibility: visible;
    }

    .menu ul > li:hover > ul {
        left: 100%;
        opacity: 1;
        z-index: 1;
        visibility: visible;
    }

    .menu-gold, .menu-gold a {
        color: #000;
    }

    .menu-gold a:hover {
        background-color: white;
        color: #000;
    }

    body {
        background-image: url("http://recruit.framgia.vn/wp-content/themes/framgia-vn/css/images/bg/banner.jpg");
        text-align: center;
    }

    h1 {
        text-align: center;
        margin-top: 20px;
        color: #fff;
        font-size: 40px;
    }

    /* ends */

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
                        <button style="margin-left: 10px" class="btn btn-outline-secondary" type="submit">Search
                        </button>
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
    <div class="row" id="fullLeft">
        <div class="col-2" id="boxLeft">
            <div class="table">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-table" style="font-size: 30px"></i>
                    <span class="textSpan">Table</span>
                </div>
            </div>
            <div class="group">
                <div class="d-flex  align-items-center">
                    <div class="group_name">
                        <i class="fa-solid fa-user-group" style="font-size: 30px"></i>
                        <span class="textSpan">Group</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-10" id="content">
            <div id="member">
                <div class="name_member">
                    <h2 style=" margin-top: 50px ; margin-left: 60%"><c:out value="${groups.name}"></c:out></h2>
                </div>
                <div class="add_member">
                    <a href="/addMembers?action=addMember&groupId=<c:out value="${groups.id}"></c:out>">
                        <button style="margin-left: 20% ; height: 45px; border-radius: 15px; background: #4799e2 ; color: white"
                                type="submit">Invite members to the workspace
                        </button>
                    </a>
                </div>
            </div>

            <div class="table_all">
                <table class="table table-hover">
                    <tr>
                        <th>Name member</th>
                        <th>Email</th>
                        <th>Role</th>
                        <c:choose>
                            <c:when test="${roleMember.role.equals('Admin')}">
                                <th>Actions</th>
                            </c:when>
                        </c:choose>
                    </tr>
                    <c:forEach var="member" items="${member}">

                        <tr>
                            <td>${member.nameUser}</td>
                            <td>${member.emailUser}</td>
                            <td>
                                <div class="menu-wrapper menu-gold">

                                    <ul class="menu">
                                        <li>
                                            <a href="">${member.role}</a>
                                            <c:choose>
                                                <c:when test="${roleMember.role.equals('Admin')}">
                                                    <ul>
                                                        <c:choose>
                                                            <c:when test="${member.role.equals('Member')}">
                                                                <form action="/addMembers?action=updatePermissionMember&idMember=${member.id}&idGroup=${groups.id}&idUser=${user.id}"
                                                                      method="post">
                                                                    <li>
                                                                        <input type="submit" value="Admin"
                                                                               style="border: none; font-size: 17px"/>
                                                                    </li>
                                                                </form>
                                                            </c:when>
                                                        </c:choose>
                                                    </ul>
                                                </c:when>
                                            </c:choose>

                                        </li>
                                    </ul>
                                </div>
                            </td>

                            <c:choose>
                                <c:when test="${roleMember.role.equals('Admin')}">
                                    <td>
                                        <c:if test="${member.role.equals('Member')}">
                                            <div class="">
                                                <a onclick="showConfirmation()" style="font-size: 20px;color: black">
                                                    <i class="fa-solid fa-trash"></i>
                                                </a>
                                            </div>
                                        </c:if>
                                    </td>
                                </c:when>
                            </c:choose>

                        </tr>
                        <script>
                            function showConfirmation() {
                                var result = confirm("Are you sure you want to remove this member from the group?");
                                if (result) {
                                    window.location.href = "/addMembers?action=deleteMember&idMember=${member.idMember}&groupId=${groups.id}&idUser=${user.id}";
                                }
                            }
                        </script>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="bg-light py-2" id="footer">
        <div class="container text-center">
            <p class="text-muted mb-0 py-1">© 2023 Trello Group 7.</p>
        </div>
    </div>
</div>
</body>
</html>