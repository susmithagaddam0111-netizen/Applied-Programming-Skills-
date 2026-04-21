class Solution {
   HashSet<Integer> set=new HashSet<>();
        while(n!=1&& !set.contains(n)){
            set.add(n);
            n=getNext(n);
        }
        return n==1;
        
    }
    private int getNext(int num){
        int sum=0;
        while(num>0){
            int digit=num%10;
            sum+=digit*digit;
            num/=10;
        }
        return sum;
    }
}
