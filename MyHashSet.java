import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface
{	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20;  // **DO NOT CHANGE THIS NUMBER**

	public MyHashSet( int numBuckets )
	{	size=0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format("IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n", numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE );
	}

    private boolean insertInOrder(String key, Node[] arr)
    {
        int index = hashOf(key,numBuckets);
        Node cur = arr[index];
        if (cur == null) 
        {
            arr[index] = new Node(key);
            return true;
        }
        if (cur.data.equals(key)) return false;
        while(cur.next!=null && cur.next.data.compareTo(key)<0) // stop if null OR compareTo is greater than 0
            {
                cur = cur.next;
            }
    
        if (cur.next!= null && key.equals(cur.next.data))
            return false;
        cur.next = new Node(key,cur.next);
        return true;
    }

	public boolean add( String key )
	{
        if(!(insertInOrder(key, bucketArray)))
            return false;
        ++size;
        if ( size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
            upSize_ReHash_AllKeys();
        return true;
	}
	public boolean contains( String key )
	{	
        return search(key) != null;
	}

    private Node search(String key)
    {
        int keyHash=hashOf(key,numBuckets);

        Node cur = bucketArray[keyHash];
        while (cur!=null && !(cur.data.equals(key)))
            cur = cur.next;
        
        return cur;
    }

	private void upSize_ReHash_AllKeys()
	{	
        System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArray.length, bucketArray.length*2  );
		Node[] biggerArray = new Node[ bucketArray.length * 2 ];
		this.numBuckets = biggerArray.length;

        for (int i = 0; i<bucketArray.length; i++)
        {
            Node cur = bucketArray[i];
            while (cur!=null)
            {
                insertInOrder(cur.data, biggerArray);
                cur = cur.next;
            }
        }
		bucketArray = biggerArray;
	}

    private boolean removeInOrder(String key)
    {
        int index = hashOf(key,numBuckets);
        Node cur = bucketArray[index];

        if (cur==null) 
            return false;

        if (cur.data.equals(key))
        {
            bucketArray[index] = cur.next;
            return true;
        }

        while(cur.next!=null && cur.next.data.compareTo(key)<0)
            cur = cur.next;

        if (cur.next==null || !(cur.next.data.compareTo(key)<=0))
            return false;

        cur.next = cur.next.next;
        return true;
    }

    // NEXT PLAN: ADD IN A GET METHOD AND A RETRIEVE LIST METHOD
	public boolean remove( String key )
	{
        return removeInOrder(key);
	}
	public boolean isEmpty()
	{
		return size()==0;
	}
	public void clear()
	{
	}
	public int size()
	{
		int size = 0;
        for (int i =0; i<numBuckets; i++)
        {
            Node cur = bucketArray[i];
            while (cur!=null)
            {
                size = size + 1;
                cur = cur.next;
            }
        }
        this.size = size;
        return size;
	}

    private int hashOf( String key, int numBuckets )
	{
		int total = 0;
		for (int i =0; i<key.length(); i++)
		{
			int ascii = key.charAt(i);
			total = total*17;
			total = (total + ascii * 23)/5;
		}
		return Math.abs(total % numBuckets);
	}
	
} //END MyHashSet CLASS

class Node
{	String data;
	Node next;
	public Node ( String data, Node next )
	{ 	this.data = data;
		this.next = next;
	}
    public Node(String data)
    {
        this(data,null);
    }
}