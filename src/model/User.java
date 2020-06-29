package model;

import framework.annotation.CustomOrmColumn;
import framework.annotation.CustomOrmRole;
import framework.annotation.CustomOrmTable;
import framework.db.Database;
import framework.db.DatabaseOrm;
import framework.db.exceptoin.CustomOrmException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@CustomOrmTable(tableName = "users")
public class User {

    private static int userId;
    @CustomOrmColumn(columnName = "user_name")
    public String username;
    @CustomOrmColumn(columnName = "user_pass")
    public String pass;
    @CustomOrmColumn(columnName = "user_email")
    public String email;
    @CustomOrmColumn(columnName = "user_fname")
    public String fName;
    @CustomOrmColumn(columnName = "user_lname")
    public String lName;
    @CustomOrmRole(columnName = "role_id")
    public static ArrayList<Integer> roleCollection;

    public static void create(String username, String pass, String email, String fName, String lName) throws SQLException {
        Database.getInstance().insert("users", new HashMap<String, Object>(){{
            put("usermane", username);
            put("password", pass);
            put("email", email);
            put("fname", fName);
            put("lname", lName);
        }}).execute();
        long lastInsertedID = Database.getInstance().getLastInsertedID();
        int userRoleID = 1;
        Database.getInstance().insert("user_roles", new HashMap<String, Object>(){{
            put("user_id", lastInsertedID);
            put("role_id", userRoleID);
        }}).execute();
    }

    public static void create(User user) throws CustomOrmException, SQLException, InstantiationException, IllegalAccessException {
        DatabaseOrm.insert(user);
        long lastInsertedID = Database.getInstance().getLastInsertedID();
        userId = (int) lastInsertedID;
        int userRoleID = 1;
        Database.getInstance().insert("user_roles", new HashMap<String, Object>(){{
            put("user_id", lastInsertedID);
            put("role_id", 1);
        }}).execute();
        roleCollection.add(userRoleID);

    }

}
