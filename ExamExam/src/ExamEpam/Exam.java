package ExamEpam;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exam {
public static void main (String[] args) {
	
	
		/*int n = 4;
		int[] arr = new int[n];
		arr[0] = 0;
		arr[1] = 1;
		int sum = 0;
		for (int i = 2; i < arr.length; i++) {
			arr[i] = arr[i-2] +arr[i-1];
		}
		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
			System.out.print(arr[i]);	
			System.out.println(sum);
		}
	}*/


		String text = "aa hh aa kk kk aa ll nn nn mm mm mm mmm";
		int count=0;
		String[] arr = text.split(" ");
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			count = 0;
		for (int j = 0; j < arr.length; j++) {
			if(arr[i].equals(arr[j])){
		        count++;
			}
		}
		        map.put(arr[i], count);
		        }
			System.out.println(map);
		}
}