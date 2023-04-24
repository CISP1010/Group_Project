package Employee;


/**
 * The Employee.Employee class represents an individual employee and contains the Employee.Employee constructor used to add employees to the employeeData hashMap.
 */
    /**
    * @todo proofread Employee.java javadoc info
    * @body proofread javadoc info
     * Comments are completed
    */
public class Employee implements Cloneable {

    private String name; //This string holds the name of the employee
    private String position; //This string holds the position of the employee
    private int schedule; //This integer holds the schedule option of the employee
    private int section; //This integer holds the number representing the section of tables the employee is responsible for

    /**
     * Constructs a new Employee.Employee object with the specified name, position, schedule, and section.
     *
     * @param name     the first and last name of the employee
     * @param position the position of the employee
     * @param schedule numerical value representing the schedule of the employee
     * @param section  the section of tables that the employee is responsible for
     */
    public Employee(String name, String position, int schedule, int section) {
        this.name = name; //Sets the name of the employee
        this.position = position; //Sets the position of the employee
        this.schedule = schedule; //Sets the schedule of the employee
        this.section = section; //Sets the section of tables the employee is responsible for
    }

    /**
     * Sets the first and last name of the employee.
     *
     * @param name the new name of the employee
     */
    public void setName(String name) {
        this.name = name; //Sets the name of the employee
    }

    /**
     * Returns the position of the employee.
     *
     * @return the position of the employee
     */
    public String getPosition() {
        return position; //returns the position of the employee
    }

    /**
     * Sets the position of the employee.
     *
     * @param position the new position of the employee
     */
    public void setPosition(String position) {
        this.position = position; //Sets the position of the employee
    }

    /**
     * Returns the schedule of the employee.
     *
     * @return the schedule of the employee as a numerical value (1, 2, or 3)
     */
    public int getSchedule() {
        return schedule; //returns the schedule of the employee
    }

    /**
     * Sets the schedule of the employee.
     *
     * @param schedule the new schedule of the employee as a numerical value (1, 2, or 3)
     */
    public void setSchedule(int schedule) {
        this.schedule = schedule; //Sets the schedule of the employee
    }

    /**
     * Returns the section of tables that the employee is responsible for.
     *
     * @return the section of tables the employee is responsible for
     */
    public int getSection() {
        return section; //returns the section of tables the employee is responsible for
    }

    /**
     * Sets the section of tables that the employee is responsible for.
     *
     * @param section the new section of tables the employee is responsible for
     */
    public void setSection(int section) {
        this.section = section; ////Sets the section of tables the employee is responsible for
    }

    /**
     * Returns a string representation of the employee in the format:
     * "Name: [first and last name] | Position: [position] | Schedule: [schedule] | Section: [section]"
     *
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        return "Name: " + name + " | Position: " + position + " | Schedule: " + schedule + " | Section: " + section;  //returns a string representation of the employee
    }

    /**
     * Returns a clone of the employee for backup.
     *
     * @return a backup clone of the employee
     */
    public Object clone() {
        try {
            return super.clone(); //returns a clone of the employee
        } catch (CloneNotSupportedException e) { //
            throw new AssertionError(); //throws an assertion error if the employee cannot be cloned
        }
    }
}
