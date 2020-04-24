package com.ygj.test;


import java.util.HashSet;
import java.util.Set;

public class Permutation {

	  public int solution(int A[], int N)
	  {
		 HashSet<Integer> set=new HashSet<>();
		  
		 for(int i=1;i<=N;i++)
			 set.add(i);
		 
		 for(int element:A)
		 {
			 if(!set.remove(element))
			  return 0;
		 }
		 
		 if(set.size()==0)
		  return 1;
		 else
			 return 0;
	  }
}
