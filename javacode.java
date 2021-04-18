package libmanage;

import java.util.*;
import java.sql.*;
import java.time.*;


class Student {

    String name;
    long sapId;
    String libraryId;
    String courseName;

    Student(String name,
            long sapId,
            String libraryId,
            String courseName) {
        this.courseName = courseName;
        this.libraryId = libraryId;
        this.sapId = sapId;
        this.name = name;

    }

}

class Admin extends Student {

    long id;

    public Admin(String name, long sapId, String libraryId, String courseName, long id) {
        super(name, sapId, libraryId, courseName);
        this.id = id;
    }

    //Student st=new Student();
    String getStudentDetails() {
        return "student's details are " + super.name + " " + super.courseName + " " + super.libraryId + " " + super.sapId;
    }
// ma'am it is in a class whoch is not called
}

public class Libmanage {

    public static void main(String[] args) throws SQLException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/student", "shru", "shru");
            Statement stmt = con.createStatement();
            ResultSet s;
            int g;
            Scanner sc = new Scanner(System.in);
            long pswd = 1234_56789l;
            System.out.println("i am library bot, my name is shaan");
            System.out.println(""
                    + "");
            System.out.println("tell me you are admin(a) or student(s)");
            char ch = sc.next().charAt(0);
            if (ch == 'a') {
                System.out.println("so, hello Admin, enter password");
                long t = sc.nextLong();
                if (t == pswd) {
                    System.out.println("i am library bot how can i help you, mr.Admin. please enter  1  for viewing student details, 2 for knowing its books,3 for updating record in student table, 4 for issuing book");
                    System.out.println("I am waiting ...");

                    g = sc.nextInt();
                    Long h;
                    switch (g) {
                        case 1://currently i am here
                            System.out.println("please enter sap id of the student whose data you want to see");
                            h = sc.nextLong();

                            s = stmt.executeQuery("select * from Student where SAPID =" + h);
                            while (s.next()) {
                                System.out.println(s.getString("name") + " " + s.getString("libraryid") + " " + s.getLong("sapid") + " " + s.getString("coursename"));

                            }
                            break;
                            

                        case 2:
                            System.out.println("insert student record from table");

                            System.out.println("enter sapid, name ,libraryid,coursename");

                            Long sapiid = sc.nextLong();

                            String name = sc.next();

                            String libraryid = sc.next();

                            String coursename = sc.next();

                            String query = "insert into STUDENT values( ? , ? , ? , ? )";

                            PreparedStatement pstmt = con.prepareStatement(query);

                            pstmt.setLong(1, sapiid);

                            pstmt.setString(2, name);

                            pstmt.setString(3, libraryid);

                            pstmt.setString(4, coursename);
                            int l = pstmt.executeUpdate();
                            System.out.println("no of rows updated " + l);

                            System.out.println("updation happened coolely 🛴");
                            break;
                        case 3://where??
                            System.out.println("delete student record from table");
                            System.out.println("enter sapid");
                            Long sapd = sc.nextLong();
                            stmt.executeUpdate("delete from Student where sapId=" + sapd);
                            s = stmt.executeQuery("select * from Student");
                            while (s.next()) {
                                System.out.println(s.getLong("SAPID") + s.getString("name") + s.getString("coursename"));
                            }
                            break;//yes
                        //line 114
                    }
                    String name1 = sc.nextLine();// this is for student input not for admin
                    long sap = sc.nextLong();
                    String Lib = sc.nextLine();
                    String courseName = sc.nextLine();
                    long id = sc.nextLong();
                    Admin ad = new Admin(name1, sap, Lib, courseName, id);
                    String p = ad.getStudentDetails();

                }
            } else if (ch == 's') {
                System.out.println("hi student, welcome please enter your sapid");
                int sapId = sc.nextInt();
                System.out.println("press 1 to view all fines  and book issue/due date till now");
                System.out.println("press 2 to issue new book");
                System.out.println("press 3 to return a book");

                g = sc.nextInt();
                switch (g) {
                    case 1:
                        s = stmt.executeQuery("select * from LIBRARY_log where sapid=" + sapId);

                      while (s.next()) {
                            System.out.println(s.getLong("sapid") + s.getString("BOOK_name") + " " + s.getString("ISSUEDATE") + " " + s.getString("returndate") + " " );
                        }
                        break;
                    case 2:
                        LocalDate localDate = LocalDate.now();
                        LocalDate p = localDate.plusMonths(3);
                        System.out.println("insert book record inside the table");

                        System.out.println("enter sapid ,book name,issuedate, returndate");

                        int sapid = sc.nextInt();

                        String book_name = sc.next();

                        String issuedate = sc.next();

                        String returndate = sc.next();

                        String query = "insert into LIBRARY_LOG values( ? , ? , ? , ? )";

                        PreparedStatement pstmt = con.prepareStatement(query);

                        pstmt.setInt(1, sapid);

                        pstmt.setString(2, book_name);

                        pstmt.setString(3, issuedate);

                        pstmt.setString(4, returndate);
                        int l = pstmt.executeUpdate();
                        System.out.println("no of rows updated " + l);

                        System.out.println("updation happened coolely 🛴");
                        break;
                    case 3:
                        System.out.println("enter book_name which you wanna return");
                        System.out.println("enter book name and sap ");
                        String book = sc.next();
                        sapid = sc.nextInt();
                        int h = stmt.executeUpdate("delete from library_log where book_name=" + book + "and sapid =" + sapid);
                        System.out.println("no of rows deleted " + h);
                        break;//yes

                    default:
                        System.out.println("bye bye !, choose from 1 to 3 ");
                }

            }
            else
            {
                System.out.println("are you staff ?");
                float stafId;
                System.out.println("pls enter your id");
                stafId=sc.nextFloat();
                if(stafId>5000&&stafId<5450){
                    System.out.println("hello staff what is your designation");
                    System.out.println("press t for teacher, p for principal, c for clerk, s for sweeper");
                    char charac=sc.next().charAt(0);switch(charac){
                    case 't' :System.out.println("hello teacher, happy day");
                        System.out.println("enter teacherid");
                        int tid=sc.nextInt();
                        System.out.println("kindly write your name, book name");
                 String d=sc.next();
                 String n=sc.next();
                 
                     String query = "insert into T_detail values( ? , ? , ?  )";

                        PreparedStatement pstmt = con.prepareStatement(query);

                        pstmt.setInt(1, tid);

                        pstmt.setString(2,d);

                        pstmt.setString(3, n);

                      
                        int l = pstmt.executeUpdate();
                        System.out.println("no of rows updated " + l);

                        System.out.println("updation happened coolely 🛴");
                        break;
                    
                    case 'p':
                        System.out.println("hello principal sir, pls add the book name you issue");
                        String book_name;
                        book_name=sc.next();
                              PreparedStatement pstmtk = con.prepareStatement("insert into T_detail values( ? , ? , ?  )");
                        pstmtk.setInt(1,2);
                         pstmtk.setString(2," ");
                          pstmtk.setString(3,book_name);
                        
                      int e= pstmtk.executeUpdate();
                        System.out.println("no of rows updated " + e);

                        System.out.println("updation happened coolely 🛴");
                        break;
                    
                    default:
                        
                     int timd=sc.nextInt();
                        System.out.println("kindly write your name, book name");
                 String di=sc.next();
                 String ni=sc.next();
                 
                     String queryu = "insert into T_detail values( ? , ? , ?  )";

                        PreparedStatement pstmmt = con.prepareStatement(queryu);

                        pstmmt.setInt(1, timd);

                        pstmmt.setString(2,di);

                        pstmmt.setString(3, ni);

                      
                        int gj = pstmmt.executeUpdate();
                        System.out.println("no of rows updated " + gj);

                        System.out.println("updation happened coolely 🛴");
                        break;
                    
                }
            }}}
         catch (SQLException e) {
            System.out.println(e);
        }

    }
    }

