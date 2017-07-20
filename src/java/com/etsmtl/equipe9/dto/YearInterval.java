package com.etsmtl.equipe9.dto;

public class YearInterval {
    
    private int start;
    private int end;
    
    public YearInterval(int start, int end){
        this.start = start;
        this.end = end;
    }
    
    public int getStart(){
        return start;
    }
    
    public int getEnd(){
        return end;
    }
    
}
