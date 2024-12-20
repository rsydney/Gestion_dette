package com.ism.repository.bd;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ism.entity.Article;
import com.ism.repository.ArticleRepositoryInter;

public class ArticleRepositoryBD extends DataServiceImpl<Article> implements ArticleRepositoryInter{
    public boolean insert(Article article) {
        boolean nbrLigne =false;
        String querypg="INSERT INTO article (reference, libelle, prix, qteStock) VALUES ($1, $2, $3, $4)";
        
        String query =  "INSERT INTO article (reference, libelle, prix, qteStock) VALUES (?, ?, ?, ?);";
        try (Connection conn =getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(querypg, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, article.getId());
            stmt.setString(2, article.getName());
            stmt.setDouble(3, article.getPrice());
            stmt.setInt(4, article.getQuantity());
    
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        article.setId(generatedKeys.getInt(1));
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
    

    public boolean delete(int id) {
        String query = "DELETE FROM article WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
            return false;
        }finally {
            closeConnexion();
        }
    }

    public List<Article> selectAll() {
        List<Article> articles = new ArrayList<>();
        String query = "SELECT * FROM article";
        try (Connection conn =getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Article article= new Article();
                article.setId(rs.getInt("id"));
                article.setName(rs.getString("libelle"));
                article.setPrice(rs.getInt("prix"));
                article.setQuantity(rs.getInt("qteStock"));
                articles.add(article);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally {
            closeConnexion();
        }
        return articles;
    }

    public  Article selectByQte(){
        Article article = null;
        String query ="SELECT * FROM article WHERE qteStock!=0";
        try (Connection conn =getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setName(rs.getString("libelle"));
                    article.setPrice(rs.getInt("prix"));
                    article.setQuantity(rs.getInt("qteStock"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally {
            closeConnexion();
        }
        return article;
      
    }
     public  Article selectById(int id){
        Article article = null;
        String query = "SELECT a.* FROM article a WHERE a.id = ?";
        try (Connection conn =getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    article = new Article();
                    article.setId(rs.getInt("id"));
                    article.setName(rs.getString("libelle"));
                    article.setPrice(rs.getInt("prix"));
                    article.setQuantity(rs.getInt("qteStock"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally {
            closeConnexion();
        }
        return article;
       
    }
    public  Article selectBylibelle(String libelle){
        Article article = null;
        String query = "SELECT * FROM article WHERE libelle = ?";
        try (Connection conn =getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, libelle);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    article= new Article();
                    article.setId(rs.getInt("id"));
                    article.setName(rs.getString("libelle"));
                    article.setPrice(rs.getInt("prix"));
                    article.setQuantity(rs.getInt("qteStock"));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur de connexion ou d'exécution : " + e.getMessage());
        }finally {
            closeConnexion();
        }
        return article;
       
    } 
    public boolean updateArticle(Article article) {
        String query = "UPDATE article SET reference = ?, libelle = ?, prix = ?, qteStock = ? WHERE id = ?";
        
        try (Connection conn =getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, article.getId());
            stmt.setString(2, article.getName());
            stmt.setDouble(3, article.getPrice());
            stmt.setInt(4, article.getQuantity());
            stmt.setInt(5, article.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erreur lors de la mise à jour de l'article : " + e.getMessage());
            return false;
        }finally {
            closeConnexion();
        }
    }


    @Override
    public void add(Article entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }


    @Override
    public List<Article> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }


    @Override
    public Optional<Article> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }


    @Override
    public Optional<Article> findBy(String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBy'");
    }


    @Override
    public List<Article> getAvailableArticles() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvailableArticles'");
    }


    @Override
    public Article findByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByName'");
    }

    /*@Override
    public boolean insert(Article article) {
        String query = "INSERT INTO article (reference, libelle, prix, qteStock) VALUES (?, ?, ?, ?)";
        try {
            return insertArticle(article, query);
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            System.out.println("Erreur lors de l'insertion de l'article : " + e.getMessage());
            return false;
        }
    }

    public boolean insertArticle(Article article, String query) throws SQLException, IllegalAccessException, NoSuchFieldException {
        return insert(article, query);
    }*/
      
}
