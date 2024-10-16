package ex02;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

		//1.java.lang.Runnable :void run()
		// Runaalbe 인터페이스 리턴값도 없고 파라미터가 없는 타입
		// 매개변수 x, 반환값 x
		//@FunctionalInterface 을 대신함

		//2. java.util.fuction패키지 : Supplier<T> : T get()
		//java 8이상부터 생김
		//매개변수x ,반환값o 
		
		//3. java.util.function : Consumer<T> : void accept(T t)
		//매개변수o,반환값x
		
		//4.java.util.function : Function<T,R> R apply(T t)
		//매개변수o,반환값o

		//5.java.util.function : Predicate<T> boolean  test (T t) :조건식에 이용
		//매개변수o,반환값o(boolean)

		// 매개변수 2개
		//6.java.util.function: BiConsumer<T,U> :void accept(T t,U u)
		//7.java.util.function: BiPredicate<T,U> :boolean test(T t, U u)
		//8.java.util.function: BiFunction<T,U,R> : R apply(T t,U u)
		
		//매개변수 타입과 반환 타입이 일치 :Function의 변형
		//UnaryOperator<T> : T apply(T t)
		//BinaryOperator<T> : T apply(T t, T t)


		// @FunctionalInterface : 오직 단 하나의 메서드만을 가질 수 있음을 의미  
		//interface MyFunction {
		//    void myMethod();
		// }
		
public class LamdaFuncTest1 {

	public static void main(String args[]) {
		
		//1.
		Runnable f1 =() ->System.out.println("Lamda Test1");		
		f1.run();
		
		//2.
		Supplier<Integer> f2 =() ->(int)(Math.random()*100)+1;
		System.out.println(f2.get());

		//3.
		Consumer<Integer> f3 = (i)->System.out.println(i+",");
		f3.accept(5);
	
		//4.
		Function<Integer,Integer> f4 = i -> i/10*10;
		System.out.println(f4.apply(15));

		//5.
		Predicate<Integer> f5= i-> i%2 == 0;
		System.out.println(f5.test(13));

		//6.
		BiConsumer<Integer, Integer> f6 = (x,y) ->System.out.println(x+y);
		f6.accept(5, 6);
	
		
	}
}
