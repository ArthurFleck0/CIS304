
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amad Asim
 */
public class CourseData{
    private String fileName = "course.txt";
    private int lineNumber = 0;
    private File cFile = new File(fileName);
    private Scanner cScan;
    private Map<Course, ArrayList<Integer>> courseLine = new HashMap<Course, ArrayList<Integer>>();
    private Map<String, Integer> courseStudentLine = new HashMap<String, Integer>();
    private Map<String, Course> courses = new HashMap<String, Course>();
    public CourseData(){
        
       ensureNewLine();
       updateData();
    }
    
    public void addCourse(String cName){
        cName = cName.trim();
        Course course = new Course(cName);
        courses.put(cName, course);
        ArrayList<Integer> lines = new ArrayList();
        lines.add(lineNumber);
        courseLine.put(course, lines);  
        lineNumber++;
        try{
            FileWriter fw = new FileWriter(cFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cName+"<>" + System.lineSeparator());
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
   private void updateData(){
        lineNumber = 0;
        courseLine.clear();
        courseStudentLine.clear();
        courses.clear();
        try{
            cScan = new Scanner(cFile);
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        
        String tokens[];
        int lineNum = 1;
        String line;
        while(cScan.hasNext()){
            
            line = cScan.nextLine();
            
            if(!line.isEmpty()){
                tokens = line.split("<>");
                if(tokens.length == 2){
                    String courseName = tokens[0];
                    String studentName = tokens[1];
                    Course course = courses.get(courseName);

                    if(course == null){
                        course = new Course(courseName);
                    }

                    ArrayList<Integer> lines = courseLine.get(course);

                    if(lines == null){
                        lines = new ArrayList<Integer> ();
                        courseLine.put(course, lines);
                    }

                    lines.add(lineNum);
                    courseStudentLine.put(courseName+studentName, lineNum);
                    courses.put(courseName, course);
                    course.addStudent(studentName);
                }

                if(tokens.length == 1 ){
                    String courseName = tokens[0];
                    Course course = new Course(courseName);

                    ArrayList<Integer> lines = courseLine.get(course);

                    if(lines == null){
                        lines = new ArrayList<Integer> ();
                        courseLine.put(course, lines);
                    }
                    lines.add(lineNum);
                    courses.put(courseName, course);
                }
            }

            lineNum++;
             
        }
        lineNumber = lineNum;
   }
   public void addStudent(String cName, String sName){
       sName = sName.trim();
       Course course = courses.get(cName);
       course.addStudent(sName);
       ArrayList<Integer> lines = courseLine.get(course);
       courseStudentLine.put(cName + sName, lineNumber);
       lines.add(lineNumber);
       lineNumber++;
       try{
            FileWriter fw = new FileWriter(cFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(cName+"<>"+sName + System.lineSeparator());
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
   }
    public String[] getStudents(String cName){
        Course course = courses.get(cName);
        return course.getStudents();
    }
    
    public void deleteCourse(String cName){
        Course course = courses.get(cName);
        ArrayList<Integer> lines = courseLine.get(course);
        courses.remove(cName);
        course.deleteAllStudents();
        deleteLines(lines);
        courseLine.remove(course);
    }
    
    public void deleteStudent(String cName, List names){
        Course course = courses.get(cName);
        int index = 0;
        ArrayList<Integer> lines = new ArrayList();
        
        while(index < names.size()){
            String sName = names.get(index).toString();
            lines.add(courseStudentLine.get(cName+sName));
            course.deleteStudent(sName);
            index++;
        }
        
        deleteLines(lines);
        
    }
    
    public String[] getCourses(){
        return this.courses.keySet().toArray(new String[0]);
    }
     
  
    private void deleteLines(ArrayList<Integer> lines){
        try{
        File temp = new File("temp.txt");
        FileWriter fw = new FileWriter(temp);
        FileReader fr = new FileReader(cFile);
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);
        int currentLineNum = 1;
        String currentLine;
        while((currentLine = br.readLine()) != null){
            if(!lines.contains(currentLineNum)){
                bw.write(currentLine + System.lineSeparator());
            }
            currentLineNum++;
        }
        bw.close(); 
        br.close();
        
        fw = new FileWriter(cFile);
        fr = new FileReader(temp);
        br = new BufferedReader(fr);
        bw = new BufferedWriter(fw);
        
        while((currentLine = br.readLine()) != null){
            bw.write(currentLine + System.lineSeparator());
        }
        
        bw.close();
        br.close();
        
      }catch(Exception e){
          e.printStackTrace();
      }
        updateData();
    }
    
    
    public void ensureNewLine(){
         try{
        File temp = new File("temp.txt");
        FileWriter fw = new FileWriter(temp);
        FileReader fr = new FileReader(cFile);
        BufferedReader br = new BufferedReader(fr);
        BufferedWriter bw = new BufferedWriter(fw);
        String currentLine;
        while((currentLine = br.readLine()) != null){
                bw.write(currentLine + System.lineSeparator());
        }
        bw.close(); 
        br.close();
        
        fw = new FileWriter(cFile);
        fr = new FileReader(temp);
        br = new BufferedReader(fr);
        bw = new BufferedWriter(fw);
        
        while((currentLine = br.readLine()) != null){
            bw.write(currentLine + System.lineSeparator());
        }
        
        bw.close();
        br.close();
        
      }catch(Exception e){
          e.printStackTrace();
      }
    }
}

