/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice;

import com.sun.jndi.toolkit.url.Uri;
import eu.jacquet80.minigeo.MapWindow;
import eu.jacquet80.minigeo.POI;
import eu.jacquet80.minigeo.Point;
import eu.jacquet80.minigeo.Segment;
import gcsouradnice.data.Cache;
import gcsouradnice.data.Coordinates;
import utils.DataTime;
import gcsouradnice.data.Database;
import gcsouradnice.data.Found;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import utils.CoordinatesMethods;

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
        int choice, choicecache = 0, choiceothercache;
        String source, latcoordslu, latcoordsrd, longcoordslu, longcoordsrd;
        double latfirstFormat,longfirstFormat,latsecondFormat,longsecondFormat; 
        Database database = new Database();
        Database inRectangle = new Database();
        Database actualDatabase = database;
        String parent = System.getProperty("user.dir")+ File.separator +"Data" + File.separator;
        
        System.out.println(DataTime.today());
        mainMenu();
        try{
        while((choice = sc.nextInt()) != 0){
            switch(choice){
                case 1:
                    try{
                     /*   
                        GC9K5R8
                        Found
                        50.47.311N
                        015.05.035E
                        40
                        Bajkerská
                        
                        */
                    System.out.println("Where you wanna write new cache (in ./Data/....)");
                    source = sc.next();
                    System.out.println("Enter gradually: GCcode, Found status, Latitude NN.NN.NNNC, Longtitude NNN.NN.NNNC. amount of favourite points and Name");
                    database.addCacheToFile(new File("./Data/"+source), new Cache(sc.next(),Found.valueOf(sc.next()),sc.next().replaceFirst("[.]","°"),sc.next().replaceFirst("[.]","°"),sc.nextInt(),sc.next()));
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
                    Liberec             50.46.176N     015.01.110E
                    Jablonec            50.42.945N     015.11.365E
                    Česká Lípa          50.41.193N     014.32.220E
                    Semily              50.36.114N     015.20.131E
                    Nový Bor            50.45.456N     014.33.333E
                    Hrádek nad Nisou    50.51.167N     014.50.672E
                    Frýdlant            50.55.283N     015.04.784E
                    Český Dub           50.39.574N     014.59.740E
                    */
                    System.out.println("Enter the latitude degrees of the upper left corner of the rectangle in format NN.NN.NNND N = number D = char (N or S)");
                    latcoordslu = sc.next();
                    System.out.println("Enter the longtitude degrees of the upper left corner of the rectangle in format NNN.NN.NNND = number D = char (E or W)");
                    longcoordslu = sc.next();
                    System.out.println("Enter the latitude degrees of the lower right corner of the rectangle in format NN.NN.NNND = number D = char (N or S)");
                    latcoordsrd = sc.next();
                    System.out.println("Enter the longtitude minutes of the lower right corner of the rectangle in format NNN.NN.NNND N = number D = char (E or W)");
                    longcoordsrd = sc.next();
                    inRectangle.setLoadedCaches(database.cachesinArea(CoordinatesMethods.getCoordinatesfromString(latcoordslu.replaceFirst("[.]","°"), longcoordslu.replaceFirst("[.]","°")), CoordinatesMethods.getCoordinatesfromString(latcoordsrd.replaceFirst("[.]","°"), longcoordsrd.replaceFirst("[.]","°"))));
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
                            sortMenu();
                            choice = sc.nextInt();
                            switch(choice){
                                case 1:
                                    database.sortByGCcode();
                                    System.out.println(database.printLoaded());
                                    break;
                                case 2:
                                    database.sortByFP();
                                    System.out.println(database.printLoaded());
                                    break;
                                case 3:
                                    database.filterFound();
                                    System.out.println(database.printLoaded());
                                    break;
                                case 4:
                                    database.filternotFound();
                                    System.out.println(database.printLoaded());
                                    break;
                                case 5:
                                    database.filterWatchlist();
                                    System.out.println(database.printLoaded());
                                    break;
                                default:
                                    break;
                            }
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
                case 4:
                    try{
                    System.out.println("");
                    System.out.println("Write name of your document (in ./Data/....)");
                    source = sc.next();
                    System.out.println(database.readFromBinaryResults(new File("./Data/"+source)));
                    System.out.println("If you want to work with cache from document, enter its number(left column), otherwise enter 0");
                    choicecache = sc.nextInt();
                    database.loadCaches(new File("./Data/Database.txt"));
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("something went wrong");
                    }
                    break;
                case 5:
                    System.out.println("1-add new cache to database - will add new cache to database");
                    System.out.println("2-find cache in rectangle - after saying left top corner coords and right down coords of rectangle, it will return caches in this rectangle");
                    System.out.println("3-show all from file - will write all caches from file");
                    System.out.println("4-show all from binary file - will write all codes from binary file");
                    System.out.println("\n");
                    break;
                default:
                    break;
            }
            
            if (choicecache != 0) {
                cacheMenu();
                choice = sc.nextInt();
                switch(choice){
                case 1:
                    try{
                    //System.out.println(("http://"+ actualDatabase.getLoadedCaches().get(choicecache-1).getLink()));
                    String url_open ="https://"+ actualDatabase.getLoadedCaches().get(choicecache-1).getLink();
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open));
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("something went wrong");
                    }
                    break;
                case 2:
                    try{
                    String url_open_coords ="https://www.google.com/maps/@"+ actualDatabase.getLoadedCaches().get(choicecache-1).getCoordsinGoogleFormat()+",14z" ;
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url_open_coords));
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("something went wrong");
                    }
                    break;
                case 3:
                    try{
                        database.getLoadedCaches().get(choicecache-1).setFound(Found.Watchlist);
                        System.out.println("set up successfully");
                    }
                    catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("something went wrong..."); 
                    }
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
                case 5:
                    try{
                    System.out.println("choose another from database");
                    choiceothercache = sc.nextInt();
                    latfirstFormat =  Double.parseDouble(database.getLoadedCaches().get(choicecache-1).getLatFormat());
                    longfirstFormat =  Double.parseDouble(database.getLoadedCaches().get(choicecache-1).getLongFormat());
                    latsecondFormat =  Double.parseDouble(database.getLoadedCaches().get(choiceothercache-1).getLatFormat());
                    longsecondFormat =  Double.parseDouble(database.getLoadedCaches().get(choiceothercache-1).getLongFormat());
                    MapWindow window = new MapWindow();
                    window.addSegment( new Segment( new Point(latfirstFormat, longfirstFormat), new Point(latsecondFormat, longsecondFormat), Color.RED));
                    window.setVisible(true);
                        System.out.println("Distance between is " + CoordinatesMethods.distance(latfirstFormat, longfirstFormat, latsecondFormat, longsecondFormat)+ "km");
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                        System.out.println("something went wrong..."); 
                    }
                    break;
                default:
                    break;
                }  
            } 
            choicecache = 0;
            mainMenu();
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("wrong input");
        }
    }
    
    public static void mainMenu(){
        System.out.println("1-add new cache to database");
        System.out.println("2-find caches in rectangle");
        System.out.println("3-show all from file");
        System.out.println("4-show codes from Binary file");
        System.out.println("5-Help menu");
        System.out.println("0-end app");
        
    }
    
    public static void cacheMenu(){
        System.out.println("1-show on internet");
        System.out.println("2-show coordinates on map");
        System.out.println("3-add to watchlist");
        System.out.println("4-set as Found");
        System.out.println("5-distance to other from database");
        System.out.println("0-back to main menu");
    }
    
    public static void sortMenu(){
        System.out.println("1-sort by GCcode");
        System.out.println("2-sort by favorite points");
        System.out.println("3-filter Found");
        System.out.println("4-filter NotFound");
        System.out.println("5-filter Watchlist");
        System.out.println("0-exit from sort menu");
    }
}
