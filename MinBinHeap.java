package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	//be null. This is ok! Just build out 
	//from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
		//of child/parent computations...
		//the book/animation page both do this.
	}

	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		int i = size + 1;
		array[i] = entry;
		while(hasParent(i)) {
			if(array[i].priority <  parentPair(i).priority) {
				swap(i, parentIndex(i));
				i = parentIndex(i);
			}
			else {
				break;
			}
		}

		size += 1;

	}

	@Override
	public void delMin() {
		if(size == 0) {
			return;
		}
		else if(size == 1) {
			array[1] = null;
			size -= 1;

		}
		else {
			swap(1, size);
			array[size] = null;
			size -= 1;
			fixOrder(1);
		}


	}

	@Override
	public EntryPair getMin() {
		// TODO Auto-generated method stub
		return array[1];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		size = entries.length;
		for(int i = 0; i < entries.length; i++) {
			array[i + 1] = entries[i];
			
		}
		for(int i = size/2; i > 0; i--) {
			fixOrder(i);
		}

	}
	private void fixOrder(int i) {
		int smallest = i;
		while(hasLeftChild(i) || hasRightChild(i)) {
			if(hasLeftChild(i) && array[smallest].priority > leftChild(i).priority ) {
				smallest = leftChildIndex(i);	
			}
			if(hasRightChild(i) && array[smallest].priority > rightChild(i).priority) {
				smallest = rightChildIndex(i);
			}
			if(i == smallest) {
				break;
			}

			swap(i, smallest);
			i = smallest;
		}
	}

	private void swap(int index1, int index2) {
		EntryPair temp = null;
		temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	private int parentIndex(int i) {
		return i/2;
	}

	private int leftChildIndex(int i) {
		return 2*i;
	}

	private int rightChildIndex(int i) {
		return 2*i + 1;
	}

	private EntryPair parentPair(int i) {
		return array[parentIndex(i)];
	}

	private EntryPair leftChild(int i) {
		return array[leftChildIndex(i)];
	}

	private EntryPair rightChild(int i) {
		return array[rightChildIndex(i)];
	}

	private boolean hasParent(int i) {
		if(i != 1) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean hasLeftChild(int i) {
		if(leftChildIndex(i) <= size && array[leftChildIndex(i)] != null ) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean hasRightChild(int i) {
		if(rightChildIndex(i) <= size && array[rightChildIndex(i)] != null) {
			return true;
		}
		else {
			return false;
		}
	}
}