/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gcsouradnice.data.Coordinates;
import static gcsouradnice.data.Coordinates.newCoordinates;

/**
 *
 * @author tomch
 */
public class CoordinatesMethods {
    private static final double LONGITUDEHOURS_MAX = 180;
    private static final double LATITUDEHOURS_MAX = 90;
    private static final double LMINUTES_MAX = 60;
    private static final double LDECIMALMINUTES_MAX = 1000;
    private static final double LMIN = 0;
    
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
        if (((!(Character.toString(slat).equals("N") || Character.toString(slat).equals("S"))) || (!(Character.toString(slong).equals("W") || Character.toString(slong).equals("E"))))) {
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
        Coordinates coords = newCoordinates(partslat[1].charAt(partslat[1].length() - 1), Integer.parseInt(partslat[0]), Double.parseDouble(partslat[1].substring(0, partslat[1].length() - 1)), partslong[1].charAt(partslong[1].length() - 1), Integer.parseInt(partslong[0]), Double.parseDouble(partslong[1].substring(0, partslong[1].length() - 1))); 
        return coords;
    }
}
