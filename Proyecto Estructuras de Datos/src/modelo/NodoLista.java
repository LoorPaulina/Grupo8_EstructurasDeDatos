/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author user
 */
public class NodoLista<E> implements Iterable<E> {

    Nodo<E> cabeza;
    Nodo<E> cola;
    int size;
    int posicion; //iterador
      
    public class Nodo<E>{
        E contenido;
        Nodo<E> anterior;
        Nodo<E> siguiente;
        
        public Nodo(E e){
            contenido=e;
            siguiente=null;
            anterior=null;
        }
        
        public E get(){
            return contenido;
        }
    }
    
    public NodoLista(){
        cabeza=null;
        cola=null;
        this.size=0;
    }
    
    //agrega un Nodo
    public void add(E e){
        Nodo<E> tmp=new Nodo(e);
        
        if(cabeza==null){
            cabeza=tmp;
            cola=tmp;
            cabeza.siguiente=cabeza;
            cola.anterior=cola;
        }else{
            cola.siguiente=tmp;
            tmp.siguiente=cabeza;
            tmp.anterior=cola;
            cola=tmp;
            cabeza.anterior=cola; //refrescando apuntador
        }

        size++;
    }
    
    //añade elemento en el indice "indice"
    public boolean add(int indice, E e){
        Nodo<E> nuevo=new Nodo(e);
        if((indice<0)||(indice>size)){
            return false;
        }
        if(e==null){
            return false;
        }
        if(indice==0){
            nuevo.siguiente=cabeza;
            cabeza=nuevo;
            cabeza.anterior=cola;
            cola.siguiente=cabeza;
            size++;
        }
        if(indice==size-1){
            cola.siguiente=nuevo;
            cabeza.anterior=nuevo;
            size++;
        }else{
            Nodo<E> tmp=cabeza;
            for(int i=0; i<indice-1; i++){
                tmp=tmp.siguiente;}
                nuevo.siguiente=tmp.siguiente;
                tmp.siguiente=nuevo;
                size++;
        
    }
        return true; 
    }
    
    //añade nodo al principio de la lista
    public void addFirst(E e){
            Nodo<E> nuevo=new Nodo(e);
            nuevo.siguiente=cabeza;
            cabeza=nuevo;
            cabeza.anterior=cola;
            cola.siguiente=cabeza;
            size++;
    }
    
    //elimina nodo del principio
    public void removeFirst(){
        if(size==0){
            System.out.println("La lista esta vacia");
        }else{
                cabeza=cabeza.siguiente;
                cola.anterior=cola.siguiente;
                cola.siguiente=cabeza;
                cabeza.anterior=cola;
        }
        size--;
    }
    
    //elimina nodo del ultimo
    public void removeLast(){
        if(size==0){
            System.out.println("La lista esta vacia");
        }else{
            cola=cola.anterior;
            cola.siguiente=cabeza;
            cabeza.anterior=cola;
            size--;
        }}
    
    //añade nodo al ultimo
    public void addLast(E e){
         Nodo<E> nuevo=new Nodo(e);
         if(size==0){
             cabeza=nuevo;
             nuevo.siguiente=cabeza;
             cola=cabeza;
             size++;
         }else{
         cola.siguiente=nuevo;
         nuevo.siguiente=cabeza;
         nuevo.anterior=cola;
         cola=nuevo;
         cabeza.anterior=cola;
         size++;
         }
    }
    
    
    //remueve nodo del indice "indice"
    public void remove(int indice){
        if(indice>size-1){
            System.out.println("Fuera del rango");
        }else{
            Nodo<E> tmp=cabeza;
            for(int i=0; i<indice-1; i++){
                tmp=tmp.siguiente;
            }
                tmp.siguiente=tmp.siguiente.siguiente;
                size--;       
        }
        
    }
    
    //imprime la lista
    public void imprimir(){
        Nodo<E> actual=cabeza;
        int i=0;
        while(i<size){
            System.out.println(actual.contenido);
            actual=actual.siguiente;
            i++;
        }
    }
    
    //obtiene el contenido del primer nodo
    public E getFirst(){
        return cabeza.contenido;
    }
    
    //obtiene contenido del ultimo nodo
    public E getLast(){
        return cola.contenido;
    }
    
    //devuelve tamaño de la lista
    public int length(){
        return size;
    }
    
    //Indica si la lista esta vacia o no
    public boolean isEmpty(){
        boolean resultado= (size==0)?true:false;
        return resultado;
    }
    
    //Obtiene el contenido del nodo en el indice e
    public E getNode(int indice){
        Nodo<E> nodo=cabeza;
        if(indice==0){
            return nodo.contenido;
        }else{
        for(int i=0; i<indice; i++){
            nodo=nodo.siguiente;
        }
            return nodo.contenido;
        }
        
    }
    
    public Nodo<E> getNodeContSiguiente(int indice){
        Nodo<E> nodo=cabeza;
        for(int i=0; i<indice; i++){
            nodo=nodo.siguiente;
        }
            return nodo.siguiente;
        
    }
    
    public Nodo<E> getNodeContAnterior(int indice){
        Nodo<E> nodo=cabeza;
        if(indice==0){
            return nodo.anterior;
        }else{
        for(int i=0; i<indice; i++){
            nodo=nodo.siguiente;
        }
            return nodo.anterior;
        }  
    }
    
     @Override
    public Iterator<E> iterator(){
        posicion=0;
        Iterator<E> i=new Iterator<E>(){
        Nodo<E> tmp= cabeza;
        
        @Override
        public boolean hasNext(){
            if(posicion<size){
                return true;
            }else{
                return false;
            }
            }
        
        
        @Override
        public E next(){
                E valor= tmp.contenido;
                tmp=tmp.siguiente; 
                posicion++;
                return valor;    
        }};
            return i;
        }
    
    //encontrar valor exacto
    public E find(Comparator<E> cmp, E elemento) {
        E element=null;
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();
            if ((cmp.compare(e, elemento))== 0) {
                element=e;
            }
     }
        return element;
    }
    
    public NodoLista<E> findAll(Comparator<E> cmp, E elemento) {
        NodoLista<E> coincidencias=new NodoLista();
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E e = iterator.next();
            if ((cmp.compare(e, elemento)) == 0) {
                coincidencias.addLast(e);
            }
        }
        return coincidencias;
    }

    
    public void concat(NodoLista nodo){
        Iterator<E> iterator = nodo.iterator();
        while (iterator.hasNext()) {
                E e = iterator.next();
                this.addLast(e);
            }
        }
    }
  
    
    
    

