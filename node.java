
// Node in the SkipList
  class node<T extends Comparable<? super T>> {
	T data;
	int level;
	node<T> next;
	node<T> down;
	node()
	{
	
	}
	node(T data)
	{
		this.data = data;
	}
	
	}
