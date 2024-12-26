package com.ecp.ps.parser;

import lombok.Data;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;



@Data
public class JsonParserFilter {

    private String type;
    private String operator;
    private String selector;
    private Object argument;
    private String lhs;
    private String rhs;

    public static int indexA(String s){
        Map<Character,Integer> map = new HashMap();
        for(int i =0 ;i<s.length();i++)
        {
            if(map.containsKey(s.charAt(i)))
            {
                int j = map.get(s.charAt(i));
                map.put(s.charAt(i),++j);
            }
            else{
                System.out.println( s.charAt(i));
                map.put(s.charAt(i),1);
            }
        }

        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i)) && 1==map.get(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }
}





