public class Fibonacci4{
	public static int fibonacci(int n){
		if (n == 0){
			return 0;
		}
		
		if (n == 1){
			return 1;
		}
		
		int a = 0;
		int b = 1;
		int c = 1;
		for (int i = 2;i < n;i++){
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}
	
	public static void main(String[] args) {
        System.out.print(fibonacci(10));
	}
}