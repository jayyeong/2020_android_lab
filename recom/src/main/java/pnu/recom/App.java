package pnu.recom;

import java.io.*;
import java.util.*;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import java.util.Timer;
import java.util.TimerTask;

public class App
{	
	static long box;
	static int i = 2;
	
    public static void main( String[] args ) throws IOException, TasteException
    {
		
		String className = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/user2?useSSL=false&useUnicode=true&characterEncoding=euckr";
        String user = "root";
        String passwd = "1234";
        
        String createfile = "C:\\Users\\aia12\\Desktop\\data.csv";
        FileWriter fw = new FileWriter(createfile);
        
        try 
        {
        Class.forName(className);
        Connection con = DriverManager.getConnection(url, user, passwd);
        
        String qu = "select * from favorite2";
        
        Statement st = con.createStatement();
        
        ResultSet rs = st.executeQuery(qu);
        
        while(rs.next()) {
        	String un = rs.getString("userNumber");
        	String name = rs.getString("restaurant");
        	String favorite = rs.getString("favorite");
        	
        	System.out.format("%s , %s , %s", un, name, favorite);
        	fw.append(un);
        	fw.append(',');
        	fw.append(name);
        	fw.append(',');
        	fw.append(favorite);
        	fw.append('\n');
        	System.out.println();
        }
        fw.flush();
        fw.close();
        st.close();
        }
        catch(Exception e) {
        	System.err.println("Got an exception");
        	System.err.println(e.getMessage());
        }
        
        for(i = 1; i < 5 ; i++) {
        // 추천 알고리즘 부분  --------------------------------------------------------------------------------------           
        
        DataModel model = new FileDataModel(new File("C:\\Users\\aia12\\Desktop\\data.csv")); // 파일 읽기  

       
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model); // 유저 기반 추천

        //0.1보다 큰 유사성을 가진 모든 것을 사용. 그리고 ThresholdUserNeighborhood를 통해 구현

        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);

        //유저 기준 추천 모델

        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        //x번 유저에게 y개 아이템 추천  => (x, y)
        
        List<RecommendedItem> recommendations = recommender.recommend(i,1);
        
        for (RecommendedItem recommendation : recommendations) {
         System.out.println(recommendation);
         System.out.println(recommendation.getValue());
         System.out.println(recommendation.getItemID());
         
         if((Long)recommendation.getItemID() == null) box = 222;
         else  box = recommendation.getItemID();
        } 
        
        
       // db 정보 업데이트---------------------------------------------------------------------------------------
       try {
    	   Connection con = null;
    	   PreparedStatement st = null;
    	   
    	   Class.forName(className);
    	   Connection con1 = DriverManager.getConnection(url, user, passwd);
    	   
    	   String sql = "update login set result = ? where num = ?";
           st = con1.prepareStatement(sql);
           st.setLong(1, box); // best recommend
           st.setInt(2, i); // id number
           
           st.executeUpdate();
           
           con1.close();
           }catch(Exception e) {
        	   System.err.println("Got an exception");
           	   System.err.println(e.getMessage());
           }
        }//s
	}
}

 
