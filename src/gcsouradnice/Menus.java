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
    public static void MainMenu(){
        System.out.println("1-add new cache");
        System.out.println("2-find caches in rectangle");
        System.out.println("3-show watchlist");
        System.out.println("0-end app");
        
    }
    
    public static void CacheMenu(){
        System.out.println("1-generate URL address");
        System.out.println("2-show coordinates on map");
        System.out.println("3-add to watchlist");
        System.out.println("0-back to main menu");
    }
    
}
