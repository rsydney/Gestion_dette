package com.ism.repository.bd;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.reflect.Field;


public abstract class RepositoryImplBD<T> extends DataServiceImpl<T>{

     public boolean insert(T data,String sql)throws SQLException, IllegalAccessException, NoSuchFieldException {
        boolean nbrLigne =false;
        String query =generateSql(sql);
        try (Connection conn =getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setFieldData(stmt,data);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Field idField =data.getClass().getDeclaredField("id");
                        idField.setAccessible(true);
                        idField.set(data, generatedKeys.getInt(1));
                        return true;
                    } else {
                        throw new SQLException("Échec de la création du client, aucun ID généré.");
                    }
                }
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally {
            closeConnexion();
        }
        return nbrLigne;
    }
  
}
