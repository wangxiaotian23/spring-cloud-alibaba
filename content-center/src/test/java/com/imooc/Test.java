package com.imooc;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lawhen
 * @Date 2021/1/14
 */
public class Test {


    public static void main(String[] args) {
        int[] A = new int[]{1,0,1,0,0,0,0,0,0,0,0,1,1,1,0,0,1,0,1,1,1,1,1,1,0,0,0,1,0,1,1,1,1,0,1,1,0,1,0,1,0,0,0,1,0,0,0,0,0,1,0,0,1,1,0,0,1,1,1};
        prefixesDivBy5(A);
    }

    public static List<Boolean> prefixesDivBy5(int[] A){
        List<Boolean> booleans = new ArrayList<>();
        int[] dig={2,1,-2,-1};
        int base=0;
        for(int i=0;i<A.length;i++){
            base=(base+dig[i&3]*A[i])%5;
            booleans.add(base==0);
        }
        for (Boolean a:booleans){
            System.out.println(a);
        }
        return booleans;
    }

}
