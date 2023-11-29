import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * Create the Student and Priorities classes here.
 */
class Student {
    int id;
    double cgpa;
    String name;
    public Student(int id,String name, double cgpa){
        this.id=id;
        this.cgpa=cgpa;
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public int getID(){
        return id;
    }
    public double getCGPA(){
        return cgpa;
    }
}
class Priorities{
    public List<Student> getStudents(List<String> events){
        List<String[]> names=new ArrayList<>();
        List<Student> studs=new ArrayList<>();
        int j=-1;
        for(String i:events){
            String[] details=i.split(" ");
            if(details[0].equals("ENTER")){
                j+=1;
                if(details[2].length()==3){details[2]+="0";}
                names.add(details);
            }
            else{
                int max=0;
                for(int k=1;k<=j;k++){
                    String a1=names.get(max)[2]+names.get(max)[1]+names.get(max)[3];
                    String a2=names.get(k)[2]+names.get(k)[1]+names.get(k)[3];
                    if(a1.compareTo(a2)<0){
                        max=k;
                    }
                }
                names.remove(max);
            }
        }
        for(String[] i:names){
            studs.add(new Student(Integer.parseInt(i[3]),i[1],Double.parseDouble(i[2])));
        }
    return studs;    
    }
}

public class Solution {
    private final static Scanner scan = new Scanner(System.in);
    private final static Priorities priorities = new Priorities();
    
    public static void main(String[] args) {
        int totalEvents = Integer.parseInt(scan.nextLine());    
        List<String> events = new ArrayList<>();
        
        while (totalEvents-- != 0) {
            String event = scan.nextLine();
            events.add(event);
        }
        
        List<Student> students = priorities.getStudents(events);
        
        if (students.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student st: students) {
                System.out.println(st.getName());
            }
        }
    }
}