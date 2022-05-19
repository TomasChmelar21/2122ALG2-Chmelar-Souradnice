/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice;

import gcsouradnice.data.Database;
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
        int choice;
        Menus.MainMenu();
        
        choice = sc.nextInt();
        while(choice != 0){
            switch(choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        
        }
        
    }
    
}
