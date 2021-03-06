package framework.db;

import framework.config.ConfigDB;
import model.User;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = ConfigDB.getUrl();
    private final String DB_USER = ConfigDB.getUser();
    private final String DB_PASS = ConfigDB.getPass();
    private Connection dbConnection;
    private Statement dbStatement;
    private String queryBuilder;
    private static Database instance;

    public static Database getInstance() {
        if (instance == null) {
            return new Database();
        }
        return instance;
    }

    private Database() {
        try {
            Class.forName(this.DB_DRIVER);
            this.dbConnection = DriverManager.getConnection(this.DB_URL, this.DB_USER, this.DB_PASS);
            this.dbStatement = this.dbConnection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getDbStatement() {
        return this.dbStatement;
    }

    public String getQueryBuilder() {
        return queryBuilder;
    }

    public static String stringValue(String value) {
        return "'" + value + "'";
    }

    public static int intValue(String value) {
        return Integer.parseInt(value.trim());
    }

    public Database selectAll(String tableName) {
        String query = "SELECT * FROM ";
        query += tableName;
        this.queryBuilder = query;
        return this;
    }

   /* public framework.db.Database select(String tableName, List<String> columnList) {
        String query = "SELECT ";
        for (String element : columnList) {
            query += element + ", ";
        }
        query = query.substring(0, query.length() - 2);
        query += " FROM " + tableName;
        this.queryBuilder = query;
        return this;
    }*/

    /*public framework.db.Database select(String tableName, HashMap<String, String> columnWithAliasMap) {
        String query = "SELECT ";
        for (Map.Entry<String, String> element : columnWithAliasMap.entrySet()) {
            String columnName = element.getKey();
            String columnAlias = element.getValue();
            if (columnAlias == null) {
                query += columnName + ", ";
                continue;
            }
            if (columnAlias.equals("")) {
                query += columnName + ", ";
                continue;
            }
            query += columnName + " AS " + columnAlias + ", ";
        }
        query = query.substring(0, query.length() - 2);
        query += " FROM " + tableName;
        this.queryBuilder = query;
        return this;
    }*/

//    public WhereCondition getWhereCondition(String column, WhereOperator operator, String value, WhereRelationNex relationNex) {
//        return new WhereCondition(column, operator, value, relationNex);
//    }

    public static class WhereClause {
        String column;
        Object value;
        WhereOperator operator;
        WhereRelationNext relationNext;
        String fromTable;

        public WhereClause(String column, WhereOperator operator, Object value, WhereRelationNext relationNext, String fromTable) {
            this.column = column;
            this.value = value;
            this.operator = operator;
            this.relationNext = relationNext;
            this.fromTable = fromTable;
        }

        public WhereClause(String column, WhereOperator operator, Object value, WhereRelationNext relationNext) {
            this.column = column;
            this.value = value;
            this.operator = operator;
            this.relationNext = relationNext;
            this.fromTable = null;
        }

        public WhereClause(String column, WhereOperator operator, Object value) {
            this.column = column;
            this.value = value;
            this.operator = operator;
            this.relationNext = null;
            this.fromTable = null;
        }

        public WhereClause(String column, WhereOperator operator, Object value, String fromTable) {
            this.column = column;
            this.value = value;
            this.operator = operator;
            this.relationNext = null;
            this.fromTable = fromTable;
        }
    }

    public enum WhereOperator {
        GREATER, LOWER, EQUAL
    }

    public enum WhereRelationNext {
        AND, OR
    }

    public Database where(List<WhereClause> whereClauseList) {
        String query = " WHERE ";
        for (WhereClause element : whereClauseList) {
            String value = getWhereValue(element);
            String column = element.column;
            String operator = getWhereOperator(element);
            String relationNext;
            String tab = (element.fromTable == null || element.fromTable.trim().isEmpty()) ? "" : element.fromTable + ".";
            relationNext = (element.relationNext == null) ? "" : element.relationNext.toString();
            query += tab + column + operator + value + " " + relationNext + " ";
        }
        query = query.substring(0, query.length() - 1);
        this.queryBuilder += query;
        return this;
    }

    public Database where(WhereClause whereClause) {
        this.queryBuilder += " WHERE " + getWhereElements(whereClause);
        return this;
    }

    public Database like(String column, Object likeValue) {
        this.queryBuilder += " WHERE " + column + " LIKE " + "\"%" + likeValue + "%\"";
        return this;
    }

    public Database count(String tableName) {
        this.queryBuilder = "SELECT COUNT(*) AS entry_count FROM " + tableName;
        return this;
    }

    public Database andWhere(WhereClause whereClause) {
        this.queryBuilder += " AND " + getWhereElements(whereClause);
        return this;
    }

    public Database orWhere(WhereClause whereClause) {
        this.queryBuilder += " OR " + getWhereElements(whereClause);
        return this;
    }

    private String getWhereElements(WhereClause whereClause) {
        String fromTable = (whereClause.fromTable == null || whereClause.fromTable.trim().isEmpty()) ? "" : whereClause.fromTable + ".";
        String column = whereClause.column;
        String operator = getWhereOperator(whereClause);
        String value = getWhereValue(whereClause);
        return fromTable + column + operator + value;
    }

    private String getWhereOperator(WhereClause whereClause) {
        String operator = "";
        switch (whereClause.operator) {
            case LOWER:
                operator = "<";
                break;
            case GREATER:
                operator = ">";
                break;
            case EQUAL:
                operator = "=";
            default:
                break;
        }
        return operator;
    }

    private String getWhereValue(WhereClause whereClause) {
        String value = "";
        if (whereClause.value instanceof Integer){
            value = intValue(whereClause.value.toString()) + "";
        } else {
            value = stringValue(whereClause.value.toString());
        }
        return value;
    }



    public Database limit(int limit, int offset) {
        this.queryBuilder += " LIMIT " + offset + ", " + limit;
        return this;
    }

    public Database limit(int limit) {
        this.queryBuilder += " LIMIT " + limit;
        return this;
    }

    public ResultSet fetch() {
        try {
            return this.dbStatement.executeQuery(this.queryBuilder);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean execute() {
        try {
            return this.dbStatement.execute(this.queryBuilder, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getLastInsertedID() throws SQLException {
        System.out.println("Before getGeneratedKeys");
        ResultSet generatedKeyCollection = this.dbStatement.getGeneratedKeys();
        System.out.println("After getGeneratedKeys");
        generatedKeyCollection.next();
        return generatedKeyCollection.getLong(1);
    }

    public Database insert(String tableName, HashMap<String, Object> inputHashMap) {
        String keyCollection = "";
        String valueCollection = "";

        for (Map.Entry<String, Object> element : inputHashMap.entrySet()) {
            keyCollection += element.getKey() + ", ";
            if (element.getValue() instanceof Integer) {
                valueCollection += intValue(element.getValue().toString()) + ", ";
            } else {
                valueCollection += stringValue((String) element.getValue()) + ", ";
            }
        }
        keyCollection = keyCollection.substring(0, keyCollection.length() - 2);
        valueCollection = valueCollection.substring(0, valueCollection.length() - 2);
        this.queryBuilder = "INSERT INTO " + tableName + " (" + keyCollection + ") VALUE (" + valueCollection + ")";
//        System.out.println(this.queryBuilder);
        return this;
    }

    public Database update(String tableName, HashMap<String, Object> updateHashMap) {
        String updateCollection = "";
        for (Map.Entry<String, Object> element : updateHashMap.entrySet()) {
            if (element.getValue() instanceof Integer) {
                updateCollection += element.getKey() + "=" + intValue(element.getValue().toString()) + ", ";
                continue;
            }
            updateCollection += element.getKey() + "=" + stringValue(element.getValue().toString()) + ", ";
        }
        updateCollection = updateCollection.substring(0, updateCollection.length() - 2);
        this.queryBuilder = "UPDATE " + tableName + " SET " + updateCollection;
//        System.out.println(this.queryBuilder);
        return this;
    }

    public static class Column {
        String column;
        String fromTable;
        String columnAlias;

        public Column() {
            this.column = null;
            this.fromTable = null;
            this.columnAlias = null;

        }

        public Column(String column) {
            this.column = column;
            this.fromTable = null;
            this.columnAlias = null;

        }

        public Column(String column, String fromTable) {
            this.column = column;
            this.fromTable = fromTable;
            this.columnAlias = null;
        }


        public Column(String column, String fromTable, String columnAlias) {
            this.column = column;
            this.fromTable = fromTable;
            this.columnAlias = columnAlias;
        }
    }

    public static class Table {
        String table;
        String tableAlias;

        public Table(String table) {
            this.table = table;
            this.tableAlias = null;
        }

        public Table(String table, String tableAlias) {
            this.table = table;
            this.tableAlias = tableAlias;
        }
    }

    public Database masterSelect(List<Column> columnList, List<Table> tableList) {
        String query = "SELECT ";
        String col = "";
        String tab = "";
        String ali = "";
        for (Column element : columnList) {
            col = (element.column == null) ? "" : element.column;
            tab = (element.fromTable == null) ? "" : element.fromTable + ".";
            ali = (element.columnAlias == null) ? "" : " AS " + element.columnAlias;
            query += tab + col + ali + ", ";
        }
        query = query.substring(0, query.length() - 2) + " FROM ";

        String tabName;
        String tabAli;
        for (Table element : tableList) {
            tabName = (element.table == null) ? "" : element.table;
            tabAli = (element.tableAlias == null) ? "" : " " + element.tableAlias;
            query += tabName + tabAli + ", ";
        }
        query = query.substring(0, query.length() - 2);

        this.queryBuilder = query;

        return this;
    }

    public Database printQuery() {
        System.out.println(this.queryBuilder);
        return this;
    }





}
