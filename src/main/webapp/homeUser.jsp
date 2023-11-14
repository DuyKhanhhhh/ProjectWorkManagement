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

    #fullLeft {
        display: flex;
    }

    #boxLeft {
        max-height: 100%;
        margin-top: 2.7%;
        height: 100%;
        position: fixed;
        flex: 0 0 auto;
        margin-bottom: 4%;
        box-shadow: 2px 0 6px rgba(0, 0, 0, 1);
        clip-path: inset(0px -10px -10px -10px);
        background-color: #e9ecef;
    }

    #content {
        margin-top: 1%;
        margin-left: 16%;
        padding-left: 3.5%;
        margin-bottom: 9%;
    }

    #footer {
        bottom: 0;
        left: 0;
        right: 0;
        position: fixed;
    }

    .textSpan {
        font-size: 28px;
        margin-left: 14px;
        font-weight: bold;
    }

    i {
        font-size: 25px;
    }

    .icon {
        text-decoration: none;
        color: black;
        margin-right: 30px;
        padding: 4px 10px 1px 10px;
        box-shadow: 2px 0 4px rgba(0, 0, 0, 1.5);
        background-color: #e9ecef;
        border-radius: 4px
    }

    #contentTitle {
        margin-top: 48px;
    }

    .table {
        width: 100%;
        height: 3rem;
        margin-top: 1rem;
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
        margin-left: 8%;
    }

    .d_flex_left {
        width: 50%;
        height: auto;
        float: left;
    }

    .d_flex_right {
        width: 50%;
        height: auto;
        float: left;
        padding-left: 8.8%;
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

    .group_main {
        width: 100%;
        height: 18%;
    }

    .name_group {
        width: 85%;
        margin-left: 15%;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .group_header {
        width: 100%;
        height: 3rem;
    }

    .groupContent {
        width: 200px;
        height: 140px;
        border-radius: 40px;
        border: 1px solid black;
        background-color: white;
        float: left;
        margin-right: 2%;
        display: flex;
        align-items: center;
        justify-content: center;
    }

</style>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-dark" id="header">
            <div class="container-fluid">
                <div class="headImg">
                    <img src="https://logos-world.net/wp-content/uploads/2021/02/Trello-Logo.png"
                         width="100" height="50">
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
    <div class="row" id="fullLeft">
        <div class="col-2" id="boxLeft">
            <div class="table">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-table"></i>
                    <span class="textSpan">Table</span>
                </div>
            </div>
            <div class="group">
                <div class="d-flex  align-items-center">
                    <div class="group_name">
                        <i class="fa-solid fa-user-group"></i>
                        <span class="textSpan">Group</span>
                    </div>
                    <div class="group_add">
                        <a href="/homeUser?action=addGroup">
                            <i class="fa-regular fa-square-plus"></i>
                        </a>
                    </div>
                </div>
            </div>
            <c:forEach var="group" items="${groups}">
                <div class="name_group">
                    <span>${group.name}</span>
                </div>
            </c:forEach>
        </div>
        <div class="col-10" id="content">
            <div class="d-flex  align-items-center" id="contentTitle">
                <i class="fa-solid fa-user-group"></i>
                <span class="textSpan">Your group</span>
            </div>
            <c:forEach var="group" items="${groups}">
                <hr style="width: 98.3%" align="center">
                <div class="group_main">
                    <div class="group_header">
                        <div class="d_flex_left">
                            <div class="project">
                                <span style="font-size: 25px">${group.name}</span>
                            </div>
                        </div>
                        <div class="d_flex_right">

                            <div class="d-flex align-items-center ml-auto">
                                <a href="/homeUser?action=updateGroup&id=${group.id}" class="icon">
                                    <i class="fa-solid fa-table-cells"></i>
                                    <span style="font-size: 20px">Information</span>
                                </a>

                                <a href="/homeUser?action=table&idGroup=${group.id}" class="icon">
                                    <i class="fa-solid fa-table"></i>
                                    <span style="font-size: 20px">Table</span>
                                </a>

                                <a href="/homeUser?action=member&idGroup=${group.id}&idUser=${user.id}" class="icon">
                                    <i class="fa-solid fa-user-group" ></i>

                                    <span style="font-size: 20px">Member</span>
                                </a>

                                <a href="#" class="icon">
                                    <i class="fa-solid fa-gear"></i>
                                    <span style="font-size: 20px">Setting</span>
                                </a>
                            </div>
                        </div>
                    </div>
                        <c:forEach items="${tables}" var="table">
                            <c:if test="${group.id eq table.idGroup}">
                            <div class="groupContent">
                                <a href="/addUserToTable?action=showTable&id=${group.id}&idTable=${table.id}&idUser=${user.id}"
                                   style="text-decoration: none; color: black">
                                    <div class="table_create">
                                        <h4 style="text-align: center;margin-top: 33%">${table.name}</h4>
                                    </div>
                                </a>
                            </div>
                            </c:if>
                        </c:forEach>
                    <div class="groupContent">
                        <div class="table_create">
                            <h4 style="text-align: center;margin-top: 33%"><c:out value="${listTable.name}"/></h4>
                        </div>
                        <a href="/homeUser?action=addTableToSQL&id=${group.id}"
                           style="text-decoration: none; color: black">
                            <div class="table_create">
                                <span style="text-align: center;font-size: 110px">+</span>
                            </div>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
