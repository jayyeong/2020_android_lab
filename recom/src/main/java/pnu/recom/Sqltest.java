package pnu.recom;

import java.sql.Connection;
import java.sql.DriverManager;

public class Sqltest {

	public static void main(String[] args) {
		Connection con = null;
		
		String className = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/user?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user = "root";
        String passwd = "1234";
        
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connect Success!");
        } catch(Exception e) {
            System.out.println("Connect Failed!");
            e.printStackTrace();
        } finally {
                try {
                    if(con != null && !con.isClosed()) {
                        con.close();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
        }

	}

}
