

package com.mycompany.todoapp;

import Model.Project;
import controler.ProjectController;


public class TodoApp {

    public static void main(String[] args) {
        
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("description");
        projectController.save(project);
    }
}
