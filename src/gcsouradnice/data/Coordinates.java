/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

import java.text.DecimalFormat;
import java.text.NumberFormat;
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
    /**
     * check correct coordinates
     * @param slat
     * @param hourslat
     * @param minuteslat
     * @param slong
     * @param hourslong
     * @param minuteslong
     * @return 
     */
    public static boolean check(char slat, int hourslat, double minuteslat, char slong, int hourslong, double minuteslong){
        if (((!Character.toString(slat).equals("N") || !Character.toString(slat).equals("S")) && (!Character.toString(slong).equals("W") || !Character.toString(slong).equals("E")))) {
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
    /**
     * transfer string coordinates to coordinates
     * @param coordslat latitude coordinate
     * @param coordslong longtitude coordinate
     * @return 
     */
    public static Coordinates getCoordinatesfromString(String coordslat, String coordslong){
        String[] partslat = coordslat.split("[ °]");
        String[] partslong = coordslong.split("[ °]");
        Coordinates coords = new Coordinates(partslat[1].charAt(partslat[1].length() - 1), Integer.parseInt(partslat[0]), Double.parseDouble(partslat[1].substring(0, partslat[1].length() - 1)), partslong[1].charAt(partslong[1].length() - 1), Integer.parseInt(partslong[0]), Double.parseDouble(partslong[1].substring(0, partslong[1].length() - 1))); 
        return coords;
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
         //System.out.println(check('N', 50,08.240,'E', 14, 42.504));
         /*Coordinates coords = getCoordinatesfromString(n1,n2);
         System.out.println(coords.directionlat);
         System.out.println(coords.directionlong);
         System.out.println(coords.hourslat);
         System.out.println(coords.minuteslat);
         System.out.println(coords.hourslong);
         System.out.println(coords.minuteslong);   
         System.out.println(coords.toString());*/
         Coordinates New = new Coordinates('N', 50, 05.215, 'E', 14, 42.504);
         System.out.println(New.toString());
    }  
    
}
