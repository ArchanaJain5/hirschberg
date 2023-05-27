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

      // calculate the break point of k
      int lcs = compute_lcs(A, B);
      int k=0;
      for(k=0;k<lenB;k++){
        String y1 = B.substring(0,k);
        String y2 = B.substring(k);
        if(compute_lcs(x1,y1)+compute_lcs(x2, y2)==lcs)
          break;
      }
      String y1 = B.substring(0, k);
      String y2 = B.substring(k);

      String lcs1 = find(x1, y1);
      String lcs2 = find(x2,y2);

      return lcs1+lcs2;
  }
  private int compute_lcs(String s1,String s2){
    int n = s1.length();
    int m = s2.length();

    int prev[]=new int[m+1];
    int cur[]=new int[m+1];
    for(int ind1=1;ind1<=n;ind1++){
        for(int ind2=1;ind2<=m;ind2++){
            if(s1.charAt(ind1-1)==s2.charAt(ind2-1))
                cur[ind2] = 1 + prev[ind2-1];
            else
                cur[ind2] = 0 + Math.max(prev[ind2],cur[ind2-1]);
        }
        prev=(int[])(cur.clone());
    }
    
    return prev[m];

  }

}