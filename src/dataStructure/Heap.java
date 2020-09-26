package dataStructure;

public class Heap<E extends Comparable<E>> implements IHeap<E>, IPriorQueue<E>{

	private int arraysize;
	private int heapSize;
	private E[] elements;
	private int[] keys;
	
	/**
	 * @param arraysize
	 * @param heapSize
	 */
	@SuppressWarnings("unchecked")
	public Heap(int arraysize, int heapSize) {
		elements = (E[]) new Comparable[arraysize];
		keys = new int[arraysize];
		this.arraysize = arraysize;
		this.heapSize = heapSize;		
	}
	
	@Override
	public void buildMaxHeap() {
		heapSize = arraysize;
		for (int i = (heapSize/*-1*/)/2; i >= 1; i--) {
			maxHeapify(i/*-1*/);
		}
	}

	@Override
	public void maxHeapify(int i) {
		
		int l = left(i);
		int  r = right(i);
		i--;
		int largest = i;
		
		if (!isLeaf(l) && !isLeaf(r)) {
			if (l <= heapSize & (elements[l].compareTo(elements[i]) > 0)) {
				largest = l;
			}
			if (r <= heapSize && (elements[r/*-1*/].compareTo(elements[largest]) > 0)) {
				largest = r;
			}
		}
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest);
		}
		
	}
	
	public boolean isLeaf(int i) {
		boolean leaf = false;
		if (i < heapSize) {
			leaf = true;
		}
		return leaf;
	}

	@Override
	public void heapSort() {
		buildMaxHeap();
		for (int i = arraysize-1; i >= 0; i--) {
			swap(0, i);
			heapSize--;
			maxHeapify(i);
		}
	}

	@Override
	public int right(int i) {
		return (int) Math.floor(2*i+1) -1;
	}

	@Override
	public int left(int i) {
		return (int) Math.floor(2*i) -1;
	}
	@Override
	public int parent(int i) {
		return (int) Math.floor(2/i) -1;
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
	public void setElements(E[] n) {
		elements = n;
	}
	
	public void setKeys(int[] keys) {
		this.keys = keys;
	}
	
	//Implementacion de la priority queue
	
	
	@Override
	public E extractMaxheap() {
		E max = null;
		if(heapSize < 1) {
			//exception
		}else {
			max = elements[0];
			elements[0] = elements[heapSize];
			maxHeapify(0);
		}
		return max;
	}

	@Override
	public void increaseKey(int i, int key) {
		if (key < keys[i]) {
			// exception
		}
		keys[i] = key;
		while (i > 0 && (elements[parent(i)].compareTo(elements[i]) < 0)) {
			E tmp = elements[i];
			elements[i] = elements[parent(i)];
			elements[parent(i)] = tmp;
			i = parent(i);
			swap(i, parent(i));
		}
	}
	
	public void decreaseKey(int i, int key) {
		if(key > keys[i]) {
			//exception
		}
		keys[i] = key;
		maxHeapify(i);
	}
	//agregar tipo E
	@Override
	public void priorityInsert(int key) {

		heapSize++;
		if(heapSize > arraysize) {
			reSize();
		}
		
		keys[heapSize] = Integer.MIN_VALUE;
		
		increaseKey(heapSize, key);
	}
	
	@SuppressWarnings("unchecked")
	public void reSize() {
		int newArraySize = arraysize*2;
		E[] tmpE = (E[]) new Comparable[newArraySize];
		int[] tmpKeys = new int[newArraySize];
		for(int i = 0; i < arraysize; i++) {
			tmpE[i] = elements[i];
			tmpKeys[i] = keys[i];
		}
		setElements(tmpE);
		setKeys(tmpKeys);
		arraysize = newArraySize;
	}
	
	public void swap(int i, int j) {
		E tmp = elements[i];
		int tmpk = keys[i];
		elements[i] = elements[j];
		keys[i] = keys[j];
		elements[j] = tmp;
		keys[j] = tmpk;
	}

}
