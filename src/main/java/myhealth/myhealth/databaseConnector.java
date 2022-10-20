package myhealth.myhealth;

import java.sql.*;

public class databaseConnector {
    // a class to connect to database

    private Connection conn = null;
    private DriverManager dm;
    private Statement stmnt;

    databaseConnector(){
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/myHealth", "postgres", "pranav125");

            conn.setAutoCommit(false);
            stmnt = conn.createStatement(); // to execute queries in the database.

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    User getUser(String username, String enteredPassword) throws SQLException {
        String userDetails = String.format("SELECT * FROM public.users where \"userName\" = \'%s\' and \"passwordHash\" = \'%s\';", username, enteredPassword);
        ResultSet rs = null;
        try {
            rs = stmnt.executeQuery(userDetails);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rs != null) {
            while (rs.next()) {
                try {
                    String user = rs.getString("userName");
                    String passwordHash = rs.getString("passwordHash");
                    String firstName = rs.getString("firstName");
                    String secondName = rs.getString("secondName");
//                    int userID = rs.getInt("userID");
                    return new User(user, passwordHash, firstName, secondName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            return null;
        }
        return null;
    }

    boolean insertUser(User userToBeEntered){
        try {
            String userDetails = String.format("INSERT INTO public.users(\"userName\", \"passwordHash\", \"firstName\", \"secondName\") VALUES (\'%s\', \'%s\', \'%s\', \'%s\');", userToBeEntered.getUsername(), userToBeEntered.getPasswordHash(), userToBeEntered.getFirstName(), userToBeEntered.getLastName());
//            System.out.println(userDetails);
            stmnt.executeUpdate(userDetails);
            conn.commit();
            return true;
        }catch(SQLException e){
            //unique constraint is exploited
            System.out.println(e);
            return false;
        }
    }

//    boolean updateUserDetails(User userToBeUpdated){
//        try {
//            String userDetails = String.format("INSERT INTO public.users(\"userName\", \"passwordHash\", \"firstName\", \"secondName\") VALUES (\'%s\', \'%s\', \'%s\', \'%s\');", userToBeEntered.getUsername(), userToBeEntered.getPasswordHash(), userToBeEntered.getFirstName(), userToBeEntered.getLastName());
//            System.out.println(userDetails);
//            stmnt.executeUpdate(userDetails);
//            conn.commit();
//            return true;
//        }catch(SQLException e){
//            System.out.println(e);
//            return false;
//        }
//    }




}
