package com.mikhaildolgopolov.spring;

import java.util.HashMap;
import java.util.Map;

public class Transliteration {
    private static final Map<Character, String> map = new HashMap<>() {
        {
            put('а', "a");
            put('б', "b");
            put('в', "v");
            put('г', "g");
            put('д', "d");
            put('е', "e");
            put('ж', "zh");
            put('з', "z");
            put('и', "i");
            put('й', "j");
            put('к', "k");
            put('л', "l");
            put('м', "m");
            put('н', "n");
            put('о', "o");
            put('п', "p");
            put('р', "r");
            put('с', "s");
            put('т', "t");
            put('у', "u");
            put('ф', "f");
            put('х', "x");
            put('ц', "c");
            put('ч', "ch");
            put('ь', "'");
            put('ш', "sh");
            put('щ', "shy");
            put('ы', "y");
            put('э', "e");
            put('ю', "yu");
            put('я', "ya");
        }
    };

    public static String transliterate(String phrase) {
        if(phrase==null || phrase.equals("")) return "";
        StringBuilder result = new StringBuilder();

        for (int i=0;i<phrase.length();i++){
            char ch = phrase.charAt(i);
            String add = map.getOrDefault(Character.toLowerCase(ch), Character.toString(ch));
            result.append((Character.isUpperCase(ch)) ? add.toUpperCase() : add);
        }
        return result.toString();
    }
}
