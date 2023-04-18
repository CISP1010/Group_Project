package Employee;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static java.lang.System.out;


/**
 * The EmployeeData class represents a collection of employees' data, including their first and last names, positions,
 * section assignments, and schedules.
 */
public class EmployeeData {

    /**
     * A HashMap to store the employee data, with the employee first and last name as the key and an instance of the
     * Employee class as the value.
     */
    private static final HashMap<String, Employee> employeeData = new HashMap<>();

    /**
     * An ArrayList to store the Schedule options.
     */
    private static final ArrayList<String> scheduleOptions = new ArrayList<>();

    /**
     * An ArrayList to store the sections.
     */
    private static final ArrayList<String> sections = new ArrayList<>();

    /**
     * The default constructor for the EmployeeData class that initializes the employeeData HashMap, scheduleOptions
     * ArrayList, and sections ArrayList with default data.
     */
    public EmployeeData() {

        // Adding default schedule options since data is not stored between program runs.
        scheduleOptions.add("Monday-Friday, 9am-5pm");
        scheduleOptions.add("Tuesday-Saturday, 10am-6pm");
        scheduleOptions.add("Wednesday-Sunday, 11am-7pm");

        // Adding default section data since data is not stored between program runs.
        sections.add("Tables 1-5");
        sections.add("Tables 6-10");
        sections.add("Tables 11-15");
        sections.add("Tables 16-20");
    }

    /**
     * Adds a schedule to the scheduleOptions ArrayList.
     *
     * @param newSchedule The String text of the schedule to be added.
     */
    public void addScheduleOptions(String newSchedule) {
        scheduleOptions.add(newSchedule);
        out.println("Added schedule | Number: " + (scheduleOptions.size() - 1) + " | Schedule:  " + newSchedule);
    }

    /**
     * Removes a schedule from the scheduleOptions ArrayList.
     *
     * @param schedule The numerical representation of the schedule to be removed.
     */
    public void remScheduleOptions(int schedule) {
        scheduleOptions.remove(schedule);
    }

    /**
     * Returns a formatted String of all schedule options stored in the scheduleOptions ArrayList.
     *
     * @return A formatted String of all schedule options stored in the scheduleOptions ArrayList.
     */
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
     * @param name     The employee's first and last name.
     * @param position The employee's position.
     * @param schedule The numerical representation of the employee's schedule.
     * @param section  The numerical representation of the section of tables the employee is responsible for.
     */
    public void addEmployee(String name, String position, int schedule, int section) {
        StringBuilder sb = new StringBuilder();
        Employee employee = new Employee(name, position, schedule, section);
        employeeData.put(name, employee);
        sb.append(name).append("\n").append(employee.getPosition()).append("\n")
                .append("Section: ").append(employee.getSection()).append(" ").append(sections.get(employee.getSection())).append("\n")
                .append("Schedule: #").append(employee.getSchedule()).append(" | ").append(scheduleOptions.get(schedule)).append("\n");
        System.out.println(sb);
        out.println("Successfully added.");
    }

    /**
     * Removes an employee from the employeeData HashMap.
     * @param name  The employee's first and last name
     */
    public void remEmployee(String name) {
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
        } else System.out.println("Employee not found.");
    }

    /**
     * Searches for an employee's data in the employeeData HashMap.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's data in a formatted string if found, otherwise a message that the employee was not found
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

    /**
     * Returns an employee's schedule number from the HashMap.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's schedule number if found.
     * @throws RuntimeException if the employee is not found in the HashMap data
     */
    public Integer getSchedule(String name){
        if (employeeData.containsKey(name)){
            Employee employee = employeeData.get(name);
            return employee.getSchedule();
        }else throw new RuntimeException("Employee not found.");
    }

    /**
     * Returns an employee's section number from the HashMap.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's section number if found.
     * @throws RuntimeException if the employee is not found in the HashMap data
     */
    public Integer getSection(String name){
        if (employeeData.containsKey(name)){
            Employee employee = employeeData.get(name);
            return employee.getSection();
        }else throw new RuntimeException("Employee not found.");
    }

    /**
     * Returns an employee's section from the HashMap as a formatted string.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's section in a formatted string if found.
     */
    public String sectionString(String name) {
        StringBuilder sb = new StringBuilder();
        if (employeeData.containsKey(name)) {
            Employee employee = employeeData.get(name);
            sb.append("Section: ").append(employee.getSection()).append(" ").append(sections.get(employee.getSection())).append("\n");
        }return sb.toString();
    }

    /**
     * Returns an employee's section from the HashMap as a Integer array containing table numbers.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's tables in an integer array.
     */
    public int[] sectionArray(String name){
        if (employeeData.containsKey(name)) {
            Employee employee = employeeData.get(name);
            String str = sections.get(employee.getSection());
            String numStr = str.replaceAll("\\D+", " "); //WOW i HATE regular expressions. This method took WAYYY too long
            String[] numArr = numStr.trim().split("\\s+");
            int[] numIntArr = new int[numArr.length];
            for (int i = 0; i < numArr.length; i++) {
                numIntArr[i] = Integer.parseInt(numArr[i]);
            }
            return numIntArr;
        }else return null;
    }

    /**
     * Restores an employee's name from a backup and updates the employeeData map accordingly.
     *
     * @param backupName the name of the backup for the employee to be restored
     * @param oldName the old name of the employee before the backup was made
     * @param newName the new name of the employee after the backup was made
     */
    public void employeeRestore(String backupName, String oldName, String newName) {
        if (employeeData.containsKey(backupName)) {
            Employee restore = employeeData.get(backupName);
            restore.setName(oldName);
            employeeData.remove(newName);
            employeeData.put(oldName, restore);
            employeeData.remove(backupName);
        }
    }
    /**
     * Checks whether an employee with the specified name exists in the employeeData map.
     *
     * @param name the name of the employee to check
     * @return true if the employeeData map contains an entry with the specified name, false otherwise
     */
    public boolean isEmployee(String name) {
        return employeeData.containsKey(name);
    }

    /**
     * This method returns a formatted String containing the employee information based on the given criteria.
     *
     * @param criteria An integer indicating the format of the employee information to be returned.
     *                 1 - Returns only the name of each employee.
     *                 2 - Returns the name, position, and section of each employee.
     *                 3 - Returns the name, position, section, and schedule of each employee.
     * @return A String containing the formatted employee information based on the given criteria.
     * @throws IllegalStateException if the given criteria is not 1, 2, or 3.
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

    /**
     * This method returns a formatted String containing the sections.
     *
     * @return A String containing the formatted section information.
     */
    public String listSection() {
        StringBuilder sb = new StringBuilder();
        for (String index : sections) {
            sb.append("Section: ").append(sections.indexOf(index)).append(" | ").append(index).append("\n");
        }
        return sb.toString();
    }
}

