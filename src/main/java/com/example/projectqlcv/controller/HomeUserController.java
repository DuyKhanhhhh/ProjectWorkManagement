package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.Group;
import com.example.projectqlcv.model.Member;
import com.example.projectqlcv.model.Table;
import com.example.projectqlcv.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet(name = "AddGroupController", value = "/homeUser")
public class HomeUserController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "editUser":
                editInformation(request, response);
                break;
            case "addGroup":
                addGroup(request, response);
                break;
            case "updateGroup":
                updateGroup(request, response);
                break;
            case "addTable":
                addTable(request, response);
                break;
            case "search":
                searchUser(request, response);
                break;
        }
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) {
        int idGroup =Integer.parseInt(request.getParameter("idGroup"));
        String name = request.getParameter("search");
        List<User> userList = userDAO.searchNameUser(idGroup,name);
        request.setAttribute("list", userList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("home/addMember.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void editInformation(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("idUd"));
        String name = request.getParameter("nameUd");
        String phoneNumber = request.getParameter("phoneNumberUd");
        String address = request.getParameter("addressUd");
        String avatar = request.getParameter("avatarUd");
        User user = new User(name, phoneNumber, address, avatar);
        userDAO.editInformationUser(id, user);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String group = request.getParameter("group");
        String permission = request.getParameter("permission");
        String information = request.getParameter("information");
        Group groups = new Group(name, group, permission, information);
        userDAO.updateGroup(id, groups);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addTable(HttpServletRequest request, HttpServletResponse response) {
        int idGroup = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        String name = request.getParameter("name");
        String permission = request.getParameter("permission");
        Table table = new Table();
        table.setName(name);
        table.setPermission(permission);
        table.setIdUser(id);
        table.setIdGroup(idGroup);
        userDAO.addTable(table);
        userDAO.addAdminToTable(table.getId(), user);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addGroup(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        User newUser = userDAO.selectAllUserId(user.getId());
        int id = user.getId();

        String name = request.getParameter("name");
        String groupType = request.getParameter("groupType");
        String permission = request.getParameter("permission");
        String information = request.getParameter("information");
        Group group = new Group();
        group.setName(name);
        group.setGroupType(groupType);
        group.setPermission(permission);
        group.setInformation(information);
        userDAO.addGroup(group);
        userDAO.addAdminToGroup(group.getId(), newUser);
        try {
            response.sendRedirect("/homeUser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {

            case "addGroup":
                showNewFormGroup(request, response);
                break;
            case "updateGroup":
                showUpdateGroup(request, response);
                break;
            case "deleteGroup":
                showDeleteGroup(request, response);
                break;
            case "deleteMember":
                deleteMember(request, response);
                break;
            case "addTableToSQL":
                showNewFromTable(request, response);
                break;
            case "informationGroup":
                showNewFormByIdOfGroup(request, response);
                break;
            case "editUser":
                showEditFormUser(request, response);
                break;
            case "member":
                showMember(request, response);
                break;
            case "table":
                showTableInGroup(request,response);
                break;
            case "deleteTable":
                deleteTable(request, response);
                break;
            default:
                selectGroupFromSql(request, response);

        }
    }



    private void showTableInGroup(HttpServletRequest request ,HttpServletResponse response){
        int idGroup =Integer.parseInt(request.getParameter("idGroup"));
        List<Table> tableList = userDAO.showTableInGroup(idGroup);
        request.setAttribute("tables",tableList);
        Group groupList = userDAO.findGroupById(idGroup);
        request.setAttribute("groups",groupList);
        try {
            request.getRequestDispatcher("home/showTable.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void deleteMember(HttpServletRequest request, HttpServletResponse response) {
        int idMember = Integer.parseInt(request.getParameter("idMember"));
        int idGroup = Integer.parseInt(request.getParameter("groupId"));
        userDAO.deleteMember(idMember);
        request.setAttribute("message", "Delete success !");
        Group group = userDAO.findGroupById(idGroup);
        HttpSession session = request.getSession();
        session.setAttribute("groups", group);
        List<Member> member = userDAO.selectGroupMember(idGroup);
        session.setAttribute("member", member);
        try {
            request.getRequestDispatcher("home/showMember.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showMember(HttpServletRequest request, HttpServletResponse response) {
        try {
            int idGroup = Integer.parseInt(request.getParameter("idGroup"));
            Group group = userDAO.findGroupById(idGroup);
            HttpSession session = request.getSession();
            session.setAttribute("groups", group);
            List<Member> member = userDAO.selectGroupMember(idGroup);
            session.setAttribute("member", member);
            request.getRequestDispatcher("home/showMember.jsp").forward(request, response);

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showEditFormUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.selectAllUserId(id);
        request.setAttribute("user", user);
        try {
            request.getRequestDispatcher("home/editUser.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void showDeleteGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteGroup(id);
        List<Group> list = userDAO.selectGroupFromSQL();
        request.setAttribute("message", "Delete success !");
        request.setAttribute("groups", list);
        try {
            request.getRequestDispatcher("homeUser.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUpdateGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Group group = userDAO.findGroupById(id);
        request.setAttribute("listGroup", group);
        try {
            request.getRequestDispatcher("home/updateGroup.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void showNewFromTable(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
//            List<Table> tableList = userDAO.selectAllTable();
//            List<Group> groups = userDAO.selectGroupFromSQL();
//            request.setAttribute("table", tableList);
            Group group = new Group();
            group.setId(id);
            request.setAttribute("groups", group);
            request.getRequestDispatcher("home/addTable.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewFormGroup(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("home/addGroup.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void selectGroupFromSql(HttpServletRequest request, HttpServletResponse response) {
        List<Group> groups = userDAO.selectGroupFromSQL();
        List<Table> tables = userDAO.selectAllTable();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        user = userDAO.selectAllUserId(id);
        Collections.sort(groups, new Comparator<Group>() {
            @Override
            public int compare(Group group1, Group group2) {
                return group1.getName().compareToIgnoreCase(group2.getName());
            }
        });
        session.setAttribute("tables", tables);
        session.setAttribute("groups", groups);
        session.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeUser.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewFormByIdOfGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Group group = userDAO.findGroupById(id);
        request.setAttribute("group", group);
        request.getRequestDispatcher("/home/information.jsp").forward(request, response);
    }

    private void deleteTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idTable"));
        int idGroup = Integer.parseInt(request.getParameter("groupId"));
        userDAO.deleteTable(id);

        request.setAttribute("message", "Delete success !");
        Table table = userDAO.findTableById(id);
        HttpSession session = request.getSession();
        session.setAttribute("tables", table);
        List<Table> tables = userDAO.showTableInGroup(idGroup);
        session.setAttribute("tables", tables);

        try {
            request.getRequestDispatcher("home/showTable.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}