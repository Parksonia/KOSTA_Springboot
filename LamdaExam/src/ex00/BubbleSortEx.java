package ex00;
import java.util.Arrays;

class Person {
	Integer age;
	String name;
	
	
	public Person(Integer age, String name) {
		
		super();
		this.age = age;
		this.name = name;
	}
	public Person() {}
	
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name +"]";
	}
	
	
}
interface Compare {
	int toCompare(Person p1,Person p2); 
}

public class BubbleSortEx {

	static void bubbleSort(Person[] pers, Compare compare) {
		for(int i=0; i < pers.length-1; i++ ) {
			for(int j=0; j < pers.length-i-1;j++) {
				if(compare.toCompare(pers[j], pers[j+1])>0) {
					Person p = pers[j];
					pers[j] = pers[j+1];
					pers[j+1] = p;
				}
			}
		}
	}
	
	public static void main(String args[]) {
		Person[] pers = {new Person(10,"hong"),new Person(15,"song"),new Person(11,"gong")};
		bubbleSort(pers,new Compare() {
			@Override
			public int toCompare(Person p1, Person p2) {
				
				return p1.age-p2.age; //asc
			}
		});
		System.out.println(Arrays.asList(pers));
	
		Compare compare2 = new Compare() {
			@Override
			public int toCompare(Person p1, Person p2) {
				return p1.name.compareTo(p2.name);
			}
		};
		
		bubbleSort(pers,compare2);
		System.out.println(Arrays.asList(pers));
		
		Compare compare3 = (p1,p2) -> p2.age -p1.age; 
		bubbleSort(pers,compare3); //람다식의 함수를 인터페이스의 파라미터로 사용		
		System.out.println(Arrays.asList(pers));
		
		bubbleSort(pers,(p1,p2) -> p2.name.compareTo(p1.name));
		System.out.println(Arrays.asList(pers));
	}
}
