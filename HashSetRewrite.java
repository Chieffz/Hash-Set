import java.util.*;

public class HashSetRewrite<T extends Comparable<T>> implements HS_InterfaceRewrite<T>
{
    private GenericLists<Node<T>> bucketArr;
    private int numBuckets;
    private int size;

    private final int AVG_BUCKET_LENGTH = 20; // Average Bucket Length

    public HashSetRewrite(int numBuckets)
    {
        this.numBuckets = numBuckets;
        size=0;
        bucketArr = new GenericLists<Node<T>>(numBuckets);
    }

    // HashSet Linked List Functions

	public boolean add( T key ) // dupes must be rejected and return false
    {
        if (!(insertInOrder(key, bucketArr)))
            return false;
        ++size;
        if ( size > AVG_BUCKET_LENGTH * this.numBuckets)
            upSizeHashSet();
        return true;
    }
   
	public boolean remove( T key ) // if not found return false else remove & return true
    {
        if (!(removeInOrder(key)))
            return false;
        size--;
        return true;
    }

	public T get(int index) // Takes in a hashed key, and returns the linked list headed @ that array index
    {
        return null;
    }
   
	public boolean contains( T key ) // true if foound false if not
    {
        return search(key) != null;

    }

    public Node<T> search(T key) // Searching to see if the key exists in the set or not
    {
        int index = hashOf(key,numBuckets);

        Node<T> cur = bucketArr.get(index);
        while (cur.next!=null && !(cur.data.equals(key)))
            cur = cur.next;
        
        if (cur.next!=null && cur.data.equals(key))
            return cur;
        return null;

    }

    public ArrayList<T> search(int index) // Searching to see if an array index is null or not, if not, returns the list as an ArrayList
    {
        Node<T> cur = bucketArr.get(index);
        if (cur==null) return null;

        ArrayList<T> list = new ArrayList<T>();
        while (cur.next!=null)
        {
            list.add(cur.data);
            cur = cur.next;

        }

        return list;
    }
   
	public boolean isEmpty()
    {
        return size() == 0;

    }
   
	public int size()
    {
        return size;
    }
   
   	public void clear() // Will essentially delete all hashed elements and downsize the bucketArray to the original size
    {
        return;
    }

    // End HashSet Linked List Functions

    // Hash Set Data Maniuplation Functions

    private boolean insertInOrder(T key, GenericLists<Node<T>> arr) // Returns True IF insertions was sucessful OR no duplications were detected, returns false IF insertion was unsuccessful OR dupes were found
    {
        int index = hashOf(key, numBuckets);
        Node<T> cur = arr.get(index);

        if (cur==null)
            {
                arr.set(index,new Node<T>(key));
                return true;
            }
        if (cur.data.equals(key))
            return false;
            
        while (cur.next!=null && cur.next.data.compareTo(key)<0)
            cur = cur.next;
        if (cur.next!=null && cur.next.data.equals(key))
        {
            return false;
        }
        cur.next = new Node<T>(key,cur.next);
        return true;
    }

    private boolean removeInOrder(T key) // Returns True IF deletion was sucessful OR no duplications were detected, returns false IF deletion was unsuccessful OR dupes were found
    {
        int index = hashOf(key,numBuckets);
        Node<T> cur = bucketArr.get(index);
        if (cur==null)
        {
            return false;
        }

        if (cur.data.equals(key))
        {
            bucketArr.set(index,cur.next);
            return true;
        }
        while (cur.next!=null && cur.next.data.compareTo(key)<0)
            cur = cur.next;
        if (cur.next==null || cur.next.data.compareTo(key)>0) return false;
        cur.next = cur.next.next;
        return true;
    }


    // Hashing Functions

    private int hashOf(T key, int numBuckets) // Actual Hashing function, returns a int for a array index position for the key to be addressed to
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

    private void upSizeHashSet() // We're getting a bunch of null curs, maybe we're making all indexes null on WRONG array
    {
        System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
						   size, bucketArr.size(), bucketArr.size()*2  );
        this.numBuckets = bucketArr.size()*2;
        GenericLists<Node<T>> nArr = new GenericLists<Node<T>>(numBuckets);

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

class GenericLists<D>
{
    private Object[] Arr;

    GenericLists(int size)
    {
        Arr = new Object[size];
    }

    @SuppressWarnings("unchecked")
    public D get(int index)
    {
        return (D)Arr[index];
    }

    public void set(int index, D head)
    {
        Arr[index] = head;
    }

    public int size()
    {
        return Arr.length;
    }
}