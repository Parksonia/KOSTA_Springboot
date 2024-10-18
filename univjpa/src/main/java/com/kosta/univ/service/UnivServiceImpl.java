package com.kosta.univ.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.ProfessionRepository;
import com.kosta.univ.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {
	
	private final DepartmentRepository deptRepo;
	private final StudentRepository studRepo;
	private final ProfessionRepository profRepo;

	
	@Override
	public void saveDepartment(DepartmentDto depDto) throws Exception {
		deptRepo.save(depDto.toEntity());
	}

	@Override
	public void saveStudent(StudentDto studDto) throws Exception {
		studRepo.save(studDto.toEntity());

	}

	@Override
	public void saveProfessor(ProfessorDto profDto) throws Exception {
		profRepo.save(profDto.toEntity());
	}

	@Override
	public List<StudentDto> studentListByName(String studName) throws Exception {
		
		return studRepo.findByName(studName).stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptName(String deptName) throws Exception {
		
		return studRepo.findByDepartment1_Dname(deptName).stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptNo) throws Exception {
		
		return studRepo.findByDepartment1_Deptno(deptNo).stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptName(String deptName) throws Exception {
		
		return studRepo.findByDepartment2_Dname(deptName).stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptNo(Integer deptNo) throws Exception {
		Department dept = deptRepo.findById(deptNo).orElseThrow(()->new Exception("학과번호오류"));
		 return dept.getStudList2().stream().map((s)->s.toDto()).collect(Collectors.toList());
		//return studRepo.findByDepartment2_Deptno(deptNo).stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListByGrade(Integer grade) throws Exception {
			
		return studRepo.findByGrade(grade).stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListByNoProfessor() throws Exception {
		
		return studRepo.findByProfessor_ProfNoIsNull().stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<StudentDto> studentListByProfNo(Integer profNo) throws Exception {
		Professor prof = profRepo.findById(profNo).orElseThrow(()->new Exception("교수 조회 실패"));
		return prof.getStudent().stream().map((s)->s.toDto()).collect(Collectors.toList());
	}

	@Override
	public StudentDto studentByStudNo(Integer studeNo) throws Exception {
			Student student = studRepo.findById(studeNo).orElseThrow(()->new Exception("학생조회 실패"));
		return student.toDto();
	}
	
	
	 @Override
	 public StudentDto studentInfoByStudName(String studName) throws Exception {
	  
	  //findByName을 사용하기 때문에 중복으로 만들지 못하고 List형태를 활용 
	  //.stream -> List,Set과 같은 컬렉션의 여러 값을 처리하기 위해서 사용 
	  //.findFirst ->Stream의 첫번째 요소 반환 
		 Student student = studRepo.findByName(studName) .stream() .findFirst() .orElseThrow(() -> new Exception("학생조회 오류"));
	  
	  return student.toDto();
	  
	  }
	
	@Override
	public StudentDto studentByJumin(String jumin) throws Exception {
		
		return studRepo.findByJumin(jumin).orElseThrow(()->new Exception("학생 조회 실패")).toDto();
	}

	@Override
	public ProfessorDto professorByProfNo(Integer profNo) throws Exception {		
		return profRepo.findById(profNo).orElseThrow(()->new Exception("교수조회실패")).toDto();
	}

	@Override
	public List<ProfessorDto> professorListByProfName(String profName) throws Exception {
	
		return profRepo.findByName(profName).stream().map((p)->p.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception {
		Department dept = deptRepo.findById(deptNo).orElseThrow(()->new Exception("학과번호조회 오류"));
		
		return dept.getProfList().stream().map((p)->p.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDto> professorListByDeptName(String deptName) throws Exception {
		Department dept = deptRepo.findByDname(deptName).orElseThrow(()->new Exception("학과번호조회 오류"));
		return dept.getProfList().stream().map((p)->p.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<ProfessorDto> professorListByPosition(String position) throws Exception {
		
		return profRepo.findByPosition(position).stream().map((p)->p.toDto()).collect(Collectors.toList());
	}

	@Override
	public DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception {
		
		return	deptRepo.findById(deptNo).orElseThrow(()->new Exception("학과 조회 오류")).toDto();
	}

	@Override
	public DepartmentDto departmentByDeptName(String deptName) throws Exception {
		
		return deptRepo.findByDname(deptName).orElseThrow(()->new Exception("학과 조회 오류")).toDto();
	}
	@Override
	public List<DepartmentDto> departmentByDeptPart(Integer part) throws Exception {
		
		return deptRepo.findByPart(part).stream().map((d)->d.toDto()).collect(Collectors.toList());
	}

	@Override
	public List<DepartmentDto> dapartmentListByBuild(String build) throws Exception {
		return deptRepo.findByBuild(build).stream().map((d)->d.toDto()).collect(Collectors.toList());
	}





}
