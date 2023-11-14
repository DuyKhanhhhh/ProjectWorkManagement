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

    #content {
        padding-top: 5%;
        margin-top: 1%;
        margin-left: -2%;
        padding-left: 3.5%;
        margin-bottom: 9%;
    }

    i {
        font-size: 25px;
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

    #member {
        width: 90%;
        height: 110px;
        border-bottom: solid #d4d0d0 1px;
        margin: 0 auto;
    }

    #table_all {
        width: 80%;
        margin: 80px 230px;
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
        margin-top: 17px;
        margin-bottom: 35px;
    }

    #table {

        width: 22%;
        height: 110px;
        border-radius: 3px;
        background-image: linear-gradient(to right, #DECBA4, #3E5151);
        margin-left: 25px;
        float: left;
        margin-bottom: 20px;
    }
    #permission{
        margin-left: 120px;
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
        <div id="content">
            <div id="member">
                <div class="name_member">
                    <div style="width: 30%; height: 100%; margin-left: 20%;">
                        <h3 style=" margin-top: 10px ; margin-left: 40%"><b><c:out value="${groups.name}"></c:out></b>
                        </h3>
                        <div id="permission">
                            <div>
                                <c:choose>
                                    <c:when test="${groups.permission.equals('Public')}">
                                        <div style="float:left; margin-bottom: 100px;">
                                            <small><i class="fa-solid fa-earth-asia"></i></small>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div style="float:left; margin-bottom: 100px;">
                                            <small><i class="fa-solid fa-lock"></i></small>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div>
                                <small style="margin-left: 7px; margin-bottom: 100px">
                                    <c:out value="${groups.permission}"></c:out>
                                </small>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="add_member">
                    <a href="/addMembers?action=addMember&groupId=<c:out value="${groups.id}"></c:out>">
                        <button style="margin-left: 12% ; height: 45px; border-radius: 5px; background: #268aeb ; color: white"
                                type="submit"><i style="margin-left: 7px ; margin-right: 6px" class="fa-solid fa-user-plus"></i>Invite members to the workspace
                        </button>
                    </a>
                </div>
            </div>

            <br>
            <br>
            <br>
            <h3 style="margin-left: 260px; "><b>Table</b></h3>
            <c:forEach var="table" items="${tables}">
                <a href="/addUserToTable?action=showTable&id=${groups.id}&idTable=${table.id}&idUser=${user.id}">
                    <div id="table_all">
                        <div id="table">
                            <h4 style="font-family: initial; margin-left: 1rem ; margin-top: 1rem; color: white">${table.name}</h4>
                        </div>
                    </div>
                </a>
            </c:forEach>

        </div>
    </div>
</div>
</body>
</html>
