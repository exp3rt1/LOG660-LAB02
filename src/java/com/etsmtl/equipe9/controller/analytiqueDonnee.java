package com.etsmtl.equipe9.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class analytiqueDonnee {
    public static void main(String[] args) {
        
        StringBuilder requete = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        Scanner scanner;
        boolean ajouterAnd = false;
        

        requete.append("SELECT * FROM ");
        requete.append("TRANSFORMATION_LOCATION l NATURAL JOIN TRANSFORMATION_TEMPS t ");
        requete.append("NATURAL JOIN TRANSFORMATION_CLIENT c NATURAL JOIN TRANSFORMATION_FILM f");

        /*******************************************
         *             Groupe d'age                *
         *******************************************/
        System.out.print("Veuillez entrez un groupe d'age (ex: 32 ans => 7) (ex: Tous => *): ");
        scanner = new Scanner(System.in);
        String groupeAge = scanner.nextLine();

        if(!groupeAge.equals("*")) {
            temp.append("c.GROUPEAGE = ");
            temp.append(groupeAge);
            ajouterAnd = true;
        }

        /*******************************************
         *               Province                  *
         *******************************************/
        System.out.print("Veuillez entrez une province (ex: Quebec => QC) (ex: Tous => *): ");
        scanner = new Scanner(System.in);
        String province = scanner.nextLine();

        if(!province.equals("*")) {
            if(ajouterAnd) {
                temp.append(" AND ");
            }
            
            temp.append("c.PROVINCE = '");
            temp.append(province);
            temp.append("'");
            ajouterAnd = true;
        }
        
        /*******************************************
         *             JourSemaine                 *
         *******************************************/
        System.out.print("Veuillez entrez un jour de la semaine (ex: FRIDAY) (ex: Tous => *): ");
        scanner = new Scanner(System.in);
        String jourSemaine = scanner.nextLine();

        if(!jourSemaine.equals("*")) {
            if(ajouterAnd) {
                temp.append(" AND ");
            }
            
            temp.append("t.JOURSEMAINE = '");
            temp.append(jourSemaine);
            temp.append("'");
            ajouterAnd = true;
        }
        
        /*******************************************
         *                 Annee                   *
         *******************************************/
        System.out.print("Veuillez entrez une annee (ex: 2016) (ex: Tous => *): ");
        scanner = new Scanner(System.in);
        String annee = scanner.nextLine();

        if(!annee.equals("*")) {
            if(ajouterAnd) {
                temp.append(" AND ");
            }
            
            temp.append("t.ANNEE = ");
            temp.append(annee);
        }
        
        if(temp.length() != 0) {
            requete.append(" WHERE ");
            requete.append(temp);
        }
        
        int nbLocation = getNbLocations(requete.toString());
        System.out.println("Nombre de location: " + nbLocation);
            
    }
    
    private static Connection connectionBD() {
        String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String DB_URL = "jdbc:oracle:thin:@big-data-3.logti.etsmtl.ca:1521:Log660";
        String USER = "equipe9";
        String PASS = "88lyZUIU";
        int MAX_GRANDEUR_BATCH = 10;

       try {
           // Enregistrer le driver JDBC
           Class.forName(JDBC_DRIVER);

           // Ouvrir une connection
           Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
           conn.setAutoCommit(true);
           return conn;
       }
       catch (Exception e) {
           System.out.println(e.getMessage());
       }
       return null;
    }


    public static int getNbLocations(String query){

        Connection conn = null;
        int nbLocations = 0;
        
        conn = connectionBD();
        
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(query);
            
            if(rs.next() != false) {
                rs.last();
                nbLocations = rs.getRow();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return nbLocations;
    }
}

