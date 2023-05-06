import java.util.*;

import javax.print.attribute.standard.MediaSize.NA;

public class HashSetRewrite<T extends Comparable<T>> implements HS_InterfaceRewrite<T>
{
    private ArrayList<Node<T>> bucketArr;
    private int numBuckets;
    private int size;

    private final int AVG_BUCKET_LENGTH = 20; // Average Bucket Length

    public HashSetRewrite(int numBuckets)
    {
        this.numBuckets = numBuckets;
        size=0;
        bucketArr = new ArrayList<Node<T>>(numBuckets);
        for (int i = 0; i<numBuckets; i++)
        {
            bucketArr.add(i, null);
        }
    }

    // HashSet Linked List Functions

	public boolean add( T key ) // dupes must be rejected and return false
    {
        if(!(insertInOrder(key, bucketArr)))
            return false;
        ++size;
        if ( size > AVG_BUCKET_LENGTH * this.numBuckets)
            upSizeHashSet();
        return true;
    }
   
	public boolean remove( T key ) // if not found return false else remove & return true
    {
        return false;
    }

	public T get(int index) // Takes in a hashed key, and returns the linked list headed @ that array index
    {
        return null;
    }
   
	public boolean contains( T key ) // true if foound false if not
    {
        return false;

    }
   
	public boolean isEmpty() // use the call to size
    {
        return false;

    }
   
	public int size() // number of keys currently stored in the container
    {
        return size;
    }
   
   	public void clear()
    {
        return;
    }

    // End HashSet Linked List Functions

    // Hash Set Data Maniuplation Functions

    private boolean insertInOrder(T key, ArrayList<Node<T>> arr) // Returns True IF insertions was sucessful OR no duplications were detected, returns false IF insertion was unsuccessful OR dupes were found
    {
        int index = hashOf(key, numBuckets);
        Node<T> cur = arr.get(index);

        if (cur==null)
            {
                arr.set(index,new Node<T>(key));
                return true;
            }
        if (cur.data.equals(key)) return false;
        while (cur.next!=null && cur.next.data.compareTo(key)<0)
            cur = cur.next;

        if (cur.next!=null && key.equals(cur.next.data)) return false;
        cur.next = new Node<T>(key,cur.next);
        return true;
    }


    // Hashing Functions

    private int hashOf(T key, int numBuckets)
    {
		int total = 0;
        String keyStr = key.toString();
		for (int i =0; i<keyStr.length(); i++)
		{
			int ascii = keyStr.charAt(i);
			total = total*17;
			total = (total + ascii * 23)/5;
		}
		return Math.abs(total % numBuckets);
    }

    private void upSizeHashSet()
    {
        System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArr.size(), bucketArr.size()*2  );
        this.numBuckets = bucketArr.size()*2;
        ArrayList<Node<T>> nArr = new ArrayList<Node<T>>(numBuckets);
        for (int i = 0; i<numBuckets; i++)
        {
            nArr.add(i, null);
        }

        for (int i = 0; i<bucketArr.size(); i++)
        {
            Node<T> cur = nArr.get(i);

            while (cur!=null)
            {
                insertInOrder(cur.data,nArr);
                cur = cur.next;
            }
        }
        bucketArr = nArr;
    }
}

class Node<T>
{
    T data;
    Node<T> next;
    public Node(T data, Node<T> next)
    {
        this.next = next;
        this.data = data;
    }
    public Node(T data) {this(data,null);}
}