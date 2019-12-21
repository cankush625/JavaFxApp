package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;


public class Controller {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField ageTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField contTextField;
    @FXML
    private TextField oldName;
    @FXML
    private TextField updateName;
    @FXML
    private TextArea showRecord;

    // Connecting the database
    @FXML
    public void addRecord() {
        System.out.println("Inserting values in mysql database table!");
        String name = nameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        String address = addressTextField.getText();
        String contact = contTextField.getText();

        Connection conn;
        String url = "jdbc:mysql://127.0.0.1:3306/";
        String db = "javafxapp";
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + db, "root", "Ankush");
            try {
                Statement st = conn.createStatement();
                String sql = "insert into javafxapp.user"
                        + " values(" + "'" + name + "'" + "," + age + "," + "'" + address + "'" + "," + "'" + contact + "'" + ")";
                System.out.println(sql);
                int val = st.executeUpdate(sql);
                System.out.println("1 row affected + return value: " + val);
            } catch(SQLException s) {
                System.out.println("SQL statement is not executable! Error is: " + s.getMessage());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void recordDisplay() {
        System.out.println("Displaying the record");

        Connection conn;
        Statement st;
        String url = "jdbc:mysql://127.0.0.1:3306/";
        String db = "javafxapp";
        String driver = "com.mysql.jdbc.Driver";

        StringBuilder sb = new StringBuilder();

        try {
            //Registering the driver
            Class.forName(driver);
            //Open a connection
            conn = DriverManager.getConnection(url + db, "root", "Ankush");
            try {
                st = conn.createStatement();
                String sql = "select * from javafxapp.user";
                System.out.println(sql);
                ResultSet val = st.executeQuery(sql);

                while(val.next()) {
//                    Retrieve by column name
					String name = val.getString("name");
					int age = val.getInt("age");
					String address = val.getString("address");
					String contact = val.getString("contact");

					// Displaying the records to the admin
                    System.out.println("Name : " + name);
                    System.out.println("Age : " + age);
                    System.out.println("Address : " + address);
                    System.out.println("Contact : " + contact);

                    sb.append("Name: " + name + "\t");
                    sb.append("Age: " + age + "\t");
                    sb.append("Address: " + address + "\t");
                    sb.append("Contact: " + contact + "\t" + "\n");
                }
                val.close();
            } catch(SQLException s) {
                System.out.println("SQL statement is not executable! Error is: " + s.getMessage());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }




        showRecord.setText(sb.toString());
    }

    public void updateName() {
        System.out.println("Updating values in mysql database table!");

        Connection conn;
        String url = "jdbc:mysql://localhost:3306/";
        String db = "javafxapp";
        String driver = "com.mysql.jdbc.Driver";

        String oldName_ = oldName.getText();
        String updateName_ = updateName.getText();

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url + db, "root", "Ankush");
            try {
                Statement st = conn.createStatement();
                String sql = "update javafxapp.user"
                        + " set name = " + "'" + updateName_ + "'" + " where name = " + "'" + oldName_ + "'";
                System.out.println(sql);
                int val = st.executeUpdate(sql);
                System.out.println("1 row affected + return value: " + val);
            } catch(SQLException s) {
                System.out.println("SQL statement is not executable! Error is: " + s.getMessage());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

// by Ankush Chavan