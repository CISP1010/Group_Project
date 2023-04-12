import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.lang.System.out;

/**
 * The EmployeeData class represents a collection of employees' data, including their first and last names, positions, section assignments, and schedules.
 */
public class EmployeeData {
    /**
     * A HashMap to store the employee data, with the employee first and last name as the key and an instance of the Employee class as the value.
     */
    private static HashMap<String, Employee> employeeData = new HashMap<>();

    /**
     * An arraylist to store the Schedule options.
     */
    private static ArrayList<String> scheduleOptions = new ArrayList<>();

    /**
     *An arraylist to store the sections.
     */
    private static ArrayList<String> sections = new ArrayList<>();
    /**
     * The default constructor for the EmployeeData class that initializes the employeeData HashMap and scheduleOptions HashMap.
     */

    public EmployeeData() {
        
        //Adding default schedule options since data is not stored between program runs. 
        scheduleOptions.add("Monday-Friday, 9am-5pm");
        scheduleOptions.add("Tuesday-Saturday, 10am-6pm");
        scheduleOptions.add("Wednesday-Sunday, 11am-7pm");
        //Adding default section data since data is not stored between program runs.
        sections.add("Tables 1-5");
        sections.add("Tables 6-10");
        sections.add("Tables 11-15");
        sections.add("Tables 16-20");
        //Initialize Table objects



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
        scheduleOptions.add(newSchedule);
        out.println("Added schedule | Number: " + (scheduleOptions.size() - 1) + " | Schedule:  " + newSchedule);
    }

    public String getScheduleOptions() {
        StringBuilder sb = new StringBuilder();
        for (String index : scheduleOptions) {
            sb.append("Schedule: ").append(scheduleOptions.indexOf(index)).append(" | ").append(index).append("\n");
        }
        return sb.toString();
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
        sb.append(name).append("\n").append(employee.getPosition()).append("\n").append("Section: ")
                .append(employee.getSection()).append(" ").append(sections.get(employee.getSection())).append("\n")
                .append("Schedule: #").append(employee.getSchedule()).append(" | ").append(scheduleOptions.get(schedule)).append("\n");
        System.out.println(sb);
        out.println("Successfully added.");
    }
    public void remEmployee(String name){
        employeeData.remove(name);
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
        if (employeeData.containsKey(name)) {
            Employee employee = employeeData.get(name);
            Employee backupEmployee = (Employee) employee.clone();
            employeeData.put(name + "BACKUP", backupEmployee);

            if (!newName.equals("")) {
                employee.setName(newName);
            } else newName = String.valueOf(name);
            if (!newPosition.equals("")) {
                employee.setPosition(newPosition);
            }
            if (newSchedule != 0) {
                employee.setSchedule(newSchedule);
            }
            if (newSection != 0) {
                employee.setSection(newSection);
            }
            employeeData.remove(name);
            employeeData.put(newName, employee);
        }else System.out.println("Employee not found.");
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
            sb.append("Name: ").append(name).append("\n")
                    .append("Position: ").append(employee.getPosition()).append("\n")
                    .append("Section: ").append(employee.getSection()).append(" ").append(sections.get(employee.getSection())).append("\n")
                    .append("Schedule: #").append(employee.getSchedule()).append(" ").append(scheduleOptions.get(employee.getSchedule())).append("\n");
            return sb.toString();
        } else {
            return "Employee not found.";
        }
    }
    public void employeeRestore(String backupName, String oldName, String newName) {
        if (employeeData.containsKey(backupName)) {
            Employee restore = employeeData.get(backupName);
            restore.setName(oldName);
            employeeData.remove(newName);
            employeeData.put(oldName, restore);
            employeeData.remove(backupName);
        }
    }
    public boolean isEmployee(String name){
        return employeeData.containsKey(name);
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
                    sb.append(name).append(" | ").append(employee.getPosition()).append(" | ").append("Section: ").append(employee.getSection()).append("\n");
                }
            }
            // Return Name | Position | Section | Schedule
            case 3 -> {
                for (Map.Entry<String, Employee> entry : employeeData.entrySet()) {
                    String name = entry.getKey();
                    Employee employee = entry.getValue();
                    sb.append(name).append(" | ").append(employee.getPosition()).append(" | ").append("Section: ").append(employee.getSection()).append(" | ")
                            .append("Schedule: #").append(employee.getSchedule()).append(" ").append(scheduleOptions.get(employee.getSchedule())).append("\n");
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + criteria);
        }
        return sb.toString();
    }

    public String listSection(){
    StringBuilder sb = new StringBuilder();
        for (String index : sections) {
            sb.append("Section: ").append(sections.indexOf(index)).append(" | ").append(index).append("\n");
        }
        return sb.toString();
    }

}

