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
    
    private String start;
    private String end;
    
    private YearInterval(String start, String end){
        this.start = start;
        this.end = end;
    }
    
    public String getStart(){
        return start;
    }
    
    public String getEnd(){
        return end;
    }
    
}
