/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import Model.Project;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConnectionFactory;

/**
 *
 * @author jefferson
 */
public class ProjectController {
    public void save(Project project){
        
        String sql = "INSERT INTO projects(name,"
                + "description, "
                + "creatdAt, "
                + "updatedAt)"
                + "VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //cria conexão com o banco
            connection = ConnectionFactory.getConnection();
            //cria um PreparedStatement, classe usada para executar a query
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            
            //Executa a sql para inserção dos dados
            statement.execute();
        
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    
    }
    
    public void update(Project project) {
        
        String sql = "UPDATE projects SET"
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // Cria uma conexao com o banco
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            //Executa a sql para inserção de dados 
            statement.execute();
        
        } catch(SQLException ex){
            throw new RuntimeException("Erro em atualizar o projeto", ex);
        
        } finally{
            ConnectionFactory.closeConnection(connection, statement);
        
        }
    
    }
    public List<Project> getAll(){
        
        String sql = "SELECT * FROM projects";
        
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        //classe que vai recuperar os dados do banco de dados
        ResultSet resultSet = null;
        
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdatedAt(resultSet.getDate("updatedAt"));
                
                //Adiciono o contato recuperado, a lista de contatos
                projects.add(project);
            
            }
        
        } catch(SQLException ex){
            throw new RuntimeException("Erro ao buscar os projetos");
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        
        }
        return projects;
    }
    
    public void removeById(int idProject){
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try{
            connection = ConnectionFactory. getConnection();
            statement = connection.prepareCall(sql);
            statement.setInt(1, idProject);
            statement.execute();
        } catch (SQLException ex){
            throw new RuntimeException("Erro ao deletar o projeto", ex);
        
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        
        }
    }
    
}
