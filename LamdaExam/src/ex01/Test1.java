package ex01;

@FunctionalInterface //오직 하나의 추상 메서드만 정의
interface MyFunction1 { 
	int max(int x,int y);
}

interface MyFunction2 {
	void myMethod();
}

public class Test1 {
	static void myMethod() {
		System.out.println("main method");
	}

	public static void main(String args[]) {
		
		MyFunction1 mf1 = new MyFunction1() {
			@Override
			public int max(int x, int y) {
				return x>y? x:y;
			}
		};
		System.out.println(mf1.max(3, 4));
	
		//람다식
		MyFunction1 mf2 = (x,y) ->x>y? x:y;
		System.out.println(mf2.max(3, 4));
		
		MyFunction2 mfm = ()->System.out.println("Lamda Test"); 
		
		mfm.myMethod();
	}
}
