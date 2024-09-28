import java.util.*
import java.lang.Math;




public static void main(){

	String Name[]; 		//list of all employyes
	int id[];           //list of all ids
	int seperiorId[];   //list of seperior ids
	int skills[];       //list of skill levels
 

//read from file by iterating thru the 4 arrays and filling them one by one each iteration

//team optimalTeam = new team(); or do it in brute force?

//call brute force

// print results to user or to file?






}

public class team{
	int memberids[];
	int totalSkills;
	int broadSkillSet;

	public team(int m1, int m2){
		memberids = {m1,m2};
		totalSkills = m1.skills + m2.skills;
		broadSkillSet = Math.abs(m1.skills - m2.skills);

	}

}

public class BruteForce {

	//loop thru all skills
	//loop thru all skills+1
	//calc total skill
	//save in temp
	//compare to best


team teams[];




}