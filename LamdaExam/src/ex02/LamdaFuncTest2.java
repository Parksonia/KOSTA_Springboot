package ex02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class LamdaFuncTest2 {

	static void method (Integer n) {
		System.out.println(n);
	}
	
	static Integer max(Integer x, Integer y) {
		return x>y? x:y;
	}
	
	public static void main(String[] args) {

		//함수형 인터페이스를 사용하는 컬렉션 프레임웍의 메서드
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			list.add(i);
		}
		
		list.forEach(i->System.out.print(i+",")); //Consumer<>인터페이스 사용하여 반복문 출력
		System.out.println();
		
		list.removeIf(x ->x%2==0||x %3==0); //Predicate<> :boolean type 사용하여  삭제 
		list.forEach(i->System.out.print(i+",")); //Consumer<>인터페이스 사용하여 반복문 출력
		System.out.println();
		
		list.replaceAll(x->x*10);
		list.forEach(i->System.out.print(i+",")); //Consumer<>인터페이스 사용하여 반복문 출력
		System.out.println();
		
		Map<String,String> map = new HashMap<>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.forEach((k,v)->System.out.println("{"+k+","+v+"}"));
		
		
		Consumer<Integer> f =LamdaFuncTest2::method; 
		
		BinaryOperator<Integer> f2 = LamdaFuncTest2::max;
		System.out.println(f2.apply(1, 10));
	
	
	}

}
