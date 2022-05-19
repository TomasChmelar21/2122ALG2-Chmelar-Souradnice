/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tomch
 */
public class Database {
    private static ArrayList<Cache> loadedCaches;
    
    public Database(){
           loadedCaches = new ArrayList<>();
    }
    
    public void loadCaches(File database) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(database))){
        String line;
        String[] parts;
        Cache r;
        br.readLine();
        line = br.readLine();
        while(line != null) {
            parts = line.split("[ ]");
            //String code, String latitude, String longtitude, String name
            r = new Cache(parts[0], parts[1], parts[2], parts[3]);
            loadedCaches.add(r);
            line = br.readLine();
        }
        }
    }
    
    public static String printloaded(){
        StringBuilder sb = new StringBuilder();
        for (Cache loadedCache : loadedCaches) {
            sb.append(loadedCache.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        Database database = new Database();
                try{
                    database.loadCaches(new File("./Data/Database.txt"));
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
                
                System.out.println(printloaded());
                //System.out.println((database.loadedCaches.get(2).getCode())).getLink();
                System.out.println((database.loadedCaches.get(2)).getLink());
    }
    
}

