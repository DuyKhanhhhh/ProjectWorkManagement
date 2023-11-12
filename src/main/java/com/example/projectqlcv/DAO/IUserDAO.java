package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.*;

import java.util.List;

public interface IUserDAO {
    User login(String userName, String password);

    void signUp(String email, String password, String name, String phoneNumber);

    User checkLoginUser(String email);

    User findPasswordByEmail(String email, String password);

    User findUserById(int id);

    User selectAllUserId(int id);

    void editPassWordUser(String email, String rePassword);

    boolean editInformationUser(int id, User user);
    void addGroup(Group group);

    List<Group> selectGroupFromSQL();
    List<AddUserToTable> selectUserToTable(int idTable);

    void addTable(Table table);
    List<Table> showTableInGroup(int idGroup);

    List<Table> selectAllTable();
    boolean updateGroup(int id,Group group);
    boolean deleteGroup(int id);
    Group findGroupById(int id);
    Table findTableById(int id);

    List<User> searchNameUser(int idGroup, String nameUser);
    void addAdminToGroup(int idGroup,User user);
    void addMemberToGroup(int idGroup, User user);
    List<Member> selectGroupMember(int idGroup);
    boolean deleteMember(int id);
    void addUserToTable(int idTable, User user);
    void addAdminToTable(int idTable, User user);


    boolean deleteTable(int id);
}
