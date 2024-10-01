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
    private static List<List<Employee>> BestTeam = new ArrayList<>();



public static void main(){
ReadEmployees(/*file path*/);
int maxSize;

OptimalTeams(employees, maxSize)

System.out.println("Maximum Skill level: " + maxSkill);
    for (int i = 0; i <   BestTeam.size(); i++) {
    List<Employee> team = BestTeam.get(i);
    System.out.print("Best Team is: ");
    for (int j = 0; j < team.size(); j++) {
        Employee member = team.get(j);
        System.out.print(member.name + " ");
    }
    System.out.println();
}

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


    public OptimalTeams(Employee[] employees, int maxSize){
        int maxSkill =0;
        List<List<Employee>> combos = new ArrayList<>();
        List<Employee> BestTeam = null;
        comboGenerator(employees, 0, new ArrayList<>(), maxSize, combos);

        //two for loops over the employees list to compare Skill level
    for (int i = 0; i < combos.size(); i++) {
    List<Employee> team = combos.get(i);
    if (isValidTeam(team)) {
        int skill = skillTotal(team);
        if (skill > maxSkill) {
            maxSkill = skill;
            BestTeam.clear();
            BestTeam.add(new ArrayList<>(team));
        } else if (skill == maxSkill) {
            BestTeam.add(new ArrayList<>(team));
        }
    }
}

    }


    private static void comboGenerator(Employee[] employees, int start, List<Employee> currentCombo, int maxSize, List<List<Employee>> theTeam) {
        if (currentCombo.size() > maxSize) {
            return;
        }

        if (!currentCombo.isEmpty()) {
            result.add(new ArrayList<>(currentCombo));
        }

        for (int i = start; i < employees.length; i++) {
            currentCombo.add(employees[i]);
            comboGenerator(employees, i + 1, currentCombo, maxSize, theTeam);
            currentCombo.remove(currentCombo.size() - 1); // Backtrack
        }
    }


    private boolean isValidTeam(Employee E1 , Employee E2){ 
        return E1.Supervisor !=E2 && E2.Supervisor != E1;
    }

    private int skillTotal(List<Employee> team){
        int skills = 0;
        for (int j = 0; j < team.size(); j++) {
            Employee member = team.get(j);
            skills += member.skillLevel;
        }
        return skills
    }
}