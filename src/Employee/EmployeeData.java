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
        scheduleOptions.add(newSchedule); //adds the schedule to the arraylist
        out.println("Added schedule | Number: " + (scheduleOptions.size() - 1) + " | Schedule:  " + newSchedule); //prints the schedule that was added
    }

    /**
     * Returns a formatted String of all schedule options stored in the scheduleOptions ArrayList.
     *
     * @return A formatted String of all schedule options stored in the scheduleOptions ArrayList.
     */
    public String getScheduleOptions() {
        StringBuilder sb = new StringBuilder();
        for (String index : scheduleOptions) { //for each schedule in the arraylist
            sb.append("Schedule: ").append(scheduleOptions.indexOf(index)).append(" | ").append(index).append("\n"); //append the schedule to the string builder
        }
        return sb.toString(); //return the string builder
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
        Employee employee = new Employee(name, position, schedule, section); //creates a new employee with the passed parameters
        employeeData.put(name, employee); //adds the employee object to the hashmap
        sb.append(name).append("\n").append(employee.getPosition()).append("\n") //appends the employee's data to the string builder
                .append("Section: ").append(employee.getSection()).append(" ").append(sections.get(employee.getSection())).append("\n")
                .append("Schedule: #").append(employee.getSchedule()).append(" | ").append(scheduleOptions.get(schedule)).append("\n");
        System.out.println(sb); //prints the string builder
        out.println("Successfully added.");
    }

    /**
     * Removes an employee from the employeeData HashMap.
     * @param name  The employee's first and last name
     */
    public void remEmployee(String name) {
        employeeData.remove(name); //removes the employee object from the hashmap
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
        if (employeeData.containsKey(name)) { //if the hashmap contains the employee
            Employee employee = employeeData.get(name); //gets the employee object from the hashmap
            Employee backupEmployee = (Employee) employee.clone(); //creates a backup of the employee object
            employeeData.put(name + "BACKUP", backupEmployee); //adds the backup to the hashmap

            if (!newName.equals("")) { //if the new name is not empty
                employee.setName(newName); //sets the employee's name to the new name
            } else newName = String.valueOf(name); //else sets the new name to the old name
            if (!newPosition.equals("")) { //if the new position is not empty
                employee.setPosition(newPosition); //sets the employee's position to the new position
            }
            /**
             * @todo possible bug.
             * @body The if block considers int 0 as empty, but I believe 0 is a valid schedule option. Need to make schedule options start at 1.
             */
            if (newSchedule != 0) { //if the new schedule is not empty
                employee.setSchedule(newSchedule); //sets the employee's schedule to the new schedule
            }
            /**
             * @todo possible bug.
             * @body The if block considers int 0 as empty, but I believe 0 is a valid section option. Need to make sections options start at 1.
             */
            if (newSection != 0) { //if the new section is not empty
                employee.setSection(newSection); //sets the employee's section to the new section
            }
            employeeData.remove(name); //removes the old employee object from the hashmap
            employeeData.put(newName, employee); //adds the new employee object to the hashmap
        } else System.out.println("Employee not found."); //else prints that the employee was not found
    }

    /**
     * Searches for an employee's data in the employeeData HashMap.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's data in a formatted string if found, otherwise a message that the employee was not found
     */
    public String searchEmployee(String name) {
        if (employeeData.containsKey(name)) { //if the hashmap contains the employee
            StringBuilder sb = new StringBuilder();
            Employee employee = employeeData.get(name); //gets the employee object from the hashmap
            sb.append("Name: ").append(name).append("\n") //appends the employee's data to the string builder
                    .append("Position: ").append(employee.getPosition()).append("\n")
                    .append("Section: ").append(employee.getSection()).append(" ").append(sections.get(employee.getSection())).append("\n")
                    .append("Schedule: #").append(employee.getSchedule()).append(" ").append(scheduleOptions.get(employee.getSchedule())).append("\n");
            return sb.toString(); //returns the string builder
        } else {
            return "Employee not found."; //else returns that the employee was not found
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
        if (employeeData.containsKey(name)){ //if the hashmap contains the employee
            Employee employee = employeeData.get(name); //gets the employee object from the hashmap
            return employee.getSchedule(); //returns the employee's schedule number
        }else throw new RuntimeException("Employee not found."); //else throws an exception
    }

    /**
     * Returns an employee's section number from the HashMap.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's section number if found.
     * @throws RuntimeException if the employee is not found in the HashMap data
     */
    public Integer getSection(String name){
        if (employeeData.containsKey(name)){ //if the hashmap contains the employee
            Employee employee = employeeData.get(name); //gets the employee object from the hashmap
            return employee.getSection(); //returns the employee's section number
        }else throw new RuntimeException("Employee not found."); //else throws an exception
    }

    /**
     * Returns an employee's section from the HashMap as an Integer array containing table numbers.
     *
     * @param name the employee's first and last name to search for
     * @return the employee's tables in an integer array.
     */
    public int[] sectionArray(String name){
        if (employeeData.containsKey(name)) { //if the hashmap contains the employee
            Employee employee = employeeData.get(name); //gets the employee object from the hashmap
            String str = sections.get(employee.getSection()); //gets the section string from the hashmap
            String numStr = str.replaceAll("\\D+", " "); //WOW! I HATE regular expressions. This method took WAYYY too long
            String[] numArr = numStr.trim().split("\\s+");          //to figure out. Basically it removes all non-numeric characters and splits the string into an array
            int[] numIntArr = new int[numArr.length];                     //of strings containing only the numbers. Then it converts the string array into an integer array
            for (int i = 0; i < numArr.length; i++) { //loops through the string array
                numIntArr[i] = Integer.parseInt(numArr[i]); //converts the string array into an integer array
            }
            return numIntArr; //returns the integer array
        }else return null; //else returns null
    }

    /**
     * Restores an employee's name from a backup and updates the employeeData map accordingly.
     *
     * @param backupName the name of the backup for the employee to be restored
     * @param oldName the old name of the employee before the backup was made
     * @param newName the new name of the employee after the backup was made
     */
    public void employeeRestore(String backupName, String oldName, String newName) {
        if (employeeData.containsKey(backupName)) { //if the hashmap contains the backup name
            Employee restore = employeeData.get(backupName); //gets the employee object from the hashmap
            restore.setName(oldName); //sets the employee's name to the old name
            employeeData.remove(newName); //removes the employee with the new name from the hashmap
            employeeData.put(oldName, restore); //restores the backup of old employee data to the hashmap
            employeeData.remove(backupName); //removes the backup from the hashmap
        }
    }
    /**
     * Checks whether an employee with the specified name exists in the employeeData map.
     *
     * @param name the name of the employee to check
     * @return true if the employeeData map contains an entry with the specified name, false otherwise
     */
    public boolean isEmployee(String name) {
        return employeeData.containsKey(name); //returns whether the hashmap contains the employee
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
        StringBuilder sb = new StringBuilder(); //creates a string builder
        switch (criteria) { //switches based on the search criteria
            case 1 -> { //if the criteria is 1 return only the name
                for (String name : employeeData.keySet()) { //loops through the hashmap
                    sb.append(name).append("\n"); //appends the name to the string builder
                }
            }
            case 2 -> { // Return Name | Position | Section
                for (Map.Entry<String, Employee> entry : employeeData.entrySet()) { //loops through the hashmap
                    String name = entry.getKey(); //gets the employee name
                    Employee employee = entry.getValue(); //gets the employee object
                    sb.append(name).append(" | ").append(employee.getPosition()).append(" | ").append("Section: ").append(employee.getSection()).append("\n"); //appends the employee information to the string builder
                }
            }
            case 3 -> { // Return Name | Position | Section | Schedule
                for (Map.Entry<String, Employee> entry : employeeData.entrySet()) { //loops through the hashmap
                    String name = entry.getKey(); //gets the employee name
                    Employee employee = entry.getValue(); //gets the employee object
                    sb.append(name).append(" | ").append(employee.getPosition()).append(" | ").append("Section: ").append(employee.getSection()).append(" | ") //appends the employee information to the string builder
                            .append("Schedule: #").append(employee.getSchedule()).append(" ").append(scheduleOptions.get(employee.getSchedule())).append("\n");
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + criteria); //throws an exception if the criteria is not 1, 2, or 3
        }
        return sb.toString(); //returns the string builder
    }

    /**
     * This method returns a formatted String containing the sections.
     *
     * @return A String containing the formatted section information.
     */
    public String listSection() {
        StringBuilder sb = new StringBuilder();
        for (String index : sections) { //loops through the sections arraylist
            sb.append("Section: ").append(sections.indexOf(index)).append(" | ").append(index).append("\n"); //appends the section information to the string builder
        }
        return sb.toString(); //returns the string builder
    }
}

