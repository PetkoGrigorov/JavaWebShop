package model;

import framework.annotation.CustomOrmColumn;
import framework.annotation.CustomOrmTable;
import framework.db.Database;
import framework.db.DatabaseOrm;
import framework.db.exceptoin.CustomOrmException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@CustomOrmTable(tableName = "products")
public class Product {

    @CustomOrmColumn(columnName = "id")
    private String productId;
    @CustomOrmColumn(columnName = "title")
    private String title;
    @CustomOrmColumn(columnName = "description")
    private String description;
    @CustomOrmColumn(columnName = "price")
    private String price;

    public String getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<Product> fetchAll()
            throws CustomOrmException, SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Object> collection = DatabaseOrm.fetchAll(Product.class);
        ArrayList<Product> productCollection = new ArrayList<>();
        for (Object element : collection) {
            productCollection.add((Product) element);
        }
        return  productCollection;
    }

    public static ArrayList<Product> fetchLimitAll(int limit, int offset) throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Product> collection = new ArrayList<>();
        ResultSet result = Database.getInstance().selectAll("products").limit(limit, offset).printQuery().fetch();
        while (result.next()) {
            collection.add((Product) DatabaseOrm.fetch(Product.class, result));
        }
        return collection;
    }

    public static ArrayList<Product> fetchLimitLike(String column, String likeValue, int limit, int offset) throws SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Product> collection = new ArrayList<>();
        ResultSet result = Database.getInstance().selectAll("products").like(column, likeValue).limit(limit, offset).printQuery().fetch();
        while (result.next()) {
            collection.add((Product) DatabaseOrm.fetch(Product.class, result));
        }
        return collection;
    }

    public static int getCountAll() throws SQLException {
        int productCount = 0;
        ResultSet resultSet = Database.getInstance().count("products").printQuery().fetch();
        while (resultSet.next()) {
            productCount = resultSet.getInt("entry_count");
        }
        return productCount;
    }

    public static int getCountLike(String column, String likeValue) throws SQLException {
        int productCount = 0;
        ResultSet resultSet = Database.getInstance().count("products").like(column, likeValue).printQuery().fetch();
        while (resultSet.next()) {
            productCount = resultSet.getInt("entry_count");
        }
        return productCount;
    }


    public static Product fetchProductByID(int id)
            throws SQLException, InstantiationException, IllegalAccessException {
        ResultSet result = Database.getInstance().selectAll("products")
                            .where(new Database.WhereClause("id", Database.WhereOperator.EQUAL, id))
                            .printQuery().fetch();
        while (result.next()) {
            return (Product) DatabaseOrm.fetch(Product.class, result);
        }
        return null;
    }




}
