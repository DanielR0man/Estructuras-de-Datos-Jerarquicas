class NodoSplay {
    int clave;
    NodoSplay izquierdo, derecho, padre;

    public NodoSplay(int clave) {
        this.clave = clave;
        this.izquierdo = this.derecho = this.padre = null;
    }
}

class ArbolSplay {
    private NodoSplay raiz;

    public NodoSplay buscar(int clave) {
        NodoSplay nodo = buscarNodo(raiz, clave);
        if (nodo != null) {
            splay(nodo);
        }
        return nodo;
    }

    private NodoSplay buscarNodo(NodoSplay nodo, int clave) {
        if (nodo == null || nodo.clave == clave) {
            return nodo;
        }
        if (clave < nodo.clave) {
            return buscarNodo(nodo.izquierdo, clave);
        } else {
            return buscarNodo(nodo.derecho, clave);
        }
    }

    public void insertar(int clave) {
        NodoSplay nuevo = new NodoSplay(clave);
        if (raiz == null) {
            raiz = nuevo;
        } else {
            NodoSplay actual = raiz, padre = null;
            while (actual != null) {
                padre = actual;
                if (clave < actual.clave) {
                    actual = actual.izquierdo;
                } else {
                    actual = actual.derecho;
                }
            }
            nuevo.padre = padre;
            if (clave < padre.clave) {
                padre.izquierdo = nuevo;
            } else {
                padre.derecho = nuevo;
            }
            splay(nuevo);
        }
    }

    private void splay(NodoSplay nodo) {
        while (nodo.padre != null) {
            if (nodo.padre.padre == null) { // Zig
                if (nodo == nodo.padre.izquierdo) {
                    rotarDerecha(nodo.padre);
                } else {
                    rotarIzquierda(nodo.padre);
                }
            } else if (nodo == nodo.padre.izquierdo && nodo.padre == nodo.padre.padre.izquierdo) { // Zig-Zig
                rotarDerecha(nodo.padre.padre);
                rotarDerecha(nodo.padre);
            } else if (nodo == nodo.padre.derecho && nodo.padre == nodo.padre.padre.derecho) { // Zig-Zig
                rotarIzquierda(nodo.padre.padre);
                rotarIzquierda(nodo.padre);
            } else { // Zig-Zag
                if (nodo == nodo.padre.izquierdo) {
                    rotarDerecha(nodo.padre);
                    rotarIzquierda(nodo.padre);
                } else {
                    rotarIzquierda(nodo.padre);
                    rotarDerecha(nodo.padre);
                }
            }
        }
        raiz = nodo;
    }

    private void rotarIzquierda(NodoSplay nodo) {
        NodoSplay temp = nodo.derecho;
        nodo.derecho = temp.izquierdo;
        if (temp.izquierdo != null) {
            temp.izquierdo.padre = nodo;
        }
        temp.padre = nodo.padre;
        if (nodo.padre == null) {
            raiz = temp;
        } else if (nodo == nodo.padre.izquierdo) {
            nodo.padre.izquierdo = temp;
        } else {
            nodo.padre.derecho = temp;
        }
        temp.izquierdo = nodo;
        nodo.padre = temp;
    }

    private void rotarDerecha(NodoSplay nodo) {
        NodoSplay temp = nodo.izquierdo;
        nodo.izquierdo = temp.derecho;
        if (temp.derecho != null) {
            temp.derecho.padre = nodo;
        }
        temp.padre = nodo.padre;
        if (nodo.padre == null) {
            raiz = temp;
        } else if (nodo == nodo.padre.derecho) {
            nodo.padre.derecho = temp;
        } else {
            nodo.padre.izquierdo = temp;
        }
        temp.derecho = nodo;
        nodo.padre = temp;
    }

    public void imprimir() {
        imprimir(raiz, "", true);
    }

    private void imprimir(NodoSplay nodo, String prefijo, boolean esDerecho) {
        if (nodo != null) {
            System.out.println(prefijo + (esDerecho ? "└── " : "├── ") + nodo.clave);
            imprimir(nodo.izquierdo, prefijo + (esDerecho ? "    " : "│   "), false);
            imprimir(nodo.derecho, prefijo + (esDerecho ? "    " : "│   "), true);
        }
    }
}

public class Test_ArbolSplay {
    public static void main(String[] args) {
        ArbolSplay arbol = new ArbolSplay();
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.buscar(20);

        System.out.println("Árbol Splay después de buscar:");
        arbol.imprimir();
    }
}
