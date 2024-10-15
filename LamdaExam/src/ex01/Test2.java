package ex01;

@FunctionalInterface
interface kFunction {
	void run();
}

public class Test2 {
	static void execute(kFunction f) {
		f.run();
	}
	static kFunction getKFunction() {
		return ()->System.out.println("f3.run");
				
	}
	public static void main(String args[]) {
		//람다를 익명객체를 지역변수로 
		kFunction  kf1 = ()->System.out.println("f1.run");
		kf1.run();
		
		//람다를 파마미터로 넘겨 사용
		execute(()->System.out.println("f2.run"));
		
		//람다 함수의 리턴
		kFunction kf3 = getKFunction();
		kf3.run();
	}
}
