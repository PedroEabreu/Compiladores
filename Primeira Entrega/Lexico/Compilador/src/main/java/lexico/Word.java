/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexico;

/**
 *
 * @author pedroelias
 */
public class Word extends Token{
    private String lexeme = "";

    public String getLexeme() {
        return lexeme;
    }
    
    public static final Word and = new Word("&&",Tag.AND,0);
    public static final Word or = new Word("||",Tag.OR,0);
    public static final Word eq = new Word("==",Tag.EQ,0);
    public static final Word ne = new Word("!=",Tag.NE,0);
    public static final Word le = new Word("<=",Tag.LE,0);
    public static final Word ge = new Word(">=",Tag.GE,0);
    public static final Word True = new Word("&&",Tag.TRUE,0);
    public static final Word False = new Word("&&",Tag.FALSE,0);
    
    public Word(String s, int tag, int line){
        super(tag, line);
        lexeme = s;
    }
    
    public String toString() {
        return "" + lexeme;
    }
    
}
