import java.sql.*;

public class App {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {



        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbc = "jdbc:mysql://localhost/employees?user=root&password=1909mm81";
        Connection connection = DriverManager.getConnection(jdbc);
        Statement st = ((Connection) connection).createStatement();



        insertQuery(st);
        selectQuery(st);
//        updateQuery(st);
//        selectQuery(st,500002);
        deleteQuery(st);
//        selectQuery(st,500002);


    }

    public boolean getCityFromEachCountry(Statement st) throws SQLException{
        String sql = "select ci.city, co.country from country co inner join city ci on ci.country_id = co.country_id";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            System.out.println(rs.getString("co.country") + " | " +rs.getString("ci.city"));
        }


    }

    public boolean getCibyNumberFromCountryID(Statement st) throws SQLException{
        String sql = "select co.country_id, count(ci.city_id) cities from country co inner join city ci on ci.country_id = co.country_id group by ci.country_id";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            System.out.println(rs.getInt("co.country_id") + " | " +rs.getString("cities"));
        }


    }

    public boolean getCibyNumberFromCountryName(Statement st) throws SQLException{
        String sql = "select co.country, count(ci.city_id) cities from country co inner join city ci on ci.country_id = co.country_id group by ci.country_id";
        st.executeUpdate(sql);


    }


    public boolean getAddress(Statement st) throws SQLException{
        String sql = "select * from address";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            System.out.println(rs.getString("address_id")
                        + " | " +rs.getString("address")
                        + " | " +rs.getString("address2")
                        + " | " +rs.getString("district")
                        + " | " +rs.getString("city_id")
                        + " | " +rs.getString("postal_code")
                        + " | " +rs.getString("phone")
                        + " | " +rs.getString("location")
                        + " | " +rs.getString("last_update"));
        }


    }

    public boolean numberOfAddresses(Statement st) throws SQLException{
        String sql = "select count(a.address) as num from address a";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            System.out.println(rs.getString("num"));

        }


    }

    public boolean numberOfAddressesDis(Statement st) throws SQLException{
        String sql = "select count(distinct(a.district)) as num from address a";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {

            System.out.println(rs.getInt("num"));

        }


    }

    public static boolean insertQuery(Statement st) throws SQLException{

        String lastIdQuery = "Select max(emp_no) as last_id from employees";
        ResultSet rs = st.executeQuery(lastIdQuery);
        String sql="";

        while (rs.next()){
            int nextId = rs.getInt("last_id");
            int finalId = nextId +1;
            sql = "INSERT INTO employees (emp_no, birth_date, first_name, last_name, gender, hire_date) VALUES "+ "(" + finalId +",'1999-1-1','Mario', 'silva','M','1999-1-1');";
        }

        st.executeUpdate(sql);




    }


    public static boolean (Statement st) throws SQLException{

        String lastIdQuery = "Select max(emp_no) as last_id from employees";
        ResultSet rs = st.executeQuery(lastIdQuery);
        String sql="";

        while (rs.next()){
            int finalId = rs.getInt("last_id");
            sql = "select * from employees where emp_no =" + finalId+";";
        }


        rs = st.executeQuery(sql);
        while (rs.next()) {

            System.out.println(rs.getInt(1)
                    +" | "
                    +rs.getString("birth_date")
                    +" | "
                    +rs.getString("first_name")
                    +" | "
                    +rs.getString("last_name")
                    +" | "
                    +rs.getString("gender")
                    +" | "
                    +rs.getString("hire_date"));

        }


    }

    public boolean updateQuery(Statement st) throws SQLException{

        String sql = "update employees set emp_no = 500002 where emp_no = 500001";
        st.executeUpdate(sql);



    }


    public boolean deleteQuery(Statement st) throws SQLException{
        String sql = "delete from employees where emp_no = 500002;";
        st.executeUpdate(sql);
    }







}


