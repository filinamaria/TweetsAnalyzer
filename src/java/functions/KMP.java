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

public class KMP {

     private static int[] computeFail(String Pattern){
        int b[];
        b = new int [Pattern.length()];
        b[0] = 0;
        int m = Pattern.length();
        int j = 0;
        int i = 1;
        while (i < m){
            if(Pattern.charAt(j) == Pattern.charAt(i)) {
                b[i] = j + 1;
                i++;
                j++;
            }
            else if (j > 0){
                j = b[j-1];
            }
            else {
                b[i] = 0;
                i++;
            }
        }
        return b;
    }
    
    private static  int KMPMatch(String text, String Pattern){
        Pattern = Pattern.toUpperCase();
        text = text.toUpperCase();
        int n = text.length();
        int m = Pattern.length();
        int b[] = computeFail(Pattern);
        int i=0;
        int j=0;
        while (i < n){
            if (Pattern.charAt(j) == text.charAt(i)){
                if (j == m - 1){
                    return i - m + 1;
                }
                i++;
                j++;
            }
            else if (j > 0){
                j = b[j-1];
            }
            else{
                i++;
            }
        }
        return -1;
    } 
    
    private static  int indeksMin(int[] arrInt){
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
            patternStartingPosition[i] = KMPMatch(text, pattern[i]);
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
            pattern[1] = "kecelakaan";
            pattern[2] = "stuck";
            pattern[3] = "tabrak";
            pattern[4] = "tutup";
            
                t.SetTweetFromDatabase();       
                String tweet[] = t.GetTweetText();
                String user[] = t.GetTweetUser();
                for(i=0; i<tweet.length; i++){
                   if(!tweet[i].equals("null")){
                        System.out.println(i + " @" + user[i] + " " + tweet[i] + "\nKeyword: " + filter(tweet[i], pattern));
                    }
                }
            
            
    }    
}
