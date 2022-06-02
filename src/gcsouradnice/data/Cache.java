/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

import utils.CacheInterface;
import utils.CoordinatesMethods;

/**
 *
 * @author tomch
 */
public class Cache implements Comparable<Cache>, CacheInterface {
    /*private static final double LONGITUDE_MIN = -180;
    private static final double LONGITUDE_MAX = 180;
    private static final double LATITUDE_MIN = -90;
    private static final double LATITUDE_MAX = 90;*/
    
    private String code;
    private Coordinates coords;
    private String name;
    private int fp;
    private Found found;
    
    public Cache(String code, String latitude, String longtitude, int fp, String name){
        this.code = code;
        this.name = name.replace("_", " ");
        this.coords = CoordinatesMethods.getCoordinatesfromString(latitude, longtitude);
        this.fp = fp;
        this.found = Found.NotFound; //na začátku vždy ne
    }
    
    public Cache newCache(String code, String latitude, String longtitude, int fp, String name){
        Cache cache = new Cache(code, latitude, longtitude, fp, name);
        return cache;
    }

    public String getCode() {
        return code;
    }
    
    public Coordinates getCoords() {
        return coords;
    }

    public String getName() {
        return name;
    }
    
    public int getFp() {
        return fp;
    }
    public Found getFound() {
        return found;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFound(Found state) {
        this.found = state;
    }
    
    /**
     * 
     * @return name with replace " " to "_"
     */
    public String nameToOneString(){
        return name.replace(" ", "_");     
    }
    
    /**
     * string form to be writted in file
     * @return string
     */    
    public String filetoString() {
        StringBuilder s = new StringBuilder();
        return code + " " + coords.toString() + " " + fp + " " + nameToOneString();    
    }
    
    /**
     * cache to string
     * @return string of cache
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        return String.format("%-9s %-25s %-4d %-50s", code, coords.toString(), fp, name);
    }
    /**
     * get link of cache
     * @return link of cache
     */
    public String getLink(){
        return "www.geocaching.com/geocache/"+ code;
    }
    
    public boolean checkGCcode(String code){
        if (code.substring(0,2).equals("GC")) {
            if (code.contains("[A-Z0-9]")) {
                return true;
            }
        }
        return false;
    
    }
    
    /**
     * comparing two Caches based on amount of favourite points
     * @param e other cache
     * @return if this Cache have more, less or same amount of FP than other
     */
    @Override
	public int compareTo(Cache e) {
            if(this.getFp() < e.getFp()){
                return -1;
            } else if(this.getFp() > e.getFp()){
                return 1;
            }else {
                return 0;
            }
	}

    
    
}
