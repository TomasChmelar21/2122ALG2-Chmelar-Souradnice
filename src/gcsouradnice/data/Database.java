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
    
    public static String printLoaded(){
        StringBuilder sb = new StringBuilder();
        int order = 0;
        for (Cache loadedCache : loadedCaches) {
            sb.append(order + "  ").append(loadedCache.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public static String printArray(ArrayList<Cache> caches){
        StringBuilder sb = new StringBuilder();
        int order = 0;
        for (Cache cache : caches) {
            order++;
            sb.append(order + "  ").append(cache.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public static ArrayList<Cache> cachesinArea(Coordinates a, Coordinates b){
        ArrayList<Cache> caches = new ArrayList<>();
        for (Cache loadedCache : loadedCaches) {
            if (loadedCache.getCoords().isinArea(a, b)) {
                caches.add(loadedCache);
            }
        }
        return caches;
    }
    
    public static Cache cachefromList(int code, ArrayList<Cache> array){
        return array.get(code-1);
    }
    
    public static void main(String[] args) {
        Database database = new Database();
                try{
                    database.loadCaches(new File("./Data/Database.txt"));
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
                
                /*System.out.println(printloaded());
                //System.out.println((database.loadedCaches.get(2).getCode())).getLink();
                System.out.println((database.loadedCaches.get(2)).getLink());*/
                
                Coordinates a = Coordinates.getCoordinatesfromString("50°46.176N", "015°01.110E");
                Coordinates b = Coordinates.getCoordinatesfromString("50°42.945N", "015°11.365E");
                ArrayList<Cache> array = cachesinArea(a,b);
                System.out.println(printArray(array));
                Cache cs = cachefromList(5, array);
                System.out.println(cs.getLink());
                
                
    }
    
}

