package com.zeromass.springbootwebappproject.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.zeromass.springbootwebappproject.dao.AdminDao;
import com.zeromass.springbootwebappproject.dao.DepartmentDao;
import com.zeromass.springbootwebappproject.dao.StudentDao;
import com.zeromass.springbootwebappproject.dto.Admin;
import com.zeromass.springbootwebappproject.dto.Department;
import com.zeromass.springbootwebappproject.dto.Student;
import com.zeromass.springbootwebappproject.service.AdminService;
import com.zeromass.springbootwebappproject.service.DepartmentService;
import com.zeromass.springbootwebappproject.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private AdminService adminService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentDao studentDao;

	@GetMapping("/")
	public String index() {

		return "index";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/register")
	public String register() {

		return "register";
	}

	@GetMapping("/home")
	public String home() {

		return "home";
	}

	@GetMapping("/departmentregister")
	public String departmentregister() {
		return "departmentregister";
	}

	@GetMapping("/studentregister")
	public String studentregister() {
		return "studentregister";
	}

	@PostMapping("/saveAdmin")
	public String saveAdmin(@ModelAttribute Admin admin) {

		adminService.saveAdmin(admin);
		return "redirect:/login";
	}

	@GetMapping("/loginAdmin")
	public String loginAdmin(@ModelAttribute Admin admin) {

		List<Admin> admins = adminDao.getAllAdmin();

		if (admins != null) {
			for (Admin admin2 : admins) {

				if ((admin2.getAdminUserName().equalsIgnoreCase(admin.getAdminUserName()))
						&& (admin2.getAdminPassword().equalsIgnoreCase(admin.getAdminPassword()))) {

					return "redirect:/home";
				}
			}
		}
		return null;
	}

	@PostMapping("/saveDepartment")
	public String saveDepartMent(@ModelAttribute Department department) {

		departmentService.saveDepartment(department);
		return "redirect:/home";
	}

	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute Student student, HttpServletRequest httpServletRequest) {

		String name = httpServletRequest.getParameter("departmentname");

		for (Department department : departmentDao.getAllDepartmentData()) {

			if (department.getDepartmentName().equalsIgnoreCase(name)) {

				student.setDepartment(department);

				studentService.saveStudent(student);

				return "redirect:/home";
			}
		}
		return null;
	}

	@GetMapping("/studentreport")
	public String studentReport() throws FileNotFoundException {

		System.out.println("this is excel report");

		// Create blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workbook.createSheet(" Student Info ");

		// Create row object
		XSSFRow row;

		List<Student> students = studentDao.getAllStudents();

		// This data needs to be written (Object[])

		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();

		int studentId = 1;

		for (Student student : students) {

			if (studentId == 1) {
				empinfo.put("" + studentId + "",
						new Object[] { "Student Name", "Student Roll Number", "Department Name" });
			}
			studentId++;

			empinfo.put("" + studentId + "", new Object[] { "" + student.getStudentName() + "",
					"" + student.getStudentRollNumber() + "", "" + student.getDepartment().getDepartmentName() + "" });

		}

		// Iterate over data and write to sheet
		Set<String> keyid = empinfo.keySet();
		int rowid = 0;

		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("D:\\zeromass.xlsx"));

		try {
			workbook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/home";
	}

	// departmentdisplay
	@GetMapping("/displaydepartment")
	public String displayedepartment(Model model) {

		model.addAttribute("departments", departmentDao.getAllDepartmentData());
		return "displayedepartment";
	}

	//departmentdisplay
	@GetMapping("/displaystudent")
	public String displaystudent(Model model) {

		model.addAttribute("students", studentDao.getAllStudents());
		return "displaystudent";
	}

	@GetMapping("/updatestudent")
	public String updatestudent(Model model,HttpServletRequest httpServletRequest) {

		int id = Integer.parseInt(httpServletRequest.getParameter("id"));
		
		model.addAttribute("updatestudentdata", studentDao.getStudentById(id));
		
		return "updatestudent";
	}
	
	@PutMapping("/updateStudentDetails")
	public String updateStudentDetails(@ModelAttribute Student student) {
		
		studentDao.updateStudent(student);
		
		return "redirect:/displaystudent";
	}
}
