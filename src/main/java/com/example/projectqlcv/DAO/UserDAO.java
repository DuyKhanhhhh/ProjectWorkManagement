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
    private static final String ADD_TABLE_TO_SQL = "INSERT INTO tableWork( idGroup, name, permission) VALUES( ?, ?, ?) ";
    private static final String SELECT_GROUP_BY_ID = "SELECT * FROM groupWork where id = ?";
    private static final String SELECT_TABLE_BY_ID = "SELECT * FROM tableWork where id = ?";
    private static final String DELETE_GROUP_SQL = "DELETE FROM groupWork where id = ?";
    private static final String DELETE_MEMBER_OF_GROUP = "DELETE FROM member where id = ?";
    private static final String SELECT_ALL_TABLE = "SELECT * FROM tableWork";
    private static final String SEARCH_NAME_PRODUCT = "SELECT * FROM user WHERE id NOT IN (select user.id  from member join user on idUser = user.id where idGroup = ? ) AND  name LIKE  ? || email LIKE ? ";
    private static final String ADD_MEMBER_TO_SQL = "INSERT INTO member(idGroup,idUser,role) VALUES (?,?,?)";
    private static final String ADD_MEMBER_TO_TABLE = "INSERT INTO userToTable(idUser,idTable,role) VALUES (?,?,?)";
    private static final String SELECT_ALL_GROUP_MEMBER = "SELECT m.id,u.name,u.email,role FROM user u JOIN member m ON u.id = m.idUser JOIN groupWork g ON m.idGroup = g.id WHERE g.id = ?";
    private static final String SELECT_ALL_USER_TO_TABLE = "SELECT id,u.name,u.email,m.role,u.avatar FROM user u JOIN userToTable m ON u.id = m.idUser JOIN tableWork t ON m.idTable = t.id WHERE t.id = ?";
    private static final String SELECT_TABLE_IN_GROUP = "SELECT t.id ,t.name ,t.permission FROM tableWork t JOIN groupWork g ON t.idGroup=g.id WHERE g.id = ?";
    private  static  final String  DELETE_TABLE_SQL= "DELETE userToTable, tableWork FROM userToTable JOIN tableWork ON userToTable.idTable = tableWork.id WHERE tableWork.id = ?";



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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void signUp(String email, String password, String name, String phoneNumber) {
        try (Connection connection = DataConnector.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_TO_SQL);) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
                String group = rs.getString("title");
                String permission = rs.getString("permission");
                String information = rs.getString("information");
                listGroup = new Group(id, name, group, permission, information);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
                int idUser = rs.getInt("idUser");
                String name = rs.getString("name");
                String permission = rs.getString("permission");
                table = new Table(id, idGroup, idUser, name, permission);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
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
    public List<Member> selectGroupMember(int idGroup) {
        List<Member> memberList = new ArrayList<>();
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_GROUP_MEMBER);
            preparedStatement.setInt(1, idGroup);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idMember = resultSet.getInt("id");
                String nameUser = resultSet.getString("name");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                memberList.add(new Member(idMember, nameUser, email, role));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return memberList;
    }

    @Override
    public List<AddUserToTable> selectUserToTable(int idTable) {
        List<AddUserToTable> addUserToTables = new ArrayList<>();
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_TO_TABLE);
            preparedStatement.setInt(1, idTable);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameUser = resultSet.getString("name");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                String avatar = resultSet.getString("avatar");
                addUserToTables.add(new AddUserToTable(id, nameUser, email, role, avatar));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return addUserToTables;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tableList;
    }
    @Override
    public boolean deleteTable(int id) {
        boolean rowDelete;
        try {
            Connection connection = DataConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement( DELETE_TABLE_SQL);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rowDelete;
    }
}
