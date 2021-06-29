
import java.io.FileNotFoundException;
import java.io.IOException;
import lexico.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pedroelias
 */
public class Main{
    //classe principal  
     
     public static void main (String[] a) throws FileNotFoundException, IOException  {   
         //instanciação do objeto  
        Lexer lex;
        lex = new Lexer("arq.txt");
        System.out.println("teste funcionamento"); 
        
        while(true){
            Token text = lex.scan();
            if(text.tag == 261){
                break;
            }
            System.out.print('<');
            System.out.print(text.tag);
            System.out.print(',');
            System.out.println(text.imprimeToken(text)+'>');
        }
        System.out.println(lex.words);
        
     }  
}