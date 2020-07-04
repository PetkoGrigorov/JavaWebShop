package model;

import framework.annotation.CustomOrmColumn;
import framework.annotation.CustomOrmTable;
import framework.db.DatabaseOrm;
import framework.db.exceptoin.CustomOrmException;

import java.sql.SQLException;
import java.util.ArrayList;

@CustomOrmTable(tableName = "products")
public class Product {

    private static int productId;
    @CustomOrmColumn(columnName = "title")
    private String title;
    @CustomOrmColumn(columnName = "description")
    private String description;

    public String getTitle() {
        return title;
    }

    public static ArrayList<Product> fetchAll()
            throws CustomOrmException, SQLException, InstantiationException, IllegalAccessException {
        ArrayList<Object> collection = DatabaseOrm.fetchAll(Product.class);;
        ArrayList<Product> productCollection = new ArrayList<>();
        for (Object element : collection) {
            productCollection.add((Product) element);
        }
        return  productCollection;

    }




}
