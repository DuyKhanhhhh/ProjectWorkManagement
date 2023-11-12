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
        margin-top: 2.6%;
        height: 100%;
        position: fixed;
        flex: 0 0 auto;
        margin-bottom: 4%;
        box-shadow: 2px 0 6px rgba(0, 0, 0, 1);
        clip-path: inset(0px -10px -10px -10px);
        background-color: #e9ecef;
    }

    #content {
        margin-top: 3%;
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
    i{
        font-size: 25px;
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
    a {
        text-decoration: none;
        color: black;
    }
    span{
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
    .dropdown-content a{
        font-size: 18px;
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
    #content {
        margin-top: 10%;
        margin-left: 25%;
        border: 3px solid #9b9b9b;
        width: 67%;
        height: 608px;
        padding-right: 4%;
        border-radius: 2%;
        background-color: #e9ecef;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);

    }
    .col-10 {
        text-align: center;
    }

    .select {
        width: 70%;
        height: 110px;
        margin: 0 auto;
    }

    .group_type {
        width: 50%;
        height: 40px;
        float: left;
    }

    .permission {
        width: 50%;
        height: 40px;
        float: left;
    }
    .title_h2{
        margin-top: 40px;
    }

    #inputName {
        padding-left: 15%;
        padding-right: 15%;
    }
    #content {
        margin-top: 9%;
        margin-left: 25%;
        padding-left: 3.5%;
        margin-bottom: 9%;
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
            <form method="post" action="/homeUser?action=addGroup">
                <div>
                    <div class="title_h2">
                        <h2>Create Group</h2>
                    </div>

                    <div class="form-outline mb-4" id="inputName">
                        <label style="float: left;font-size: 20px">Name(<span style="color:red;">*</span>)</label>
                        <input type="text" name="name" class="form-control form-control-lg"
                               placeholder="Name" required/>
                    </div>

                    <div class="select">
                        <div class="group_type">
                            <label style="float: left;font-size: 20px">Group Type(<span
                                    style="color:red;">*</span>)</label>
                            <select class="form-select" name="groupType"
                                    style="width: 380px;height: 45px;" required>
                                <option></option>
                                <option>Manage</option>
                                <option>Accountant</option>
                                <option>Plan</option>
                            </select>
                        </div>
                        <div class="permission">
                            <label style="float: left;margin-left: 15px;font-size: 20px">Permission(<span
                                    style="color:red;">*</span>)</label>
                            <select class="form-select" name="permission"
                                    style="width: 380px;height: 45px;margin-left: 4%;" required>
                                <option></option>
                                <option>Public</option>
                                <option>Private</option>
                            </select>
                        </div>
                    </div>
                    <div style="margin-left: 15% ">
                        <div class="form-floating">
                            <textarea class="form-control" placeholder="Leave a comment here" name="information"  style="height: 180px;width: 82%;"></textarea>
                            <label style="font-size: 20px; margin-right: 55rem">Information</label>
                        </div>
                    </div>
                    <div>
                        <button style="width: 190px;height: 50px;margin-top: 25px" class="btn btn-light"
                                type="submit">Create
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="bg-light py-2" id="footer">
    <div class="container text-center">
        <p class="text-muted mb-0 py-1">© 2023 Trello Group 7</p>
    </div>
</div>
</div>
</body>
</html>