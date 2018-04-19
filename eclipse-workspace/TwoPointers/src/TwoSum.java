import java.util.ArrayList;
import java.util.HashMap;

//170. Two Sum III - Data structure design
//LinkedIn. 
public class TwoSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
