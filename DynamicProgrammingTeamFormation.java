import java.io.*;
import java.util.*;

public class DynamicProgrammingTeamFormation {
    static class Employee {
        int id;
        String name;
        int skillLevel;
        List<Employee> subordinates;

        Employee(int id, String name, int skillLevel) {
            this.id = id;
            this.name = name;
            this.skillLevel = skillLevel;
            this.subordinates = new ArrayList<>();
        }
    }
