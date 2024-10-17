package com.kosta.univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.univ.dto.DepartmentDto;
import com.kosta.univ.dto.ProfessorDto;
import com.kosta.univ.dto.StudentDto;
import com.kosta.univ.service.UnivService;

@RestController

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

		
//		@GetMapping("/studInfoByName") //학생명으로 학생조회
//		@GetMapping("/studInfoByNo") //학번으로 학생조회
//		@GetMapping("/deptInfoByName") //학과이름으로 학과조회
//		@GetMapping("/deptInfoByNo") //학과번호로 학과조회
//		@GetMapping("/deptInfoByPart") //파트로 학과조회
//		@GetMapping("/deptInfoByBuild")//건물로 학과조회
//		
//		@GetMapping("/studInDeptNo1")// 학과번호에 소속된 학생조회(주전공)
//		@GetMapping("/studInDeptName1")//학과명에 소속된 학생조회(주전공)
//		@GetMapping("/studInDeptNo2")// 학과번호에 소속된 학생조회(부전공)
//		@GetMapping("/studInDeptName2")//학과명에 소속된 학생조회(부전공)
//		@GetMapping("/studInProfNo")// 지도교수번호에 소속된 학생조회(부전공)
//		
//		
//		@GetMapping("/profInfoByNo") //교수번호로 교수조회
//		@GetMapping("/profInfoByName") //교수이름으로 교수조회
//		@GetMapping("/profInDeptNo") //학과번호에 소속된 교수조회
//		@GetMapping("/profInDeptName") //학과명에 소속된 교수조회
//		
//		
		
		
		
}
