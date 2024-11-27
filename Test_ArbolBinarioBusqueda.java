class Nodo {
    int dato;
    Nodo izquierdo, derecho;

    public Nodo(int dato) {
        this.dato = dato;
        izquierdo = derecho = null;
    }
}

class ArbolBinarioBusqueda {
    Nodo raiz;

    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo insertarRec(Nodo nodo, int dato) {
        if (nodo == null) {
            return new Nodo(dato);
        }
        if (dato < nodo.dato) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        } else if (dato > nodo.dato) {
            nodo.derecho = insertarRec(nodo.derecho, dato);
        }
        return nodo;
    }

    public boolean buscar(int dato) {
        return buscarRec(raiz, dato);
    }

    private boolean buscarRec(Nodo nodo, int dato) {
        if (nodo == null) {
            return false;
        }
        if (dato == nodo.dato) {
            return true;
        }
        return dato < nodo.dato ? buscarRec(nodo.izquierdo, dato) : buscarRec(nodo.derecho, dato);
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

public class Test_ArbolBinarioBusqueda {
    public static void main(String[] args) {
        ArbolBinarioBusqueda bst = new ArbolBinarioBusqueda();
        bst.insertar(50);
        bst.insertar(30);
        bst.insertar(70);
        bst.insertar(20);
        bst.insertar(40);
        bst.insertar(60);
        bst.insertar(80);

        System.out.println("Recorrido inorden:");
        bst.inorden();

        System.out.println("\nBuscar 40: " + bst.buscar(40));
        System.out.println("Buscar 90: " + bst.buscar(90));
    }
}
