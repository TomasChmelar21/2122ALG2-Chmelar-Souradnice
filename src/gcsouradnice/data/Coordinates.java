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
        throw new IllegalArgumentException("bad input exception");
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
        return hourslat + "°" + usForm.format(minuteslat) + directionlat + " " + hourslong + "°" + usForm.format(minuteslong) + directionlong;
        //return String.format(Locale.US,"%2d°%2.3f%s %3d°%2.3f%s", hourslat, minuteslat, directionlat, hourslong, minuteslong, directionlong);
    }
    
    
    public static void main(String[] args) {
         String n1 = "50°08.240N";
         String n2 = "014°42.504E";
         System.out.println(CoordinatesMethods.check('N', 50,08.240,'E', 14, 42.504));
         /*Coordinates coords = getCoordinatesfromString(n1,n2);
         System.out.println(coords.directionlat);
         System.out.println(coords.directionlong);
         System.out.println(coords.hourslat);
         System.out.println(coords.minuteslat);
         System.out.println(coords.hourslong);
         System.out.println(coords.minuteslong);   
         System.out.println(coords.toString());*/
         Coordinates New = new Coordinates('N', 50, 05.215, 'E', 14, 42.504);
         //System.out.println(New.toString());
    }  
    
}
