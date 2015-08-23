package functions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import twitter4j.TwitterException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class BM {
    
    private static int[] lastOccurrence(String pattern){
        int last[] = new int[128];
        
        for(int i = 0; i < 128; i++){
            last[i] = -1;
        }
        
        for(int i = 0; i < pattern.length(); i++){
            last[pattern.charAt(i)] = i;
        }
        return last;
    }
    
    private static int bmMatch(String text, String pattern){
        pattern = pattern.toUpperCase();
        text = text.toUpperCase();
        int last[] = lastOccurrence(pattern);
        int n = text.length();
        int m = pattern.length();
        int i = m-1;
        
        if(i > n-1){
            return -1;
        }
        
        int j = m-1;
        do{
            if(pattern.charAt(j) == text.charAt(i)){
                if(j == 0){
                    return i;
                }else{
                    i = i-1;
                    j = j-1;
                }
            }else{
                int lo = last[text.charAt(i)];
                i = i + m - Math.min(j, 1 + lo);
                j = m - 1;
            }
        }while(i <= n-1);
        return -1;
    }
    
    private static int indeksMin(int[] arrInt){
        int min = 999999;
        int indeks = -1;
        for(int i = 0; i < arrInt.length; i++){
            if(arrInt[i] < min && arrInt[i] != -1){
                min = arrInt[i];
                indeks = i;
            }
        }
        if(min == 999999){
            indeks = -1;
        }
        return indeks;
    }
    
    public static String filter(String text, String[] pattern){
        text = text.replaceAll("[^\\x00-\\x7F]", "");
        int patternStartingPosition[] = new int[pattern.length];
        for(int i = 0; i < pattern.length; i++){
            patternStartingPosition[i] = bmMatch(text, pattern[i]);
        }
        int indeksFirstPattern = indeksMin(patternStartingPosition);
        if(indeksFirstPattern == -1){
            return "Unknown";
        }else{
            return pattern[indeksFirstPattern];
        }
    }

    public static void main(String[] args) {
        int i;
            tweets t = new tweets();
            String pattern[] = new String[5];
            String hashtag = "#lalinbdg";
            pattern[0] = "lancar";
            pattern[1] = "macet";
            pattern[2] = "stuck";
            pattern[3] = "tabrak";
            pattern[4] = "tutup";
            try{
                t.SetTweet(hashtag);       
                String tweet[] = t.GetTweetText();
                String user[] = t.GetTweetUser();
                for(i=0; i<tweet.length; i++){
                    if(!tweet[i].equals("null")){
                        System.out.println(i + " @" + user[i] + " " + tweet[i] + "\nKeyword: " + filter(tweet[i], pattern));
                    }
                }
            }
            catch(TwitterException e){
                System.out.println("Tidak bisa terhubung ke twitter");
            }
    }   
    
}
