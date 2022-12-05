/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author user
 */
public class TDA_ArrayList<E>{
    private E[] arreglo; //no getters y setters
    private int ultimo;
    private int capacidad;
    
    
    public TDA_ArrayList(){
        arreglo = (E[])new Object[1];
        capacidad=1;
        ultimo =-1;
    }
    public boolean isEmpty(){
      return(ultimo==-1); //retorna V o F, si da menos uno está vacío  
        
    }
    
    
    public int size(){
      return arreglo.length;
    }
    
    public E getElement(int index){
        return arreglo[index];
    }
    
    private void crecerArreglo(){
         E[] temporal=(E[])new Object[capacidad+1];
         
         for(int i=0; i<=capacidad-1; i++){
                temporal[i]=arreglo[i];
             
         }
         arreglo=temporal;
         capacidad=arreglo.length;    
    }

    public String imprimir(){
        String s="";
         for(Object i :arreglo){
          s=s+" , "+i;
       }
         return s.substring(3);
    }
    

    //Añade elementos a la lista
    public boolean add(E e){
        if(ultimo==capacidad-1){
            crecerArreglo();
            ultimo++;
            arreglo[ultimo]=e;
            return false;  
        }else{
           ultimo++;
           arreglo[ultimo]=e;
           return true;
        }
    }
    
    //Añade en determinado indice valido entre los indices existentes
    public void add(int indice, E e){
        if(indice>arreglo.length){
            System.out.println("Fuera del rango");
        }else{
            E[] temporal=(E[])new Object[capacidad+1];
            for(int i=0; i<indice; i++){
                temporal[i]=arreglo[i];
            }
            
            for(int i=indice; i<temporal.length-1; i++){
                temporal[i+1]=arreglo[i];
            }
            arreglo=temporal;
            arreglo[indice]=e;
           
        }
    }
    
    //Elimina elemento del indice n
    public void remove(int indice){
        if(indice>arreglo.length){
            System.out.println("Fuera del rango");
        }else{
           E[] temporal=(E[])new Object[capacidad+1];
            for(int i=0; i<indice; i++){
                temporal[i]=arreglo[i];
            } 
            
            for(int i=indice; i<temporal.length-1; i++){
                temporal[i]=arreglo[i+1];
            }
            arreglo=temporal;
        }
    }
    

}
