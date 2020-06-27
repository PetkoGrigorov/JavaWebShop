package model;

import framework.annotation.CustomOrmColumn;
import framework.annotation.CustomOrmTable;

@CustomOrmTable(tableName = "users")
public class User {

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


}
