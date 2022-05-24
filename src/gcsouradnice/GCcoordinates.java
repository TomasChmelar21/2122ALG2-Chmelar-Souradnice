/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice;

import gcsouradnice.data.Cache;
import gcsouradnice.data.Coordinates;
import static gcsouradnice.data.Coordinates.getCoordinatesfromString;
import gcsouradnice.data.DataTime;
import gcsouradnice.data.Database;
import gcsouradnice.data.Found;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tomch
 */
public class GCcoordinates {

    /**
     * @param args the command line arguments
     */
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int choice, choicecache = 0;
        String source, lathcoordslu, lathcoordsrd, longhcoordslu, longhcoordsrd, latmcoordslu, latmcoordsrd, longmcoordslu, longmcoordsrd;
        Database database = new Database();
        Database inRectangle = new Database();
        Database actualDatabase = database;
        String parent = System.getProperty("user.dir")+ File.separator +"Data" + File.separator;
        
        
        
        System.out.println(DataTime.today());
        Menus.mainMenu();
        
        while((choice = sc.nextInt()) != 0){
            switch(choice){
                case 1:
                    try{
                     /*   
                        GC9K5R8
                        50°47.311N
                        015°05.035E
                        Bajkerská
                        
                        */
                    System.out.println("Where you wanna write new cache (in ./Data/....)");
                    source = sc.next();
                    System.out.println("Enter gradually: GCcode, Latitude NN°NN.NNNC, Longtitude NNN°NN.NNNC and Name");
                    database.addCacheToFile(new File("./Data/"+source), new Cache(sc.next(),sc.next(),sc.next(),sc.next()));
                    //database.addCacheToFile(new File("./Data/Database_other1.txt"), new Cache("GC9GYD3","50°45.502N","015°04.171E","Znám Liberec? IV."));
                    System.out.println("set up successfully");
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("something went wrong... check if the name is correct and try again");
                    }
                    break;
                case 2:
                    try{
                    while(database.getLoadedCaches().isEmpty()){
                        try{
                            /*
                            Database.txt
                            Database_copy.txt
                            Database_other1.txt
                            */
                            System.out.println("Write name of your document (in ./Data/....)");
                            source = sc.next();
                            database.loadCaches(new File("./Data/"+source));
                            break;
                        } catch(Exception e){
                            System.out.println(e.getMessage());
                            System.out.println("something went wrong... check if the name is correct and try again");
                        }
                    }
                    /*
                                        h  m           h   m
                    Liberec             50°46.176N     015°01.110E
                    Jablonec            50°42.945N     015°11.365E
                    Česká Lípa          50°41.193N     014°32.220E
                    Semily              50°36.114N     015°20.131E
                    Nový Bor            50°45.456N     014°33.333E
                    Hrádek nad Nisou    50°51.167N     014°50.672E
                    Frýdlant            50°55.283N     015°04.784E
                    Český Dub           50°39.574N     014°59.740E
                    */
                    System.out.println("Enter the latitude degrees of the upper left corner of the rectangle in format NN N = number");
                    lathcoordslu = sc.next();
                    System.out.println("Enter the latitude minutes of the upper left corner of the rectangle in format NN.NNN N = number");
                    latmcoordslu = sc.next();
                    System.out.println("Enter the longtitude degrees of the upper left corner of the rectangle in format NNN N = number");
                    longhcoordslu = sc.next();
                    System.out.println("Enter the longtitude minutes of the upper left corner of the rectangle in format NN.NNN N = number");
                    longmcoordslu = sc.next();
                    System.out.println("Enter the latitude degrees of the lower right corner of the rectangle in format NN = number");
                    lathcoordsrd = sc.next();
                    System.out.println("Enter the latitude minutes of the lower right corner of the rectangle in format NN.NNN = number");
                    latmcoordsrd = sc.next();
                    System.out.println("Enter the longtitude degrees of the lower right corner of the rectangle in format NNN N = number");
                    longhcoordsrd = sc.next();
                    System.out.println("Enter the longtitude minutes of the lower right corner of the rectangle in format NN.NNN N = number");
                    longmcoordsrd = sc.next();
                    inRectangle.setLoadedCaches(database.cachesinArea(Coordinates.getCoordinatesfromString(lathcoordslu+"°"+latmcoordslu+"N", longhcoordslu+"°"+longmcoordslu+"E"), Coordinates.getCoordinatesfromString(lathcoordsrd+"°"+latmcoordsrd+"N", longhcoordsrd+"°"+longmcoordsrd+"E")));
                    //inRectangle.setLoadedCaches(database.cachesinArea(Coordinates.getCoordinatesfromString("50°46.176N", "015°01.110E"), Coordinates.getCoordinatesfromString("50°42.945N", "015°11.365E")));
                    System.out.println(inRectangle.printLoaded());
                    actualDatabase = inRectangle;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("You entered the wrong coordinates format");
                    }
                    break;
                case 3:
                    while(true){
                        try{
                            /*
                            Database.txt
                            Database_copy.txt
                            Database_other1.txt
                            */
                            System.out.println("Write name of your document (in ./Data/....)");
                            source = sc.next();
                            database.loadCaches(new File("./Data/"+source));
                            System.out.println(database.printLoaded());
                            System.out.println("If you want to work with cache from document, enter its number(left column), otherwise enter 0");
                            choicecache = sc.nextInt();
                            actualDatabase = database;
                            break;
                        } catch(Exception e){
                            System.out.println(e.getMessage());
                            System.out.println("something went wrong... check if the name is correct");
                        }
                        System.out.println(database.printLoaded());
                    }
                    break;
                default:
                    break;
            }
            
            if (choicecache != 0) {
                Menus.cacheMenu();
                choice = sc.nextInt();
                switch(choice){
                case 1:
                    System.out.println((actualDatabase.getLoadedCaches().get(choicecache-1).getLink()));
                    break;
                case 2:
                    System.out.println("still in preparing");
                    break;
                case 3:
                    System.out.println("still in preparing");
                    break;
                case 4:
                    try{
                        database.getLoadedCaches().get(choicecache-1).setFound(Found.Found);
                        System.out.println("set up successfully");
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("something went wrong..."); 
                    }
                    break;
                default:
                    break;
                }  
            } 
            choicecache = 0;
            Menus.mainMenu();
        }
    }
}
