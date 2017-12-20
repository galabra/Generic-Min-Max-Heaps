import java.util.ArrayList;

public class GenericMaxHeap<T extends Comparable<T>> {

	/**
	 * Describes a Maximum Heap of general elements of general type T.
	 * This data structure allows to pop the maximal value at every given moment.
	 * 
	 * This implementation of Maximum Heap uses ArrayList as the core of the
	 * data base; and it allows (by public methods) to:
	 * 		-	{@link #insert(T)}	Inserts a new element to the heap, while
	 * 								keeping the heap's order.
	 * 
	 * 		-	{@link #remove()}	Removes and returns the maxinmal element
	 * 								that was inserted to the heap.
	 * 
	 * 		-	{@link #print()}	Simply prints the heap, mainly for
	 * 								testing purposes.
	 * 
	 * The private methods are also pretty self-explanatory.
	 * 
	 * @param <T>	Repesents the type of elements in the heap, as mentioned above.
	 * 				Please note that <T> must implement the {@link Comparable}
	 * 				interface.
	 */
    private ArrayList<T> Heap = new ArrayList<T>();
    private static final int FRONT = 1;
    
    /**
     * T maximalElement is null only for the sake of generalization.
     * For example, for an <Integer> heap, the maximal element
	 * would be "Integer.MAX_VALUE";
     */
    private final T maximalElement = null;
    
    // Constructor
    public GenericMaxHeap() {
        Heap.add(maximalElement);
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    private boolean isLeaf(int pos) {
        if (pos >=  ((Heap.size()-1) / 2)  &&  pos <= (Heap.size()-1)) {
            return true;
        }
        return false;
    }
    
    /**
     * Swaps two elements by their given positions in the {@link #Heap} <ArrayList>.
     * @param fpos - the first element's position
     * @param spos - the second element's position
     */
    private void swap(int fpos, int spos) {
        T tmp;
        tmp = Heap.get(fpos);
        Heap.set(fpos, Heap.get(spos));
        Heap.set(spos, tmp);
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) { 
            if ( Heap.get(pos).compareTo(Heap.get(leftChild(pos))) < 0  || Heap.get(pos).compareTo(Heap.get(rightChild(pos))) < 0 ) {
                if ((Heap.get(leftChild(pos)).compareTo(Heap.get(rightChild(pos)))) > 0) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    } 

    /**
     * Inserts a new element to the heap.
     * 
     * @param element - New element to insert
     * @return whether 'element' is the new head of the heap (hence, the maximal element)
     */
    public boolean insert(T element) {
    	Heap.add(element);
        int current = Heap.size()-1;
        while((Heap.get(current).compareTo(Heap.get(parent(current)))) > 0) {
            swap(current,parent(current));
            current = parent(current);
        }
        
        return (element == Heap.get(FRONT));
    }

    /**
     * Prints the heap as a tree
     */
    public void print() {
        for (int i = 1; i <= (Heap.size()-1) / 2; i++ ) {
            System.out.print(" PARENT : " + Heap.get(i) + " LEFT CHILD : " + Heap.get(2*i));
            if(Heap.size() > 2*i + 1) {
            	System.out.print(" RIGHT CHILD :" + Heap.get(2 * i + 1));
            }
            System.out.println();
        }
    }
    
    /**
     * Removes the head of the heap
     * @return the heap's head that was removed
     */
    public T remove() {
        T popped = Heap.get(FRONT);
        Heap.set(FRONT, Heap.get(Heap.size()-1));
        maxHeapify(FRONT);
        return popped;
    }
    
}