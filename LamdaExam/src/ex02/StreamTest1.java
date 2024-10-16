package ex02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Student implements Comparable<Student> {
	String name;
	int ban;
	int totalScore;
	
	public Student() {
		
	}
		
	public Student(String name, int ban, int totalScore) {
		super();
		this.name = name;
		this.ban = ban;
		this.totalScore = totalScore;
	}


	public String getName() {
		return name;
	}

	public int getBan() {
		return ban;
	}

	public int getTotalScore() {
		return totalScore;
	}

	@Override
	public int compareTo(Student o) {
		
		return o.totalScore-totalScore;
	}



	@Override
	public String toString() {
		return "Student [name=" + name + ", ban=" + ban + ", totalScore=" + totalScore + "]";
	}


}

public class StreamTest1 {


	public static void main(String[] args) {
		Stream<Student> studStream = Stream.of(
				new Student("이자바",3,300),
				new Student("김자바",1,200),
				new Student("박자바",2,100),
				new Student("강자바",2,250),
				new Student("신자바",1,150),
				new Student("나자바",3,180),
				new Student("안자바",1,200));
	
//		studStream.sorted().forEach(System.out::println);//기본정렬(totalScore)
//		studStream.sorted(Comparator.reverseOrder()).forEach(System.out::println);//기본정렬역순
		
		
//		studStream.sorted(Comparator.comparing(Student::getBan) //반별정렬
//				.thenComparing(Comparator.naturalOrder())) //반이 같을 경우 기본 정렬(totalScore)
//			.forEach(System.out::println);
//	
//		studStream.sorted(Comparator.comparing(Student::getBan) //반별정렬
//				.thenComparing(Comparator.reverseOrder())) //반이 같을 경우 정렬 역순
//			.forEach(System.out::println);
//	
//		studStream.filter(s->s.getBan()==1).map(Student::getName).forEach(System.out::println); //김자바 신자바 안자바
//		studStream.filter(s->s.getBan()==1).peek(s->System.out.println(s)).map(Student::getName).forEach(System.out::println); //peek 스트림 데이터를 소모하지 않는다.
	
		List<String> studName = studStream.filter(s->s.getBan()==3).map(Student::getName).collect(Collectors.toList());
		System.out.println(studName);  //이자바,나자바
		
		
	}

}
