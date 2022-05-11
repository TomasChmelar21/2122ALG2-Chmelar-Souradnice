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
    private static final double LONGITUDE_MIN = -180;
    private static final double LONGITUDE_MAX = 180;
    private static final double LATITUDE_MIN = -90;
    private static final double LATITUDE_MAX = 90;
    
    private String code;
    private String latitude;
    private String longtitude;
    private String name;
    private Found found;
    
    private Cache(String code, String latitude, String longtitude, String name){
        this.code = code;
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.found = Found.NotFound; //na začátku vždy ne
    }

    public String getCode() {
        return code;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
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

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
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
        s.append(code).append("  ").append(latitude).append("  ").append(longtitude).append("  ").append(name).append("  ").append(found).append("\n");
        return s.toString();
    }
    
    
}
