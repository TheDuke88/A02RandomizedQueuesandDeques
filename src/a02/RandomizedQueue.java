package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] a;
	private int size;

	public RandomizedQueue() {// construct an empty randomized queue
		a = (Item[]) new Object[1];
		size = 0;
	}

	public boolean isEmpty() {// is the queue empty?
		return size == 0;
	}

	public int size() {// return the number of items on the queue
		return size;
	}

	private void resize(int full) {
		Item[] copy = (Item[]) new Object[full];
		for (int i = 0; i < size; i++) {
			copy[i] = a[i];
		}
		a = copy;
	}

	public void enqueue(Item item) {// add the item

		if (item == null)
			throw new NullPointerException();

		a[size++] = item;
		if (a.length == size) {
			resize(size * 2);
		}

	}

	public Item dequeue() {// delete and return a random item

		if (isEmpty())
			throw new NoSuchElementException();

		int rand = StdRandom.uniform(size);
		Item r = a[rand];
		a[rand] = a[--size];
		a[--size] = null;

		if (size <= (a.length / 4)) {
			resize(a.length / 2);
		}
		return r;
	}

	public Item sample() {// return (but do not delete) a random item

		if (isEmpty())
			throw new NoSuchElementException();

		int rand = StdRandom.uniform(size);
		return a[rand];

	}

	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator<>();// return an independent iterator over items in random order

	}

	private class QueueIterator<Item> implements Iterator<Item> {
		private int index;
		private int[] shuffled = new int[size];

		QueueIterator() {
			for (int i = 0; i < size; i++)
				shuffled[i] = i;
			StdRandom.shuffle(shuffled);
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return (Item) a[shuffled[index++]];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {// unit testing
		RandomizedQueue<String> r = new RandomizedQueue<String>();

		System.out.println("Is queue empty: " + r.isEmpty());
		System.out.println("Size of queue: " + r.size());

		r.enqueue("A");
		r.enqueue("B");
		r.enqueue("C");
		r.enqueue("D");
		r.enqueue("E");
		r.enqueue("F");

		System.out.println("Output after adding: ");
		for (String s : r) {
			System.out.println(s);
		}

		System.out.println("Size after add: " + r.size());
		System.out.println("Is queue empty: " + r.isEmpty());

		r.dequeue();

		System.out.println("Output after removing: ");
		for (String s : r) {
			System.out.println(s);
		}

		System.out.println("Size after remove: " + r.size());
		System.out.println("Is queue empty: " + r.isEmpty());

	}

}