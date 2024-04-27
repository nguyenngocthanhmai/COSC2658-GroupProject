public class ArrayList<T> {
	private int size;
	private int pointer;
	private T[] items;

	public ArrayList(int capacity) {
		size = 0;
		pointer = 0;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		items = temp;
	}

	public ArrayList(ArrayList<T> other) {
		this.size = other.size;
		this.pointer = 0; // Reset pointer for new list
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[other.items.length];
		this.items = temp;
		for (int i = 0; i < other.size; i++) {
			// Assuming T has a method clone() which returns a deep copy of the object.
			// If T does not support cloning, you might need to manually copy properties or use a copy constructor.
			this.items[i] = (T) other.items[i].clone();
		}
	}

	// shift all elements from index one position to the right
	private void shiftRight(int index) {
		for (int i = size; i > index; i--) {
			items[i] = items[i - 1];
		}
	}

	// shift all elements from the end one position to the left
	// until index
	private void shiftLeft(int index) {
		for (int i = index + 1; i < size; i++) {
			items[i - 1] = items[i];
		}
	}

	public int size() {
		return size;
	}

	public void reset() {
		pointer = 0;
	}

	public T get(int index) {
		if (index >= size) {
			return null;
		}
		return items[index];
	}

	public boolean hasNext() {
		return (pointer < size);
	}

	public T next() {
		pointer++;
		return items[pointer - 1];
	}

	public boolean contains(T value) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

	public boolean insertAt(int index, T value) {
		if (index > size) {
			return false;
		}
		shiftRight(index);
		items[index] = value;
		size++;
		return true;
	}

	public boolean insert(T value) {
		return insertAt(size, value);
	}

	public boolean insertBefore(T searchValue, T value) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(searchValue)) {
				return insertAt(i, value);
			}
		}
		return false;
	}

	public boolean insertAfter(T searchValue, T value) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(searchValue)) {
				return insertAt(i + 1, value);
			}
		}
		return false;
	}

	public boolean removeAt(int index) {
		if (index >= size) {
			return false;
		}
		shiftLeft(index);
		size--;
		return true;
	}

	public boolean remove(T value) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(value)) {
				return removeAt(i);
			}
		}
		return false;
	}

	public void addAll(ArrayList<T> list) {
		for (int i = 0; i < list.size(); i++) {
			insertAt(size, list.get(i));
		}
	}
}