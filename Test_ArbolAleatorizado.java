import java.util.Random;

class NodoTreap {
    int clave, prioridad;
    NodoTreap izquierdo, derecho;

    public NodoTreap(int clave) {
        this.clave = clave;
        this.prioridad = new Random().nextInt(100); // Genera prioridad aleatoria
    }
}

class Treap {
    private NodoTreap raiz;

    public void insertar(int clave) {
        raiz = insertar(raiz, clave);
    }

    private NodoTreap insertar(NodoTreap nodo, int clave) {
        if (nodo == null) {
            return new NodoTreap(clave);
        }
        if (clave < nodo.clave) {
            nodo.izquierdo = insertar(nodo.izquierdo, clave);
            if (nodo.izquierdo.prioridad > nodo.prioridad) {
                nodo = rotarDerecha(nodo);
            }
        } else if (clave > nodo.clave) {
            nodo.derecho = insertar(nodo.derecho, clave);
            if (nodo.derecho.prioridad > nodo.prioridad) {
                nodo = rotarIzquierda(nodo);
            }
        }
        return nodo;
    }

    public void eliminar(int clave) {
        raiz = eliminar(raiz, clave);
    }

    private NodoTreap eliminar(NodoTreap nodo, int clave) {
        if (nodo == null) return null;
        if (clave < nodo.clave) {
            nodo.izquierdo = eliminar(nodo.izquierdo, clave);
        } else if (clave > nodo.clave) {
            nodo.derecho = eliminar(nodo.derecho, clave);
        } else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null) return nodo.izquierdo;

            if (nodo.izquierdo.prioridad > nodo.derecho.prioridad) {
                nodo = rotarDerecha(nodo);
                nodo.derecho = eliminar(nodo.derecho, clave);
            } else {
                nodo = rotarIzquierda(nodo);
                nodo.izquierdo = eliminar(nodo.izquierdo, clave);
            }
        }
        return nodo;
    }

    private NodoTreap rotarDerecha(NodoTreap y) {
        NodoTreap x = y.izquierdo;
        y.izquierdo = x.derecho;
        x.derecho = y;
        return x;
    }

    private NodoTreap rotarIzquierda(NodoTreap x) {
        NodoTreap y = x.derecho;
        x.derecho = y.izquierdo;
        y.izquierdo = x;
        return y;
    }

    public void imprimir() {
        imprimir(raiz, "", true);
    }

    private void imprimir(NodoTreap nodo, String prefijo, boolean esDerecho) {
        if (nodo != null) {
            System.out.println(prefijo + (esDerecho ? "└── " : "├── ") + nodo.clave + " (" + nodo.prioridad + ")");
            imprimir(nodo.izquierdo, prefijo + (esDerecho ? "    " : "│   "), false);
            imprimir(nodo.derecho, prefijo + (esDerecho ? "    " : "│   "), true);
        }
    }
}

public class Test_ArbolAleatorizado {
    public static void main(String[] args) {
        Treap treap = new Treap();
        treap.insertar(50);
        treap.insertar(30);
        treap.insertar(70);
        treap.insertar(20);
        treap.insertar(40);

        System.out.println("Árbol Treap después de inserciones:");
        treap.imprimir();

        treap.eliminar(30);
        System.out.println("Árbol Treap después de eliminar 30:");
        treap.imprimir();
    }
}
