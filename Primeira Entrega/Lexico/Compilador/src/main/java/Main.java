
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import lexico.*;
import java.util.Scanner;
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
        String fileName = "prog1.txt";
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite uma opção: ");
        System.out.println("1 - Rodar teste 1: 1");
        System.out.println("2 - Rodar teste 2: 2");
        System.out.println("3 - Rodar teste 2 Corrigido: 3");
        System.out.println("4 - Rodar teste 3 : 4");
        System.out.println("5 - Rodar teste 3 Corrigido: 5");
        System.out.println("6 - Rodar teste 4: 6");
        System.out.println("7 - Rodar teste 4 Corrigido: 7");
        System.out.println("8 - Rodar teste 5: 8");
        System.out.println("9 - Rodar teste 5 Corrigido: 9");
        System.out.println("10 - Rodar teste 6");
        System.out.println("11 - Rodar teste 6 Corrigido");
        System.out.println("12 - Rodar teste 7");
        System.out.println("13 - Rodar teste 7 Corrigido");
        System.out.println("14 - Digitar nome do arquivo: 10");
        
        int opt;
                opt = ler.nextInt();
        switch(opt){
            case 1:
                fileName = "prog1.txt";
                break;
            case 2:
                fileName = "prog2.txt";
                break;
            case 3:
                fileName = "prog2Fixed.txt";
                break;
            case 4:
                fileName = "prog3.txt";
                break;
            case 5:
                fileName = "prog3Fixed.txt";
                break;
            case 6:
                fileName = "prog4.txt";
                break;
            case 7:
                fileName = "prog4Fixed.txt";
                break;
            case 8:
                fileName = "prog5.txt";
                break;
            case 9:
                fileName = "prog5Fixed.txt";
                break;
            case 10:
                fileName = "prog6.txt";
                break;
            case 11:
                fileName = "prog6Fixed.txt";
                break;
            case 12:
                fileName = "prog7.txt";
                break;
            case 13:
                fileName = "prog7Fixed.txt";
                break;
            case 14:
                fileName = ler.nextLine();
                fileName = ler.nextLine();
                break;
        }
        
        
        //fileName = ler.nextLine();
        lex = new Lexer(fileName);
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