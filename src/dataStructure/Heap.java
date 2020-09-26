package dataStructure;

import customExceptions.BiggerKeyException;
import customExceptions.HeapUnderFlowException;
import customExceptions.SmallerKeyException;

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
		int counter;
		for (int i = arraysize-1; i >= 1; i--) {
			swap(0, i);
			heapSize--;
			counter = 0;
			maxHeapify(1, counter);
		}
	}
	
	@Override
	public void buildMaxHeap() {
		heapSize = arraysize;
		int counter;
		for (int i = heapSize/2; i >= 1; i--) {
			counter = 0;
			maxHeapify(i, counter);
		}
	}

	@Override
	public void maxHeapify(int i, int counter) {
		int l;
		int r;
		if (counter == 0) {
			l = left(i);
			r = right(i);
			i--;
		} else {
			l = 2 * i + 1;
			r = 2 * i + 1 + 1;
		}
		counter++;
		
		int largest = i;
		if (exist(l) && l <= heapSize & (elements[l].compareTo(elements[i]) > 0)) {
			largest = l;
		}
		if (exist(r) && r <= heapSize && (elements[r].compareTo(elements[largest]) > 0)) {
			largest = r;
		}
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest, counter);
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
	
	@Override
	public boolean exist(int i) {
		boolean leaf = false;
		if (i < heapSize) {
			leaf = true;
		}
		return leaf;
	}
	
	@Override
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
	
	@Override
	public int parent(int i) {
		return (int) Math.floor(2/i) -1;
	}	

	/**
	 * @return the array Size
	 */
	public int getArraysize() {
		return arraysize;
	}

	/**
	 * @param arraysize the array Size to set
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
	
	@Override
	public E extractMaxheap() throws HeapUnderFlowException {
		E max = null;
		if(heapSize < 1) {
			throw new HeapUnderFlowException(heapSize);
		}else {
			max = elements[0];
			elements[0] = elements[heapSize];
			int counter = 0;
			maxHeapify(0, counter);
		}
		return max;
	}

	@Override
	public void increaseKey(int i, int key) throws SmallerKeyException {
		if (key < keys[i]) {
			throw new SmallerKeyException(key);
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
	
	@Override
	public void decreaseKey(int i, int key) throws BiggerKeyException {
		if(key > keys[i]) {
			throw new BiggerKeyException(key);
		}
		keys[i] = key;
		int counter = 0;
		maxHeapify(i, counter);
	}
	
	@Override
	public void priorityInsert(int key) throws SmallerKeyException {
		heapSize++;
		if(heapSize > arraysize) {
			reSize();
		}
		keys[heapSize] = Integer.MIN_VALUE;
		increaseKey(heapSize, key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void reSize() {
		int newArraySize = arraysize * 2;
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
