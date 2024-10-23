package com.kosta.univdsl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.univdsl.dto.DepartmentDto;
import com.kosta.univdsl.dto.ProfessorDto;
import com.kosta.univdsl.dto.StudentDto;
import com.kosta.univdsl.service.UnivService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UnivController {
		
	 
	@Autowired
	private UnivService univService;
	
		@PostMapping("/regDept")
		public ResponseEntity<String> registDepartment(@RequestBody DepartmentDto deptDto) {
			try {
				univService.saveDepartment(deptDto);
				return new ResponseEntity<String>("true",HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
			}
		}
		
		@PostMapping("/regStud")
		public ResponseEntity<String> registStudent(@RequestBody StudentDto studDto) {
			try {
				univService.saveStudent(studDto);
				return new ResponseEntity<String>("true",HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
			}
		}
		 
		@PostMapping("/regProf")
		public ResponseEntity<String> registProfessor(@RequestBody ProfessorDto profDto) {
			try {
				univService.saveProfessor(profDto);
				return new ResponseEntity<String>("true",HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<String>("false",HttpStatus.BAD_REQUEST);
			}
		}

		
		@GetMapping("/studInfoByName") //학생명으로 학생조회
		public ResponseEntity<List<StudentDto>> studentInfoByName(@RequestParam ("studName")String studName) {
			   try {
				   List<StudentDto> student = univService.studentListByName(studName);
				   return new ResponseEntity<List<StudentDto>>(student,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}  
		}
		@GetMapping("/studInfoByNo") //학번으로 학생조회
		public ResponseEntity<StudentDto> studnetByStudentNo(@RequestParam("studNo")Integer studNo) {
			   try {
				   StudentDto student = univService.studentByStudNo(studNo);
				   return new ResponseEntity<StudentDto>(student,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<StudentDto>(HttpStatus.BAD_REQUEST);
			}  

		}
		@GetMapping("/deptInfoByName") //학과이름으로 학과조회
		public ResponseEntity<DepartmentDto> departmentByDeptName(@RequestParam("dName")String dName) {
			   try {
				   DepartmentDto department = univService.departmentByDeptName(dName);
				   return new ResponseEntity<DepartmentDto>(department,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<DepartmentDto>(HttpStatus.BAD_REQUEST);
			}  
		}
		@GetMapping("/deptInfoByNo") //학과번호로 학과조회
		public ResponseEntity<DepartmentDto> departmentByDeptNo(@RequestParam("deptNo")Integer deptNo) {
			   try {
				   DepartmentDto department = univService.departmentByDeptNo(deptNo);
				   return new ResponseEntity<DepartmentDto>(department,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<DepartmentDto>(HttpStatus.BAD_REQUEST);
			}  
		}
		
		@GetMapping("/deptInfoByPart") //파트로 학과조회
		public ResponseEntity<List<DepartmentDto>> departmentByDeptPart(@RequestParam("deptPart")Integer deptPart) {
			   try {
				   List<DepartmentDto> departments = univService.departmentByDeptPart(deptPart);
				   return new ResponseEntity<List<DepartmentDto>>(departments,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<DepartmentDto>>(HttpStatus.BAD_REQUEST);
			}  
		}
		
		@GetMapping("/deptInfoByBuild")//건물로 학과조회
		public ResponseEntity<List<DepartmentDto>> departmentByDeptBuild(@RequestParam("deptBuild")String build) {
			   try {
				   List<DepartmentDto> departments = univService.dapartmentListByBuild(build);
				   return new ResponseEntity<List<DepartmentDto>>(departments,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<DepartmentDto>>(HttpStatus.BAD_REQUEST);
			}  
		}

//		
		@GetMapping("/studInDeptNo1")// 학과번호에 소속된 학생조회(주전공)
		public ResponseEntity<List<StudentDto>> studentListInDeptNo1(@RequestParam("deptNo1")Integer deptNo1){
			   try {
				   List<StudentDto> studentList = univService.studentListInDept1ByDeptNo(deptNo1);
				   return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}  
		
		}
		
		@GetMapping("/studInDeptName1")//학과명에 소속된 학생조회(주전공)
		public ResponseEntity<List<StudentDto>> studentListInDeptName1(@RequestParam("dName1")String deptName){
			   try {
				   List<StudentDto> studentList = univService.studentListInDept1ByDeptName(deptName);
				   return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}  
		
		}
		@GetMapping("/studInDeptNo2")// 학과번호에 소속된 학생조회(부전공)
		public ResponseEntity<List<StudentDto>> studentListInDeptNo2(@RequestParam("deptNo2")Integer deptNo2){
			   try {
				   List<StudentDto> studentList = univService.studentListInDept2ByDeptNo(deptNo2);
				   return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}  
		
		}
		
		@GetMapping("/studInDeptName2")//학과명에 소속된 학생조회(부전공)
		public ResponseEntity<List<StudentDto>> studentListInDeptName2(@RequestParam("dName2")String deptName){
			   try {
				   List<StudentDto> studentList = univService.studentListInDept2ByDeptName(deptName);
				   return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}  
		
		}
		
		@GetMapping("/studInProfNo")// 지도교수번호에 소속된 학생조회
		public ResponseEntity<List<StudentDto>> studentListInProfNo(@RequestParam("profNo")Integer profNo){
			   try {
				   List<StudentDto> studentList = univService.studentListByProfNo(profNo);
				   return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}  
		
		}
		@GetMapping("/studInNoProf")// 지도교수가 없는학생조회
		public ResponseEntity<List<StudentDto>> studentListInNoProf(){
			   try {
				   List<StudentDto> studentList = univService.studentListByNoProfessor();
				   return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}  
		
		}
		
		@GetMapping("/profInfoByNo") //교수번호로 교수조회
		public ResponseEntity<ProfessorDto> professorByProfNo(@RequestParam("profNo")Integer profNo) {
			   try {
				   ProfessorDto professor = univService.professorByProfNo(profNo);
				   return new ResponseEntity<ProfessorDto>(professor,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<ProfessorDto>(HttpStatus.BAD_REQUEST);
			}  
		}
		
		@GetMapping("/profInfoByName") //교수이름으로 교수조회
		public ResponseEntity<List<ProfessorDto>> professorByProfName(@RequestParam("profName")String profName) {
			   try {
				   List<ProfessorDto> professor = univService.professorListByProfName(profName);
				   return new ResponseEntity<List<ProfessorDto>>(professor,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);
			}  
		}
		
		@GetMapping("/profInDeptNo") //학과번호에 소속된 교수조회
		public ResponseEntity<List<ProfessorDto>> professorListInDeptNo(@RequestParam("deptNo")Integer deptNo) {
			   try {
				   List<ProfessorDto> professor = univService.professorListByDeptNo(deptNo);
				   return new ResponseEntity<List<ProfessorDto>>(professor,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);
			}  
		}
		
		@GetMapping("/profInDeptName") //학과명에 소속된 교수조회
		public ResponseEntity<List<ProfessorDto>> professorListInDeptName(@RequestParam("deptName")String deptName) {
			   try {
				   List<ProfessorDto> professor = univService.professorListByDeptName(deptName);
				   return new ResponseEntity<List<ProfessorDto>>(professor,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);
			}  
		}
		@GetMapping("/profInPosition") //학과명에 소속된 교수조회
		public ResponseEntity<List<ProfessorDto>> professorListInPosition(@RequestParam("position")String position) {
			   try {
				   List<ProfessorDto> professor = univService.professorListByPosition(position);
				   return new ResponseEntity<List<ProfessorDto>>(professor,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<ProfessorDto>>(HttpStatus.BAD_REQUEST);
			}  
		}
		
		//학년으로 학생목록 조회
		@GetMapping("/studInfoByGrade")
		public ResponseEntity<List<StudentDto>> studentInGrade(@RequestParam("grade")Integer grade){
			   try {
				   List<StudentDto> studentList = univService.studentListByGrade(grade);
				   return new ResponseEntity<List<StudentDto>>(studentList,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<List<StudentDto>>(HttpStatus.BAD_REQUEST);
			}
		}
		
		//주민번호로 학생 조회
		@PostMapping("/studInfoByJumin")
		public ResponseEntity<StudentDto> studentInJumin(@RequestParam("jumin")String jumin){
			   try {
				   StudentDto student = univService.studentByJumin(jumin);
				   return new ResponseEntity<StudentDto>(student,HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<StudentDto>(HttpStatus.BAD_REQUEST);
			}
		}
		
		
		
		
}
