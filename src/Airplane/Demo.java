package Airplane;

import java.util.Arrays;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        /*int k=1;
        for(int i=0;i<40/4;i++){
            for(int j=0;j<4;j++){
                System.out.print(k+"\t");
                k++;
            }
            System.out.println();
        }
        */
        Scanner sc=new Scanner(System.in);
        int a[]=new int[3];
        for(int i=0;i<3;i++){
            a[i]=sc.nextInt();
        }
        String s= Arrays.toString(a);
        System.out.println(s);
        String sout=s.substring(1,s.length()-1);
        System.out.println(sout);
    }
}
