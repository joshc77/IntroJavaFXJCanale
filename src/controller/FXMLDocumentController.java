/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.Student;

/**
 *
 * @author Josh
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button buttonReadByPhone;

    @FXML
    private Button buttonCreateStudent;

    @FXML
    private Button buttonRead;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonReadByName;

    @FXML
    private Button buttonReadByEmail;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonReadByID;

    @FXML
    void createStudent(ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        System.out.println("Enter Email:");
        String email = input.next();
        
        System.out.println("Enter Phone Number:");
        String phone = input.next();
        
        Student student = new Student(); //The code here and below is guided from the demo
        
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        
        create(student);
    }

    @FXML
    void readStudent (ActionEvent event) {
        
    }
    
    @FXML
    void readByIdName (ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        List<Student> students = readByIdName(id, name);
        System.out.println(students.toString());
    }

    @FXML
    void readByID(ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Student s = readByID(id);
        System.out.println(s.toString());
    }

    @FXML
    void readByName(ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        List<Student> s = readByName(name);
        System.out.println(s.toString());
    }

    @FXML
    void readByPhone(ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Phone Number:");
        String phone = input.next();
        
        List<Student> s = readByPhone(phone);
        System.out.println(s.toString());
    }

    @FXML
    void readByEmail(ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter Email:");
        String email = input.next();
        
        Student s = readByEmail(email);
        System.out.println(s.toString());
    }

    @FXML
    void updateStudent(ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        System.out.println("Enter Name:");
        String name = input.next();
        
        System.out.println("Enter Email:");
        String email = input.next();
        
        System.out.println("Enter Phone Number:");
        String phone = input.next();
        
        Student student = new Student(); //The code here and below is guided from the demo
        
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        
        update(student);
    }

    @FXML
    void deleteStudent(ActionEvent event) {
        //This code snippet is from the demo
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter ID:");
        int id = input.nextInt();
        
        Student s = readByID(id);
        System.out.println("we are deleting this student: " + s.toString());
        delete(s);

    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
        Query query = manager.createNamedQuery("Student.findAll"); //this code snippet is from demo project
        List<Student> data = query.getResultList(); 
        
        for (Student s : data) {            
            System.out.println(s.getId() + " " + s.getName()+ " " + s.getEmail() + " " + s.getPhone());         
        }           

    }
    
    @FXML
    void searchTable(ActionEvent event) {
        System.out.println("Clicked");
    }
    
    //This code is from demo project
     // Database manager
    EntityManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        // loading data from database
        //database reference: "IntroJavaFXJCanalePU"
        manager = (EntityManager) Persistence.createEntityManagerFactory("IntroJavaFXJCanalePU").createEntityManager();
    }        
    
    public void create(Student student) {
        //the code below is from demo project
        
        try {
            manager.getTransaction().begin();
            
            if(student.getId() != null) {
                manager.persist(student);
                manager.getTransaction().commit();
                
                System.out.println(student.toString() + " is created.");
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public Student readByID(int id) {
        //This code snippet is from the demo
        Query query = manager.createNamedQuery("Student.findById");
        
        query.setParameter("id", id);
        
        Student student = (Student) query.getSingleResult();
        if(student != null) {
            System.out.println(student.getId() + " " + student.getName() + " " + student.getEmail() + " " + student.getPhone());
        }
        
        return student;
    }
    
    public List<Student> readByName(String name) {
        //This code snippet is from the demo
        Query query = manager.createNamedQuery("Student.findByName");
        
        query.setParameter("name", name);
        
        List<Student> students = query.getResultList();
        for (Student student: students) {
            System.out.println(student.getId() + " " + student.getName() + " " + student.getEmail() + " " + student.getPhone());
        }
        
        return students;
    }
    
    public List <Student> readByIdName(int id, String name) {
        //This code snippet is from the demo
        Query query = manager.createNamedQuery("Student.findByIdName");
        
        query.setParameter("id", id);
        query.setParameter("name", name);

        
        List<Student> students = query.getResultList();
        for (Student student: students) {
            System.out.println(student.getId() + " " + student.getName() + " " + student.getEmail() + " " + student.getPhone());
        }
        
        return students;
    }
    
    public Student readByEmail(String email) {
        //This code snippet is from the demo
        Query query = manager.createNamedQuery("Student.findByEmail");
        
        query.setParameter("email", email);
        
        Student student = (Student) query.getSingleResult();
        if(student != null) {
            System.out.println(student.getId() + " " + student.getName() + " " + student.getEmail() + " " + student.getPhone());
        }
        
        return student;
    }
    
    public List<Student> readByPhone(String phone) {
        //This code snippet is from the demo
        Query query = manager.createNamedQuery("Student.findByPhone");
        
        query.setParameter("phone", phone);
        
        List<Student> students = query.getResultList();
        for (Student student: students) {
            System.out.println(student.getId() + " " + student.getName() + " " + student.getEmail() + " " + student.getPhone());
        }
        
        return students;
    }
    
    public void update(Student model) {
        //the code below is from demo project
        
        try {
            Student existingStudent = manager.find(Student.class, model.getId());
            
            if(existingStudent != null) {
                manager.getTransaction().begin();
                
                existingStudent.setName(model.getName());
                existingStudent.setEmail(model.getEmail());
                existingStudent.setPhone(model.getPhone());
                
                manager.getTransaction().commit();
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    public void delete(Student student) {
        //the code below is from demo project
        
        try {
            Student existingStudent = manager.find(Student.class, student.getId());
            
            if(existingStudent != null) {
                manager.getTransaction().begin();
                
                manager.remove(existingStudent);
                
                manager.getTransaction().commit();
            }
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
