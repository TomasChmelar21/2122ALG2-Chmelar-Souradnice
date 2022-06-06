/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

import utils.ComparatorCachesByCode;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author tomch
 */
public class Database {
    private ArrayList<Cache> loadedCaches;
    
    public Database(){
           loadedCaches = new ArrayList<>();
    }
    /**
     * loading caches from file
     * @param database file from which we reading
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void loadCaches(File database) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(database))){
        loadedCaches.clear();
        String line;
        String[] parts;
        Cache r = null;
        br.readLine();
        while((line = br.readLine()) != null) {           
            parts = line.split("[ ]");
            //String code, String latitude, String longtitude, String name
            r = new Cache(parts[0], Found.valueOf(parts[1]), parts[2], parts[3], Integer.parseInt(parts[4]), parts[5]);
            loadedCaches.add(r);
        }
            
        }
    }
    /**
     * getting array
     * @return array of caches
     */
    public ArrayList<Cache> getLoadedCaches() {
        return loadedCaches;
    }
    /**
     * setting array
     * @param loadedCaches array of caches
     */
    public void setLoadedCaches(ArrayList<Cache> loadedCaches) {
        this.loadedCaches = loadedCaches;
    }
    
    /**
     * return String of array
     * @return array in String
     */
    public String printLoaded(){
        StringBuilder sb = new StringBuilder();
        int order = 0;
        for (Cache loadedCache : loadedCaches) {
            order++;
            sb.append(String.format("%-4d",order)).append(loadedCache.toString()).append("\n");
        }
        return sb.toString();
    }
    
    /*public static String printArray(ArrayList<Cache> caches){
        StringBuilder sb = new StringBuilder();
        int order = 0;
        for (Cache cache : caches) {
            order++;
            sb.append(order + "  ").append(cache.toString()).append("\n");
        }
        return sb.toString();
    }*/
    /**
     * writing data to file
     * @param results File where we wanna to write
     * @throws IOException 
     */
    public void exportToFile(File results) throws IOException{
        try ( PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results, true)))) {
            pw.println("GCKod" + " " + "State" + " " + "Latitude" + " " + "Longtitude" + " " + "FP" + " " + "Name");
            for (Cache loadedCache : loadedCaches) {
                pw.println(loadedCache.filetoString());
            }
        }            
    }
    /**
     * add one cache to file
     * @param results File where we wanna to write
     * @param cache cache which we wanna to add
     * @throws IOException 
     */
    public void addCacheToFile(File results, Cache cache) throws IOException{
        try ( PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(results, true)))) {
                pw.println(cache.filetoString());
        }  catch(Exception e){
        
        }
    }
    /**
     * writing data to binary file
     * @param results File where we wanna to write
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveToBinaryFile(File results) throws FileNotFoundException, IOException {
        try ( DataOutputStream out = new DataOutputStream(new FileOutputStream(results, true))) {
            out.writeInt(loadedCaches.size());
            for (Cache cache : loadedCaches) {
                //out.writeInt(cache.getRegistracniCislo());
                out.writeUTF(cache.getCode());
            }
        }
    }
    /**
     * returning read informations from File
     * @param results File from where we reading
     * @return String of data from file
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public String readFromBinaryResults(File results) throws FileNotFoundException, IOException {
        StringBuilder sb = new StringBuilder();
        int nCaches;
        String code = "";
        int rank = 1;
        try ( DataInputStream in = new DataInputStream(new FileInputStream(results))) {
            boolean end = false;
            while (!end) {
                try {
                    rank = 1;
                    nCaches = in.readInt();
                    for (int i = 0; i < nCaches; i++) {
                        code = in.readUTF();
                        sb.append(String.format("%3s %10s",rank, code));
                        sb.append("\n");
                        rank++;
                    }
                } catch (EOFException e) {
                    end = true;
                }
            }
        }
        return sb.toString();
    }
    /**
     * sorting array by GCcode
     */
    public void sortByGCcode() {
        Comparator comp = new ComparatorCachesByCode();
        Collections.sort(loadedCaches, comp);
    }
    /**
     * sorting array by amount of favourite points
     */
    public void sortByFP() {       
        Collections.sort(loadedCaches, Collections.reverseOrder());
    }
    
        /**
         * searching caches in area (rectangle)
         * @param a coordinates a
         * @param b coordinates b
         * @return array of Caches in rectangle
         */
    public ArrayList<Cache> cachesinArea(Coordinates a, Coordinates b){
        ArrayList<Cache> caches = new ArrayList<>();
        for (Cache loadedCache : loadedCaches) {
            if (loadedCache.getCoords().isinArea(a, b)) {
                caches.add(loadedCache);
            }
        }
        return caches;
    }
    /**
     * Filter caches with Found status
     */ 
    public void filterFound(){
        ArrayList<Cache> caches = new ArrayList<>();
        for (Cache loadedCache : loadedCaches) {
            if (loadedCache.getFound().toString().equals("Found")) {
                caches.add(loadedCache);
            }
        } 
       loadedCaches = caches;
    
    }
    /**
     * Filter caches with notFound status
     */
    public void filternotFound(){
        ArrayList<Cache> caches = new ArrayList<>();
        for (Cache loadedCache : loadedCaches) {
            if (loadedCache.getFound().toString().equals("NotFound")) {
                caches.add(loadedCache);
            }
        } 
       loadedCaches = caches;
    
    }
    /**
     * Filter caches with Watchlist status
     */
    public void filterWatchlist(){
        ArrayList<Cache> caches = new ArrayList<>();
        for (Cache loadedCache : loadedCaches) {
            if (loadedCache.getFound().toString().equals("Watchlist")) {
                caches.add(loadedCache);
            }
        } 
       loadedCaches = caches;
    
    }     
    /*public Cache cachefromList(int code){
        return loadedCaches.get(code-1);
    }*/
    

    
}

