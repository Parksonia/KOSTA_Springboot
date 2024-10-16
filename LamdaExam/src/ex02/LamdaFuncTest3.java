package ex02;

import java.util.function.BiFunction;
import java.util.function.Supplier;

class Person {
	String name;
	Integer age;

	public Person() {}
	
	public Person(String name,Integer age) {
		this.name = name;
		this.age =age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	
}

public class LamdaFuncTest3 {

	public static void main(String[] args) {

		Supplier<Person> np1 = Person::new; //람다식을 정의 () ->new Person() 이랑 같다.
		Person p1 = np1.get(); // 실행 - 기본 생성자가 있어서 알아서 찾아감. 
		
		
		BiFunction<String,Integer,Person>np2 = Person::new;	//(name,age) ->new Person(name,age)와 같다. 
		Person p2 = np2.apply("홍길동", 20);
		System.out.println(p2);
	}

}
