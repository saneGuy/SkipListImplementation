import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
/**
 * 
 */
import java.util.Scanner;

/**
 * @author Viswanadha Pratap 
 * vxk147730
 * @param <T>
 *
 */
public class SkipList<T extends Comparable<? super T>> {

	/**
	 * @param args
	 */
	
	int max_level;
	int top_level = 0;
	
	node[] head;
	 SkipList(T data)
	{
		head = new node[50];
		head[0] = new node();
		head[0].data = data;
		head[0].next = null;
		head[0].down = null;
		head[0].level = 0;
		int i = 1;
		while(i < 50)
		{
		 head[i] = new node();
		 head[i].data = data;
		 head[i].next = null;
		 head[i].level = i;
		 head[i].down = head[i-1];
	     max_level = i;
	     i++;
	     }
			
	}
	
	/*
	 * isEmpty() will check whether the list is empty or not
	 * if it is empty it returns true else it returns false 
	 */
	boolean isEmpty(node list)
	{
		if(list.next == null)
		{
			return true;
		}
		return false;
	}
	/*
	 * search() will return the level at which it finds the data element 
	 */
     int search(T data)
	{
		int top_level = top_level(this);
		if(top_level == -1)
		{
			return -1;
		}
		node temp1 =  this.head[top_level];
		node temp = temp1;
		int level = top_level;
		while(true)
		{
			
			if(level <= 0 && temp == null)
			{
				return -1;
			}
			else if(temp != null && temp.data.compareTo(data) == 0)
				return level; 
			else if(temp != null && temp.data.compareTo(data) < 0)
			{
				temp1 = temp;
				temp = temp.next;
				temp1.next = temp;				
			}
			else if(temp == null && level != 0)
			{
				temp = temp1;
				temp = temp.down;
				temp1 = temp;
				level = level-1;
			}
			
			else if(temp != null && temp.data.compareTo(data) > 0)
			{
				temp = temp1;
				temp = temp.down;	
				temp1 = temp;
				level = level-1 ;
				
			}
		
		}
	}
     T Ceiling(T data)
 	{
 		int top_level = top_level(this);
 		if(top_level == -1)
 		{
 			return null;
 		}
 		node temp1 =  this.head[top_level];
 		node temp = temp1;
 		int level = top_level;
 		while(true)
 		{
 			
 			if(temp != null && temp.data.compareTo(data) == 0)
 				return (T) temp.data; 
 			else if(temp != null && temp.data.compareTo(data) < 0)
 			{
 				temp1 = temp;
 				temp = temp.next;
 				temp1.next = temp;				
 			}
 			else if(temp == null && level != 0)
 			{
 				temp = temp1;
 				temp = temp.down;
 				temp1 = temp;
 				level = level-1;
 			}
 			
 			else if(temp != null && temp.data.compareTo(data) > 0)
 			{
 				temp = temp1;
 				temp = temp.down;	
 				temp1 = temp;
 				level = level-1 ;
 				
 			}
 		
 		}
 	}
 	
     
	/*
	 * insert() will insert the data in the skiplist
	 */
     int insert(T data,int level,node<T> predecessor)
	{   
		int level1;
		Random ran = new Random();
		boolean one =  true; 		
		node<T> temp = (node<T>) head[level];
		node<T> temp1;
		node<T> pred = null;
			
		if(temp.next == null )
		{
	    temp1 = new node<T>();
		temp1.data = data;
		temp1.next = null;
		temp1.down = predecessor;
		temp1.level = temp.level;
		temp.next = temp1;
		pred = temp1;
		one = false;
		}
		
		while(temp.next != null && one == true)
		{
			
		 if(temp.data.compareTo(data) < 0 && temp.next.data.compareTo(data) > 0)
			{
				temp1 = new node();
				temp1.data = data;
				temp1.next = temp.next;
				temp1.level = temp.level;
				temp1.down =  predecessor;
				temp.next = temp1;
				pred = temp1;
								
			}
						
	     temp = temp.next;			
		}
		if(temp.data.compareTo(data) < 0 && one == true)
		{
			temp1 = new node();
			temp1.data = data;
			temp1.next = null;
			temp1.down = predecessor ;
			temp1.level = temp.level;
			temp.next = temp1;
			pred = temp1;
			
		}
		if(ran.nextInt(2) == 1)
		{
			level1 = level + 1;
			insert(data,level1,pred);
		}
		
	return 1;	
	}
	/*
	 * printList() prints all the elements of the list at level given as argument
	 */
	void printList(int level)
	{   
		node temp = head[level];
		while(temp!=null)
		{
			System.out.print(temp.data+ " ");
			temp = temp.next;
		}
		System.out.println();
	}
	/*
	 * top_level returns the max level in the skiplist
	 */
	int top_level(SkipList sl)
	{
		int i = 0;
		while(sl.isEmpty(head[i]) == false)
		{
			i++;
		}
				
		return i-1;
		}
	/*
	 * remove() will remove the data from all the levels in the skiplist
	 */
	boolean remove(T data)
	{
		int level_to_remove = 0;
		while(true)
		{
		level_to_remove = search(data);
		if(level_to_remove == -1)
		{
			return false;
		}
		node temp = head[level_to_remove];
		node temp1 = head[level_to_remove];
		while(temp!=null)
		{
			if(temp.data.compareTo(data) == 0)
			{
				temp1.next = temp.next;
			    temp= temp.next;
			    			
			}
			else
			{
				temp1 = temp;
			    temp = temp.next;
			    temp1.next = temp;
			 
			}
		}
		
	    }
		
    }
	    /* Driver function which creates the skiplist,searches 
	     * for an element and if found removes the element from 
	     * the skiplist 
	     * 
	     */
		public static <T> void main(String[] args) throws FileNotFoundException
		{
			
			SkipList<Long> sl =  new SkipList(new Long(Long.MIN_VALUE));
			int top_level;
			Scanner sc;int data;
			if (args.length > 0) 
			{
		            File inputFile = new File(args[0]);
		            sc = new Scanner(inputFile);
		          
		    } 
			else 
		    {
		           sc = new Scanner(System.in);
		    }
					
			String operation = "";
			long operand = 0;
			int modValue = 997;
			long result = 0;
			Long returnValue = null;
			long startTime = System.currentTimeMillis();
			while (!((operation = sc.next()).equals("End"))) {
				switch (operation) {
				case "Add": {
					operand = sc.nextLong();
					sl.add(operand);
					result = (result + 1) % modValue;
					break;
				}
				/*case "Ceiling": {
					operand = sc.nextLong();
					returnValue = sl.ceiling(operand);
					if (returnValue != null) {
						result = (result + returnValue) % modValue;
					}
					break;
				}*/
				/*case "FindIndex": {
					int intOperand = sc.nextInt();
					returnValue = sl.findIndex(intOperand);
					if (returnValue != null) {
						result = (result + returnValue) % modValue;
					}
					break;
				}*/
				case "First": {
					returnValue = sl.first();
					if (returnValue != null) {
						result = (result + returnValue) % modValue;
					}
					break;
				}
				case "Last": {
					returnValue = sl.last();
					if (returnValue != null) {
						result = (result + returnValue) % modValue;
					}
					break;
				}
				/*case "Floor": {
					operand = sc.nextLong();
					returnValue = sl.floor(operand);
					if (returnValue != null) {
						result = (result + returnValue) % modValue;
					}
					break;
				}*/
				case "Remove": {
					operand = sc.nextLong();
					if (sl.remove(operand)) {
						result = (result + 1) % modValue;
					}
					break;
				}
				case "Contains":{
				    operand = sc.nextLong();
				    if (sl.contains(operand)) {
					result = (result + 1) % modValue;
				    }
				    break;
				}

				}
			}
			

			// End Time
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;

			System.out.println(result + " " + elapsedTime);
			
		}

	
		private Long floor(long operand) {
			// TODO Auto-generated method stub
			return null;
		}

