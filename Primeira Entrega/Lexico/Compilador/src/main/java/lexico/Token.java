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
    
    public Token(int t){
        tag = t;
    }
    
    public String toString(){
        return "" + tag;
    }

    public String imprimeToken(Token T) {
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
            case  Tag.FLOAT:
                valor ="float";
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
                valor = "greater_equals";
                break;
            case Tag.LE:
                valor = "less_equals";
                break;
            case Tag.NE:
                valor = "not_equal";
                break;
            case Tag.GT:
                valor = "greater_than";
                break;
            case Tag.LT:
                valor = "less_than";
                break;
            case Tag.AND:
                valor = "and";
                break;
            case Tag.OR:
                valor = "or";
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
            case Tag.NUM:       //fim dos operadores e pontuação
                valor = "num";
                break;
            case Tag.ID:
                valor = "id";
                break;
            default:
                valor = "" + (char)tag;
        }
        return valor;
    }
}
