import db.DB;
import db.PropertiesConnection;
import dto.Employee;
import org.junit.Assert;
import org.junit.Test;
import service.DBService;
import db.DB;


import java.io.*;
import java.sql.SQLException;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class DBServiceTest {

    @Test
    public void insert_validUser_returnPersistedUser() throws SQLException, ClassNotFoundException, IOException {

        DBService service = new DBService();
        Employee employee = new Employee();

        employee.setDob("1989-03-02");
        employee.setFirstName("Mário");
        employee.setLastName("Silva");
        employee.setGender("M");


        Employee newEmpl = service.insertEmployee(employee);

        Assert.assertTrue(newEmpl.getFirstName().equals(employee.getFirstName()));

        assertThat(newEmpl).isEqualToIgnoringGivenFields(employee, "number", "hireDate");


    }
    @Test
    public void getProperties_returnResultTrue() throws SQLException, ClassNotFoundException, IOException {

        PropertiesConnection propertiesConnection = new PropertiesConnection();

        Properties prop = new Properties();
        FileInputStream file;


        try{
            file = new FileInputStream("src/main/resources/db/db.properties");

            if (file != null) {
                prop.load(file);
            } else {
                throw new FileNotFoundException("property file not found in the classpath");
            }

            prop.load(file);

            propertiesConnection.setDbuser(prop.getProperty("dbuser"));
            propertiesConnection.setDbpasswrod(prop.getProperty("dbpassword"));
            propertiesConnection.setDbase(prop.getProperty("dbase"));


        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


        Assert.assertTrue(propertiesConnection.getDbuser().equals("root"));




    }
}
