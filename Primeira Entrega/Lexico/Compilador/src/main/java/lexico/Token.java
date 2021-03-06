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
public class Token {
    public final int tag;
    public int line;
    private String lexeme="";
    
    public Token(int t,int line){
        tag = t;
        this.line = line;
    }
    
    public String toString(){
       /* if (lexeme == '1') {
            return "" + lexeme;
        }
        else*/ return "" + tag;
    }

    public void imprimeToken(Token T) {
        String valor;
        switch (tag) {
            case Tag.CLASS:
                valor = "class";
                break;
            case Tag.INT:
                valor = "integer";
                break;
            case Tag.STRING:
                valor = "string";
                break;
            case Tag.FLOAT:
                valor = "float";
                break;
            case Tag.INIT:
                valor = "init";
                break;
            case Tag.STOP:
                valor = "stop";
                break;
            case Tag.IF:
                valor = "if";
                break;
            case Tag.ELSE:
                valor = "else";
                break;
            case Tag.DO:
                valor = "do";
                break;
            case Tag.WHILE:
                valor = "while";
                break;
            case Tag.READ:
                valor = "read";
                break;
            case Tag.WRITE:
                valor = "write";
                break;
            case Tag.BEGIN:
                valor = "begin";
                break;
            case Tag.END:
                valor = "end";
                break;
            case Tag.NOT:               //fim das palavras reservadas
                valor = "not";
                break;
            case Tag.EQ:
                valor = "equal";
                break;
            case Tag.GE:
                valor = ">=";
                break;
            case Tag.LE:
                valor = "<=";
                break;
            case Tag.NE:
                valor = "!=";
                break;
            case Tag.GT:
                valor = ">";
                break;
            case Tag.LT:
                valor = "<";
                break;
            case Tag.AND:
                valor = "&&";
                break;
            case Tag.OR:
                valor = "||";
                break;
            case Tag.ADD:
                valor = "add";
                break;
            case Tag.SUB:
                valor = "sub";
                break;
            case Tag.TRUE:
                valor = "true";
                break;
            case Tag.FALSE:
                valor = "false";
                break;
            case Tag.NUM:       //fim dos operadores e pontua????o
                valor = "num";
                break;
            case Tag.ID:
                valor = "id";
                break;
            case Tag.STR:
                valor = "str";
                break;
            case Tag.ATT:
                valor = ":=";
                break;
            default:
                valor = "" + (char) tag;
        }
        System.out.print(valor);
         
    }
    public String getLexeme(){
        return lexeme;
    }
    public int getTag(){
        return tag;
    }

    public static boolean isLetter(char ch){
        int A = (int)'A';
        int Z = (int)'Z';
        int a = (int)'a';
        int z = (int)'z';
        if (((int)ch >= A && (int)ch <=Z)|| ((int)ch >=a && (int)ch <= z)){
            return true;
        }
        return false;
    }

    public static boolean isNumber(char ch){
        int zero = (int)'0';
        int nove = (int)'9';
        if (((int)ch >= zero && ch <= nove)) {
            return true;
        }
        return false;
    }

}
