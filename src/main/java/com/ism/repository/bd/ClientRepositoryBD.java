package com.ism.repository.bd;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ism.entity.Client;
import com.ism.repository.ClientRepositoryInter;
public class ClientRepositoryBD extends DataServiceImpl<Client> implements ClientRepositoryInter  {
    public boolean insert(Client client) {
        boolean nbrLigne = false;
        String queryPg = "INSERT INTO client (surname, telephone, adresse, id_user) VALUES ($1, $2, $3, NULL)";

        String query =  "INSERT INTO client (surname, telephone, adresse, id_user) VALUES (?, ?, ?,NULL)";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, client.getSurname());
                stmt.setString(2, client.getPhone());
                stmt.setString(3, client.getAddress());
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            client.setId(generatedKeys.getInt(1));
                            return true;
                        } else {
                            throw new SQLException("Échec de la création du client, aucun ID généré.");
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        } finally {
            closeConnexion();
        }
        return nbrLigne;
    }
    
    public Client selectBytelephone(String telephone) {
        Client client = null;
        String query = "SELECT * FROM client WHERE telephone = ?";
        Connection conn = null;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, telephone);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        client = new Client();
                        client.setId(rs.getInt("id"));
                        client.setSurname(rs.getString("surname"));
                        client.setPhone(rs.getString("telephone"));
                        client.setAddress(rs.getString("adresse"));
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        } finally {
            closeConnexion();
        }
        return client;
    }
    
    public boolean delete(int id) {
        String query = "DELETE FROM client WHERE id = ?";
        Connection conn = null;
        boolean result = false;
        try {
            conn = getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                result = stmt.executeUpdate() > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        } finally {
            closeConnexion();
        }
        return result;
    }
    

    public List<Client> selectAll() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM client";
        Connection conn = null;
        try {
            conn = getConnection();
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setSurname(rs.getString("surname"));
                    client.setPhone(rs.getString("telephone"));
                    client.setAddress(rs.getString("adresse"));
                    clients.add(client);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        } finally {
            closeConnexion();
        }
        return clients;
    }
    

    public Client selectById(int id) {
        Client client = null;
        String query = "SELECT c.* " +
                       "FROM client c " +
                       "WHERE c.id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = new Client();
                    client.setId(rs.getInt("id"));
                    client.setSurname(rs.getString("surname"));
                    client.setPhone(rs.getString("telephone"));
                    client.setAddress(rs.getString("adresse"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }
        return client;
    }

    @Override
    public void add(Client entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public List<Client> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<Client> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<Client> findBy(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }

    @Override
    public List<Client> getClientsWithAccount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientsWithAccount'");
    }

    @Override
    public List<Client> getClientsWithoutAccount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClientsWithoutAccount'");
    }
 
}