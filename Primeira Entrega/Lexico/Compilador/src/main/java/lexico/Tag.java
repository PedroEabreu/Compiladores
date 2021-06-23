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
public class Tag {
        //Palavras reservadas
    public final static int CLASS = 256;
    public final static int INT = 257;
    public final static int STRING = 258;
    public final static int FLOAT = 259;
    public final static int INIT = 260;
    public final static int STOP = 261;
    public final static int IF = 262;
    public final static int ELSE = 263;
    public final static int DO = 264;
    public final static int WHILE = 265;
    public final static int READ = 266;
    public final static int WRITE = 267;
    public final static int BEGIN = 282;
    public final static int END = 283;
    public  final static int NOT = 284;
    
    //Operadores e pontuação
    public final static int EQ = 268;
    public final static int GE = 269;
    public final static int LE = 270;
    public final static int NE = 271;
    public final static int GT = 272;
    public final static int LT = 273;
    public final static int AND = 274;
    public final static int OR = 275;
    public final static int ADD = 276;
    public final static int SUB = 277;
    public final static int TRUE = 280;
    public final static int FALSE = 281;

    //Outros tokens
    public final static int NUM = 278;
    public final static int ID = 279;

}
