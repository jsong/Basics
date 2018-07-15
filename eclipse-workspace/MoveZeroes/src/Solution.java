
public class Solution {

	public static void main(String[] args) {

	}
	
	public void moveZeroes(int[] nums) {
        int pivot = 0;
        if (nums.length == 0) return;
        for (int i = 0; i < nums.length; i++) 
        		if(nums[i] != 0) nums[pivot++] = nums[i];
        int zeros = nums.length - pivot;
        while(zeros > 0) {
        		nums[pivot++] = 0;
        		zeros --;
        }
    }
}
