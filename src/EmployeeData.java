import java.util.HashMap;
import java.util.Map;

/**
 * The EmployeeData class represents a collection of employees' data, including their first and last names, positions, and schedules.
 */
public class EmployeeData {
    //test test

    /**
     * A HashMap to store the employee data, with the employee first and last name as the key and an instance of the Employee class as the value.
     */
    private static HashMap<String, Employee> employeeData;
    /**
     * A HashMap to store the Schedule options.
     */
    private final HashMap<String, String> scheduleOptions;

    /**
     * The default constructor for the EmployeeData class that initializes the employeeData HashMap and scheduleOptions HashMap.
     */
    public EmployeeData() {
        employeeData = new HashMap<>();
        scheduleOptions = new HashMap<>();
        scheduleOptions.put("1", "Monday-Friday, 9am-5pm");
        scheduleOptions.put("2", "Tuesday-Saturday, 10am-6pm");
        scheduleOptions.put("3", "Wednesday-Sunday, 11am-7pm");
    }

    /**
     * Adds an employee's data to the employeeData HashMap.
     *
     * @param name     the employee's first and last name
     * @param position the employee's position
     * @param schedule the employee's numerical representation of their schedule
     */
    public void addEmployee(String name, String position, String schedule) {
        Employee employee = new Employee(name, position, schedule);
        employeeData.put(name, employee);
        System.out.println("Employee added");
    }

    /**
     * Edits an employee's data in the employeeData HashMap.
     *
     * @param name        the employee's first and last name to be edited
     * @param newName     the new first and last name for the employee
     * @param newPosition the new position for the employee
     * @param newSchedule the new numerical representation of the schedule for the employee
     */
    public void editEmployee(String name, String newName, String newPosition, String newSchedule) {
        if (employeeData.containsKey(name)) {
            Employee employee = new Employee(newName, newPosition, newSchedule);
            employeeData.remove(name);
            employeeData.put(newName, employee);
            System.out.println("Employee updated.");
        } else {
            System.out.println("Employee not found.");
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
            sb.append(name).append(" | ").append("\n").append(employee.getPosition()).append(" | ").append("\n")
                    .append(scheduleOptions.get(employee.getSchedule())).append("\n");
            return sb.toString();
        } else {
            return "Employee not found.";
        }
    }

    /**
     * Returns a formatted string listing all employees stored in the employeeData hashmap.
     *
     * @param namesOnly a boolean indicating whether to only list employee names or to include all employee data
     * @return a formatted string listing all employees stored in the employeeData hashmap
     */
    public String listEmployee(boolean namesOnly) {
        StringBuilder sb = new StringBuilder();
        if (namesOnly) {
            // list only employee names
            for (String name : employeeData.keySet()) {
                sb.append(name).append("\n");
            }
        } else {
            // list all employee data
            for (Map.Entry<String, Employee> entry : employeeData.entrySet()) {
                String name = entry.getKey();
                Employee employee = entry.getValue();
                sb.append(name).append(" | ").append(employee.getPosition()).append(" | ")
                        .append(scheduleOptions.get(employee.getSchedule())).append("\n");
            }
        }
        return sb.toString();
    }

    public HashMap<String, String> getScheduleOptions() {
        return scheduleOptions;
    }
}

