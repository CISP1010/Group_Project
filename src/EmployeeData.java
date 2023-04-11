import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import static java.lang.System.out;
import java.util.Scanner;

/**
 * The EmployeeData class represents a collection of employees' data, including their first and last names, positions, section assignments, and schedules.
 */
public class EmployeeData {
    /**
     * A HashMap to store the employee data, with the employee first and last name as the key and an instance of the Employee class as the value.
     */
    private static HashMap<String, Employee> employeeData;

    /**
     * An arraylist to store the Schedule options.
     */
    private static ArrayList<String> scheduleOptions = new ArrayList<>();

    /**
     *
     */
    private static Iterator<String> iter = scheduleOptions.iterator();

    /**
     * The default constructor for the EmployeeData class that initializes the employeeData HashMap and scheduleOptions HashMap.
     */

     Scanner input = new Scanner(System.in);
    public EmployeeData() {
        employeeData = new HashMap<>();
        
        //Adding default schedule options since data is not stored between program runs. 
        scheduleOptions.add("Monday-Friday, 9am-5pm");
        scheduleOptions.add("Tuesday-Saturday, 10am-6pm");
        scheduleOptions.add("Wednesday-Sunday, 11am-7pm");
    }

    public void editScheduleOptions(int schedule, String newSchedule){
        scheduleOptions.add(newSchedule);
    }

    public void remScheduleOptions(int schedule){
        scheduleOptions.remove(schedule);
    }

    /**
     * Adds a schedule to the scheduleOptions Arraylist
     * 
     * @param newSchedule The String text of the schedule to be added.
     */
    public void addScheduleOptions(String newSchedule){
        int i = 0;
        while (iter.hasNext()) {
            ++i;
        }
        scheduleOptions.add(i, newSchedule);
        out.println("Added schedule: " + i + " : " + scheduleOptions.get(i));
    }

    /**
     * Adds an employee's data to the employeeData HashMap.
     *
     * @param name     the employee's first and last name
     * @param position the employee's position
     * @param schedule the employee's numerical representation of their schedule
     * @param section  the section of tables the employee is responsible for
     */
    public void addEmployee(String name, String position, int schedule, int section) {
        StringBuilder sb = new StringBuilder();
        Employee employee = new Employee(name, position, schedule, section);
        employeeData.put(name, employee);
        sb.append(name).append(" | ").append("\n").append(employee.getPosition()).append(" | ").append("\n").append(employee.getSection()).append(" | ")
        .append(scheduleOptions.get(employee.getSchedule())).append("\n");
        System.out.println(sb);
        out.println("Successfully added.");
    }

    /**
     * Edits an employee's data in the employeeData HashMap.
     *
     * @param name        the employee's first and last name to be edited
     * @param newName     the new first and last name for the employee
     * @param newPosition the new position for the employee
     * @param newSchedule the new numerical representation of the schedule for the employee
     * @param newSection  the new section of tables the employee is responsible for
     */
    public void editEmployee(String name, String newName, String newPosition, int newSchedule, int newSection) {
        boolean restart = false;
        StringBuilder sb = new StringBuilder();
        while(!restart){
         if (employeeData.containsKey(name)) {
                Employee employee = employeeData.get(name);
                if (!newName.equals("")){
                    employee.setName(newName);
                }else newName = name;
                if (!newPosition.equals("")){
                    employee.setPosition(newPosition);
                }
                if (newSchedule != 0) {
                    employee.setSchedule(newSchedule);
                }
                if (newSection != 0) {
                    employee.setSection(newSection);
                }
                sb.append(newName).append(" | ").append("\n").append(employee.getPosition()).append(" | ").append("\n").append(employee.getSection()).append(" | ")
                .append(employee.getSchedule()).append("\n");
                out.println("New info: " + sb);
                out.println("Enter Yes to confirm or No to re-enter data");
                String answer = input.nextLine();
                if (answer.equals("Yes") || answer.equals("yes")){
                    employeeData.remove(name);
                    employeeData.put(newName, employee);
                    System.out.println("Employee updated.");
                }else {
                    restart = true;
                }
            } else {
                System.out.println("Employee not found.");
            }
        }
    }

    /**
     * Searches for an employee's data in the employeeData HashMap.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's data if found, otherwise a message that the employee was not found
     */
    public String searchEmployee(String name) {
        if (employeeData.containsKey(name)) {
            StringBuilder sb = new StringBuilder();
            Employee employee = employeeData.get(name);
            sb.append(name).append(" | ").append("\n").append(employee.getPosition()).append(" | ").append("\n").append(employee.getSection()).append(" | ")
                    .append(scheduleOptions.get(employee.getSchedule())).append("\n");
            return sb.toString();
        } else {
            return "Employee not found.";
        }
    }

    /**
     * Returns a formatted string listing all employees stored in the employeeData hashmap.
     *
     * @param criteria an integer indicating which dataset should be listed.
     * @return a formatted string listing the indicated criteria from the employeeData hashmap
     */
    public String listEmployee(int criteria) {
        StringBuilder sb = new StringBuilder();
        switch (criteria) {
            // Return only name
            case 1 -> {
                for (String name : employeeData.keySet()) {
                    sb.append(name).append("\n");
                }
            }
            // Return Name | Position | Section
            case 2 -> {
                for (Map.Entry<String, Employee> entry : employeeData.entrySet()) {
                    String name = entry.getKey();
                    Employee employee = entry.getValue();
                    sb.append(name).append(" | ").append(employee.getPosition()).append(" | ").append(employee.getSection()).append("\n");
                }
            }
            // Return Name | Position | Section | Schedule
            case 3 -> {
                for (Map.Entry<String, Employee> entry : employeeData.entrySet()) {
                    String name = entry.getKey();
                    Employee employee = entry.getValue();
                    sb.append(name).append(" | ").append(employee.getPosition()).append(" | ").append(employee.getSection()).append(" | ")
                            .append(scheduleOptions.get(employee.getSchedule())).append("\n");
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + criteria);
        }
        return sb.toString();
    }

    public String listSection(){
    StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Employee> entry : employeeData.entrySet()) {
            String name = entry.getKey();
            Employee employee = entry.getValue();
            sb.append(name).append(" | ").append(employee.getSection()).append("\n");
        return sb.toString();
        }
        return null;
    }

    public void editSection(String name, int newSection){
        if (employeeData.containsKey(name)) {
            Employee employee = employeeData.get(name);
            employee.setSection(newSection);
            employeeData.remove(name);
            employeeData.put(name, employee);
            out.println(name + ": " + "Section updated to: "+ newSection);
            
        }else {
            out.println("Employee not found.");
        }
    }

    public ArrayList<String> getScheduleOptions() {
        return scheduleOptions;
    }
}

