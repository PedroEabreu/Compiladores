/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

        
package lexico;
import java.io.*;
import java.util.*;
//import java.io.IOExceptiom;
import java.io.FileReader;
import static java.lang.System.exit;
import java.util.Hashtable;

/**
 *
 * @author pedroelias
 */
public class Lexer {

    public int line = 1;
    private char ch = ' ';
    private FileReader file;
    public Hashtable words = new Hashtable();
    
    private void reserve(Word w){
        words.put(w.getLexeme(), w);
    }

    //Construtor
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

    public Token scan() throws IOException{
        //desconsidea delimetadors na estrada
        for (;;readch()){
         //   System.out.print(ch);
            if(ch==' '||ch=='\t'||ch=='\r'||ch=='\b'){
                continue;
            }
            else if (ch=='\n') line ++; //conta linha
            else break;
        }
        //System.out.println();
        switch (ch){ //operador
            case '&':
                if (readch('&')) return Word.and;   // &&
                else return new Token('&');         // &
            case '|':
                if (readch('|')) return Word.or;    // ||
                else return new Token('|');         // |
            case '=':
                if (readch('=')) return Word.eq;      // ==
                else return new Token('=');         // =
            case '<':
                if (readch('=')) return Word.le;    // <=
                else return new Token('<');         // <
            case '>':
                if (readch('=')) return Word.ge;    // >=
                else return new Token('>');         // >
            case '!':
                if (readch('=')) return Word.ne;     // !=
                else return new Token('!');         // !
            case ':':
                if (readch('=')) return Word.att;     // :=
                else return new Token('!');
            case '/':
                if(readch('*')) ignoreComment('*');
                else if(ch=='/') ignoreComment('/');
                else return new Token('/');
        }
        
        //string
        if(ch == '"'){
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(ch);
                readch();
                if(ch == '\n'){
                    System.out.println("Erro: Aspas não foram fechadas.");
                    exit(0);
                }
            }while(ch != '"');
            sb.append(ch);
                        readch();
            return new StringSentence(sb.toString(),Tag.STR);
        }
        
        
        //Numeros  (constante Numericas)
        if (Character.isDigit(ch)) {
            int value = 0;
            do {
                value = 10 * value + Character.digit(ch, 10);
                readch();
            } while (Character.isDigit(ch));
            return new Num(value);
        }
        //identificadores
        if(Character.isLetter(ch)){
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(ch);
                readch();
            }while(Character.isLetterOrDigit(ch) || ch == '_');
            
            String s = sb.toString();
            Word w = (Word) words.get(s);
            
            if(w!=null){
                return w;
            }
            w = new Word(s,Tag.ID);
            words.put(s,w);
            return w;
        }
        if(ch == 65535){
            exit(0);
        }
        Token t = new Token(ch);
        ch = ' ';
        return t;
    }

    private void ignoreComment(char type) throws IOException {
        if(type == '*'){
            boolean exit = false;
        
            while(true){
                readch();
                if(ch == '*'){
                    do{
                        if(readch('/')){
                         exit = true;
                         break;
                        }
                    }while(ch=='*');

                            if(exit){
                                break;
                            }
                }
                if(ch == 65535){
                    System.out.println("Erro: Comentário não foi fechado.");
                    exit(0);
                }
            }
            for (;;readch()){
                if(ch==' '||ch=='\t'||ch=='\r'||ch=='\b'){
                    continue;
                }
                else if (ch=='\n') line ++; //conta linha
                else break;
            }
        }
        
        else{
            do{readch();}while(ch != '\n');
            
            for (;;readch()){
                if(ch==' '||ch=='\t'||ch=='\r'||ch=='\b'){
                    continue;
                }
                else if (ch=='\n') line ++; //conta linha
                else break;
            }
            
        }
    }
    

}
