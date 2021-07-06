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
public class Num extends Token{
    
    public final int value;
    
    public Num(int value){
        super(Tag.NUM, 0);
        this.value = value;
    }
    
    public String toString(){
        return "" + value;
    }
}
