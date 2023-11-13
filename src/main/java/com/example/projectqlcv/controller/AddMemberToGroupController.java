package com.example.projectqlcv.controller;

import com.example.projectqlcv.DAO.IUserDAO;
import com.example.projectqlcv.DAO.UserDAO;
import com.example.projectqlcv.model.Group;
import com.example.projectqlcv.model.Member;
import com.example.projectqlcv.model.User;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "AddMemberToGroupController", value = "/addMembers")
public class AddMemberToGroupController extends HttpServlet {
    private IUserDAO iUserDAO;

    @Override
    public void init() {
        iUserDAO = new UserDAO();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "updatePermissionMember":
                updatePermissionMember(request, response);
                break;
        }
    }

    private void updatePermissionMember(HttpServletRequest request, HttpServletResponse response) {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idMember = Integer.parseInt(request.getParameter("idMember"));
        int idGroup = Integer.parseInt(request.getParameter("idGroup"));
        Member roleMember = iUserDAO.findRoleUserToMember(idUser);
        HttpSession session = request.getSession();
        request.setAttribute("roleMember", roleMember);
        iUserDAO.updatePermissionMember(idMember);
        List<Member> member = iUserDAO.selectGroupMember(idGroup);
        session.setAttribute("member", member);
        try {
            request.getRequestDispatcher("home/showMember.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addUserToGroup(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int idGroup = Integer.parseInt(request.getParameter("idGroup"));
        User user = iUserDAO.selectAllUserId(id);
        iUserDAO.addMemberToGroup(idGroup, user);
        request.setAttribute("message", "Add member success");
        try {
            request.getRequestDispatcher("home/addMember.jsp").forward(request, response);
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
            case "addMember":
                addMember(request, response);
                break;
            case "addUser":
                addUserToGroup(request, response);
                break;
            case "deleteMember":
                deleteMember(request, response);
                break;
        }
    }

    private void addMember(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("groupId"));
            Group group = iUserDAO.findGroupById(id);
            request.setAttribute("groups", group);
            request.getRequestDispatcher("home/addMember.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteMember(HttpServletRequest request, HttpServletResponse response) {
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idMember = Integer.parseInt(request.getParameter("idMember"));
        int idGroup = Integer.parseInt(request.getParameter("groupId"));
        Member roleMember = iUserDAO.findRoleUserToMember(idUser);
        if ((roleMember.getRole()).equals("Admin")) {
            iUserDAO.deleteMember(idMember);
            request.setAttribute("message", "Delete success !");
            Group group = iUserDAO.findGroupById(idGroup);
            HttpSession session = request.getSession();
            session.setAttribute("groups", group);
            List<Member> member = iUserDAO.selectGroupMember(idGroup);
            session.setAttribute("member", member);
        }
        try {
            request.getRequestDispatcher("home/showMember.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
