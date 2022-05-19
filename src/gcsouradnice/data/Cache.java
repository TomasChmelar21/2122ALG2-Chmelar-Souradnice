/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

/**
 *
 * @author tomch
 */
public class Cache {
    /*private static final double LONGITUDE_MIN = -180;
    private static final double LONGITUDE_MAX = 180;
    private static final double LATITUDE_MIN = -90;
    private static final double LATITUDE_MAX = 90;*/
    
    private String code;
    private Coordinates coords;
    private String name;
    private Found found;
    
    public Cache(String code, String latitude, String longtitude, String name){
        this.code = code;
        this.name = name.replace("_", " ");
        this.coords = Coordinates.getCoordinatesfromString(latitude, longtitude);
        this.found = Found.NotFound; //na začátku vždy ne
    }
    
    public static Cache newCache(String code, String latitude, String longtitude, String name){
        Cache cache = new Cache(code, latitude, longtitude, name);
        return cache;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
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

    public void setFound(Found Found) {
        this.found = Found;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        return String.format("%-9s %-25s %-50s", code, coords.toString(), name);
    }
    
    public String getLink(){
        return "www.geocaching.com/geocache/"+ code;
    }

    
    
}
