class NodoAVL {
    int clave, altura;
    NodoAVL izquierdo, derecho;

    public NodoAVL(int clave) {
        this.clave = clave;
        this.altura = 1; // Altura inicial
    }
}

class ArbolAVL {
    private NodoAVL raiz;

    private int altura(NodoAVL nodo) {
        return nodo == null ? 0 : nodo.altura;
    }

    private int factorDeBalanceo(NodoAVL nodo) {
        return nodo == null ? 0 : altura(nodo.izquierdo) - altura(nodo.derecho);
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izquierdo;
        NodoAVL T = x.derecho;

        x.derecho = y;
        y.izquierdo = T;

        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;
        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;

        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.derecho;
        NodoAVL T = y.izquierdo;

        y.izquierdo = x;
        x.derecho = T;

        x.altura = Math.max(altura(x.izquierdo), altura(x.derecho)) + 1;
        y.altura = Math.max(altura(y.izquierdo), altura(y.derecho)) + 1;

        return y;
    }

    public void insertar(int clave) {
        raiz = insertar(raiz, clave);
    }

    private NodoAVL insertar(NodoAVL nodo, int clave) {
        if (nodo == null) return new NodoAVL(clave);

        if (clave < nodo.clave) {
            nodo.izquierdo = insertar(nodo.izquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.derecho = insertar(nodo.derecho, clave);
        } else {
            return nodo; // Claves duplicadas no permitidas
        }

        nodo.altura = 1 + Math.max(altura(nodo.izquierdo), altura(nodo.derecho));
        int balanceo = factorDeBalanceo(nodo);

        if (balanceo > 1 && clave < nodo.izquierdo.clave) {
            return rotarDerecha(nodo);
        }
        if (balanceo < -1 && clave > nodo.derecho.clave) {
            return rotarIzquierda(nodo);
        }
        if (balanceo > 1 && clave > nodo.izquierdo.clave) {
            nodo.izquierdo = rotarIzquierda(nodo.izquierdo);
            return rotarDerecha(nodo);
        }
        if (balanceo < -1 && clave < nodo.derecho.clave) {
            nodo.derecho = rotarDerecha(nodo.derecho);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    public void imprimirEnOrden() {
        imprimirEnOrden(raiz);
    }

    private void imprimirEnOrden(NodoAVL nodo) {
        if (nodo != null) {
            imprimirEnOrden(nodo.izquierdo);
            System.out.print(nodo.clave + " ");
            imprimirEnOrden(nodo.derecho);
        }
    }
}

public class Test_ArbolAVL {
    public static void main(String[] args) {
        ArbolAVL avl = new ArbolAVL();
        avl.insertar(10);
        avl.insertar(20);
        avl.insertar(30);
        avl.insertar(40);
        avl.insertar(50);
        avl.insertar(25);

        System.out.println("Recorrido en orden del árbol AVL:");
        avl.imprimirEnOrden();
    }
}
