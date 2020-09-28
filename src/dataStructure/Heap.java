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
		if (exist(l) && l <= heapSize && (elements[l].compareTo(elements[i]) > 0)) {
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
	public int right(int i) {
		return (int) Math.floor(2*i+1) -1;
	}

	@Override
	public int left(int i) {
		return (int) Math.floor(2*i) -1;
	}
	
	@Override
	public int parent(int i) {
		return (int) Math.floor(i/2) -1;
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
		setArraysize(n.length);
		setHeapSize(n.length);
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
			elements[0] = elements[heapSize-1];
			int counter = 1;
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
		if (i != 0) {
			while (parent(i) >= 0 && (keys[parent(i)] < keys[i])) {
				swap(i, parent(i));
				i = parent(i);
			}
		}		
	}
	
	/**
	 * @return the keys
	 */
	public int[] getKeys() {
		return keys;
	}
	
	@Override
	public void decreaseKey(int i, int key) throws BiggerKeyException {
		if(key > keys[i]) {
			throw new BiggerKeyException(key);
		}
		keys[i] = key;
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		int largest = i;
		if (exist(l) && l <= heapSize & (keys[l] > keys[i])) {
			largest = l;
		}
		if (exist(r) && r <= heapSize && (keys[r] > keys[largest])) {
			largest = r;
		}
		if (largest != i) {
			swap(i, largest);
			maxHeapify(largest, 1);
		}
	}
	
	@Override
	public void priorityInsert(int key, E element) throws SmallerKeyException {
		heapSize++;
		if(heapSize > arraysize) {
			reSize();
		}
		elements[heapSize-1] = element;
		keys[heapSize-1] = Integer.MIN_VALUE;
		increaseKey(heapSize-1, key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void reSize() {
		int newArraySize;
		if (arraysize == 0) {
			newArraySize = 1;
		} else {
			newArraySize = arraysize * 2;
		}
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
