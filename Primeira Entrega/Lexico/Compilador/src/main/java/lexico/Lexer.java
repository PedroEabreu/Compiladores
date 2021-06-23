/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        
package lexico;
import java.io.*;
import java.util.*;
import java.io.IOExceptiom;
import java.io.FileReader;
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
   
        
    }
    //ler arquivos
    private void readch() throws IOExceptiom{
        ch=(char)file.read;
    }
//leitura do proximo caractere
    private boolean readech(char c) throws IOException{
        readch();
        if(ch!=c) return false;
        ch='';  //se verdade retona true e limpa ch
        return true;
    }
    public Token scan() throws IOExceptions{
        //desconsidea delimetadors na estrada
        for (;;readch()){
            if (ch=='' || ch=='\t' || ch=='\r' || ch=='\b') continue;
            else if (ch=='\n') line ++; //conta linha
            else break;
        }
        Sitch (ch){ //operador
            case "&":
                if (readch('&')) return Word.and;   // &&
                else return new Token('&');         // &
            case '|':
                if (readch('|')) return Word.or;    // ||
                else return new Token('|');         // |
            case'=':
                if(readch('='))return Word.eq;      // ==
                else return new Token('=');         // =
            case'<':
                if (readch('=')) return Word.le;    // <=
                else return new Token('<');         // <
            case '>':
                if (readch('=')) return Word.ge;    // >=
                else return new Token('>');         // >
            case '!':
                if (readch('='))return Word.ne;     // !=
                else return new Token('!');         // !
        }
        //Numeros  (constante Numericas)
        if (Character.isDigit(ch)){
            int value =0;
            do {
                value = 10*value + Character.digit(ch, 10);
                readch();
            } while (Character.isDigit(ch));
            return new Num(value);
        }
        //identificadores
        if(Character.isLetter(ch)){
            stringBuffer sb = new stringBuffer();
            do {
                sb.append(ch);
    

}
