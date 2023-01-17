import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Case_hearing_System {

    static Scanner sc = new Scanner(System.in);

    public static void judgeLogin() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //creating a connection
        String url = "jdbc:mysql://localhost:3306/Project";
        String username = "root";
        String password = "Pcml1234";

        Connection con = DriverManager.getConnection(url, username, password);
        String q = "Select * from judge where username =?";
        java.sql.PreparedStatement pstmt = con.prepareStatement(q);
        String adm_username, adm_password;
        System.out.println("Enter Judge username:");
        adm_username = sc.next();
        System.out.println("Enter Judge Password:");
        adm_password = sc.next();
        pstmt.setString(1, adm_username);
        ResultSet result = pstmt.executeQuery();
        String check = null;
        while (result.next()) {
            check = result.getString(2);
        }

        if (Objects.equals(check, adm_password)){
            System.out.println("*Welcome to the Judge Section*");
            while(true){
                System.out.println("1.View all Cases");
                System.out.println("2.Change status to completed");
                System.out.println("3.Delete a Case");
                System.out.println("4.Exit");


                System.out.println("Enter your choice:");
                int choice =sc.nextInt();
                switch (choice){
                    case 1:
                        String s = "Select * from cases";
                        Statement stmt=con.createStatement();
                        ResultSet r= stmt.executeQuery(s);
                        while(r.next()){
                            System.out.println("********");
                            System.out.print("Case id:"+r.getString(1)+" ");
                            System.out.print("Name:"+r.getString(2)+" ");
                            System.out.print("Phone No."+r.getString(3)+" ");
                            System.out.print("City:"+r.getString(4)+" ");
                            System.out.print("Case Description:"+r.getString(5)+" ");
                            System.out.print("Hearing Time:"+r.getString(6)+" ");
                            System.out.println("Courtroom:"+r.getString(7)+" ");
                            System.out.println("IPC's:"+r.getString(8)+" ");
                            System.out.println("Status:"+r.getString(9)+" ");

                        }
                        break;

                    case 2:
                        System.out.println("Enter Case Id:");
                        String input= sc.next();
                        System.out.println("Enter IPC's which are used at time of final Hearing:");
                        String inputip= sc.next();
                        String a = "Update Cases set status='Completed' where Cid=?";
                        String a1 = "Update Cases set IPC=? where Cid=?";
                        java.sql.PreparedStatement stmt1 = con.prepareStatement(a);
                        java.sql.PreparedStatement stmt2 = con.prepareStatement(a1);
                        stmt1.setString(1,input);
                        stmt2.setString(1,inputip);
                        stmt2.setString(2,input);
                        stmt1.executeUpdate();
                        stmt2.executeUpdate();
                        System.out.println("Successfully Changed the status...");
                        System.out.println("");
                        break;

                    case 3:
                        System.out.println("Enter Case Id:");
                        input=sc.next();
                        String b="delete from cases where Cid=?";
                        java.sql.PreparedStatement stmt3 = con.prepareStatement(b);
                        stmt3.setString(1,input);
                        stmt3.executeUpdate();
                        System.out.println("Deleted Successfully....");
                        break;

                    case 4:

                        return;

                }
            }

        }

        else{
            System.out.println("Incorrect Login");

        }

    }
    public static void User() throws ClassNotFoundException,SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        //creating a connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        //creating a connection
        String url2 = "jdbc:mysql://localhost:3306/Project";
        String username2 = "root";
        String password2 = "Pcml1234";

        Connection con2 = DriverManager.getConnection(url2, username2, password2);
        String r = "Select * from cases where Cid = ?";
        System.out.println("Enter your Case Id:");
        String com_id = sc.next();
        java.sql.PreparedStatement pstmt2 = con2.prepareStatement(r);
        pstmt2.setString(1, com_id);
        ResultSet result1 = pstmt2.executeQuery();
        if(result1==null){
            System.out.println("Please Enter valid Cid....");

        }

        while (result1.next()) {

            System.out.println("Case id:" + result1.getString(1));
            System.out.println("Name:" + result1.getString(2));
            System.out.println("Phone No." + result1.getString(3));
            System.out.println("City:" + result1.getString(4));
            System.out.println("Case description:" + result1.getString(5));
            System.out.println("Hearing Time:" + result1.getString(6));
            System.out.println("Courtroom:" + result1.getString(7));
            System.out.println("IPc's:" + result1.getString(8));
            System.out.println("Status:" + result1.getString(9));
            System.out.println("***************");

        }

    }
    public static void Admin() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //creating a connection
        String url = "jdbc:mysql://localhost:3306/Project";
        String username = "root";
        String password = "Pcml1234";

        Connection con = DriverManager.getConnection(url, username, password);

        String q = "Select * from Admin where username =?";
        java.sql.PreparedStatement pstmt = con.prepareStatement(q);
        String adm_username, adm_password;
        System.out.println("Enter Admin username:");
        adm_username = sc.next();
        System.out.println("Enter Admin Password:");
        adm_password = sc.next();
        pstmt.setString(1, adm_username);
        ResultSet result = pstmt.executeQuery();
        String check = null;
        while (result.next()) {
            check = result.getString(2);
        }

        if (Objects.equals(check, adm_password)) {
            System.out.println("*Welcome to the Admin Section*");
            while (true) {
                System.out.println("1.Add New Case ");
                System.out.println("2.View Status");
                System.out.println("3.Change Hearing timing for a case");
                System.out.println("4.Change courtroom  for a case");
                System.out.println("5.Exit");
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        //creating a connection
                        String url1 = "jdbc:mysql://localhost:3306/Project";
                        String username1 = "root";
                        String password1 = "Pcml1234";

                        Connection con1 = DriverManager.getConnection(url1, username1, password1);

                        String q1 = "Insert into cases(Cid,name,phone,city,casedescription,hearingtime,courtroom) values(?,?,?,?,?,?,?)";

                        java.sql.PreparedStatement pstmt1 = con.prepareStatement(q1);

                        String Cid,name,phone,city,casedescription,hearingtime,courtroom;
                        Scanner scanner = new Scanner(System.in);

                        System.out.println("Enter your Case Id:");
                        Cid = scanner.nextLine();
                        System.out.println("Enter your Name:");
                        name = scanner.nextLine();
                        System.out.println("Enter your Phone_No:");
                        phone = scanner.nextLine();
                        System.out.println("Enter your City:");
                        city = scanner.nextLine();
                        System.out.println("Enter your Case Description:");
                        casedescription = scanner.nextLine();
                        System.out.println("Enter Hearing time for case:");
                        hearingtime = scanner.nextLine();
                        System.out.println("Enter courtroom for case:");
                        courtroom = scanner.nextLine();

                        pstmt1.setString(1, Cid);
                        pstmt1.setString(2, name);
                        pstmt1.setString(3, phone);
                        pstmt1.setString(4, city);
                        pstmt1.setString(5, casedescription);
                        pstmt1.setString(6, hearingtime);
                        pstmt1.setString(7, courtroom);

                        pstmt1.executeUpdate();

                        break;

                    case 2:

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        //creating a connection
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        //creating a connection
                        String url2 = "jdbc:mysql://localhost:3306/Project";
                        String username2 = "root";
                        String password2 = "Pcml1234";

                        Connection con2 = DriverManager.getConnection(url2, username2, password2);
                        String r = "Select * from cases where Cid = ?";
                        System.out.println("Enter your Case Id:");
                        String com_id = sc.next();
                        java.sql.PreparedStatement pstmt2 = con2.prepareStatement(r);
                        pstmt2.setString(1, com_id);
                        ResultSet result1 = pstmt2.executeQuery();

                        while (result1.next()) {
                            System.out.println("Case id:" + result1.getString(1));
                            System.out.println("Name:" + result1.getString(2));
                            System.out.println("Phone No." + result1.getString(3));
                            System.out.println("City:" + result1.getString(4));
                            System.out.println("Case description:" + result1.getString(5));
                            System.out.println("Hearing Time:" + result1.getString(6));
                            System.out.println("Courtroom:" + result1.getString(7));
                            System.out.println("IPc's:" + result1.getString(8));
                            System.out.println("Status:" + result1.getString(9));
                            System.out.println("***************");

                        }

                        break;


                    case 3:
                        System.out.println("Enter your Case Id:");
                        String case_id=sc.next();
                        System.out.println("Enter hearing time:");
                        String input1=sc.next();
                        String r1 = "Update cases set hearingtime=? where cid=?";


                        java.sql.PreparedStatement pstmt3 = con.prepareStatement(r1);
                        pstmt3.setString(1, input1);
                        pstmt3.setString(2, case_id);
                        pstmt3.executeUpdate();
                        System.out.println("Successfully Updated Hearing Time for case:"+case_id);
                        System.out.println("");
                        break;
                    case 4:
                        System.out.println("Enter your Case Id:");
                        String case_id1=sc.next();
                        System.out.println("Enter Court Room:");
                        String input2=sc.next();
                        String r2 = "Update cases set courtroom=? where cid=?";


                        java.sql.PreparedStatement pstmt4 = con.prepareStatement(r2);
                        pstmt4.setString(1, input2);
                        pstmt4.setString(2, case_id1);
                        pstmt4.executeUpdate();
                        System.out.println("Successfully Updated Court Room for case:"+case_id1);
                        System.out.println("");
                        break;

                    case 5:
                        return;
                }
            }
        }

    }


    public static void main(String[] args) {

        System.out.println("**Welcome to Case Hearing System**");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //creating a connection
            String url="jdbc:mysql://localhost:3306/Project";
            String username="root";
            String password="Pcml1234";

            Connection con = DriverManager.getConnection(url,username,password);

            while(true){

                System.out.println("1.Judge");
                System.out.println("2.Admin");
                System.out.println("3.User");
                System.out.println("4.Exit");
                System.out.println("Enter your choice:");
                Scanner sc = new Scanner(System.in);
                int input=sc.nextInt();
                switch (input){
                    case 1:
                        judgeLogin();
                        break;

                    case 2:
                        Admin();
                        break;

                    case 3:
                        User();
                        return;
                    case 4:
                        return;
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}