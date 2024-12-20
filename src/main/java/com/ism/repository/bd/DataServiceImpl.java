package com.ism.repository.bd;

import java.sql.*;
import java.lang.reflect.Field;
import com.ism.repository.core.Database;

public class DataServiceImpl<T> implements Database<T>{
        String ConnectionBDMysql="";
        String ConnectionBDPostgresql="";
        
        final String driver = "org.postgresql.Driver";
        final String url = "jdbc:postgresql://localhost:5432/projet_java";
        final String user = "postgres"; 
        final String password = "23102003";
       

        /*final String driver="com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/gestion_dette_java";
        final String user = "root";
        final String password = "";*/
        
        private Connection conn = null;
    @Override
    public PreparedStatement getPs() {
        return ps;
    }

    private PreparedStatement ps;

    @Override
    public void openConnection() {
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur de chargement de driver %s",DataServiceImpl.class);
        }
    }

    @Override
    public void closeConnexion() {
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur de Fermeture de connexion");
            }
        }
    }
    @Override
    public ResultSet executeSelect(String sql) {
        ResultSet rs=null;
        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erreur de execution request");
        }
        return rs;
    }

    @Override
    public int executeUpdate(String sql) {
        int nbrLigne=0; try {
            ps=conn.prepareStatement(sql);
            nbrLigne=ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur de execution request");
        }
        return nbrLigne;
    }
    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        return DriverManager.getConnection(url,user,password);
    }
protected PreparedStatement prepareStatement(Connection conn, String sql, Object... params) throws SQLException {
    PreparedStatement statement = conn.prepareStatement(sql);
    for (int i = 0; i < params.length; i++) {
        statement.setObject(i + 1, params[i]);
    }
    return statement;
}

@Override
public String generateSql(String sql) {
    return sql;
}

@Override
public void setFieldData(PreparedStatement stmt, T data) throws SQLException,IllegalAccessException {
    Field[] fields = data.getClass().getDeclaredFields();
    int index =1;
    for(Field field : fields) {
        field.setAccessible(true); 
       if (field.getName().equalsIgnoreCase("id")) {
            continue; 
        }
        Object value =field.get(data); 
        if (value != null) {
            if (field.getType() == int.class || field.getType() == Integer.class) {
                stmt.setInt(index, (Integer) value);
            } else if (field.getType() == String.class) {
                stmt.setString(index, (String) value);
            } else if (field.getType() == double.class || field.getType() == Double.class) {
                stmt.setDouble(index, (Double) value);
            } else if (field.getType() == float.class || field.getType() == Float.class) {
                stmt.setFloat(index, (Float) value);
            } else if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                stmt.setBoolean(index, (Boolean) value);
            }
            index++;
        }
    }
}
public void initPreparedStatement(String sql) throws SQLException{
    if(sql.toUpperCase().trim().startsWith("INSERT")){
        ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }else{
        ps=conn.prepareStatement(sql);
    }

}

@Override
public T convertToObject(ResultSet rs) throws SQLException {

    throw new UnsupportedOperationException("Unimplemented method 'convertToObject'");
}


}
