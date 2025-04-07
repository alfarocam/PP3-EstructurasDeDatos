/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolbinario10m;

/**
 *
 * @author EQUIPO
 */
public class ArbolBinario {

    private Nodo raiz;

    public ArbolBinario() {
        raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    // Método wrapper.
    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    // Método recursivo
    Nodo insertarRec(Nodo nodoActual, int valor) {
        // Caso 1: El nodo actual está vacío
        if (nodoActual == null) {        // Condición de parada.
            return new Nodo(valor);
        } else {
            // Caso 2: Que el nodo a insertar sea menor a la rama actual
            if (valor < nodoActual.getDato()) {
                nodoActual.setIzquierda(insertarRec(nodoActual.getIzquierda(), valor));
            } else if (valor > nodoActual.getDato()) {
                nodoActual.setDerecha(insertarRec(nodoActual.getDerecha(), valor));
            }
        }
        return nodoActual;
    }

    // Recorridos de árboles
    public void inOrden() {
        inOrdenRec(raiz);
    }

    // Método recursivo que recorre el árbol en inorden
    private void inOrdenRec(Nodo actual) {
        if (actual != null) {
            inOrdenRec(actual.getIzquierda()); // Vuelva a entrar recursivamente, pero evaluando el hijo izq.
            System.out.print(actual.getDato() + " ");
            inOrdenRec(actual.getDerecha()); // Vuelva a entrar recursivamente, pero evaluando el hijo der.
        }
    }

    public void preOrden() {
        preOrdenRec(raiz);
    }

    // Método recursivo que recorre el árbol en preorden
    private void preOrdenRec(Nodo actual) {
        if (actual != null) {
            System.out.print(actual.getDato() + " ");
            preOrdenRec(actual.getIzquierda()); // Vuelva a entrar recursivamente, pero evaluando el hijo izq.
            preOrdenRec(actual.getDerecha()); // Vuelva a entrar recursivamente, pero evaluando el hijo der.
        }
    }

    public void postOrden() {
        postOrdenRec(raiz);
    }

    // Método recursivo que recorre el árbol en postorden
    private void postOrdenRec(Nodo actual) {
        if (actual != null) {
            postOrdenRec(actual.getIzquierda()); // Vuelva a entrar recursivamente, pero evaluando el hijo izq.
            postOrdenRec(actual.getDerecha()); // Vuelva a entrar recursivamente, pero evaluando el hijo der.
            System.out.print(actual.getDato() + " ");
        }
    }

    // Método envoltura que invoca el métodos recursivo.
    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo actual, int valor) {
        // Condición de parada
        if (actual == null) {
            return actual;
        }

        //Buscar el nodo a la izquierda o a la derecha.
        if (valor < actual.getDato()) {  // Vamos a busarlo en la izquierda del arbol
            actual.setIzquierda(eliminarRec(actual.getIzquierda(), valor));
        } else if (valor > actual.getDato()) {    // Vamos a buscarlo a la derecha del arbol.
            actual.setDerecha(eliminarRec(actual.getDerecha(), valor));
        } else {      // YA lo encontré. Es igual.
            // Caso 1: Nodo sin hijos. (Hoja)
            if (actual.getIzquierda() == null && actual.getDerecha() == null) {
                return null;   // Elimina la hoja y la sustituye por un null.
            }
            // Caso 2: Nodo con un hijo
            if (actual.getIzquierda() == null) {
                return actual.getDerecha();
            } else if (actual.getDerecha() == null) {
                return actual.getIzquierda();
            }

            // Caso 3: Nodo con 2 hijos.
            Nodo sucesor = minValorSucedor(actual.getDerecha()); // Nos devuelve el sucedor del # que queremos eliminar.
            actual.setDato(sucesor.getDato());  //Ya tenemos remplazado el valor.
            actual.setDerecha(eliminarRec(actual.getDerecha(), sucesor.getDato()));
        }
        return actual;
    }

    private Nodo minValorSucedor(Nodo nodo) {
        while (nodo.getIzquierda() != null) {
            nodo = nodo.getIzquierda();
        }
        return nodo;

    }

    public int obtenerNivel(int valor, int nivel) {
        return obtenerNivelRec(raiz, valor, nivel);
    }

    public int obtenerNivelRec(Nodo actual, int valor, int nivel) {
        if (actual == null) {
            return -1;
        }
        if (actual.getDato() == valor) {
            return nivel;    // Si encuentro el valor, retorno su nivel
        }
        int nivelIzq = obtenerNivelRec(actual.getIzquierda(), valor, nivel + 1);
        if (nivelIzq != -1) {
            return nivelIzq;    // Significa que encontré el valor que ando buscando.
        }
        return obtenerNivelRec(actual.getDerecha(), valor, nivel + 1);
    }

    public int obtenerAltura(Nodo actual) {
        if (actual == null) {
            return -1;
        }
        int alturaIzq = obtenerAltura(actual.getIzquierda());
        int alturaDer = obtenerAltura(actual.getDerecha());
        int alturaMayor = Math.max(alturaIzq, alturaDer) + 1;   // Compara cual de las 2 ramas es mas larga.
        return alturaMayor;

    }

    /**
     *
     * @author CamilaAlfaro 7/4/2025
     */
    //c)con estos métodos se elimina las hojas del árbol (nodos sin hijos)
    //este metodo le pasa la raiz a una funcion recursiva
    public void eliminarHojas() {
        raiz = eliminarHojasRec(raiz);
    }

    //este metodo es el que "revisa" o visita cada nodo y elimina las hojas
    private Nodo eliminarHojasRec(Nodo actual) {
        if (actual == null) {
            return null;
        }
        //aqui se confirma que sea una hoja es decir que el nodo no tenga hijos y la elimina
        if (actual.getIzquierda() == null && actual.getDerecha() == null) {
            return null;
        }
        //de forma rescursiva revisa los hijos
        actual.setIzquierda(eliminarHojasRec(actual.getIzquierda()));
        actual.setDerecha(eliminarHojasRec(actual.getDerecha()));
        //return ya el nodo actualizado
        return actual;
    }

    //e) multiplicar todos los nodos precargados por 10
    //igual que anteriormente este metodo le pasa la raiz a una funcion recursiva
    public void multiplicarPor10() {
        multiplicarPor10Rec(raiz);
    }
    //este metodo es el encargado de visitar todos los nodos del arbol  y multiplicar su valor
    private void multiplicarPor10Rec(Nodo actual) {
        if (actual != null) {
            actual.setDato(actual.getDato() * 10);
            multiplicarPor10Rec(actual.getIzquierda());
            multiplicarPor10Rec(actual.getDerecha());
        }
    }
}
