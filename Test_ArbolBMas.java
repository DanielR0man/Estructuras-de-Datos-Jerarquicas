import java.util.Arrays;

class NodoBPlus {
    int[] claves;
    NodoBPlus[] hijos;
    boolean hoja;
    int numClaves;

    public NodoBPlus(int grado, boolean hoja) {
        this.claves = new int[grado - 1];
        this.hijos = new NodoBPlus[grado];
        this.hoja = hoja;
        this.numClaves = 0;
    }
}

class ArbolBPlus {
    private NodoBPlus raiz;
    private int grado;

    public ArbolBPlus(int grado) {
        this.grado = grado;
        this.raiz = new NodoBPlus(grado, true);
    }

    public void insertar(int clave) {
        NodoBPlus nodo = insertar(raiz, clave);
        if (nodo != null) {
            NodoBPlus nuevaRaiz = new NodoBPlus(grado, false);
            nuevaRaiz.claves[0] = nodo.claves[0];
            nuevaRaiz.hijos[0] = raiz;
            nuevaRaiz.hijos[1] = nodo;
            raiz = nuevaRaiz;
        }
    }

    private NodoBPlus insertar(NodoBPlus nodo, int clave) {
        if (nodo.hoja) {
            if (nodo.numClaves < grado - 1) {
                nodo.claves[nodo.numClaves++] = clave;
                return null;
            } else {
                // División del nodo
                int[] temp = new int[grado];
                System.arraycopy(nodo.claves, 0, temp, 0, grado - 1);
                temp[grado - 1] = clave;
                Arrays.sort(temp);

                NodoBPlus nuevoNodo = new NodoBPlus(grado, true);
                int mid = grado / 2;
                nodo.numClaves = mid;
                nuevoNodo.numClaves = grado - 1 - mid;
                System.arraycopy(temp, mid, nodo.claves, 0, mid);
                System.arraycopy(temp, mid + 1, nuevoNodo.claves, 0, nuevoNodo.numClaves);

                return nuevoNodo;
            }
        } else {
            int i = 0;
            while (i < nodo.numClaves && clave > nodo.claves[i]) {
                i++;
            }
            NodoBPlus hijo = insertar(nodo.hijos[i], clave);
            if (hijo != null) {
                // Manejo de la subida de la clave
                if (nodo.numClaves < grado - 1) {
                    for (int j = nodo.numClaves; j > i; j--) {
                        nodo.claves[j] = nodo.claves[j - 1];
                        nodo.hijos[j + 1] = nodo.hijos[j];
                    }
                    nodo.claves[i] = hijo.claves[0];
                    nodo.hijos[i + 1] = hijo;
                    nodo.numClaves++;
                    return null;
                } else {
                    // División de nodos internos
                    return dividirNodo(nodo, i, hijo);
                }
            }
        }
        return null;
    }

    private NodoBPlus dividirNodo(NodoBPlus nodo, int i, NodoBPlus hijo) {
        // Lógica para dividir un nodo y manejar las claves ascendentes
        return null;
    }

    public void imprimir() {
        imprimir(raiz, 0);
    }

    private void imprimir(NodoBPlus nodo, int nivel) {
        if (nodo == null) return;
        System.out.println("Nivel " + nivel + ": " + Arrays.toString(Arrays.copyOfRange(nodo.claves, 0, nodo.numClaves)));
        if (!nodo.hoja) {
            for (int i = 0; i <= nodo.numClaves; i++) {
                imprimir(nodo.hijos[i], nivel + 1);
            }
        }
    }
}

public class Test_ArbolBMas {
    public static void main(String[] args) {
        ArbolBPlus arbol = new ArbolBPlus(3); // Grado 3
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.insertar(6);
        arbol.insertar(30);
        arbol.imprimir();
    }
}
