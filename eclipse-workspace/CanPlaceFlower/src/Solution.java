
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = 5/2;
		System.out.println("result:"+result);
	}
	
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		int result = 0;
		int counter = 1;
		for (int i = 0; i< flowerbed.length; i++) {
			if(flowerbed[i] == 0) {
				counter ++;
			} else {
				result += (counter - 1)/2;
				counter = 0;
			}
		}
		if(counter > 0) result += counter/2;
		return result > n;		
	}
}
