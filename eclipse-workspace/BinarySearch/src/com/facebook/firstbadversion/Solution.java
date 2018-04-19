package com.facebook.firstbadversion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
}

//First bad version.
//public class Solution extends VersionControl {
//    public int firstBadVersion(int n) {
//         int start = 1; int end = n;
//        while (start + 1 < end) {
//            int mid = start + (end - start)/2;
//            if (super.isBadVersion(mid)) {
//                end = mid;
//            } else {
//                start = mid;
//            }
//        }
//        
//        if (super.isBadVersion(start)) {
//            return start;
//        }
//        
//        return end;
//    }
//}