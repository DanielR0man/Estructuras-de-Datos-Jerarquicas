import java.util.*;

class NodoFibonacci {
    int valor;
    NodoFibonacci padre;
    NodoFibonacci hijo;
    NodoFibonacci siguiente;
    NodoFibonacci anterior;
    boolean marcado;
    int grado;

    public NodoFibonacci(int valor) {
        this.valor = valor;
        siguiente = this;
        anterior = this;
        marcado = false;
    }
}

class HeapFibonacci {
    private NodoFibonacci minimo;
    public HeapFibonacci() {
        minimo = null;
    }

    public void insertar(int valor) {
        NodoFibonacci nuevo = new NodoFibonacci(valor);
        if (minimo == null) {
            minimo = nuevo;
        } else {
            insertarNodoEnLista(minimo, nuevo);
            if (nuevo.valor < minimo.valor) {
                minimo = nuevo;
            }
        }
    }

    private void insertarNodoEnLista(NodoFibonacci referencia, NodoFibonacci nodo) {
        nodo.siguiente = referencia.siguiente;
        referencia.siguiente.anterior = nodo;
        nodo.anterior = referencia;
        referencia.siguiente = nodo;
    }

    public int extraerMin() {
        if (minimo == null) throw new IllegalStateException("Heap vacío");

        NodoFibonacci z = minimo;
        if (z != null) {
            if (z.hijo != null) {
                NodoFibonacci hijoActual = z.hijo;
                do {
                    NodoFibonacci siguienteHijo = hijoActual.siguiente;
                    insertarNodoEnLista(minimo, hijoActual);
                    hijoActual.padre = null;
                    hijoActual = siguienteHijo;
                } while (hijoActual != z.hijo);
            }
            eliminarNodoDeLista(z);
            if (z == z.siguiente) {
                minimo = null;
            } else {
                minimo = z.siguiente;
                consolidar();
            }
        }
        return z.valor;
    }

    private void eliminarNodoDeLista(NodoFibonacci nodo) {
        nodo.anterior.siguiente = nodo.siguiente;
        nodo.siguiente.anterior = nodo.anterior;
    }

    private void consolidar() {
        Map<Integer, NodoFibonacci> tablaGrados = new HashMap<>();
        List<NodoFibonacci> nodosRaiz = obtenerNodosRaiz();
        for (NodoFibonacci w : nodosRaiz) {
            NodoFibonacci x = w;
            int grado = x.grado;
            while (tablaGrados.containsKey(grado)) {
                NodoFibonacci y = tablaGrados.get(grado);
                if (x.valor > y.valor) {
                    NodoFibonacci temp = x;
                    x = y;
                    y = temp;
                }
                enlazar(y, x);
                tablaGrados.remove(grado);
                grado++;
            }
            tablaGrados.put(grado, x);
        }
        minimo = null;
        for (NodoFibonacci x : tablaGrados.values()) {
            if (minimo == null || x.valor < minimo.valor) {
                minimo = x;
            }
        }
    }

    private void enlazar(NodoFibonacci y, NodoFibonacci x) {
        eliminarNodoDeLista(y);
        if (x.hijo == null) {
            x.hijo = y;
            y.siguiente = y.anterior = y;
        } else {
            insertarNodoEnLista(x.hijo, y);
        }
        y.padre = x;
        x.grado++;
        y.marcado = false;
    }

    private List<NodoFibonacci> obtenerNodosRaiz() {
        List<NodoFibonacci> lista = new ArrayList<>();
        if (minimo != null) {
            NodoFibonacci actual = minimo;
            do {
                lista.add(actual);
                actual = actual.siguiente;
            } while (actual != minimo);
        }
        return lista;
    }
}

public class Test_HeapFibonacci {
    public static void main(String[] args) {
        HeapFibonacci heap = new HeapFibonacci();
        heap.insertar(10);
        heap.insertar(5);
        heap.insertar(20);
        heap.insertar(3);

        System.out.println("Mínimo extraído: " + heap.extraerMin());
    }
}
