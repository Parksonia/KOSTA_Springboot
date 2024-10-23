package com.kosta.univdsl.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.univdsl.dto.ProfessorDto;
import com.kosta.univdsl.entity.QDepartment;
import com.kosta.univdsl.entity.QProfessor;
import com.kosta.univdsl.entity.QStudent;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UnivRepository {

	@Autowired
	JPAQueryFactory jpaQueryFactory;

	public List<Tuple>studentsInDept1ByDeptName(String deptName) throws Exception {
	
		// student dto가 가진 모든 정보가 필요하기 때문에 4번의 조인을 진행함
		// 프론트에서 필요한 모든 정보를 보여주기 위함
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
	
		QProfessor professor =QProfessor.professor;
		
		
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.join(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(dept1.dname.eq(deptName)).fetch();
	}
	public List<Tuple>studentsInDept1ByDeptNo(Integer deptNo) throws Exception {
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
		QProfessor professor =QProfessor.professor;
		
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.join(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(student.deptNo1.eq(deptNo)).fetch();
	}

	public List<Tuple>studentsInDept2ByDeptName(String deptName) throws Exception {
		
		// student dto가 가진 모든 정보가 필요하기 때문에 4번의 조인을 진행함
		// 프론트에서 필요한 모든 정보를 보여주기 위함
		// 교수나 부전공의 경우에 데이터가 없는 경우도 있으니 left join해야함
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
		QProfessor professor =QProfessor.professor;
		
		
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.join(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(dept2.dname.eq(deptName)).fetch();
	}
	public List<Tuple>studentsInDept2ByDeptNo(Integer deptNo) throws Exception {
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
		QProfessor professor =QProfessor.professor;
		
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.join(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(student.deptNo2.eq(deptNo)).fetch();
	}
	public List<Tuple> selectStudentListByGrade(Integer grade) throws Exception {
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
		QProfessor professor =QProfessor.professor;
	
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.join(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(student.grade.eq(grade))
				.fetch();
				
	}
	public List<Tuple>selectStudentListNoProfessor() throws Exception {
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
	
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname)
				.from(student)
				.leftJoin(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.where(student.profNo.isNull())
				.fetch();
	}

	public Tuple selectStudentByStudNo(Integer studNo)throws Exception {
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
		QProfessor professor =QProfessor.professor;
	
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.leftJoin(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(student.studNo.eq(studNo))
				.fetchOne();
	}
	public Tuple selectStudentByJumin(String jumin) throws Exception{
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
		QProfessor professor =QProfessor.professor;
	
		return jpaQueryFactory.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.leftJoin(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(student.jumin.eq(jumin))
				.fetchOne();
	}
	public List<Tuple> selectStudentListByProfNo(Integer profNo) throws Exception {
		QStudent student = QStudent.student;
		QDepartment dept1 = new QDepartment("department1");
		QDepartment dept2 = new QDepartment("department2");
		QProfessor professor =QProfessor.professor;
		
		
		return jpaQueryFactory
				.select(student,dept1.dname,dept2.dname,professor.name)
				.from(student)
				.leftJoin(dept1)
				.on(student.deptNo1.eq(dept1.deptno))
				.leftJoin(dept2)
				.on(student.deptNo2.eq(dept2.deptno))
				.leftJoin(professor)
				.on(student.profNo.eq(professor.profNo))
				.where(student.profNo.eq(profNo))
				.fetch();
			
	}
	public Tuple selectProfessorByProfNo(Integer profNo) throws Exception{
		
		QDepartment dept = QDepartment.department;
		QProfessor professor = QProfessor.professor;
	
		return jpaQueryFactory.select(professor,dept.dname)
				.from(professor)
				.leftJoin(dept)
				.on(professor.deptno.eq(dept.deptno))
				.where(professor.profNo.eq(profNo))
				.fetchOne();
	}
	
	public List<Tuple> selectProfessorListByProfName(String profName) throws Exception {
		QDepartment dept = QDepartment.department;
		QProfessor professor = QProfessor.professor;
	
		return jpaQueryFactory.select(professor,dept.dname)
				.from(professor)
				.leftJoin(dept)
				.on(professor.deptno.eq(dept.deptno))
				.where(professor.name.eq(profName))
				.fetch();
	}
	public List<ProfessorDto> selectProfessorListByDeptNo(Integer deptNo) throws Exception {
		QDepartment dept = QDepartment.department;
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(Projections.bean(ProfessorDto.class,
				professor.profNo,
				professor.name,
				professor.id,
				professor.position,
				professor.hiredate,
				professor.bonus,
				professor.email,
				professor.hpage,
				professor.deptno,
				dept.dname))
				.from(professor)
				.leftJoin(dept)
				.on(professor.deptno.eq(dept.deptno))
				.where(professor.deptno.eq(deptNo))
				.fetch();
	
	}
	public List<ProfessorDto> selectProfessorListByDeptName(String deptName) throws Exception {
		QDepartment dept = QDepartment.department;
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(Projections.bean(ProfessorDto.class,
				professor.profNo,
				professor.name,
				professor.id,
				professor.position,
				professor.hiredate,
				professor.bonus,
				professor.email,
				professor.hpage,
				professor.deptno,
				dept.dname))
				.from(professor)
				.leftJoin(dept)
				.on(professor.deptno.eq(dept.deptno))
				.where(dept.dname.eq(deptName))
				.fetch();
	
	}
	public List<ProfessorDto> selectProfessorListByPosition(String position) throws Exception {
		QDepartment dept = QDepartment.department;
		QProfessor professor = QProfessor.professor;
		
		return jpaQueryFactory.select(Projections.bean(ProfessorDto.class,
				professor.profNo,
				professor.name,
				professor.id,
				professor.position,
				professor.hiredate,
				professor.bonus,
				professor.email,
				professor.hpage,
				professor.deptno,
				dept.dname))
				.from(professor)
				.leftJoin(dept)
				.on(professor.deptno.eq(dept.deptno))
				.where(professor.position.eq(position))
				.fetch();
	
	}
}
