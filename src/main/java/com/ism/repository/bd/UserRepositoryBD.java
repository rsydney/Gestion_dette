package com.ism.repository.bd;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ism.entity.Client;
import com.ism.entity.Dette;
import com.ism.entity.User;
import com.ism.repository.UserRepositoryInter;

public class UserRepositoryBD extends DataServiceImpl<User> implements UserRepositoryInter{
    private final ClientRepositoryBD clientRepositoryBD;

    public UserRepositoryBD(ClientRepositoryBD clientRepositoryBD) {
        this.clientRepositoryBD = clientRepositoryBD;
    }
    public boolean insert(User user) {
        boolean nbrLigne =false;
        String query = "INSERT INTO user (login, surname, password, id_client, role) VALUES (?,?,?,?,?,?,?);";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1,  user.getLogin());
            stmt.setString(2,  user.getSurname());
            stmt.setString(4,  user.getPassword());
            stmt.setInt(5, user.getClient().getId());
            stmt.setString(6, user.getRole());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Échec de la création de la transaction, aucun ID généré.");
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally{
            closeConnexion();
        }
        return nbrLigne;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM user WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
            return false;
        }finally{
            closeConnexion();
        }
    }

    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Connection conn =getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setSurname(rs.getString("nom"));
                user.setPassword(rs.getString("password"));
                user.setClient(clientRepositoryBD.selectById(rs.getInt("id_client")));
                String roleId = rs.getString("role");
                user.setRole(roleId);
                users.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally{
            closeConnexion();
        }
        return users;
    }

     public  User selectByLogin(String login){
        User user = null;
        String query = "SELECT * FROM user WHERE login = ?";
        try (Connection conn =getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user= new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setSurname(rs.getString("nom"));
                    user.setPassword(rs.getString("password"));
                    user.setClient(clientRepositoryBD.selectById((rs.getInt("id_client"))));
                    String roleId = rs.getString("role");
                    user.setRole(roleId);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally{
            closeConnexion();
        }
        return user;
       
    }
    public List<User> selectByRole(String role) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user WHERE role = ?";
        try (Connection conn =getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, role);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setLogin(rs.getString("login"));
                    user.setSurname(rs.getString("nom"));
                    user.setPassword(rs.getString("password"));
                    user.setClient(clientRepositoryBD.selectById(rs.getInt("id_client")));
                    String roleId = rs.getString("role");
                    user.setRole(roleId);
                    users.add(user);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally{
            closeConnexion();
        }
    
        return users;
    }
    @Override
    public void add(User entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    @Override
    public Optional<User> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    @Override
    public Optional<User> findBy(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }
    @Override
    public User findByLogin(String login) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLogin'");
    }
    @Override
    public List<User> getUsersByRole(String role) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsersByRole'");
    }
    @Override
    public List<User> getActiveUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActiveUsers'");
    }
    @Override
    public List<Dette> getUnpaidDebtsForUser(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnpaidDebtsForUser'");
    }
    @Override
    public List<Client> getClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClients'");
    }    
}
