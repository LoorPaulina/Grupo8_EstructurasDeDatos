/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author user
 */
public class Nodo<E> {
    E contenido;
    Nodo<E> siguiente;
    
    public Nodo(E e){
        contenido=e;
        siguiente=null;
    }
    
     //Obteniendo el primero
    public E getFirst(){
        return this.contenido;
    }
    
    //Devuelve el contenido del nodo en la posicion i
    //indices inician desde el 0
    public E get(int indice){
        Nodo<E> tmp=this;
        if(indice>this.length()-1){
            tmp=new Nodo("Fuera del rango");
        }else{ 
        if(indice==0){
             tmp=this;
        }else{
            for(int i=0; i<indice;i++){
                tmp=tmp.siguiente;
            }
            }}    
         return tmp.contenido;
        }
    
    //Añade nodo al final
    public void addLast(E e){
        Nodo<E> nodo=new Nodo(e);
        
        if(this.siguiente==null){
            this.siguiente=nodo;
        }else{
            Nodo<E> temporal=this;
            while(temporal.siguiente !=null){
            temporal=temporal.siguiente;
        }
            temporal.siguiente=nodo;
        }    
        }
    
    //Imprime la lista
     public String imprimir(){
        String inicio="-->";
        
        Nodo<E> nodo=this;
        while(nodo.siguiente !=null){
            inicio += String.valueOf(nodo.contenido)+"-->";
            nodo=nodo.siguiente;
            
        }     
        inicio+= String.valueOf(nodo.contenido);
        return inicio + "<--";
    }
    
    //Devuelve el tamaño de la lista
    public int length(){
        Nodo<E> nodo=this;
        int contador=1;
        for(int i=0; nodo.siguiente!=null; i++){
            contador++;
            nodo=nodo.siguiente;
        }
        return contador;
    }
    
    //Devuelve el contenido del ultimo nodo
    public E getLast(){
        Nodo<E> tmp=this;
        while(tmp.siguiente!=null){
                tmp=tmp.siguiente;
        }
        return tmp.contenido;
    }
    
    //Añade según el índice
    public void add(int indice, E e){
            if((indice>this.length())||(indice<=0) ){
                System.out.println("Fuera de rango");
            }else{
                Nodo<E> tmp=this;
                Nodo<E> anterior=null;
                while (indice > 0) {
                    anterior = tmp;
                    tmp = tmp.siguiente;
                    indice--;
        }
                anterior.siguiente = new Nodo(e);   
                anterior.siguiente.siguiente = tmp;
    }
    }
    
    //Remueve del final
    public void removeLast(){
        Nodo<E> tmp=this;
            for(int i=0; i<this.length()-2; i++){
                tmp=tmp.siguiente;
        }
        tmp.siguiente=null;   
    }
    
    //Remueve de un indice i, indices inician en el 0
    public void remove(int i){
        if(i>this.length()-1 || i<0){
            System.out.println("Indice no válido");   
        }else{
            Nodo<E> tmp=this;
            for(int e=0; e<i-1; e++){
                tmp=tmp.siguiente;
        }
            tmp.siguiente=tmp.siguiente.siguiente;       
        }  }} 
        
    
    
 

