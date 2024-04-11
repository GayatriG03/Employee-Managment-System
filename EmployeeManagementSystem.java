import java.sql.*;
import java.util.Scanner;

public class EmployeeManagementSystem {

		    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee_management";
		    private static final String USERNAME = "root";
		    private static final String PASSWORD = "root";

		    private static Connection connection;
		    private static PreparedStatement preparedStatement;
		    private static ResultSet resultSet;

		    private static Scanner scanner = new Scanner(System.in);

		    // Method to establish database connection
		    private static void connect() throws SQLException {
		        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		    }

		    // Method to close database connection
		    private static void close() throws SQLException {
		        if (resultSet != null) resultSet.close();
		        if (preparedStatement != null) preparedStatement.close();
		        if (connection != null) connection.close();
		    }

		    // Method to create a new employee
		    public static void addEmployee(int id, String name, String position, double salary) throws SQLException {
		        connect();
		        preparedStatement = connection.prepareStatement("INSERT INTO employees (id, name, position, salary) VALUES (?, ?, ?, ?)");
		        preparedStatement.setInt(1, id);
		        preparedStatement.setString(2, name);
		        preparedStatement.setString(3, position);
		        preparedStatement.setDouble(4, salary);
		        preparedStatement.executeUpdate();
		        close();
		        System.out.println("Employee added successfully.");
		    }

		    // Method to retrieve employee details by ID
		    public static void getEmployeeById(int id) throws SQLException {
		        connect();
		        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
		        preparedStatement.setInt(1, id);
		        resultSet = preparedStatement.executeQuery();
		        if (resultSet.next()) {
		            System.out.println("Employee ID: " + resultSet.getInt("id") + ", Name: " + resultSet.getString("name") + ", Position: " + resultSet.getString("position") + ", Salary: $" + resultSet.getDouble("salary"));
		        } else {
		            System.out.println("No employee found with ID: " + id);
		        }
		        close();
		    }

		    // Method to update employee details by ID
		    public static void updateEmployee(int id, String name, String position, double salary) throws SQLException {
		        connect();
		        preparedStatement = connection.prepareStatement("UPDATE employees SET name = ?, position = ?, salary = ? WHERE id = ?");
		        preparedStatement.setString(1, name);
		        preparedStatement.setString(2, position);
		        preparedStatement.setDouble(3, salary);
		        preparedStatement.setInt(4, id);
		        int rowsAffected = preparedStatement.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Employee details updated successfully.");
		        } else {
		            System.out.println("No employee found with ID: " + id);
		        }
		        close();
		    }

		    // Method to delete an employee by ID
		    public static void deleteEmployee(int id) throws SQLException {
		        connect();
		        preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
		        preparedStatement.setInt(1, id);
		        int rowsAffected = preparedStatement.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Employee deleted successfully.");
		        } else {
		            System.out.println("No employee found with ID: " + id);
		        }
		        close();
		    }

		    public static void main(String[] args) {
		        try {
		            int choice;
		            do {
		                System.out.println("\nSelect an option:");
		                System.out.println("1. Add a new employee");
		                System.out.println("2. Retrieve employee details by ID");
		                System.out.println("3. Update employee details");
		                System.out.println("4. Delete an employee");
		                System.out.println("5. Exit");
		                System.out.print("Enter your choice: ");
		                choice = scanner.nextInt();

		                switch (choice) {
		                    case 1:
		                        System.out.print("Enter employee ID: ");
		                        int id = scanner.nextInt();
		                        scanner.nextLine(); // Consume newline character
		                        System.out.print("Enter employee name: ");
		                        String name = scanner.nextLine();
		                        System.out.print("Enter employee position: ");
		                        String position = scanner.nextLine();
		                        System.out.print("Enter employee salary: ");
		                        double salary = scanner.nextDouble();
		                        addEmployee(id, name, position, salary);
		                        break;
		                    case 2:
		                        System.out.print("Enter employee ID: ");
		                        int getById = scanner.nextInt();
		                        getEmployeeById(getById);
		                        break;
		                    case 3:
		                        System.out.print("Enter employee ID: ");
		                        int updateId = scanner.nextInt();
		                        scanner.nextLine(); // Consume newline character
		                        System.out.print("Enter new name: ");
		                        String updateName = scanner.nextLine();
		                        System.out.print("Enter new position: ");
		                        String updatePosition = scanner.nextLine();
		                        System.out.print("Enter new salary: ");
		                        double updateSalary = scanner.nextDouble();
		                        updateEmployee(updateId, updateName, updatePosition, updateSalary);
		                        break;
		                    case 4:
		                        System.out.print("Enter employee ID to delete: ");
		                        int deleteId = scanner.nextInt();
		                        deleteEmployee(deleteId);
		                        break;
		                    case 5:
		                        System.out.println("Exiting...");
		                        break;
		                    default:
		                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
		                }
		            } while (choice != 5);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

	


