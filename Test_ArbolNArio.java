import java.util.ArrayList;
import java.util.List;

class NodoNArio {
    String dato;
    List<NodoNArio> hijos;

    public NodoNArio(String dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }
}

class ArbolNArio {
    NodoNArio raiz;

    public ArbolNArio(String datoRaiz) {
        this.raiz = new NodoNArio(datoRaiz);
    }

    public void agregarHijo(NodoNArio nodoPadre, String datoHijo) {
        NodoNArio nuevoHijo = new NodoNArio(datoHijo);
        nodoPadre.hijos.add(nuevoHijo);
    }

    public void recorrerDFS(NodoNArio nodo) {
        if (nodo == null) return;
        System.out.println(nodo.dato);
        for (NodoNArio hijo : nodo.hijos) {
            recorrerDFS(hijo);
        }
    }
}

public class Test_ArbolNArio {
    public static void main(String[] args) {
        ArbolNArio arbol = new ArbolNArio("Raíz");
        arbol.agregarHijo(arbol.raiz, "Hijo1");
        arbol.agregarHijo(arbol.raiz, "Hijo2");
        arbol.agregarHijo(arbol.raiz.hijos.get(0), "Nieto1");

        System.out.println("Recorrido en profundidad (DFS):");
        arbol.recorrerDFS(arbol.raiz);
    }
}
