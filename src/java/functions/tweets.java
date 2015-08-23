package functions;

import java.sql.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class tweets {
    private String tweetuser[];
    private String tweettext[];
    
    public tweets(){
        tweetuser = new String[0];
        tweettext = new String[0];
    }
    public String[] GetTweetUser(){
        return tweetuser;
    }
    public String[] GetTweetText(){
        return tweettext;
    }
    public void SetTweet(String hashtag) throws TwitterException {
        Connection connection = null; 
        Statement stmt1 = null;
        Statement stmt2 = null;
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/tweets?zeroDateTimeBehavior=convertToNull";
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            connection = DriverManager.getConnection(connectionURL, "root", "");
            stmt1 = connection.createStatement();
            String sqlQuery0 = "truncate tweets";
            ResultSet rs = stmt1.executeQuery(sqlQuery0);
        }catch(Exception ex){
            System.out.println("cannot connect to database");
        }
        tweettext = new String[100];
        tweetuser = new String[100];
        int i;
        for (i=0; i<100; i++){
            tweettext[i] = "null";
            tweetuser[i] = "null";
        }
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("nbkWBzHuX0LYmUEqINsee62uj")
                .setOAuthConsumerSecret("XIeZJiJ5WuZJAqh6ZpEpk7I8EGNu3dKUgvhwb7VSYrlLpq7T8t")
                .setOAuthAccessToken("76862882-RCWeqJSMi3OxXxhiqNh7CWAeGwZv3KvsJuY1eKe85")
                .setOAuthAccessTokenSecret("TlYgj3g49aLlTbbFpkAJ4JtnjDbZNsrcOpcYVrywwQ8w4");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        //
        int j = 0;
        Query query = new Query(hashtag);
        query.setCount(100);
        QueryResult result = twitter.search(query);
        for (Status status : result.getTweets()){
            tweetuser[j] = status.getUser().getScreenName();
            tweettext[j] = status.getText();
            try{
                String sqlQuery1 = "insert into tweets (`account`, `tweets`) values ('" + tweetuser[j] + "', '" + tweettext[j] + "');";
                stmt2 = connection.createStatement();
                stmt2.executeUpdate(sqlQuery1);
            }catch(Exception ex){
                System.out.println("gagal");
            }
            j++;
        }
        try{
            connection.close();
        }catch(Exception ex){
            System.out.println("Unable to close database");
        }
    }
    
    public void SetTweetFromDatabase() {
        Connection connection = null; 
        Statement stmt = null;
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/tweets?zeroDateTimeBehavior=convertToNull";
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            connection = DriverManager.getConnection(connectionURL, "root", "");
        }catch(Exception ex){
            System.out.println("cannot connect to database");
        }
        tweettext = new String[100];
        tweetuser = new String[100];
        int i;
        for (i=0; i<100; i++){
            tweettext[i] = "null";
            tweetuser[i] = "null";
        }
        try{
            stmt = connection.createStatement();
            String sql = "SELECT * from tweets";
            ResultSet rs = stmt.executeQuery(sql);
            int j = 0;
            while(rs.next()){
               tweettext[j] = rs.getString("tweets");
               tweetuser[j] = rs.getString("account");
               j++;
            }
        }catch(Exception ex){
            
        }
        try{
            connection.close();
        }catch(Exception ex){
            System.out.println("Unable to close database");
        }
    }
}
