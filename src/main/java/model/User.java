package model;

import framework.annotation.CustomOrmColumn;
import framework.annotation.CustomOrmRole;
import framework.annotation.CustomOrmTable;
import framework.db.Database;
import framework.db.DatabaseOrm;
import framework.db.exceptoin.CustomOrmException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@CustomOrmTable(tableName = "users")
public class User {

    private static int userId;
    @CustomOrmColumn(columnName = "user_name")
    private String username;
    @CustomOrmColumn(columnName = "user_pass")
    private String pass;
    @CustomOrmColumn(columnName = "user_email")
    private String email;
    @CustomOrmColumn(columnName = "user_fname")
    private String fName;
    @CustomOrmColumn(columnName = "user_lname")
    private String lName;
    @CustomOrmRole(columnName = "role_id")
    private static ArrayList<Integer> roleCollection;

    public String getUsername() {
        return this.username;
    }

    public String getUserFullName() {
        return this.fName + " " + this.lName;
    }

    public static void create(final String username, final String pass, final String email, final String fName, final String lName) throws SQLException {
        Database.getInstance().insert("users", new HashMap<String, Object>(){{
            put("user_name", username);
            put("user_pass", pass);
            put("user_email", email);
            put("user_fname", fName);
            put("user_lname", lName);
        }}).printQuery().execute();
//        long lastInsertedID = Database.getInstance().getLastInsertedID();
//        int userRoleID = 1;
//        Database.getInstance().insert("user_roles", new HashMap<String, Object>(){{
//            put("user_id", lastInsertedID);
//            put("role_id", userRoleID);
//        }}).execute();
    }

    public static void create(User user) throws CustomOrmException, SQLException, IllegalAccessException {
        DatabaseOrm.insert(user);
        final long lastInsertedID = Database.getInstance().getLastInsertedID();
        userId = (int) lastInsertedID;
        int userRoleID = 1;
        Database.getInstance().insert("user_roles", new HashMap<String, Object>(){{
            put("user_id", lastInsertedID);
            put("role_id", 1);
        }}).printQuery().execute();
        roleCollection.add(userRoleID);

    }

    public static User fetchUser(String email, String password) {

        User user = null;
        ResultSet tableResultSet = Database.getInstance().selectAll("users")
                .where(new Database.WhereClause("user_email", Database.WhereOperator.EQUAL, email))
                .andWhere(new Database.WhereClause("user_pass", Database.WhereOperator.EQUAL, password))
                .printQuery()
                .fetch();


        try {
            while (tableResultSet.next()) {
                user = (User) DatabaseOrm.fetch(User.class, tableResultSet);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return user;
    }
}
