/**
 * A generic ArrayList implementation that dynamically manages a collection of elements.
 * This class provides methods to manipulate the size and elements of the list, including
 * adding, removing, and accessing elements.
 *
 * @param <T> The type of elements in this ArrayList.
 */
public class ArrayList<T> {
    private int size; // The number of elements currently in the list
    private T[] items; // Array to store the elements of the list

	/**
     * Constructs an ArrayList with a specified initial capacity.
     * @param capacity The initial capacity of the ArrayList.
     */
	public ArrayList(int capacity) {
		size = 0;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity];
		items = temp;
	}

	/**
     * Constructs an ArrayList by copying another ArrayList.
     * @param other The ArrayList to copy.
     */
	public ArrayList(ArrayList<T> other) {
		this.size = other.size;
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[other.items.length];
		this.items = temp;
		for (int i = 0; i < other.size; i++) {
			this.items[i] = (T) other.items[i].clone();
		}
	}

	 /**
     * Shifts all elements from the specified index one position to the right.
     * @param index The starting index to shift right.
     * Time Complexity: O(n), where n is the number of elements to shift.
     */
	private void shiftRight(int index) {
		for (int i = size; i > index; i--) {
			items[i] = items[i - 1];
		}
	}

	/**
     * Shifts all elements from the specified index one position to the left.
     * @param index The starting index to shift left from.
     * Time Complexity: O(n), where n is the number of elements to shift.
     */
	private void shiftLeft(int index) {
		for (int i = index + 1; i < size; i++) {
			items[i - 1] = items[i];
		}
	}

	/**
     * Returns the number of elements in this ArrayList.
     * @return The number of elements in the ArrayList.
     * Time Complexity: O(1).
     */
	public int size() {
		return size;
	}

	 /**
     * Retrieves the element at the specified index.
     * @param index The index of the element to retrieve.
     * @return The element at the specified index, or null if the index is out of bounds.
     * Time Complexity: O(1).
     */
	public T get(int index) {
		if (index >= size) {
			return null;
		}
		return items[index];
	}

	/**
     * Checks if the ArrayList contains a specific element.
     * @param value The element to search for.
     * @return true if the element is found, false otherwise.
     * Time Complexity: O(n), where n is the number of elements in the list.
     */
	public boolean contains(T value) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
     * Inserts an element at the specified index.
     * @param index The index where the element should be inserted.
     * @param value The element to insert.
     * @return true if the insertion is successful, false if the index is out of bounds.
     * Time Complexity: O(n), where n is the number of elements to shift.
     */
	public boolean insertAt(int index, T value) {
		if (index > size) {
			return false;
		}
		shiftRight(index);
		items[index] = value;
		size++;
		return true;
	}

	/**
     * Inserts an element at the end of the ArrayList.
     * @param value The element to add.
     * @return true if the element is added successfully.
     * Time Complexity: O(1).
     */
	public boolean insert(T value) {
		return insertAt(size, value);
	}

	/**
     * Removes the element at the specified index.
     * @param index The index of the element to remove.
     * @return The element that was removed, or null if the index is out of bounds.
     * Time Complexity: O(n), where n is the number of elements to shift.
     */
	public boolean removeAt(int index) {
		if (index >= size) {
			return false;
		}
		shiftLeft(index);
		size--;
		return true;
	}

	/**
     * Removes the first occurrence of the specified element.
     * @param value The element to remove.
     * @return true if the element was removed, false if it was not found.
     * Time Complexity: O(n), where n is the number of elements to shift.
     */
	public boolean remove(T value) {
		for (int i = 0; i < size; i++) {
			if (items[i].equals(value)) {
				return removeAt(i);
			}
		}
		return false;
	}
}