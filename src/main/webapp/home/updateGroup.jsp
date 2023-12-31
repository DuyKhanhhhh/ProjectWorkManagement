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
    .headImg {
        margin-right: 20px;
    }

    .headRight {
        text-align: right;
        padding-right: 10px;
    }

    #boxLeft {
        background-color: #b4b4b4;
        height: 900px;
    }

    .textSpan {
        font-size: 30px;
        margin-left: 20px;
    }

    #contentTitle {
        margin: 36px;
    }

    .table {
        width: 100%;
        height: 3rem;
        margin-top: 2rem;
    }

    .group {
        width: 100%;
        height: 3rem;
    }

    .group_name {
        width: 80%;
        height: 3rem;
    }

    .group_add {
        width: 20%;
        height: 3rem;
        margin-left: 1rem;
        margin-top: 1rem;
    }

    .dropdown {
        display: inline-block;
        position: relative;
    }

    button {
        border: none;
        border-radius: 5px;
        padding: 15px 20px;
        font-size: 22px;
        cursor: pointer;
    }

    button:hover {
        background-color: #ddd;
    }

    .dropdown-options {
        display: none;
        position: absolute;
        overflow: auto;
        background-color: #646464;
        border-radius: 5px;
        box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.4);
    }

    .dropdown:hover .dropdown-options {
        display: block;
    }

    .dropdown-options a {
        float: left;
        display: block;
        color: #000000;
        padding: 5px;
        text-decoration: none;
        padding: 20px 20px;
    }

    .dropdown-options a:hover {
        color: #0a0a23;
        background-color: #ddd;
        border-radius: 5px;
    }

    .main_input {
        width: 75%;
        height: 700px;
        border: 5px solid black;
        border-radius: 10px;
        margin: 10px;
    }

    .col-10 {
        text-align: center;
    }

    .select {
        width: 450px;
        height: 70px;
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

    .title_h2 {
        margin-top: 40px;
    }

    .information {
        width: 470px;
        height: 140px;
        margin: 0 auto;
    }

    .name {
        width: 470px;
        height: 70px;
        margin: 0 auto;
    }

    .nameFloat {
        float: left;
    }
</style>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <div class="headImg">
                    <a href="/homeUser">
                        <img src="https://png.pngtree.com/png-vector/20190624/ourlarge/pngtree-managementprocessproductiontaskwork-flat-color-icon-vect-png-image_1492738.jpg"
                             width="70px"
                             height="60px">
                    </a>
                </div>
                <div class="collapse navbar-collapse">
                    <div class="dropdown">
                        <button>Group</button>
                        <div class="dropdown-options">
                            <a href="#">?</a>
                            <a href="#">?</a>
                            <a href="#">?</a>
                        </div>
                    </div>
                </div>
                <div class="headRight">
                    <span class="group-text">
                    <div class="collapse navbar-collapse">
                      <i class="fa-solid fa-bell" style="color: #ffffff;font-size: 34px; margin-right: 10px"></i>
                         <div class="dropdown">
                        <button>Name</button>
                        <div class="dropdown-options">
                            <a href="#">Setting</a>
                            <a href="../editPassword.jsp">Change PassWord</a>
                            <a href="../login.jsp">Logout</a>
                        </div>
                    </div>
                    </div>
                    </span>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="col-2" id="boxLeft">
            <div class="table">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-table" style="color: #000000; font-size: 30px;"></i>
                    <span class="textSpan">Table</span>
                </div>
            </div>
            <div class="group">
                <div class="d-flex  align-items-center">
                    <div class="group_name">
                        <i class="fa-solid fa-user-group" style="color: #000000; font-size: 30px"></i>
                        <span class="textSpan">Group</span>
                    </div>

                </div>
            </div>
            <div class="d-flex  align-items-center">

            </div>
        </div>
        <div class="main_input">
            <button type="submit" class="btn btn-secondary" style="margin-top: 10px">
                <a style="text-decoration: none;color: white" href="/homeUser">Back</a>
            </button>
            <div class="col-10">
                <form method="post" action="/homeUser?action=updateGroup">
                    <div>
                        <div class="title_h2">
                            <h2>Show Group</h2>
                        </div>
                        <br>
                        <br>
                        <c:if test="${listGroup != null}">
                            <input type="hidden" name="id" value="<c:out value='${listGroup.id}' />"/>
                        </c:if>
                        <div class="name">
                            <label style="float: left;margin-left: 34px">Name(<span style="color:red;">*</span>)</label>
                            <input type="text" name="name" style="width: 450px;height: 40px;margin-left: 32px"
                                   value="<c:out value='${listGroup.name}'/>" required>
                        </div>
                        <br>
                        <div class="select">
                            <div class="group_type">
                                <label style="float: left;margin-left: 24px">Group Type(<span
                                        style="color:red;">*</span>)</label>
                                <select name="groupType" style="width: 210px; height: 40px;margin-left: 25px" required>
                                    <option><c:out value='${listGroup.groupType}'/></option>
                                    <option>Manage</option>
                                    <option>Accountant</option>
                                    <option>Plan</option>
                                </select>
                            </div>
                            <div class="permission">
                                <label style="float: left;margin-left: 36px">Permission(<span
                                        style="color:red;">*</span>)</label>
                                <select name="permission" style="width: 210px;height: 40px;margin-left: 36px" required>
                                    <option><c:out value='${listGroup.permission}'/></option>
                                    <option>Public</option>
                                    <option>Private</option>
                                </select>
                            </div>
                        </div>
                        <br>
                        <br>
                        <div class="information">
                            <label style="float: left;margin-left: 34px">Information</label>
                            <input type="text" name="information" style="width: 450px;height: 100px;margin-left: 32px"
                                   value="<c:out value='${listGroup.information}'/>">
                        </div>
                        <br>
                        <br>
                        <div>
                            <button type="submit" class="btn btn-secondary" style="width: 200px">Edit</button>
                            <button onclick="showConfirmation()" class="btn btn-secondary" style="width: 200px">
                                <a style="text-decoration: none;color: white"
                                   href="/homeUser?action=deleteGroup&id=${listGroup.id}">Delete</a>
                            </button>
                            <script>
                                function showConfirmation() {
                                    var result = confirm("Are you sure you want to remove this member from the group?");
                                    if (result) {
                                        window.location.href = "/homeUser?action=deleteGroup&id=${group.id}";
                                    } else {
                                    }
                                }
                            </script>
                        </div>
                    </div>
                </form>
                <div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>