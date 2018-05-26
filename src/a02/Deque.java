package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node head;
	private Node tail;
	private int size;

	/**
	 * @param head
	 * @param tail
	 * @param size
	 */

	private class Node {
		private Node next;
		private Node prev;
		private Item item;
	}

	public Deque() {
		head = null;
		tail = null;
		size = 0;

	}

	public boolean isEmpty() {
		return size == 0;

	}

	public int size() {
		return size;

	}

	public void addFirst(Item item) {

		if (item == null)
			throw new NullPointerException();

		Node p = new Node();
		p.item = item;
		if (isEmpty()) {
			head = p;
			tail = p;

		} else {
			p.next = head;
			head.prev = p;
			head = p;
		}

		size++;
	}

	public void addLast(Item item) {

		if (item == null)
			throw new NullPointerException();
		Node p = new Node();
		p.item = item;
		if (isEmpty()) {
			head = p;
			tail = p;
		} else {
			tail.next = p;
			p.prev = tail;
			tail = p ;

		}

	}

	public Item removeFirst() {

		if (isEmpty())
			throw new NoSuchElementException();

		Item item = head.item;
		if (head.item != null) {
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			size--;
		}
		return item;
	}

	public Item removeLast() {

		if (isEmpty())
			throw new NoSuchElementException();

		Item item = tail.item;
		if (tail.item != null) {
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
			size--;
		}
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		
		Iterator<Item> I = new Iterator<Item>(){
			
			private Node current = head;
			public boolean hasNext() {
				
				return current != null;
				
			}
				
			public Item next() {
				
				if(!hasNext())
					throw new NoSuchElementException();
				
				Item item = current.item;
				current = current.next;
				return item;
			}
			
			public void remove() {
				
				throw new UnsupportedOperationException();
			}
		};
		return I;		
	}

	public static void main(String[] args) {

	}
}