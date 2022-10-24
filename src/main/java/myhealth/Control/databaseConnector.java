package myhealth.Control;

import java.sql.*;
import java.util.ArrayList;
import Model.Record;
import Model.User;

public class databaseConnector extends sceneHandler{
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

    int getUserID() throws SQLException{
        String userDetails = String.format("SELECT \"userID\" FROM public.users where \"userName\" = \'%s\'", super.getUsername());
        ResultSet rs = null;
        try {
            rs = stmnt.executeQuery(userDetails);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rs != null) {
            while (rs.next()) {
                try {
                    return Integer.parseInt(rs.getString("userID"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            return Integer.parseInt(null);
        }
        return Integer.parseInt(null);
    }

    User getUser(String username, String enteredPassword) throws SQLException {
        String userDetails = String.format("SELECT \"userName\", \"userID\", \"firstName\", \"secondName\" FROM public.users where \"userName\" = \'%s\' and \"passwordHash\" = \'%s\';", username, enteredPassword);
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
                    int userID = Integer.parseInt(rs.getString("userID"));
                    String firstName = rs.getString("firstName");
                    String secondName = rs.getString("secondName");
                    return new User(userID, user, firstName, secondName);
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

    public ArrayList<Record> getRecords() throws SQLException{
        String recordQuery = String.format("SELECT * from public.records where \"userID\" = (select \"userID\" from public.users where \"userName\"=\'%s\');", super.getUsername());
        ResultSet rs = null;
        try {
            rs = stmnt.executeQuery(recordQuery);
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Record> records = new ArrayList<>();
        if(rs != null) {
            while (rs.next()) {
                try {
                    int recordID = rs.getInt("recordID");
                    int weight = rs.getInt("weight");
                    int temperature = rs.getInt("temperature");
                    int bloodPressure = rs.getInt("bloodPressure");
                    String note = rs.getString("note");
                    records.add(new Record(recordID, weight, temperature, bloodPressure, note));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return records;
        }
        else{
            return null;
        }
    }

    public Record getSingleRecord(int recordID) throws SQLException{
        String recordQuery = String.format("SELECT * from public.records where \"recordID\" = %d", recordID);
        ResultSet rs = null;
        try {
            rs = stmnt.executeQuery(recordQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rs != null) {
            while (rs.next()) {
                try {
                    int weight = rs.getInt("weight");
                    int temperature = rs.getInt("temperature");
                    int bloodPressure = rs.getInt("bloodPressure");
                    String note = rs.getString("note");
                    return new Record(recordID, weight, temperature, bloodPressure, note);
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

    public boolean updateRecord() throws SQLException{
        //UPDATE public.records SET "weight" = 12, "temperature" = 12, "bloodPressure" = 34, "Note" = 'Hey there' WHERE "recordID" = 4;
        return false;
    }

    boolean insertUser(User userToBeEntered){
        try {
            String userDetails = String.format("INSERT INTO public.users(\"userName\", \"passwordHash\", \"firstName\", \"secondName\") VALUES (\'%s\', \'%s\', \'%s\', \'%s\');", userToBeEntered.getUsername(), userToBeEntered.getPasswordHash(), userToBeEntered.getFirstName(), userToBeEntered.getLastName());
            System.out.println(userDetails);
            stmnt.executeUpdate(userDetails);
            conn.commit();
            return true;
        }catch(SQLException e){
            //unique constraint is exploited
            //System.out.println(e);
            return false;
        }
    }

    boolean insertRecord(Record recordToBeEntered){
        try {
            String recordDetails = String.format("INSERT INTO records (\"weight\", \"temperature\", \"bloodPressure\", \"Note\", \"userID\")\n" + "values (%d,%d,%d,\'%s\', %d);", recordToBeEntered.getWeight(), recordToBeEntered.getTemperature(), recordToBeEntered.getBloodPressure(), recordToBeEntered.getNote(), getUserID());
            stmnt.executeUpdate(recordDetails);
            conn.commit();
            return true;
        }catch(SQLException e){
            //unique constraint is exploited
            System.out.println(e);
            return false;
        }
    }

    boolean deleteRecord(int recordID){
        try {
            String recordDetails = String.format("DELETE FROM public.records where \"recordID\" = %d;",recordID);
            stmnt.executeUpdate(recordDetails);
            conn.commit();
            return true;
        }catch(SQLException e){
            //unique constraint is exploited
            System.out.println(e);
            return false;
        }
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






