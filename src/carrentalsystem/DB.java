
package carrentalsystem;

import java.sql.*;
public class DB {
 public static Connection getConnection(){
     try{
        Class.forName("oracle.jdbc.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","PROJECT","12345");
        return con;
    }
    catch(Exception e){
        e.printStackTrace();
    }
    return null;
 }    
 public static String[][] getCarTable(){
     try{
            String query="select * from car";
            PreparedStatement st=getConnection().prepareStatement(query);
            ResultSet rs=st.executeQuery();
            String[][] result=new String[40][7];
            int i=0;
            while(rs.next()){
                result[i][0]=rs.getString("plateNumber");
                result[i][1]=rs.getString("model");
                result[i][2]=rs.getString("manfucturer");
                result[i][3]=rs.getString("color");
                result[i][4]=rs.getString("price");
                result[i][5]=rs.getString("speed");
                result[i][6]=rs.getString("quantity");
                i++;
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
 }
 public static String[] getListBy(String c){
     try {
            String query = "select " +c+ " from car";
            PreparedStatement st = getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            String[] result = new String[30];
            int i = 0;
            while (rs.next()) {
                result[i] = rs.getString(c);
                i++;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
 }
 public static String[] getResultBy(String plate){
      try {
            String query = "select * from car where plateNumber='" + plate + "'";
            PreparedStatement st = getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            String[] result = new String[10];
            while (rs.next()) {
                result[0] = rs.getString(0);
                result[1] = rs.getString(1);
                result[2] = rs.getString(2);
                result[3] = rs.getString(3);
                result[4] = rs.getString(4);
                result[5] = rs.getString(5);
                result[6] = rs.getString(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
 }
 public static String getValue(String column,String plate){
     try{
          String query = "select "+column+" from car where plateNumber='" + plate + "'";
            PreparedStatement st = getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            String name = new String();
            while(rs.next())
                name=rs.getString(column);
            return name;
      }catch(Exception e){e.printStackTrace();}
      return null;
 }
 public static void execute(String query){
     try{
            PreparedStatement st=getConnection().prepareStatement(query);
            st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
 }
 public static String[][] getRentedCars(){
      try{
            String query="select name,model,price,speed,rentdate,returndate,quantity from rent inner join car on car.plateNumber=rent.carid inner join customer on customer.id=rent.customerid";
            PreparedStatement st=getConnection().prepareStatement(query);
            ResultSet rs=st.executeQuery();
            String[][] result=new String[40][7];
            int i=0;
            while(rs.next()){
                result[i][0]=rs.getString("name");
                result[i][1]=rs.getString("model");
                result[i][2]=rs.getString("price");
                result[i][3]=rs.getString("speed");
                result[i][4]=rs.getString("rentDate");
                result[i][5]=rs.getString("returnDate");
                result[i][6]=rs.getString("quantity");
                i++;
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
 }
 public static int getTotalNumberOfCars(String column,String value){
     try{
        String query="select plateNumber from car where "+column+"='"+value+"'";
        PreparedStatement st=getConnection().prepareStatement(query);
        ResultSet rs=st.executeQuery();
        int a=0;
        while(rs.next()){
            a=rs.getRow();
        }
        return a;
    }catch(Exception e){
        e.printStackTrace();
    }
    return 0;
 }
 public static int getTotalCarsRented(){
     try{
        String query="select plateNumber from rent inner join car on car.plateNumber=rent.carid inner join customer on customer.id=rent.customerid ";
        PreparedStatement st=getConnection().prepareStatement(query);
        ResultSet rs=st.executeQuery();
        int a=0;
        while(rs.next()){
            a=rs.getRow();
        }
        return a;
    }catch(Exception e){
        e.printStackTrace();
    }
    return 0;
 }
 public static int getTotalIncomeEarned(){
     try{
         String query="select price from rent inner join car on car.plateNumber=rent.carid inner join customer on customer.id=rent.customerid ";
         PreparedStatement st=getConnection().prepareStatement(query);
         ResultSet rs=st.executeQuery();
         int i=0;
         while(rs.next()){
             int result=Integer.parseInt(rs.getString("price"));
             i=i+result;
         }
         return i;
     }catch(Exception e){
         e.printStackTrace();
     }
     return 0;
 }
 public static String[] getCustomerList(String column){
     try {
            String query = "select "+column+" from customer";
            PreparedStatement st = getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery();
            String[] result = new String[10];
            int i=0;
            while (rs.next()) {
                result[i] = rs.getString(column);
                i++;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
 }
 public static String getSelectedColumn(String column,String id){
     try{
          String query="select "+column+" from car where plateNumber='"+id+"'";
          PreparedStatement st=getConnection().prepareStatement(query);
          ResultSet rs=st.executeQuery();
          String value=new String();
          while(rs.next())
              value=rs.getString(column);
          return value;
     }catch(Exception e){
         e.printStackTrace();
     }
     return null;
 }
}
