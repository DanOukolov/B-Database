package edu.davidson.csc353.microdb.indexes.bptree;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.RandomAccessFile;

import java.nio.ByteBuffer;

import java.nio.channels.FileChannel;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.function.Function;

import edu.davidson.csc353.microdb.utils.DecentPQ;

public class BPNodeFactory<K extends Comparable<K>, V> {
	public static final int DISK_SIZE = 512;

	public static final int CAPACITY = 15;

	private String indexName;

	private Function<String, K> loadKey;
	private Function<String, V> loadValue;

	private int numberNodes;

	private RandomAccessFile relationFile;
	private FileChannel relationChannel;

	// You should change the type of this nodeMap
	private HashMap<Integer, BPNode<K,V>> nodeMap;

	/**
	 * Creates a new NodeFactory object, which will operate a buffer manager for
	 * the nodes of a B+Tree.
	 * 
	 * @param indexName The name of the index stored on disk.
	 */
	public BPNodeFactory(String indexName, Function<String, K> loadKey, Function<String, V> loadValue) {
		try {
			this.indexName = indexName;
			this.loadKey = loadKey;
			this.loadValue = loadValue;

			Files.delete(Paths.get(indexName + ".db"));

			relationFile = new RandomAccessFile(indexName + ".db", "rws");
			relationChannel = relationFile.getChannel();

			numberNodes = 0;

			nodeMap = new HashMap<>();
		}
		catch (FileNotFoundException exception) {
			// Ignore: a new file has been created
		}
		catch(IOException exception) {
			throw new RuntimeException("Error accessing " + indexName);
		}
	}

	/**
	 * Creates a B+Tree node.
	 * 
	 * @param leaf Flag indicating whether the node is a leaf (true) or internal node (false)
	 * 
	 * @return A new B+Tree node.
	 */
	public BPNode<K,V> create(boolean leaf) {
		BPNode<K,V> created = new BPNode<K,V>(leaf);
		created.number = numberNodes;
		
		nodeMap.put(created.number, created);
		numberNodes++;
		
		// TODO

		return created;
	}

	/**
	 * Saves a node into disk.
	 * 
	 * @param node Node to be saved into disk.
	 */
	public void save(BPNode<K,V> node) {
		writeNode(node);
	}

	/**
	 * Reads a node from the disk.
	 * 
	 * @param nodeNumber Number of the node read.
	 * 
	 * @return Node read from the disk that has the provided number.
	 */
	private BPNode<K,V> readNode(int nodeNumber) {
		// TODO
		return null;
	}

	/**
	 * Writes a node into disk.
	 * 
	 * @param node Node to be saved into disk.
	 */
	private void writeNode(BPNode<K,V> node) {
		// TODO
	}

	/**
	 * Evicts the last recently used node back into disk.
	 */
	private void evict() {
		// TODO
	}

	/**
	 * Returns the node associated with a particular number.
	 * 
	 * @param number The number to be converted to node (loading it from disk, if necessary).
	 * 
	 * @return The node associated with the provided number.
	 */
	public BPNode<K,V> getNode(int number) {
		// TODO
		return nodeMap.get(number);
	}
}
