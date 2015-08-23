package functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Category {
    public ArrayList <String> tempat;
    public List <String> user;
    public List <String> tweets;
    public List <String> keywords;
    
    public Category(){
        tempat = new ArrayList<> ();
        user = new ArrayList<> ();
        tweets = new ArrayList<> ();
        keywords = new ArrayList<> ();
    }
    
    public void Print(){
        int i;
        System.out.println("Jumlah: " + tweets.size());
        System.out.println("Lokasi : ");    
        for(i=0; i<tempat.size(); i++){
                System.out.print(tempat.get(i));
                if(i < tempat.size()-1){
                    System.out.print("; ");
                }
                else{
                    System.out.println();
                }
            }
        
        for(i=0; i<tweets.size(); i++){
            System.out.println("@" + user.get(i) + ": " + tweets.get(i));
        }
    }
    
    public void SetKeywords (String key){
            keywords.add(key.toLowerCase());
    }
    
    public boolean IsKeywords (String key){
        return keywords.contains(key);
    }
    
    
    public void AddTweetUser(String username, String tw){
        user.add(username);
        tweets.add(tw);
        SearchLocation(tw);
    }
    
    public void SearchLocation(String tweet){
         String location = "Unknown"; 
        try {  
            BufferedReader in = new BufferedReader(new FileReader("C:/tempat.txt"));
            String str;
            boolean found = false;
            while ((str = in.readLine()) != null){
                Pattern pattern = Pattern.compile(str.toLowerCase());
                Matcher matcher = pattern.matcher(tweet.toLowerCase());
                if (matcher.find() && !tempat.contains(str)){
                    tempat.add(str);
                }
                
            }
            in.close();
         }
        catch (IOException e) {
            System.out.println("Tidak ada file");
        }
    }
}
