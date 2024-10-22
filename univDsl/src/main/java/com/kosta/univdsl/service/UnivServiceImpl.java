package com.kosta.univdsl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.univdsl.dto.DepartmentDto;
import com.kosta.univdsl.dto.ProfessorDto;
import com.kosta.univdsl.dto.StudentDto;
import com.kosta.univdsl.repository.DepartmentRepository;
import com.kosta.univdsl.repository.ProfessorRepository;
import com.kosta.univdsl.repository.StudentRepository;
import com.kosta.univdsl.repository.UnivRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnivServiceImpl implements UnivService {

	
	private final UnivRepository univRepo;
	private final DepartmentRepository departmentRepo;
	private final ProfessorRepository professorRepo;
	private final StudentRepository studentRepo;
	
	@Override
	public void saveDepartment(DepartmentDto depDto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveStudent(StudentDto studDto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveProfessor(ProfessorDto profDto) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StudentDto> studentListByName(String studName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDto studentInfoByStudName(String studName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptName(String deptName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListInDept1ByDeptNo(Integer deptNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentDto> studentListInDept2ByDeptName(String deptName) throws Exception {
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
	public List<StudentDto> studentListByProfNo(Integer profNo) throws Exception {
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
	public List<DepartmentDto> departmentByDeptPart(Integer part) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentDto> dapartmentListByBuild(String build) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
