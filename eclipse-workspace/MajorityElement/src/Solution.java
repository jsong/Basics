import java.util.HashMap;

public class Solution {

	public static void main(String[] args) {
		Solution sl = new Solution();
		int[] nums = {1};
		int result = sl.majorityElement(nums);
	}
	public int majorityElement(int[] nums) {
		HashMap<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		int ret = 0;
		
		for (int num: nums) {
			if( myMap.containsKey(num) ) {
				myMap.put(num,myMap.get(num) + 1);
			} else {
				myMap.put(num, 1);
			}
			if(myMap.get(num) > nums.length/2 ) {
				ret = num;
				break;
			}
		}
		return ret;
	}
}
