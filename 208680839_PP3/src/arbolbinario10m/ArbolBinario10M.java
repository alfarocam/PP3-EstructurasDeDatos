/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arbolbinario10m;

/**
 *
 * @author EQUIPO
 * @author CamilaAlfaro 7/4/2025
 */
public class ArbolBinario10M {
    public static void main(String[] args) {
        //Práctica Evaluada #3 - Camila Alfaro Rivera
        ArbolBinario miArbol = new ArbolBinario();
        //a) precarga del árbol con su respectiva secuencia 
        miArbol.insertar(50);
        miArbol.insertar(20);
        miArbol.insertar(80);
        miArbol.insertar(15);
        miArbol.insertar(30);
        miArbol.insertar(60);
        miArbol.insertar(40);
        miArbol.insertar(90);
        miArbol.insertar(75);
        miArbol.insertar(33);
        miArbol.insertar(82);
        //b) imprimir el árbol
        System.out.println("\nPreorden");
        miArbol.preOrden();
        System.out.println("\nInOrden");
        miArbol.inOrden();
        System.out.println("\nPostorden");
        miArbol.postOrden();
        
        System.out.println();
        
        //d) eliminar nodo que vale 30
        miArbol.eliminar(30);
        //e) multi todos los nodos x10
        miArbol.multiplicarPor10();
        //f) imprimir el árbol para comprobar que las hojas fueron eliminadas y que los nodos han sido actualizados
        System.out.println("\n\nArbol con Cambios (preorden):");
        miArbol.preOrden();
        System.out.println("\n\nArbol con Cambios(inorden):");
        miArbol.inOrden();
        System.out.println("\n\nArbol con Cambios(postorden):");
        miArbol.postOrden();
    }
    
}
