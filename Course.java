
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amad Asim 
 */
public class Course {
    private String courseName;
    private ArrayList<String> students = new ArrayList();
    public Course(String cName){
        this.courseName = cName;
        
    }
    
    public String[] getStudents(){
        return this.students.toArray(new String[0]);
    }
    
    public void addStudent(String sName){
        students.add(sName);
    }
    
    public void deleteAllStudents(){
        this.students.clear();
    }
    
    public void deleteStudent(String student){
        this.students.remove(student);
    }
    
    
    
}