		private Long findIndex(int intOperand) {
			// TODO Auto-generated method stub
			return null;
		}

		private Long ceiling(long operand) {
			// TODO Auto-generated method stub
			return null;
		}

		/* printAllLists() prints all the lists in the skip list 
		 * in the order from 0 to top_level
		 * 
		 */
		 void printAllLists()
		{
			int i = 0;
			while(!this.isEmpty(this.head[i]))
			{
				this.printList(i);
				i++;
			}
			
			
		}
		
		/*contains returns true if the skiplist contains the data
		 * otherwise it returns false		 * 
		 */
		boolean contains(T data)
		{
			if(search(data)== -1)
				return false;
			return true;
		}
		
		/*returns the size of the elements in the list at level 0
		 */ 
		 int size()
		{
			node<T> temp = head[0];
			int count=-1;
			while(temp!=null)
			{
				count++;
				temp = temp.next;
			}
			return count;
		}
		  
		 /*
		  * Returns the last element of the skiplist
		  */
		 T last()
		 {
			 node<T> temp = this.head[0];
			 while(temp.next !=null)
			 {
				temp = temp.next;
			 }
				return temp.data;

			 
		 }
		 /*
		  * returns the first element of the skiplist 
		  */
		   T first()
		 {
			 return (T) head[0].next.data;
		 }
		   void add(T data)
		 {
			 insert(data,0,null);
		 }
		 
	
}
