import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class BruteFrorceTeamFormation{

	public class Employee{
		String name;
		int skillLevel;
		int id;
		int supervisor;

		public Employee(String name, int id, int skillLevel, int supervisor){
			this.name = name;
			this.id = id;
			this.skillLevel = skillLevel;
			this.supervisor = supervisor;
		}

	}

	public static void main(String[] args){
		List<Employee> Employees = new ArrayList<>();
		//read employees from file
		int maxSize = 2;
		Team BestTeam = BruteForceResults(Employees, maxSize) 

		if (BestTeam != null) {
            System.out.println("The optimal team is: " + BestTeam.getMembers());
            System.out.println("Total skill level is: " + BestTeam.totalSkills);
            System.out.println("Broad skill set is: " + BestTeam.broadSkillSet);
        } 
        else {
            System.out.println("No optimal team was found.");
        }

	}

	public class Team{
		List<String> members;
		int totalSkills;
		int broadSkillSet;

		public Team(List<String> members, int totalSkills, int broadSkillSet){
			this.members = new ArrayList<>(members);
			this.totalSkills = totalSkills;
			this.broadSkillSet = broadSkillSet;

		}

		public List<String> getMembers(){
			return members;
		}

	}

	public Team BruteForceResults(List<Employee> Employees, int maxSize){
		Team BestTeam = null;
		int maxSkills = 0;

		List<Employee> currentCombo = new ArrayList<>();
		for(int size = 2; size<=maxSize; size++){
			FindOptimalTeamCombo(Employees, currentCombo, size, 0)
		}

	}

	static Team BestTeam = null;
	static int maxSkills = 0;

	public void FindOptimalTeamCombo(List<Employee> Employees, List<Employee> currentCombo, int size, int start){
		if (currentCombo.size()==size) {
			if (isTeamValid(currentCombo)){
				int totalSkill = 0;
				int maxSkill = 100; 
				int minSkill = 0;

				for(int i = 0; i<currentCombo.size(); i++){
					int skillLevel = currentCombo.get(i).skillLevel;
					totalSkill += skillLevel;
					if(skillLevel>maxSkill) maxSkill = skillLevel;
					if(skillLevel<minSkill) minSkill = skillLevel;
				}
				int skillDifference = maxSkill - minSkill;

				if (totalSkill > maxSkills) {
					maxSkills = totalSkill;
					BestTeam = new Team(getEmpNames(currentCombo), totalSkill, skillDifference);
				}

			}
			return;
		}
		for(int i = start; ii<Employees.size(); i++){
			currentCombo.add(Employees.get(i));
			FindOptimalTeamCombo(Employees, currentCombo, size, i+1);
			currentCombo.remove(currentCombo.size()-1);
		}

	}

	public boolean isTeamValid(List<Employee> team){
		for(int i = 0; i<team.size(); i++){
			Employee emp1 = team.get(i)

			for(int j = 0; j<team.size(); j++){
				Employee emp2 = team.get(j);

				if(emp1.supervisor != null && emp1.supervisor== emp2.id){
					return false;
				}
			}
		}
		return true;
	}

	public List<String> getEmpNames(List<Employee> team){
		List<String> names = new ArrayList<>();
		for(int i = 0; i<team.size(); i++){
			Employee emp = team.get(i);
			names.add(emp.name);
		}
		return names;
	}

}