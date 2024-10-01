import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

class Employee()
{

    int ID;
    Employee Supervisor;
    String Name ;
    int skillLevel ;


 public Employee(int ID , String Name , int skillLevel ){
    
    this.ID = ID;
    this.skillLevel = skillLevel;
    this.Name = Name;
    this.Supervisor = null; // set later 
    }

    public String toString(){
        return Name + "("+skillLevel+")";
    
    }

}


public class TeamFoarming{
    private List<Employee> employees= newArrayList<>();  //list of employees info

}

//read all amployees from the file and store them in employees list
public void ReadEmployees(String /*file path*/){ 
    try(BufferedReader Br = new BufferedReader(new FileReader(/*file path */))){
        String Line ;
        while((Line = Br.readLine() ) != null){
            String [] cutpart = Line.split(":"); // Array of String to store and part the info from the file 

            int SupervisorId = Integer.parseInt(cutpart[0].trim());
            String Name =
            int ID = 
            int skillLevel =

            Employee employee = new Employee(ID, Name, skillLevel);
            employees.add(employee);

            if(SupervisorId >= 0 ){
                employee.Supervisor = employees.get(SupervisorId);
            }
        }
    }
}


    public OptimalTeams(){
        int maxSkill =0;
        List<Employee> BestTeam = null;
        //two for loops over the employees list to compare Skill level

    }


    private boolean isValidTeam(Employee E1 , Employee E2){ 
        return E1.Supervisor !=E2 && E2.Supervisor != E1;
    }
