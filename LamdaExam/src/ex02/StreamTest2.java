package ex02;

import java.util.Optional;

public class StreamTest2 {

	public static void main(String[] args) throws Exception {
		
		//Optional<Student> os = Optional.of(new Student("이자바",3,300));
		Optional<Student> os = Optional.of(null); // error
		//Optional<Student> os = Optional.ofNullable(null); // null이 들어갈 수 있으나 .get()에서 error나기 때문에 isPresetn활용
		
		if(os.isPresent()) { // null이 아니면 (있으면) 처리해라 <-확인하는 메서드
			Student s1 = os.get();
			System.out.println(s1);
		}else {
			new Exception("데이터 없음");
		}
		
		Student s = os.orElseThrow(Exception::new);
		//Student s = os.orElseThrow(()->new Exception("데이터없음"));
		
		System.out.println(s);
		
		
		
		//Student s= os.orElseGet(Student::new); //대체하는 메서드 
		//System.out.println(s); 
		
	}

}
