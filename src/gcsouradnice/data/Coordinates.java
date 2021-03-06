/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import utils.CoordinatesMethods;

/**
 *
 * @author tomch
 */
public class Coordinates {
    private char directionlat;
    private int hourslat;
    private double minuteslat;
    private char directionlong;
    private int hourslong;
    private double minuteslong;
    Locale usLocale = new Locale("en","US");
    NumberFormat usForm = NumberFormat.getInstance(usLocale);
    private Coordinates(char slat, int hourslat, double minuteslat, char slong, int hourslong, double minuteslong){
        this.directionlat = slat;
        this.hourslat = hourslat;
        this.minuteslat = minuteslat;
        this.directionlong = slong;
        this.hourslong = hourslong;
        this.minuteslong = minuteslong;
    
    }
    
    
    
    public static Coordinates newCoordinates(char slat, int hourslat, double minuteslat, char slong, int hourslong, double minuteslong){
        if (CoordinatesMethods.check(slat, hourslat, minuteslat, slong, hourslong, minuteslong)) {
            return new Coordinates(slat, hourslat, minuteslat, slong, hourslong, minuteslong);
        }
        throw new IllegalArgumentException("wrong input exception");
    }

    public char getDirectionlat() {
        return directionlat;
    }

    public int getHourslat() {
        return hourslat;
    }

    public double getMinuteslat() {
        return minuteslat;
    }

    public char getDirectionlong() {
        return directionlong;
    }

    public int getHourslong() {
        return hourslong;
    }

    public double getMinuteslong() {
        return minuteslong;
    }
    
    /**
     * checking if coordinates are in area
     * @param a left upper coordinates
     * @param b right down coordinates
     * @return if coordinates are in rectangle
     */
    public boolean isinArea(Coordinates a, Coordinates b){
        if (hourslat < a.hourslat && hourslat > b.hourslat && hourslong > a.hourslong && hourslong < b.hourslong) {
            return true;
        }
        
        if (hourslat <= a.hourslat && hourslat >= b.hourslat && hourslong >= a.hourslong && hourslong <= b.hourslong) {
            if (minuteslat <= a.minuteslat && minuteslat >= b.minuteslat && minuteslong >= a.minuteslong && minuteslong <= b.minuteslong) {
                return true;
            }
        }
        
        return false;
    }
    /**
     * return coordinates in String
     * @return  String of coordinates
     */
    @Override
    public String toString(){
        return hourslat + "??" + usForm.format(minuteslat) + directionlat + " " + hourslong + "??" + usForm.format(minuteslong) + directionlong;
        //return String.format(Locale.US,"%2d??%2.3f%s %3d??%2.3f%s", hourslat, minuteslat, directionlat, hourslong, minuteslong, directionlong);
    }
    
    
    
}
