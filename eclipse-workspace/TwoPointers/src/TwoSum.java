import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

//170. Two Sum III - Data structure design
//LinkedIn. 
public class TwoSum {

	public static void main(String[] args) {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		map.put(123, new ArrayList<Integer>());
		ArrayList<Integer> arr = map.get(123);
		arr.add(12345);
		ArrayList<Integer> arr2 = map.get(123);
		boolean contains = arr2.contains(12345);
		// so it's all reference, do not need to put it back.
		System.out.println("Contains: " + contains);
	}
	
	  /** Initialize your data structure here. */
    HashMap<Integer, Integer> map;
    ArrayList<Integer> arr;
    public TwoSum() {
        //HashMap for search
        //ArrayList for loop find value.
        map = new HashMap<Integer, Integer>();
        arr = new ArrayList<Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        arr.add(number);
        if (!map.containsKey(number)) {
            map.put(number, 1);
        } else {
            map.put(number, map.get(number) + 1);
        }
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int i = 0; i< arr.size(); i ++) {
            if (arr.get(i) == value - arr.get(i)) {
                if (map.get(arr.get(i)) > 1) {
                    return true;
                }
            } else if (map.containsKey(value - arr.get(i))) {
                    return true;
            }            
        }
        return false;
    }
}
