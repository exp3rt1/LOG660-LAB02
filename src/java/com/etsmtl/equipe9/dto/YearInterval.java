/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etsmtl.equipe9.dto;

/**
 *
 * @author Mario Morra
 */
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
