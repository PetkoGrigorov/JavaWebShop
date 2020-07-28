package framework.db;

import framework.annotation.CustomOrmColumn;
import framework.annotation.CustomOrmTable;
import framework.db.exceptoin.CustomOrmException;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseOrm {

    public static ArrayList<Object> fetchAll(Class classReference) 
            throws SQLException, InstantiationException, IllegalAccessException, CustomOrmException {

        ArrayList<Object> objectCollection = new ArrayList<>();
        Field[] allFields = classReference.getDeclaredFields();

        String tableName = getOrmTableName(classReference);
        ResultSet tableResultSet = Database.getInstance().selectAll(tableName).printQuery().fetch();
        while (tableResultSet.next()) {

            objectCollection.add(entryToObjectMapper(classReference, tableResultSet));
        }
        return objectCollection;
    }

    public static Object fetch(Class classReference, ResultSet tableResultSet)
            throws IllegalAccessException, SQLException, InstantiationException {

        return entryToObjectMapper(classReference, tableResultSet);
    }

    public static void insert(Object object) throws CustomOrmException, IllegalAccessException {
        Class classReference = object.getClass();
        String tableName = getOrmTableName(classReference);
        HashMap<String, Object> columnAndValueCollection = objectToEntryMapper(object);
        Database.getInstance().insert(tableName, columnAndValueCollection).execute();
    }

    private static String getOrmTableName(Class classReference) throws CustomOrmException {

        if (classReference.isAnnotationPresent(CustomOrmTable.class)) {
            CustomOrmTable classAnnotation = (CustomOrmTable) classReference.getAnnotation(CustomOrmTable.class);
            return classAnnotation.tableName();
        }
        throw new CustomOrmException("Annotation @CustomOrmTable is required in order to use the method");
    }

    private static String getOrmColumnName(Field classField) {
        classField.setAccessible(true);
        if (classField.isAnnotationPresent(CustomOrmColumn.class)) {
            CustomOrmColumn fieldAnnotation = classField.getAnnotation(CustomOrmColumn.class);
            return fieldAnnotation.columnName();
        }
        return null;
    }

    private static Object entryToObjectMapper(Class classReference, ResultSet tableResultSet)
            throws SQLException, IllegalAccessException, InstantiationException {

        Object classInstance = classReference.newInstance();
        Field[] classFieldsCollection = classReference.getDeclaredFields();
        for (Field field : classFieldsCollection) {
            String columnName = getOrmColumnName(field);
            if (columnName == null) {
                continue;
            }
            field.setAccessible(true);
            field.set(classInstance, tableResultSet.getString(columnName));
        }
        return classInstance;
    }

    private static HashMap<String, Object> objectToEntryMapper(Object object) throws IllegalAccessException {
        HashMap<String, Object> keyAndValueCollection = new HashMap<>();
        Class classReference = object.getClass();
        Field[] classFieldsCollection = classReference.getDeclaredFields();
        for (Field field : classFieldsCollection) {
            String columnName = getOrmColumnName(field);
            if (columnName == null) {
                continue;
            }
            keyAndValueCollection.put(columnName, field.get(object));
        }
        return keyAndValueCollection;
    }





}
