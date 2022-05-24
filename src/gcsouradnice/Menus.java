/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice;

/**
 *
 * @author tomch
 */
public class Menus {
    public static void mainMenu(){
        System.out.println("1-add new cache to database");
        System.out.println("2-find caches in rectangle");
        System.out.println("3-show all from file");
        System.out.println("0-end app");
        
    }
    
    public static void cacheMenu(){
        System.out.println("1-generate URL address");
        System.out.println("2-show coordinates on map");
        System.out.println("3-add to watchlist");
        System.out.println("4-set as Found");
        System.out.println("0-back to main menu");
    }
    
    public static void chooseCacheMenu(){
        System.out.println("1-generate URL address");
        System.out.println("2-show coordinates on map");
        System.out.println("3-add to watchlist");
        System.out.println("0-back to main menu");
    }
}
