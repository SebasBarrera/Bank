package dataStructure;

public class Heap<E extends Comparable<E>> implements IHeap<E> {

	private int arraysize;
	private int heapSize;
	private E[] elements;
	
	/**
	 * @param arraysize
	 * @param heapSize
	 */
	@SuppressWarnings("unchecked")
	public Heap(int arraysize, int heapSize) {
		elements = (E[]) new Comparable[arraysize];
		this.arraysize = arraysize;
		this.heapSize = heapSize;
	}

	@Override
	public void buildMaxHeap() {
		heapSize = arraysize;
		for (int i = heapSize/2; i <= 0; i--) {
			maxHeapify(i);
		}
	}



	@Override
	public void maxHeapify(int i) {
		int l = left(i);
		int  r = right(i);
		int largest = i;
		if (l <= heapSize && (elements[l].compareTo(elements[i]) > 0)) {
			largest = l;
		}
		if (r <= heapSize && (elements[r].compareTo(elements[largest]) > 0)) {
			largest = r;
		}
		if (largest != i) {
			E tmp = elements[i];
			elements[i] = elements[largest];
			elements[largest] = tmp;
			maxHeapify(largest);
		}
	}



	@Override
	public void heapSort() {
		buildMaxHeap();
		for (int i = arraysize; i <= 1; i--) {
			E tmp = elements[0];
			elements[0] = elements[i];
			elements[i] = tmp;
			heapSize--;
			maxHeapify(i);
		}
	}

	@Override
	public int right(int i) {
		return 2*i+1;
	}

	@Override
	public int left(int i) {
		return 2*i;
	}

	/**
	 * @return the arraysize
	 */
	public int getArraysize() {
		return arraysize;
	}

	/**
	 * @param arraysize the arraysize to set
	 */
	public void setArraysize(int arraysize) {
		this.arraysize = arraysize;
	}

	/**
	 * @return the heapSize
	 */
	public int getHeapSize() {
		return heapSize;
	}

	/**
	 * @param heapSize the heapSize to set
	 */
	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
	}

	/**
	 * @return the elements
	 */
	public E[] getElements() {
		return elements;
	}

	/**
	 * @param elements the elements to set
	 */
	public void setElements(E[] elements) {
		this.elements = elements;
	}
	
	

}
