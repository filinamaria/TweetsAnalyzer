package functions;

import java.util.*;

public class Function {
    public static String[] concat(String[] keyword1, String[] keyword2, String[] keyword3){
        int length = keyword1.length + keyword2.length + keyword3.length;
        String[] combined = new String[length];
        for(int i = 0; i < keyword1.length; i++){
            combined[i] = keyword1[i];
        }
        for(int i = 0; i < keyword2.length; i++){
            combined[i + keyword1.length] = keyword2[i];
        }
        for(int i = 0; i < keyword3.length; i++){
            combined[i + keyword1.length + keyword2.length] = keyword3[i];
        }
        return combined;
    }
}
