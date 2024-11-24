import java.io.*;
import java.util.*;

public class dynamicprogramingteam {

    static class Employee {
        String name;
        int skillLevel;
        int id;
        Integer supervisor;

        public Employee(String name, int id, int skillLevel, Integer supervisor) {
            this.name = name;
            this.id = id;
            this.skillLevel = skillLevel;
            this.supervisor = supervisor;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Maximum team size from the user
        System.out.print("Please enter the maximum team size: ");
        int maxSize = scanner.nextInt();
        scanner.nextLine();

        // File name that contains employee data
        System.out.print("Please enter the file name: ");
        String fileName = scanner.nextLine();

        // Read employees from the file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                Integer supervisorId = parts[0].trim().equals("null") ? null : Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int id = Integer.parseInt(parts[2].trim());
                int skillLevel = Integer.parseInt(parts[3].trim());
                employees.add(new Employee(name, id, skillLevel, supervisorId));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Find the best team using dynamic programming
        Team bestTeam = findBestTeam(employees, maxSize);

        if (bestTeam != null) {
            System.out.println("Optimal Team: " + String.join(", ", bestTeam.members));
            System.out.println("Total Skill Level: " + bestTeam.totalSkillLevel);
            System.out.println("Skill Difference (broad skill set): " + bestTeam.broadSkillSet);
        } else {
            System.out.println("No optimal team was found.");
        }
    }

    static class Team {
        List<String> members;
        int totalSkillLevel;
        int broadSkillSet;

        public Team(List<String> members, int totalSkillLevel, int broadSkillSet) {
            this.members = members;
            this.totalSkillLevel = totalSkillLevel;
            this.broadSkillSet = broadSkillSet;
        }
    }

    private static Team findBestTeam(List<Employee> employees, int maxSize) {
        int n = employees.size();
        Team[][] dp = new Team[n + 1][maxSize + 1];

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int size = 1; size <= maxSize; size++) {
                // Exclude current employee
                dp[i][size] = dp[i - 1][size];

                // Include current employee if valid
                Employee current = employees.get(i - 1);
                if (size == 1 || (dp[i - 1][size - 1] != null && isTeamValid(current, dp[i - 1][size - 1].members, employees))) {
                    int currentSkillLevel = current.skillLevel;
                    int totalSkill = (dp[i - 1][size - 1] != null ? dp[i - 1][size - 1].totalSkillLevel : 0) + currentSkillLevel;

                    // Calculate skill difference
                    int minSkill = Math.min(currentSkillLevel, dp[i - 1][size - 1] != null ? dp[i - 1][size - 1].broadSkillSet : currentSkillLevel);
                    int maxSkill = Math.max(currentSkillLevel, dp[i - 1][size - 1] != null ? dp[i - 1][size - 1].broadSkillSet : currentSkillLevel);
                    int skillDifference = maxSkill - minSkill;

                    // Create the new team
                    List<String> newTeamMembers = new ArrayList<>();
                    if (dp[i - 1][size - 1] != null) {
                        newTeamMembers.addAll(dp[i - 1][size - 1].members);
                    }
                    newTeamMembers.add(current.name);

                    Team newTeam = new Team(newTeamMembers, totalSkill, skillDifference);

                    // Update DP table if new team is better
                    if (dp[i][size] == null || newTeam.totalSkillLevel > dp[i][size].totalSkillLevel) {
                        dp[i][size] = newTeam;
                    }
                }
            }
        }

        // Find the best team across all sizes
        Team bestTeam = null;
        for (int size = 1; size <= maxSize; size++) {
            if (dp[n][size] != null && (bestTeam == null || dp[n][size].totalSkillLevel > bestTeam.totalSkillLevel)) {
                bestTeam = dp[n][size];
            }
        }

        return bestTeam;
    }

    private static boolean isTeamValid(Employee current, List<String> currentTeam, List<Employee> employees) {
        for (String member : currentTeam) {
            for (Employee emp : employees) {
                if (emp.name.equals(member)) {
                    if (emp.id == current.supervisor || (current.supervisor != null && current.supervisor == emp.id)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

