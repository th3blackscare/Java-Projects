package com.otfayoum.utils;

public class test {
    private static String Name;
    private static int Id;
    public test(){
    }
    public void setName(String name){
        this.Name=name;
    }
    public static String getName(){
        return Name;
    }
    public void setID(int id){
        this.Id=id;
    }
    public static int getId(){
        return Id;
    }
}
