package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.*;
import sun.tools.jconsole.Tab;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddUserToTableController", value = "/addUserToTable")
public class AddUserToTableController extends HttpServlet {
    UserDAO userDAO = null;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "searchUser":
                searchUser(request, response);
                break;
            case "updatePermissionUser":
                updatePermissionUser(request, response);
                break;
            case "editNameTable":
                editNameToTable(request,response);
                break;
            case "editNameColumn":
                editNameToColumn(request,response);
                break;
        }
    }

    private void editNameToColumn(HttpServletRequest request, HttpServletResponse response) {
        int idColumn = Integer.parseInt(request.getParameter("idColumn"));
        String nameUpdate = request.getParameter("nameColumnUpdate");
        userDAO.editNameColumn(idColumn,nameUpdate);
        try {
            response.sendRedirect("/column");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updatePermissionUser(HttpServletRequest request, HttpServletResponse response) {
        int idUserToTable = Integer.parseInt(request.getParameter("id"));
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        HttpSession session = request.getSession();
        userDAO.updatePermissionUserToTable(idUserToTable);
        Table table = userDAO.findTableById(idTable);
        List<AddUserToTable> addUserToTables = userDAO.findUserToTable(idTable);
        session.setAttribute("tables", table);
        session.setAttribute("userToTable", addUserToTables);
        try {
            request.getRequestDispatcher("home/showUserToTable.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        Table table = userDAO.findTableById(idTable);
        Group group = userDAO.findGroupById(id);
        request.setAttribute("groups", group);
        request.setAttribute("tables", table);
        try {
            request.getRequestDispatcher("home/addUserToTable.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showTable(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        int idGroup = Integer.parseInt(request.getParameter("id"));
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        List<AddUserToTable> addUserToTable = userDAO.findUserToTable(idTable);
        session.setAttribute("userToTable", addUserToTable);
        AddUserToTable addUserToTable1 = userDAO.findUserToTableById(idTable);
        session.setAttribute("userOfTable",addUserToTable1);
        Table table = userDAO.findTableById(idTable);
        session.setAttribute("tables", table);
        Group group = userDAO.findGroupById(idGroup);
        session.setAttribute("groups", group);
        AddUserToTable userToTable = userDAO.findRoleUserToUserToTable(idUser);
        session.setAttribute("member",userToTable);
        Member member = userDAO.findRoleUserToMember(idUser);
        session.setAttribute("memberToGroup",member);
        try {
            request.getRequestDispatcher("/column").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) {
        int idGroup = Integer.parseInt(request.getParameter("id"));
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        Table table = userDAO.findTableById(idTable);
        request.setAttribute("tables", table);
        String name = request.getParameter("search");
        List<User> userList = userDAO.searchUserToTable(idGroup, name);
        request.setAttribute("list", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("home/addUserToTable.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "addUserToTable":
                addUser(request, response);
                break;
            case "addUser":
                addUserToTable(request, response);
                break;
            case "showTable":
                showTable(request, response);
                break;
            case "show":
                showAllUserToTable(request, response);
                break;
            case "showUserToTable":
                showUserToTable(request, response);
                break;
            case "deleteUserToTable":
                deleteUserToTable(request, response);
                break;
            case "deleteTable":
                deleteTable(request, response);
                break;
            case "joinTable":
                joinTable(request,response);
                break;
        }
    }

    private void joinTable(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        User user = userDAO.selectAllUserId(id);
        userDAO.addUserToTable(idTable, user);
        List<AddUserToTable> addUserToTables = userDAO.findUserToTable(idTable);
        Table table = userDAO.findTableById(idTable);
        HttpSession session = request.getSession();
        session.setAttribute("userToTable",addUserToTables);
        session.setAttribute("tables",table);
        try {
            request.getRequestDispatcher("home/tableView.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTable(HttpServletRequest request, HttpServletResponse response) {
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        userDAO.deleteUserToTable(idTable);
        userDAO.deleteTable(idTable);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void editNameToTable(HttpServletRequest request, HttpServletResponse response) {
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        String nameUpdate = request.getParameter("nameUpdate");
        userDAO.editNameTable(idTable, nameUpdate);
        Table table = userDAO.findTableById(idTable);
        HttpSession session = request.getSession();
        session.setAttribute("tables", table);
        try {
            response.sendRedirect("/column");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUserToTable(HttpServletRequest request, HttpServletResponse response) {
        int idUserToTable = Integer.parseInt(request.getParameter("id"));
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        userDAO.deleteUserToTable(idUserToTable);
        HttpSession session = request.getSession();
        session.setAttribute("message", "Delete success !");
        List<AddUserToTable> addUserToTables = userDAO.findUserToTable(idTable);
        Table table = userDAO.findTableById(idTable);
        session.setAttribute("userToTable", addUserToTables);
        session.setAttribute("tables", table);
        try {
            request.getRequestDispatcher("home/showUserToTable.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUserToTable(HttpServletRequest request, HttpServletResponse response) {
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        List<AddUserToTable> addUserToTables = userDAO.findUserToTable(idTable);
        HttpSession session = request.getSession();
        session.setAttribute("userToTable", addUserToTables);
        Table table = userDAO.findTableById(idTable);
        session.setAttribute("tables", table);
        AddUserToTable addUserToTable = userDAO.findRoleUserToUserToTable(idUser);
        session.setAttribute("member",addUserToTable);
        try {
            request.getRequestDispatcher("home/showUserToTable.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllUserToTable(HttpServletRequest request, HttpServletResponse response) {
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        List<AddUserToTable> addUserToTable = userDAO.findUserToTable(idTable);
        HttpSession session = request.getSession();
        session.setAttribute("userToTable", addUserToTable);
        try {
            request.getRequestDispatcher("home/tableView.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void addUserToTable(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int idTable = Integer.parseInt(request.getParameter("idTable"));
        User user = userDAO.selectAllUserId(id);
        userDAO.addUserToTable(idTable, user);
        Table table = userDAO.findTableById(idTable);
        request.setAttribute("tables", table);
        request.setAttribute("message", "Add member success !");
        try {
            request.getRequestDispatcher("home/addUserToTable.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

