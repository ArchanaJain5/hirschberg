package project6;

import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class LCS {
  
  public String find(String A, String B) {
    // Implement the Hirschberg LCS algorithm 
    // base case
      int lenA = A.length(),lenB = B.length();
      if(lenA == 0) return "";
      if(lenA==1){
        for(int j=0;j<lenB;j++){
          if(A.charAt(0)==B.charAt(j)) return A;
        }
        return "";
      }
      int m = lenA/2;
      String x1 = A.substring(0, m);
      String x2 = A.substring(m);

      // calculate the break point of k which is when compute_lcs(x1,y1)+compute(x2,y2) == compute_lcs(A,B)
      int[] lcs1 = compute_lcs(x1, B);

      // reversing the strings
      StringBuilder temp1 = new StringBuilder(x2);
      String rev_x2 = temp1.reverse().toString();

      StringBuilder temp2 = new StringBuilder(B);
      String rev_B= temp2.reverse().toString();

      int[] lcs2 = compute_lcs(rev_x2, rev_B);
      int k=0,max=0;

      for(int i=0;i<=lenB;i++){
       if(lcs1[i]+lcs2[lenB-i]>max){
          max = lcs1[i]+lcs2[lenB-i];
          k=i;
       }
      }

      String y1 = B.substring(0, k);
      String y2 = B.substring(k);

      String lc1 = find(x1, y1);
      String lc2 = find(x2,y2);

      return lc1+lc2;
  }
  private int[] compute_lcs(String s1,String s2){
    int n = s1.length();
    int m = s2.length();

    int prev[]=new int[m+1];
    int curr[]=new int[m+1];
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1))
                curr[j] = 1 + prev[j-1];
            else
                curr[j] = 0 + Math.max(prev[j],curr[j-1]);
        }
        prev=(int[])(curr.clone());
    }
    
    return prev;

  }

}
