/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

import java.text.DecimalFormat;
import java.util.Locale;

/**
 *
 * @author tomch
 */
public class Coordinates {
    private static final double LONGITUDEHOURS_MAX = 180;
    private static final double LATITUDEHOURS_MAX = 90;
    private static final double LMINUTES_MAX = 60;
    private static final double LDECIMALMINUTES_MAX = 1000;
    private static final double LMIN = 0;
    private char directionlat;
    private int hourslat;
    private double minuteslat;
    private char directionlong;
    private int hourslong;
    private double minuteslong;
    
    private Coordinates(char slat, int hourslat, double minuteslat, char slong, int hourslong, double minuteslong){
        this.directionlat = slat;
        this.hourslat = hourslat;
        this.minuteslat = minuteslat;
        this.directionlong = slong;
        this.hourslong = hourslong;
        this.minuteslong = minuteslong;
    
    }
    
    public static boolean check(char slat, int hourslat, double minuteslat, char slong, int hourslong, double minuteslong){
        if ((slat != 'N' || slat != 'S') || (slong != 'W' || slong != 'E')) {
            return false;
        }
        
        if (((hourslat < LMIN) || (hourslat >= LATITUDEHOURS_MAX)) || ((hourslong < LMIN) || (hourslong >= LONGITUDEHOURS_MAX))){
            return false;
        }
        
        if (((minuteslat < LMIN) || (minuteslat >= LMINUTES_MAX)) || ((minuteslong < LMIN) || (minuteslong >= LMINUTES_MAX))){
            return false;
        }
        
        if ((minuteslat%1 >= LDECIMALMINUTES_MAX) || (minuteslong%1 >= LMINUTES_MAX)){
            return false;
        }
        return true;
    }
    
    public static Coordinates getCoordinatesfromString(String coordslat, String coordslong){
        String[] partslat = coordslat.split("[ °]");
        String[] partslong = coordslong.split("[ °]");
        Coordinates coords = new Coordinates(partslat[1].charAt(partslat[1].length() - 1), Integer.parseInt(partslat[0]), Double.parseDouble(partslat[1].substring(0, partslat[1].length() - 1)), partslong[1].charAt(partslong[1].length() - 1), Integer.parseInt(partslong[0]), Double.parseDouble(partslong[1].substring(0, partslong[1].length() - 1))); 
        return coords;
    }
    
    
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
    @Override
    public String toString(){
        DecimalFormat formatter = new DecimalFormat("#00.000");
        return hourslat + "°" + formatter.format(minuteslat) + directionlat + " " + hourslong + "°" + formatter.format(minuteslong) + directionlong;
        //return String.format(Locale.US,"%2d°%2.3f%s %3d°%2.3f%s", hourslat, minuteslat, directionlat, hourslong, minuteslong, directionlong);
    }
    
    
    public static void main(String[] args) {
         String n1 = "50°08.240N";
         String n2 = "014°42.504E";
         //System.out.println(check('N', 50,08.240,'E', 14, 42.504));
         /*Coordinates coords = getCoordinatesfromString(n1,n2);
         System.out.println(coords.directionlat);
         System.out.println(coords.directionlong);
         System.out.println(coords.hourslat);
         System.out.println(coords.minuteslat);
         System.out.println(coords.hourslong);
         System.out.println(coords.minuteslong);   
         System.out.println(coords.toString());*/
    }  
    
}
