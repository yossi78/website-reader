package com.peer39.websitereader.utils;

public class StringUtil {



    public static String removeTags(String str){
        return str.replaceAll("<[^>]*>", "");
    }

    public static String removeSpacesFromBegining(String str){
        return str.replaceFirst("^\\s+", "");
    }

    public static String removeRedundentNewLines(String str){
        return str.replaceAll("[\r\n]+", "\n");
    }

    public static String replaceNewLinesWithSpace(String str){
        return str.replaceAll("[\r\n]+", " ");
    }

    public static String replaceMultipleSpacesWithSingleOne(String str){
        return str.replaceAll("[ ]+", " ");
    }


}
