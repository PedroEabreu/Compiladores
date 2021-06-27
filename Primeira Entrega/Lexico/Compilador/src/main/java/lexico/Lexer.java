/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        
package lexico;
import java.io.*;
import java.util.*;
/**
 *
 * @author pedroelias
 */
public class Lexer {

    public static int line = 1;
    private char ch = ' ';
    private FileReader file;
    
    private Hashtable words = new Hashtable();
    
    private void reserve(Word w){
        words.put(w.getLexeme(), w);
    }
    
    public Lexer(String fileName) throws FileNotFoundException{
    
        try{
            file = new FileReader(fileName);
        }
        catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado");
            throw e;
        }
        
        reserve(new Word("if",Tag.IF));
        reserve(new Word("class",Tag.CLASS));
        reserve(new Word("int",Tag.INT));
        reserve(new Word("string",Tag.STRING));
        reserve(new Word("float",Tag.FLOAT));
        reserve(new Word("init",Tag.INIT));
        reserve(new Word("stop",Tag.STOP));
        reserve(new Word("else",Tag.ELSE));
        reserve(new Word("do",Tag.DO));
        reserve(new Word("while",Tag.WHILE));
        reserve(new Word("read",Tag.READ));
        reserve(new Word("write",Tag.WRITE));
        reserve(new Word("if",Tag.IF));
        
        
    }
    
    private void readch() throws IOException{
        ch = (char) file.read();
    }
    
    private boolean readch(char c) throws IOException{
        readch();
        
        if(ch != c){
            return false;
        }
        ch = ' ';
        return true;
    }
    

}
