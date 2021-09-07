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
public class StringSentence extends Token{
    
    String sentence;
    
    public StringSentence(String s, int tag,int line){
        super(tag,line);
        sentence = s;
    }

    public String toString(){
        return sentence;
    }
}
