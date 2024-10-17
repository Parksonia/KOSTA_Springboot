package com.kosta.univ.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptNmae(String deptName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptNmae(String deptName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptNo(Integer deptNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListByGrade(Integer grade) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListByNoProfessor() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto studentByStudNo(Integer studeNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto studentByJumin(String jumin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessorDto professorByProfNo(Integer profNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfessorDto> professorListByProfName(String profName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfessorDto> professorListByDeptNo(Integer deptNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfessorDto> professorListByDeptName(String deptName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfessorDto> professorListByPosition(String position) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDto departmentByDeptNo(Integer deptNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDto departmentByDeptName(String deptName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentDto> dapartmentListByPart(String part) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentDto> dapartmentListByBuild(String build) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
