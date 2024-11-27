class Nodo {
    int dato;
    Nodo izquierdo, derecho;

    public Nodo(int dato) {
        this.dato = dato;
        izquierdo = derecho = null;
    }
}

class ArbolBinario {
    Nodo raiz;

    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo insertarRec(Nodo raiz, int dato) {
        if (raiz == null) {
            raiz = new Nodo(dato);
            return raiz;
        }
        if (dato < raiz.dato)
            raiz.izquierdo = insertarRec(raiz.izquierdo, dato);
        else if (dato > raiz.dato)
            raiz.derecho = insertarRec(raiz.derecho, dato);
        return raiz;
    }

    public void inorden() {
        inordenRec(raiz);
    }

    private void inordenRec(Nodo nodo) {
        if (nodo != null) {
            inordenRec(nodo.izquierdo);
            System.out.print(nodo.dato + " ");
            inordenRec(nodo.derecho);
        }
    }
}

public class Test_ArbolBinario {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario();
        arbol.insertar(40);
        arbol.insertar(20);
        arbol.insertar(60);
        arbol.insertar(10);
        arbol.insertar(30);
        arbol.insertar(50);
        arbol.insertar(70);

        System.out.println("Recorrido en inorden:");
        arbol.inorden();
    }
}
