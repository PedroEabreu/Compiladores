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

    public static int line = 1;
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
        
        reserve(new Word("if",Tag.IF,0));
        reserve(new Word("class",Tag.CLASS,0));
        reserve(new Word("int",Tag.INT,0));
        reserve(new Word("string",Tag.STRING,0));
        reserve(new Word("float",Tag.FLOAT,0));
        reserve(new Word("init",Tag.INIT,0));
        reserve(new Word("stop",Tag.STOP,0));
        reserve(new Word("else",Tag.ELSE,0));
        reserve(new Word("do",Tag.DO,0));
        reserve(new Word("while",Tag.WHILE,0));
        reserve(new Word("read",Tag.READ,0));
        reserve(new Word("write",Tag.WRITE,0));
   
        
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
                else return new Token('&',0);         // &
            case '|':
                if (readch('|')) return Word.or;    // ||
                else return new Token('|',0);         // |
            case '=':
                if (readch('=')) return Word.eq;      // ==
                else return new Token('=',0);         // =
            case '<':
                if (readch('=')) return Word.le;    // <=
                else return new Token('<',0);         // <
            case '>':
                if (readch('=')) return Word.ge;    // >=
                else return new Token('>',0);         // >
            case '!':
                if (readch('=')) return Word.ne;     // !=
                else return new Token('!',0);         // !
            case '/':
                if(readch('*')) ignoreComment('*');
                else if(ch=='/') ignoreComment('/');
                else return new Token('/',0);
        }
        
        //string
        if(ch == '"'){
            StringBuffer sb = new StringBuffer();
            do {
                sb.append(ch);
                readch();
                if(ch == 65535){
                    System.out.println("Erro: Aspas não foram fechadas.");
                    exit(0);
                }
            }while(ch != '"');
            sb.append(ch);
                        readch();
            return new StringSentence(sb.toString(),Tag.STR,0);
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
            w = new Word(s,Tag.ID,0);
            words.put(s,w);
            return w;
        }
        if(ch == 65535){
            exit(0);
        }
        Token t = new Token(ch,0);
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
