package dataStructure;

public class Heap<E extends Comparable<E>> implements IHeap<E>, IPriorQueue<E> {

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
	public void heapSort() {
		buildMaxHeap();
		for (int i = arraysize-1; i >= 1; i--) {
			swap(0, i);
			heapSize--;
			maxHeapify(1);
		}
	}
	
	@Override
	public void buildMaxHeap() {
		heapSize = arraysize;
		for (int i = heapSize/2; i >= 1; i--) {
			maxHeapify(i);
			
		}
	}

	@Override
	public void maxHeapify(int i) {
		int l = left(i);
		int  r = right(i);
		i--;
		int largest = i;
		if (exist(l)) {
			if (l <= heapSize & (elements[l].compareTo(elements[i]) > 0)) {
				largest = l;
			}
		}
		if (exist(r)) {
			if (r <= heapSize && (elements[r].compareTo(elements[largest]) > 0)) {
				largest = r;
			}
		}
		if (largest != i) {
			swap(i, largest);
			maxHeapify2(largest);
		}
	}
	
	
	public void maxHeapify2(int i) {
		int l = left2(i);
		int  r = right2(i);
		int largest = i;
		if (exist(l)) {
			if (l <= heapSize && (elements[l].compareTo(elements[i]) > 0)) {
				largest = l;
			}
		}
		if (exist(r)) {
			if (r <= heapSize && (elements[r].compareTo(elements[largest]) > 0)) {
				largest = r;
			}
		}
		if (largest != i) {
			swap(i, largest);
			maxHeapify2(largest);
		}
	}
	
	public void swap(int i, int j) {
		E tmp = elements[i];
		int tmpk = keys[i];
		elements[i] = elements[j];
		keys[i] = keys[j];
		elements[j] = tmp;
		keys[j] = tmpk;
	}
	
	public boolean exist(int i) {
		boolean leaf = false;
		if (i < heapSize) {
			leaf = true;
		}
		return leaf;
	}
	
	public boolean isLeaf(int i) {
		boolean leaf = false;
		if (i < heapSize) {
			leaf = true;
		}
		return leaf;
	}

	@Override
	public int right(int i) {
		return (int) Math.floor(2*i+1) -1;
	}

	@Override
	public int left(int i) {
		return (int) Math.floor(2*i) -1;
	}
	
	public int left2(int i) {
		return 2*i+1;
	}
	
	public int right2(int i) {
		return 2*i + 1+1;
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

}
