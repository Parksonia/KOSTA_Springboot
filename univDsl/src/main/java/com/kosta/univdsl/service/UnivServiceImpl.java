package com.kosta.univdsl.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kosta.univdsl.dto.DepartmentDto;
import com.kosta.univdsl.dto.ProfessorDto;
import com.kosta.univdsl.dto.StudentDto;
import com.kosta.univdsl.entity.Professor;
import com.kosta.univdsl.entity.Student;
import com.kosta.univdsl.repository.DepartmentRepository;
import com.kosta.univdsl.repository.ProfessorRepository;
import com.kosta.univdsl.repository.StudentRepository;
import com.kosta.univdsl.repository.UnivRepository;
import com.querydsl.core.Tuple;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {
	
	// 필요한 상황에 따라 dsl과 jpaRepository를 적절하게 활용한다.
	// 예를 들어 insert의 경우 dsl을 사용할 필요없음
	private final UnivRepository univRepo;
	private final DepartmentRepository departmentRepo;
	private final ProfessorRepository professorRepo;
	private final StudentRepository studentRepo;
	
	@Override
	public void saveDepartment(DepartmentDto depDto) throws Exception {
		
		if(departmentRepo.findByDname(depDto.getDname()).isPresent()) {
			throw new Exception("등록된 학과입니다.");
		}
		departmentRepo.save(depDto.toEntity());

	}

	@Override
	public void saveStudent(StudentDto studDto) throws Exception {
		
		if(studentRepo.findById(studDto.getStudNo()).isPresent()) {
			throw new Exception("등록된 학생입니다.");
		}
			
		//학과 번호x,학과명o 학과명으로 학과번호 조회 후 저장
		if(studDto.getDeptNo1()==null && studDto.getDname1()!=null) {
			System.out.println(studDto.getDname1());
			studDto.setDeptNo1(departmentRepo.findByDname(studDto.getDname1()).orElseThrow(()->new Exception("학과명오류")).getDeptno());
		}
		
		if(studDto.getDeptNo2()==null && studDto.getDname2()!=null) {
			studDto.setDeptNo2(departmentRepo.findByDname(studDto.getDname2()).orElseThrow(()->new Exception("학과명오류")).getDeptno());
		}
		//교수번호x, 교수명으로 번호 조회 후 저장
		if(studDto.getProfNo()==null && studDto.getProfName()!=null) {
			studDto.setProfNo(professorRepo.findByNameAndDeptno(studDto.getProfName(),studDto.getDeptNo1()).orElseThrow(()->new Exception("교수명 오류 ")).getProfNo());
		}

		
		
		studentRepo.save(studDto.toEntity());

	}

	@Override
	public void saveProfessor(ProfessorDto profDto) throws Exception {
		if(professorRepo.findById(profDto.getProfNo()).isPresent()) {
			throw new Exception("등록된 교수입니다.");
		}
		//학과번호x,학과명o 학과명으로 학과번호 조회 후 저장
		if(profDto.getDeptno()==null && profDto.getDname()!=null) {
			profDto.setDeptno(departmentRepo.findByDname(profDto.getDname()).orElseThrow(()->new Exception("학과명오류")).getDeptno());
		}
		
		professorRepo.save(profDto.toEntity());

	}

	@Override
	public List<StudentDto> studentListByName(String studName) throws Exception {
		
		return studentRepo.findByName(studName).stream().map((s)->s.toDto()).collect(Collectors.toList());
		
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptName(String deptName) throws Exception {
		
		return univRepo.studentsInDept1ByDeptName(deptName)
					.stream().map(t->{
						StudentDto studDto = t.get(0, Student.class).toDto();
						studDto.setDname1(t.get(1,String.class));
						studDto.setDname2(t.get(2, String.class));
						studDto.setProfName(t.get(3, String.class));
						
						return studDto;
					}).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptNo) throws Exception {
		
		return univRepo.studentsInDept1ByDeptNo(deptNo)
				.stream().map(t->{
					StudentDto studDto = t.get(0, Student.class).toDto();
					studDto.setDname1(t.get(1,String.class));
					studDto.setDname2(t.get(2, String.class));
					studDto.setProfName(t.get(3, String.class));
					
					return studDto;
				}).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptName(String deptName) throws Exception {

		
		return univRepo.studentsInDept2ByDeptName(deptName)
					.stream().map(t->{
						StudentDto studDto = t.get(0, Student.class).toDto();
						studDto.setDname1(t.get(1,String.class));
						studDto.setDname2(t.get(2, String.class));
						studDto.setProfName(t.get(3, String.class));
						
						return studDto;
					}).collect(Collectors.toList());
	
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptNo(Integer deptNo) throws Exception {
		return univRepo.studentsInDept2ByDeptNo(deptNo)
				.stream().map(t->{
					StudentDto studDto = t.get(0, Student.class).toDto();
					studDto.setDname1(t.get(1,String.class));
					studDto.setDname2(t.get(2, String.class));
					studDto.setProfName(t.get(3, String.class));
					
					return studDto;
				}).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListByGrade(Integer grade) throws Exception {
		
		return univRepo.studentsInDept2ByDeptNo(grade)
				.stream().map(t->{
					StudentDto studDto = t.get(0, Student.class).toDto();
					studDto.setDname1(t.get(1,String.class));
					studDto.setDname2(t.get(2, String.class));
					studDto.setProfName(t.get(3, String.class));
			
					return studDto;
				}).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListByNoProfessor() throws Exception {
		
		return univRepo.selectStudentListNoProfessor()
				.stream().map(t->{
					StudentDto studDto = t.get(0, Student.class).toDto();
					studDto.setDname1(t.get(1,String.class));
					studDto.setDname2(t.get(2, String.class));
			
					return studDto;
				}).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListByProfNo(Integer profNo) throws Exception {
		
		return univRepo.selectStudentListByProfNo(profNo)
				.stream().map(t->{
					StudentDto studDto = t.get(0, Student.class).toDto();
					studDto.setDname1(t.get(1,String.class));
					studDto.setDname2(t.get(2, String.class));
					studDto.setProfName(t.get(3, String.class));
			
					return studDto;
				}).collect(Collectors.toList());
	}

	@Override
	public StudentDto studentByStudNo(Integer studeNo) throws Exception {
		Tuple t= univRepo.selectStudentByStudNo(studeNo);
		StudentDto studDto = t.get(0, Student.class).toDto();
		studDto.setDname1(t.get(1,String.class));
		studDto.setDname2(t.get(2, String.class));
		studDto.setProfName(t.get(3, String.class));
		return studDto;
	}

	@Override
	public StudentDto studentByJumin(String jumin) throws Exception {
		Tuple t = univRepo.selectStudentByJumin(jumin);
		StudentDto studDto = t.get(0, Student.class).toDto();
		studDto.setDname1(t.get(1,String.class));
		studDto.setDname2(t.get(2, String.class));
		studDto.setProfName(t.get(3, String.class));
		return studDto;
	}

	@Override
	public ProfessorDto professorByProfNo(Integer profNo) throws Exception {
		Tuple t = univRepo.selectProfessorByProfNo(profNo);
		ProfessorDto profDto = t.get(0, Professor.class).toDto();
		profDto.setDname(t.get(1, String.class));
		return profDto;
	}

	@Override
	public List<ProfessorDto> professorListByProfName(String profName) throws Exception {
		
		return univRepo.selectProfessorListByProfName(profName)
				.stream()
				.map(t->{
					ProfessorDto profDto = t.get(0, Professor.class).toDto();
					profDto.setDname(t.get(1, String.class));
					return profDto;
				}).collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception {
		return univRepo.selectProfessorListByDeptNo(deptNo);
	}
	@Override
	public List<ProfessorDto> professorListByDeptName(String deptName) throws Exception {
		
		return univRepo.selectProfessorListByDeptName(deptName);
	}

	@Override
	public List<ProfessorDto> professorListByPosition(String position) throws Exception {
		
		return univRepo.selectProfessorListByPosition(position);
	}

	@Override
	public DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception {
		
		return departmentRepo.findById(deptNo).orElseThrow(()->new Exception("학과번호 오류")).toDto();
	}

	@Override
	public DepartmentDto departmentByDeptName(String deptName) throws Exception {
		
		return departmentRepo.findByDname(deptName).orElseThrow(()->new Exception("학과명 오류")).toDto();
	}

	@Override
	public List<DepartmentDto> departmentByDeptPart(Integer part) throws Exception {
		
		return departmentRepo.findByPart(part).stream().map(d->d.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<DepartmentDto> dapartmentListByBuild(String build) throws Exception {
	
		return departmentRepo.findByBuild(build).stream().map(d->d.toDto()).collect(Collectors.toList());
	}

}
