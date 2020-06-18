package kr.ac.hansung.cse.controller;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Doctor;
import kr.ac.hansung.cse.model.Hospital;
import kr.ac.hansung.cse.model.Office;
import kr.ac.hansung.cse.service.HossService;

@Controller
@RequestMapping("/managing")
public class ManagingController {

	@Autowired
	private HossService hossService;

	@RequestMapping
	public String managingPage() {

		return "managing";

	}

	@RequestMapping("/offices")
	public String getOffices(Model model, Principal principal) {

		List<Office> offices = hossService.getOfficesByHospital_id(principal.getName());

		model.addAttribute("offices", offices);

		return "offices";
	}

	@RequestMapping(value = "/offices/addOffice", method = RequestMethod.GET)
	public String addOffice(Model model, Principal principal) {

		Office office = new Office();
		office.setHospital_id(principal.getName());

		model.addAttribute("office", office);

		return "addOffice";
	}

	@RequestMapping(value = "/offices/addOffice", method = RequestMethod.POST)
	public String addOfficePost(@Valid Office office, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addOffice";
		}

		if (!hossService.addOffice(office))
			System.out.println("Adding Office cannot be done");

		return "redirect:/managing/offices";
	}

	@RequestMapping(value = "/offices/deleteOffice/{office_number}", method = RequestMethod.GET)
	public String deleteOffice(@PathVariable String office_number, Principal principal) {

		if (!hossService.deleteOffice(principal.getName(), office_number))
			System.out.println("Deleting Office cannot be done");

		return "redirect:/managing/offices";
	}

	@RequestMapping(value = "/offices/updateOffice/{office_number}", method = RequestMethod.GET)
	public String updateOffice(@PathVariable String office_number, Model model, Principal principal) {

		Office office = hossService.getOfficeByHospital_idOffice_number(principal.getName(), office_number);
		model.addAttribute("office", office);

		return "updateOffice";
	}

	@RequestMapping(value = "/offices/updateOffice", method = RequestMethod.POST)
	public String updateOfficePost(@Valid Office office, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateOffice";
		}

		if (!hossService.updateOffice(office))
			System.out.println("Updating Office cannot be done");

		return "redirect:/managing/offices";
	}

	@RequestMapping("/doctors")
	public String getDoctors(Model model, Principal principal) {

		List<Doctor> doctors = hossService.getDoctorsByHospital_id(principal.getName());

		model.addAttribute("doctors", doctors);

		return "doctors";
	}

	@RequestMapping(value = "/doctors/addDoctor", method = RequestMethod.GET)
	public String addDoctor(Model model, Principal principal) {

		List<Office> offices = hossService.getOfficesByHospital_id(principal.getName());
		model.addAttribute("offices", offices);

		Doctor doctor = new Doctor();
		doctor.setHospital_id(principal.getName());

		model.addAttribute("doctor", doctor);

		return "addDoctor";
	}

	@RequestMapping(value = "/doctors/addDoctor", method = RequestMethod.POST)
	public String addDoctorPost(@Valid Doctor doctor, BindingResult result, HttpServletResponse response)
			throws Exception {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addDoctor";
		}

		if (hossService.checkDoctorExistence(doctor.getDoctor_id())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 존재하는 id 입니다.'); location.href='addDoctor';</script>");
			out.flush();
			return "addDoctor";
		}

		if (!hossService.addDoctor(doctor))
			System.out.println("Adding Doctor cannot be done");

		return "redirect:/managing/doctors";
	}

	@RequestMapping(value = "/doctors/deleteDoctor/{doctor_id}", method = RequestMethod.GET)
	public String deleteDoctor(@PathVariable String doctor_id) {

		if (!hossService.deleteDoctor(doctor_id))
			System.out.println("Deleting Doctor cannot be done");

		return "redirect:/managing/doctors";
	}

	@RequestMapping(value = "/doctors/updateDoctor/{doctor_id}", method = RequestMethod.GET)
	public String updateDoctor(@PathVariable String doctor_id, Model model, Principal principal) {
		List<Office> offices = hossService.getOfficesByHospital_id(principal.getName());
		model.addAttribute("offices", offices);

		Doctor doctor = hossService.getDoctorByDoctor_id(doctor_id);
		model.addAttribute("doctor", doctor);

		return "updateDoctor";
	}

	@RequestMapping(value = "/doctors/updateDoctor", method = RequestMethod.POST)
	public String updateOfficePost(@Valid Doctor doctor, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateDoctor";
		}

		if (!hossService.updateDoctor(doctor))
			System.out.println("Updating Doctor cannot be done");

		return "redirect:/managing/doctors";
	}

	@RequestMapping(value = "/updateHours", method = RequestMethod.GET)
	public String updateHours(Model model, Principal principal) {

		Hospital hospital = hossService.getHospitalByHospital_id(principal.getName());
		model.addAttribute("hospital", hospital);

		return "updateHours";
	}

	@RequestMapping(value = "/updateHours", method = RequestMethod.POST)
	public String updateHoursPost(@Valid Hospital hospital, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateHours";
		}

		if (!hossService.updateHospital(hospital))
			System.out.println("Updating Hours cannot be done");

		return "managing";
	}

}
