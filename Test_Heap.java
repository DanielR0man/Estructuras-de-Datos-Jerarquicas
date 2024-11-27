import java.util.ArrayList;

class MinHeap {
    private ArrayList<Integer> heap;

    public MinHeap() {
        heap = new ArrayList<>();
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    public void insertar(int valor) {
        heap.add(valor);
        int index = heap.size() - 1;

        while (index != 0 && heap.get(parent(index)) > heap.get(index)) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    public int extraerMin() {
        if (heap.size() == 0) throw new IllegalStateException("Heap vacío");
        if (heap.size() == 1) return heap.remove(0);

        int min = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        heapify(0);
        return min;
    }

    private void heapify(int i) {
        int menor = i;
        int izquierda = leftChild(i);
        int derecha = rightChild(i);

        if (izquierda < heap.size() && heap.get(izquierda) < heap.get(menor)) {
            menor = izquierda;
        }

        if (derecha < heap.size() && heap.get(derecha) < heap.get(menor)) {
            menor = derecha;
        }

        if (menor != i) {
            swap(i, menor);
            heapify(menor);
        }
    }

    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void imprimirHeap() {
        System.out.println(heap);
    }
}

public class Test_Heap {
    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        heap.insertar(10);
        heap.insertar(20);
        heap.insertar(5);
        heap.insertar(6);
        heap.insertar(2);

        System.out.println("Heap después de inserciones:");
        heap.imprimirHeap();

        System.out.println("Elemento mínimo extraído: " + heap.extraerMin());
        System.out.println("Heap después de extracción:");
        heap.imprimirHeap();
    }
}
