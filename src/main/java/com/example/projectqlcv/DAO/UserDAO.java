package com.example.projectqlcv.DAO;

import com.example.projectqlcv.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static final String ADD_USER_TO_SQL = "INSERT INTO user(email, name, phoneNumber, password) VALUES(?, ?, ?, ?) ";
    private static final String LOGIN_USER_HOME = "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String SELECT_USER_ID = "SELECT email FROM user WHERE id = ?";
    private static final String CHECK_USER_LOGIN = "select * from user where email = ?";
    private static final String UPDATE_PASSWORD_USER = "UPDATE user SET password = ? WHERE email = ? ";
    private static final String UPDATE_USER_ID = "UPDATE user SET name = ?, phoneNumber = ? , address = ? , avatar = ? WHERE id = ?";
    private static final String SELECT_INFORMATION_USER_ID = "SELECT id,name,phoneNumber,address,avatar FROM user WHERE id = ?";
    private static final String SELECT_PASSWORD_BY_EMAIL = "SELECT email,password FROM user WHERE email = ? AND password = ?";
    private static final String ADD_GROUP_TO_SQL = "INSERT INTO groupWork(name,title,permission,information) VALUES(?,?,?,?)";
    private static final String SELECT_ALL_GROUP_WORK = "SELECT * FROM groupWork";
    private static final String UPDATE_GROUP = "UPDATE groupWork SET name = ?, title = ?, permission = ?, information = ? WHERE id = ?";
    private static final String ADD_TABLE_TO_SQL = "INSERT INTO tableWork(idGroup, name, permission) VALUES( ?, ?, ?) ";
    private static final String SELECT_GROUP_BY_ID = "SELECT * FROM groupWork where id = ?";
    private static final String SELECT_TABLE_BY_ID = "SELECT * FROM tableWork where id = ?";
    private static final String DELETE_GROUP_SQL = "DELETE FROM groupWork where id = ?";
    private static final String DELETE_MEMBER_OF_GROUP = "DELETE FROM member where id = ?";
    private static final String SELECT_ALL_TABLE = "SELECT * FROM tableWork";
    private static final String SEARCH_NAME_PRODUCT = "SELECT * FROM user WHERE id NOT IN (select u.id  from member m join user u on m.idUser = u.id where idGroup = ? ) AND (name LIKE ? || email LIKE ?)";
    private static final String ADD_MEMBER_TO_SQL = "INSERT INTO member(idGroup,idUser,role) VALUES (?,?,?)";
    private static final String ADD_MEMBER_TO_TABLE = "INSERT INTO userToTable(idUser,idTable,role,status) VALUES (?,?,?,'Added')";
    private static final String SELECT_ALL_GROUP_MEMBER = "SELECT member.id,user.name,user.email,role FROM user JOIN member ON user.id = member.idUser JOIN groupWork ON member.idGroup = groupWork.id WHERE groupWork.id = ?";
    private static final String SELECT_TABLE_IN_GROUP = "SELECT t.id ,t.name ,t.permission FROM tableWork t JOIN groupWork g ON t.idGroup=g.id WHERE g.id = ?";
    private static final String UPDATE_PERMISSION_MEMBER = "update member set role = ? where id = ?";
    private static final String SEARCH_USER_TO_TABLE = "select * from user where id not in (select u.id from userToTable m join user u on  m.idUser = u.id  join tableWork t on m.idTable = t.id where m.status = 'Added' and t.idGroup like ?) and (name like ? || email like ?);";
    private static final String FIND_ROLE_USER_TO_MEMBER = "SELECT role FROM member WHERE idUser = ?";
    private static final String FIND_ROLE_USER_TO_USER_TO_TABLE = "SELECT role,idTable FROM userToTable WHERE idUser = ?";
    private static final String UPDATE_PERMISSION_USER_TO_TABLE = "update userToTable set role = ? where id = ?";
    private static final String SELECT_ALL_MEMBER = "select * from member where id =?";
    private static final String SELECT_USER_TO_TABLE = "select u.email, m.id, u.name, m.idTable, m.idUser, m.role, u.avatar, t.idGroup, m.status from userToTable m join user u on m.idUser = u.id join tableWork t on m.idTable = t.id where t.id =?";
    private static final String DELETE_USER_TO_TABLE = "delete from userToTable where id = ?";
    private static final String EDIT_NAME_TABLE = "UPDATE tableWork SET name = ? WHERE id= ?";
    private static final String DELETE_ID_TABLE_USER_TO_TABLE = "delete from userToTable where idTable = ?";
    private static final String DELETE_TABLE_TO_SQL = "delete from tableWork where id = ?";
    private static final String FIND_USER_TO_TABLE_BY_ID = "select * from userToTable where idTable = ?";
    private static final String EDIT_NAME_COLUMN = "UPDATE columnWork SET name = ? WHERE id= ?";

    @Override
    public Member findMemberById(int id) {
        Member member = null;
        try (Connection connection = DataConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MEMBER)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int idGroup = rs.getInt("idGroup");
                int idUser = rs.getInt("idUser");
                String role = rs.getString("role");
                member = new Member(id, idGroup, idUser, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return member;
    }

    @Override
    public boolean updatePermissionMember(int id) {
        boolean updateGroup;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERMISSION_MEMBER);
            preparedStatement.setString(1, "Admin");
            preparedStatement.setInt(2, id);
            updateGroup = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateGroup;
    }

    @Override
    public List<AddUserToTable> findUserToTable(int id) {
        List<AddUserToTable> addUserToTable = new ArrayList<>();
        try (Connection connection = DataConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_TO_TABLE)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idUserToTable = rs.getInt("id");
                int idGroup = rs.getInt("idGroup");
                int idUser = rs.getInt("idUser");
                String role = rs.getString("role");
                String avatar = rs.getString("avatar");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String status = rs.getString("status");
                addUserToTable.add(new AddUserToTable(idUserToTable, id, idUser, idGroup, role, avatar, name, email, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return addUserToTable;
    }

    @Override
    public boolean updatePermissionUserToTable(int id) {
        boolean updateGroup;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERMISSION_USER_TO_TABLE);
            preparedStatement.setString(1, "Admin");
            preparedStatement.setInt(2, id);
            updateGroup = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updateGroup;
    }

    private static final String FIND_USER_TO_GROUP = "SELECT * FROM member WHERE idGroup = ? and idUser = ? ";
    private static final String FIND_TABLE_BY_ID = "SELECT * FROM tableWork WHERE id= ?";


    @Override
    public User findPasswordByEmail(String email, String password) {
        User user = null;
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PASSWORD_BY_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String emailUser = resultSet.getString("email");
                String passwordUser = resultSet.getString("password");
                user = new User(emailUser, passwordUser);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User findUserById(int id) {
        User user = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                user = new User(email);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User selectAllUserId(int id) {
        User user = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INFORMATION_USER_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int iD = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                String address = resultSet.getString("address");
                String avatar = resultSet.getString("avatar");
                user = new User(iD, name, phoneNumber, address, avatar);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void editPassWordUser(String email, String rePassword) {
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_USER);
            preparedStatement.setString(1, rePassword);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean editInformationUser(int id, User user) {
        boolean rowUpdate;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_ID);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getAvatar());
            preparedStatement.setInt(5, id);
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdate;
    }


    public User login(String email, String password) {
        User user = null;
        try (Connection connection = DataConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_USER_HOME);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("id"));
                String name = rs.getString("name");
                String emailDB = rs.getString("email");
                String passWord = rs.getString("password");
                user = new User(id, name, emailDB, passWord);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void signUp(String email, String password, String name, String phoneNumber) {
        try (Connection connection = DataConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_TO_SQL);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User checkLoginUser(String email) {
        User user = null;
        try (Connection connection = DataConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_LOGIN)) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String emailDB = rs.getString("email");
                String password = rs.getString("password");
                user = new User(id, emailDB, password);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void addGroup(Group group) {
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_GROUP_TO_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getGroupType());
            preparedStatement.setString(3, group.getPermission());
            preparedStatement.setString(4, group.getInformation());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int idGroup = 0;
            if (resultSet.next()) {
                idGroup = resultSet.getInt(1);
            }
            group.setId(idGroup);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Group> selectGroupFromSQL() {
        List<Group> groups = new ArrayList<>();
        try {
            Connection connection = DataConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_GROUP_WORK);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String groupType = resultSet.getString("title");
                String permission = resultSet.getString("permission");
                String information = resultSet.getString("information");
                groups.add(new Group(id, name, groupType, permission, information));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    @Override
    public void addTable(Table table) {
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_TABLE_TO_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, table.getIdGroup());
            preparedStatement.setString(2, table.getName());
            preparedStatement.setString(3, table.getPermission());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int tableId = 0;
            if (resultSet.next()) {
                tableId = resultSet.getInt(1);
            }
            table.setId(tableId);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Table> selectAllTable() {
        List<Table> listTable = new ArrayList<>();
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TABLE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idGroup = rs.getInt("idGroup");
                String name = rs.getString("name");
                String permission = rs.getString("permission");
                listTable.add(new Table(id, idGroup, name, permission));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return listTable;
    }

    @Override
    public boolean updateGroup(int id, Group group) {
        boolean updateGroup;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GROUP);
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getGroupType());
            preparedStatement.setString(3, group.getPermission());
            preparedStatement.setString(4, group.getInformation());
            preparedStatement.setInt(5, id);
            updateGroup = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return updateGroup;
    }

    @Override
    public boolean deleteGroup(int id) {
        boolean rowDelete;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GROUP_SQL);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }

    @Override
    public boolean deleteMember(int id) {
        boolean rowDelete;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MEMBER_OF_GROUP);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }
    @Override
    public boolean deleteUserToTable(int id) {
        boolean rowDelete;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID_TABLE_USER_TO_TABLE);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }

    @Override
    public boolean editNameTable(int idTable,String nameUpdate) {
        boolean rowEdit;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_NAME_TABLE);
            preparedStatement.setString(1, nameUpdate);
            preparedStatement.setInt(2, idTable);
            rowEdit = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return rowEdit;
    }
    @Override
    public boolean deleteTable(int id) {
        boolean rowDelete;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TABLE_TO_SQL);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }

    @Override
    public boolean deleteIdTable(int idTable) {
        boolean rowDelete;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TABLE_TO_SQL);
            preparedStatement.setInt(1, idTable);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }


    @Override
    public Group findGroupById(int id) {
        Group listGroup = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GROUP_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String title = rs.getString("title");
                String permission = rs.getString("permission");
                String information = rs.getString("information");
                listGroup = new Group(id, name,title, permission, information);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return listGroup;
    }


    @Override
    public Table findTableById(int id) {
        Table table = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TABLE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idGroup = rs.getInt("idGroup");
                String name = rs.getString("name");
                String permission = rs.getString("permission");
                table = new Table(id, idGroup, name, permission);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return table;
    }

    @Override
    public List<User> searchNameUser(int idGroup, String nameUser) {
        try {
            List<User> list = new ArrayList<>();
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NAME_PRODUCT);
            preparedStatement.setInt(1, idGroup);
            preparedStatement.setString(2, "%" + nameUser + "%");
            preparedStatement.setString(3, "%" + nameUser + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String avatar = resultSet.getString("avatar");
                list.add(new User(id, name, email, avatar));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<User> searchUserToTable(int idGroup, String nameUser) {
        try {
            List<User> list = new ArrayList<>();
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_USER_TO_TABLE);
            preparedStatement.setInt(1,idGroup);
            preparedStatement.setString(2, "%" + nameUser + "%");
            preparedStatement.setString(3, "%" + nameUser + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String avatar = resultSet.getString("avatar");
                list.add(new User(id, name, email, avatar));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAdminToGroup(int idGroup, User user) {
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBER_TO_SQL);

            preparedStatement.setInt(1, idGroup);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setString(3, "Admin");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMemberToGroup(int idGroup, User user) {
        Connection connection = null;

        try {
            connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBER_TO_SQL);
            preparedStatement.setInt(1, idGroup);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.setString(3, "Member");
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void addUserToTable(int idTable, User user) {
        Connection connection = null;

        try {
            connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBER_TO_TABLE);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, idTable);
            preparedStatement.setString(3, "User");
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAdminToTable(int idTable, User user) {
        Connection connection = null;
        try {
            connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBER_TO_TABLE);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, idTable);
            preparedStatement.setString(3, "Admin");
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Member findUserToGroup(int idGroup, int idUser) {
        Member member = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_TO_GROUP);
            preparedStatement.setInt(1, idGroup);
            preparedStatement.setInt(2, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idGroupOb = resultSet.getInt("idGroup");
                int idUserDb = resultSet.getInt("idUser");
                String role = resultSet.getString("role");
//                member.setIdMember(id);
//                member.setGroupId(idGroup);
//                member.setUserId(idUserDb);
//                member.setRole(role);
                member = new Member(id, idGroupOb, idUserDb, role);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return member;
    }

    @Override
    public Member findRoleUserToMember(int idUser) {
        Member member = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROLE_USER_TO_MEMBER);
            preparedStatement.setInt(1,idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String role = resultSet.getString("role");
                member = new Member(role);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return member;
    }
    @Override
    public AddUserToTable findRoleUserToUserToTable(int idUser) {
        AddUserToTable addUserToTable = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROLE_USER_TO_USER_TO_TABLE);
            preparedStatement.setInt(1,idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idTable = resultSet.getInt("idTable");
                String role = resultSet.getString("role");
                addUserToTable = new AddUserToTable(idTable,role);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return addUserToTable;
    }

    @Override
    public boolean editNameColumn(int idColumn,String nameUpdate) {
        boolean rowEdit;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(EDIT_NAME_COLUMN);
            preparedStatement.setString(1, nameUpdate);
            preparedStatement.setInt(2, idColumn);
            rowEdit = preparedStatement.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return rowEdit;
    }

    @Override
    public AddUserToTable findUserToTableById(int idTable) {
        AddUserToTable addUserToTable = null;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_TO_TABLE_BY_ID);
            preparedStatement.setInt(1,idTable);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int idUser = resultSet.getInt("idUser");
                String role = resultSet.getString("role");
                addUserToTable = new AddUserToTable(id, idUser,idTable,role);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return addUserToTable;
    }


    @Override
    public List<Member> selectGroupMember(int idGroup) {
        List<Member> memberList = new ArrayList<>();
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROUP_MEMBER);
            preparedStatement.setInt(1, idGroup);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameUser = resultSet.getString("name");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                memberList.add(new Member(id, nameUser, email, role));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }


    @Override
    public List<Table> showTableInGroup(int idGroup) {
        List<Table> tableList = new ArrayList<>();
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TABLE_IN_GROUP);
            preparedStatement.setInt(1, idGroup);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idTable = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String permission = resultSet.getString("permission");
                tableList.add(new Table(idTable, name, permission));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tableList;
    }
}