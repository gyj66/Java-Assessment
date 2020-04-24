package com.ygj.test;

public class RotateArray {
	
	 void reverseArray(int arr[], int start, 
             int end) 
   { 
while (start < end) 
{ 
int temp = arr[start]; 
arr[start] = arr[end]; 
arr[end] = temp; 
start++; 
end--; 
} 
    } 


public void solution(int A[], int N, int K) 
{ 
reverseArray(A, 0, N - 1); 
reverseArray(A, 0, K - 1); 
reverseArray(A, K, N - 1); 
} 


	
	
}
