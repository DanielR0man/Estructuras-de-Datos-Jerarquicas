enum Color {
    ROJO, NEGRO;
}

class NodoRN {
    int clave;
    Color color;
    NodoRN izquierdo, derecho, padre;

    public NodoRN(int clave) {
        this.clave = clave;
        this.color = Color.ROJO; // Por defecto, los nodos nuevos son rojos
    }
}

class ArbolRojoNegro {
    private NodoRN raiz;
    private final NodoRN NULO = new NodoRN(0); // Nodo nulo para simplificar

    public ArbolRojoNegro() {
        NULO.color = Color.NEGRO;
        raiz = NULO;
    }

    public void insertar(int clave) {
        NodoRN nodo = new NodoRN(clave);
        nodo.izquierdo = NULO;
        nodo.derecho = NULO;

        NodoRN padre = null;
        NodoRN actual = raiz;

        while (actual != NULO) {
            padre = actual;
            if (nodo.clave < actual.clave) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }

        nodo.padre = padre;
        if (padre == null) {
            raiz = nodo; // Primer nodo como raíz
        } else if (nodo.clave < padre.clave) {
            padre.izquierdo = nodo;
        } else {
            padre.derecho = nodo;
        }

        nodo.color = Color.ROJO;
        balancearInsercion(nodo);
    }

    private void balancearInsercion(NodoRN nodo) {
        while (nodo.padre != null && nodo.padre.color == Color.ROJO) {
            if (nodo.padre == nodo.padre.padre.izquierdo) {
                NodoRN tio = nodo.padre.padre.derecho;
                if (tio.color == Color.ROJO) {
                    nodo.padre.color = Color.NEGRO;
                    tio.color = Color.NEGRO;
                    nodo.padre.padre.color = Color.ROJO;
                    nodo = nodo.padre.padre;
                } else {
                    if (nodo == nodo.padre.derecho) {
                        nodo = nodo.padre;
                        rotarIzquierda(nodo);
                    }
                    nodo.padre.color = Color.NEGRO;
                    nodo.padre.padre.color = Color.ROJO;
                    rotarDerecha(nodo.padre.padre);
                }
            } else {
                NodoRN tio = nodo.padre.padre.izquierdo;
                if (tio.color == Color.ROJO) {
                    nodo.padre.color = Color.NEGRO;
                    tio.color = Color.NEGRO;
                    nodo.padre.padre.color = Color.ROJO;
                    nodo = nodo.padre.padre;
                } else {
                    if (nodo == nodo.padre.izquierdo) {
                        nodo = nodo.padre;
                        rotarDerecha(nodo);
                    }
                    nodo.padre.color = Color.NEGRO;
                    nodo.padre.padre.color = Color.ROJO;
                    rotarIzquierda(nodo.padre.padre);
                }
            }
        }
        raiz.color = Color.NEGRO;
    }

    private void rotarIzquierda(NodoRN nodo) {
        NodoRN temp = nodo.derecho;
        nodo.derecho = temp.izquierdo;
        if (temp.izquierdo != NULO) {
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

    private void rotarDerecha(NodoRN nodo) {
        NodoRN temp = nodo.izquierdo;
        nodo.izquierdo = temp.derecho;
        if (temp.derecho != NULO) {
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

    private void imprimir(NodoRN nodo, String prefijo, boolean esDerecho) {
        if (nodo != NULO) {
            System.out.println(prefijo + (esDerecho ? "└── " : "├── ") + nodo.clave + " (" + nodo.color + ")");
            imprimir(nodo.izquierdo, prefijo + (esDerecho ? "    " : "│   "), false);
            imprimir(nodo.derecho, prefijo + (esDerecho ? "    " : "│   "), true);
        }
    }
}

public class Test_ArbolRojoNegro {
    public static void main(String[] args) {
        ArbolRojoNegro arbol = new ArbolRojoNegro();
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(30);
        arbol.insertar(15);
        arbol.insertar(25);
        arbol.insertar(5);

        System.out.println("Árbol Rojo-Negro:");
        arbol.imprimir();
    }
}
