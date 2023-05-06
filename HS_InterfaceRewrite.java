public interface HS_InterfaceRewrite<T extends Comparable<T>>
{
/* 	
	D O  N O T  W R I T E   A N Y   C O D E  I N  H E R E 
	A L L   T H E S E   F U N C T I O N S   G O    I N S I D E  T H E  M Y H A S H S E T.J A V A   F I L E 	
*/	

	public boolean add( T key ); // dupes must be rejected and return false
   
	public boolean remove( T key ); // if not found return false else remove & return true

	public T get(int index); // Takes in a hashed key, and returns the linked list headed @ that array index
   
	public boolean contains( T key ); // true if foound false if not
   
	public boolean isEmpty(); // use the call to size
   
	public int size(); // number of keys currently stored in the container
   
   	public void clear();
} // END HS_INTERFACE
