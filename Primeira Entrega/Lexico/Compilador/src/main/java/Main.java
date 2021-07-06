
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
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
        char aspas = '"';
        int i = 0;
        while(true){
            Token text = lex.scan();
            System.out.print("Token " + ++i + ": ");
            

            System.out.print("<" + text.imprimeToken(text));
            if("id".equals(text.imprimeToken(text))){
                Word aux = (Word) text;
                System.out.println("," + aspas + aux.getLexeme()+aspas + ">");
            }
            else if("num".equals(text.imprimeToken(text))){
                Num aux2 = (Num) text;
                System.out.println("," + aspas + aux2.toString() + aspas + ">");
            }
            else if("str".equals(text.imprimeToken(text))){
                StringSentence aux3 = (StringSentence) text;
                System.out.println("," + aspas + aux3.toString() + aspas + ">");
            }
            else{
                System.out.println(">");
            }
            
            if(text.tag == 261){
                break;
            }
        }
        System.out.println("\n\n" + "***************************" + "\n\n" + "-->Tabela de Símbolos\n");
        int j=0;
        Set<String> keys = lex.words.keySet();
      
        for(String key: keys){
            System.out.println("Lexema "+ ++j + " = " + '"' +key+'"');
        }        
     }  
}