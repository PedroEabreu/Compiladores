/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sintatico;

/**
 *
 * @author pedroelias
 * 
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import lexico.Tag;
import lexico.Token;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import lexico.Lexer;

public class Parser {
    private Token tok;
    private int tag;
    private int i;
    private int line;
    private int nID;
    private Lexer lex;
    
    public Parser(String arq) throws FileNotFoundException, IOException{
        lex = new Lexer(arq);
        advance();
        program();
    }
    
        
    private void advance() throws IOException{
        Scanner ler = new Scanner(System.in);
        //String a = ler.nextLine();
        tok = getToken();
        tag = tok.getTag();
        line = tok.line;

        //System.out.print("Token = "); tok.imprimeToken(tok);System.out.println(" -> " + tok.toString());
    }

    private void error(String funcaoDoErro) throws IOException{
        if(tag==Tag.EOF) {
            if(line==-4)
                System.out.println("Arquivo de entrada vazio");
            return;
        }
        System.out.print("Linha " + line +  ". Token não esperado: '"); //debug
        tok.imprimeToken(tok);System.out.print("'");
        if(tok.getTag() == Tag.ID) System.out.println(" -> " + tok.getLexeme());
        if(tok.getTag() == Tag.NUM) System.out.println(" -> " + tok.toString());
        System.out.println("\n"+funcaoDoErro);
        System.exit(0);
        /*while(tag!=Tag.EOF)
            advance();*/
    } 

    private void eat(int t) throws IOException{
        //System.out.println("Token esperado = " + t);
        //System.out.println("Token = " + tok.toString());
        if(tag==t){ advance(); }
        else { error("Error in: eat. Token esperado: " + "'" + (char)t + "'"); 
        
        if(tag == Tag.EOF){
            System.out.println("Fim de arquivo inesperado");
            System.exit(0);
            
        }
        }
    }

    private void program() throws IOException{
        switch(tag) {
            //program ::= class identifier [decl-list] body
            case Tag.CLASS:
                eat(Tag.CLASS); eat(Tag.ID); declList(); body();  
                break;
            default:
                error("Error in: program");
           /*if(tag == Tag.EOF){
            System.out.println("Código verificado");
            System.out.println("Arquivo sintáticamente correto!");
        }*/
        }
    }

    private void declList() throws IOException{
        //G:: decl-list   ::= decl ";" { decl ";"}
        switch(tag) {
            case Tag.INT:
            case Tag.STRING:
            case Tag.FLOAT:
                decl(); eat(';'); 
                if(tag == Tag.INIT) break; 
                declList(); break;
            case Tag.INIT:
                break;
            default:
                error("Error in: decList");
                
        }   
    }

    private void decl() throws IOException{      
        switch(tag) {
            //G:: decl ::= type ident-list 
            case Tag.INT:
            case Tag.STRING:
            case Tag.FLOAT:
                type(); identList();
                break;
            default:
                error("Error in: decl");
        }
    }

    private void identList() throws IOException{
        switch(tag) {
            //G:: ident-list ::= identifier {"," identifier} 
            case Tag.ID:
                eat(Tag.ID);
                if(tag == ','){ eat(','); identList();}  
                else if(tag == ';'){}
                break;
            default:
                error("Error in: identList");
        }
    }

    private void type() throws IOException{
        switch(tag) {
            //G:: type ::= int
            case Tag.INT:
                eat(Tag.INT);
                break;
            //G:: type ::= string
            case Tag.STRING:
                eat(Tag.STRING);
                break;
            //G:: type ::= float
            case Tag.FLOAT:
                eat(Tag.FLOAT); 
                break;        
            default:
                error("Error in: type");
        }
    }
    
    private void body() throws IOException {
         //G:: body ::= init stmt-list stop 

        switch(tag){
            case Tag.INIT:
                eat(Tag.INIT); stmtList(); eat(Tag.STOP); break;
            default:
                error("Error in: body");
        }  
        
            System.out.println("Código verificado");
            System.out.println("Arquivo sintáticamente correto!");

    }

    private void stmtList() throws IOException{
        switch(tag) {
            //G:: stmt-list ::= stmt ";" { stmt ";" } ???
            case Tag.IF:
            case Tag.ID:
            case Tag.DO:
            case Tag.READ:
            case Tag.WRITE:
                stmt(); eat(';'); stmtList();break;
            case Tag.WHILE:
                break;
            case Tag.STOP:
                break;    
            case Tag.END:
                break;
            case '}':
                break;
            default:
                error("Error in: stmtList");
        }

    }

    private void stmt() throws IOException{
        switch(tag) {
            //G:: stmt ::= assign-stmt
            case Tag.ID:
                assignStmt();
                break;
            //G:: stmt ::= if-stmt
            case Tag.IF:
                ifStmt();
                break;
            //G:: stmt ::= while-stmt
            case Tag.DO:
                doStmt();
                break;
            //G:: stmt ::= read-stmt 
            case Tag.READ:
                readStmt();
                break;
            //G:: stmt ::= write-stmt 
            case Tag.WRITE:
                writeStmt();
                break;
            default:
                error("Error in: stmt");
        }
    }

    private void assignStmt() throws IOException{
        switch(tag) {
            //G:: assign-stmt ::= identifier "=" simple_expr 
            case Tag.ID:
                eat(Tag.ID); eat('='); simpleExpr();
                break;
            default:
                error("Error in: assignStmt");
        }
    }

    private void ifStmt() throws IOException{
        switch(tag) {
            //G::  if-stmt  := if "(" condition ")" "{" stmt-list "}"  if-stmt’
            case Tag.IF:
                eat(Tag.IF); eat('(');  condition();  eat(')'); eat('{'); stmtList(); eat('}'); ifStmtPrime();break;
            default:
                error("Error in: ifStmt");
        }

    }
    
    private void ifStmtPrime() throws IOException{

        switch(tag) {
            //G::   if-stmt’ := λ | else "{" stmt-list "}" 
            case Tag.ELSE:
                eat(Tag.ELSE); eat('{'); stmtList(); eat('}');break;
            case ';':
                break;
            default:
                error("Error in: ifStmtPrime");
        }
    }
    
    private void condition() throws IOException{
        switch(tag) {
            //G:: condition ::= expression 
            case Tag.ID:
            case Tag.NUM:
            case Tag.STR:
            case '!':
            case '-':
            case '(':
                expression();
                break;
            default:
                error("Error in: condition");
        }

    }
    


    private void doStmt() throws IOException{
        switch(tag) {
            //G:: do-stmt ::= do "{" stmt-list "}" do-suffix 
            case Tag.DO:
                eat(Tag.DO); eat('{'); stmtList(); eat('}'); doSufix();
                break;
            default:
                error("Error in: doStmt");
        }
    }

    private void doSufix() throws IOException{
        switch(tag) {
            //G:: do-suffix ::= while "(" condition ")" 
            case Tag.WHILE:
                eat(Tag.WHILE); eat('('); condition(); eat(')');
                break;
            default:
                error("Error in: doSufix");
        }
    }

    private void readStmt() throws IOException{
        switch(tag) {
            //G:: read-stmt ::= read "(" identifier ")" 
            case Tag.READ:
                eat(Tag.READ); eat('('); eat(Tag.ID); eat(')');
                break;
            default:
                error("Error in: redStmt");
        }
    }

    private void writeStmt() throws IOException{
        switch(tag) {
            //G:: write-stmt ::= write "(" writable ")" 
            case Tag.WRITE:
                eat(Tag.WRITE); eat('('); writable(); eat(')');
                break;
            default:
                error("Error in: writeStmt");
        }
    }

    private void writable() throws IOException {
        switch(tag) {
            //G:: writable ::= simple-expr  
            case Tag.ID:
            case Tag.NUM:
            case Tag.STR:
            case '!':
            case '-':
            case '(':
                simpleExpr();
                break;
            default:
                error("Error in: writeStmt");
        }
    }
        
    private void  expression() throws IOException{
        //G:: expression  ::= simple-expr expression’
        switch(tag){
            case Tag.ID:
            case Tag.NUM:
            case Tag.STR:
            case '!':
            case '-':
            case '(':
                simpleExpr(); expressionPrime(); break;
            default:
                error("Error in: expression");
        }
    }

    private void expressionPrime() throws IOException{
        //G:: expression’ ::= relop simple-expr | λ
        switch(tag){
            case Tag.GT:
            case Tag.GE:
            case Tag.LT:
            case Tag.LE:
            case Tag.EQ:
            case Tag.NE:
                relop(); simpleExpr(); break;
            case ')':
                break;
            default:
                 error("Error in: expression prime");
            
        }
    }

    private void simpleExpr() throws IOException{
        switch(tag) {
            //G:: simple-expr ::= term | simple-expr addop term
            case Tag.ID:
            case Tag.NUM:
            case Tag.STR:
            case '!':
            case '-':
            case '(':
                term(); simpleExprPrime();
                break;
            default:
                error("Error in: simpleExpr");   
        }
    }

    private void simpleExprPrime() throws IOException{
        //G:: simple-expr’ ::= addop term simple-expr’ | λ

        switch(tag){
            case Tag.GT:
            case Tag.GE:
            case Tag.LT:
            case Tag.LE:
            case Tag.NE:
            case Tag.EQ:
            case ')':
            case ';':
                break;
            case '-':
            case Tag.OR:
            case '+':
                addop(); term(); simpleExprPrime();
                break;
            default:
                error("Error in: simpleExprPrime");   
        }
    }
    
    private void term() throws IOException{
        switch(tag) {
            //G:: term  ::= factor-a term’
            case Tag.ID:
            case Tag.NUM:
            case Tag.STR:
            case '!':
            case '-':
            case '(':
                factorA(); termPrime();
                break;
            default:
                error("Error in: term");
        }
    }

    private void termPrime() throws IOException{
        //G:: term’ ::= mulop factor-a term’ | λ
        switch(tag){
            case Tag.GE:
            case Tag.GT:
            case Tag.LE:
            case Tag.LT:
            case Tag.NE:
            case Tag.EQ:
            case Tag.OR:
            case '+':
            case '-':
            case ')':
            case ';':
                break;
            case Tag.AND:
            case '/':
            case '*':
                mulop(); factorA(); termPrime();
                break;
            default:
                error("Error in: Term");
        }
    }

    private void factorA() throws IOException{
        switch(tag) {
            //G:: factor-a ::= factor | "!" factor | "-" factor 
            case Tag.ID:
            case Tag.NUM:
            case Tag.STR:
            case '(':
                factor(); break;
            case '!':
                eat('!'); factor(); break;
            case '-':
                eat('-'); factor(); break;
            default:
                error("Error in: FactorA");
        }
    }

    private void factor() throws IOException{
        switch(tag) {
            //G:: factor ::= identifier | constant | "(" expression ")" 
            case Tag.ID:
                eat(Tag.ID); break;
            case Tag.NUM:
            case Tag.STR:
                constant(); break;
            case '(':
                eat('('); expression(); eat(')'); break;
            default:
                error("Error in: factor");
        }
    }

    private void relop() throws IOException{
        //G:: relop ::= ">" | ">=" | "<" | "<=" | "!=" | "==" 

        switch(tag) {
            //G:: relop ::= "=="
            case Tag.EQ:
                eat(Tag.EQ);
                break;
            //G:: relop ::= ">"
            case Tag.GT:
                eat(Tag.GT);
                break;
            //G:: relop ::= "<"
            case Tag.LT:
                eat(Tag.LT);
                break;
            //G:: relop ::= "!="
            case Tag.NE:
                eat(Tag.NE);
                break;
            //G:: relop ::= ">="
            case Tag.GE:
                eat(Tag.GE);
                break;
            //G:: relop ::= "<="
            case Tag.LE:
                eat(Tag.LE);
                break;
            default:
                error("Error in: relop");
        }
    }

    private void addop() throws IOException{
        //G:: addop ::= "+" | "-" | "||" 
        
        switch(tag) {
            //G:: addop ::= "+"
            case '+':
                eat('+');
                break;
            //G:: addop ::= "-"
            case '-':
                eat('-');
                break;
            //G:: addop ::= "||"
            case Tag.OR:
                eat(Tag.OR);
                break;
            default:
                error("Error in: addop");
        }
    }

    private void mulop() throws IOException{
        //G:: mulop ::= "*" | "/" | "&&" 
        
        switch(tag) {
            //G:: mulop ::= "*"
            case '*':
                eat('*');
                break;
            //G:: mulop ::= "/"
            case '/':
                eat('/');
                break;
            //G:: mulop ::= "AND"
            case Tag.AND:
                eat(Tag.AND);
                break;
            default:
                error("Error in: mulop");
        }
    }

    private void constant() throws IOException {
        //G:: constant → integer_const | literal | real_const
        switch (tag) {
            //G:: constant ::= integer_const
            case Tag.NUM:
                eat(Tag.NUM);
                break;
            //G:: constant ::= literal
            case Tag.STR:
                eat(Tag.STR);
                break;
            default:
                error("Error in: constant");
        }
    }

    private Token getToken() throws IOException {
            return lex.scan();
    }

}