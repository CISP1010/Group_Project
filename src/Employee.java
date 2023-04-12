/**
 * The Employee class represents an individual employee and contains the Employee constructor used to add employees to the employeeData hashMap.
 */
public class Employee implements Cloneable {
    // other fields and methods...


    private String name;
    private String position;
    private int schedule;
    private int section;

    /**
     * Constructs a new Employee object with the specified name, position,
     * and schedule.
     *
     * @param name     the first and last name of the employee
     * @param position the position of the employee
     * @param schedule Numerical value representing the schedule of the employee
     * @param section  the section of tables that the employee is responsible for
     */
    public Employee(String name, String position, int schedule, int section) {
        this.name = name;
        this.position = position;
        this.schedule = schedule;
        this.section = section;
    }

    /**
     * Returns the first and last name of the employee.
     *
     * @return the name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first and last name of the employee to the specified value.
     *
     * @param name the new name of the employee
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the position of the employee.
     *
     * @return the position of the employee
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets the position of the employee to the specified value.
     *
     * @param position the new position of the employee
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Returns the numerical value that was input when the employee was added.
     *
     * @return 1, 2, or 3 representing the schedule of the employee.
     */
    public int getSchedule() {
        return schedule;
    }

    /**
     * Sets the schedule of the employee to the specified value.
     *
     * @param schedule the numerical value entered by the user that represents the new schedule of the employee
     */
    public void setSchedule(int schedule) {
        this.schedule = schedule;
    }

    /**
     * Gets the section the employee is responsible for.
     */
    public int getSection() {
        return section;
    }

    /**
     * Gets the schedule of the employee to the specified value.
     *
     * @param section of tables the employee is responsible for
     */
    public void setSection(int section) {
        this.section = section;
    }

    /**
     * Returns a string representation of the employee in the format:
     * "Name: [first and last name] | Position: [position] | Schedule: [schedule]"
     *
     * @return a string representation of the employee
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Position: " + position + ", Schedule: " + schedule + ", Section:" + section;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
