package kr.ac.hansung.cse.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Clinic;
import kr.ac.hansung.cse.model.Doctor;
import kr.ac.hansung.cse.model.Reservation;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.HossService;

@Controller
public class ClinicController {
	@Autowired
	private HossService hossService;

	@RequestMapping("/clinics")
	public String getOffices(Model model, Principal principal) {
		Doctor doctor = hossService.getDoctorByDoctor_id(principal.getName());
		
		List<Reservation> reservations = hossService.getAcceptedReservationsByHospital_idOffice_number(doctor.getHospital_id(), doctor.getOffice_number());

		List<User> users = new ArrayList<User>();
		for (Reservation reservation : reservations)
			users.add(hossService.getUserByUser_id(reservation.getUser_id()));

		model.addAttribute("reservations", reservations);
		model.addAttribute("users", users);
		
		List<Clinic> clinics = hossService.getClinicsByDoctor_id(doctor.getDoctor_id());
		model.addAttribute("clinics", clinics);
		
		return "clinics";
	}

	@RequestMapping(value="/clinics/addClinic/{reservation_number}", method=RequestMethod.GET)
	public String addClinic(@PathVariable String reservation_number, Model model, Principal principal) {
		Doctor doctor = hossService.getDoctorByDoctor_id(principal.getName());

		Reservation reservation = hossService.getReservationByReservation_number(reservation_number);
		
		Clinic clinic = new Clinic();
		clinic.setClinic_number(Integer.parseInt(reservation_number));
		clinic.setUser_id(reservation.getUser_id());
		clinic.setDoctor_id(doctor.getDoctor_id());

		model.addAttribute("clinic", clinic);
		
		model.addAttribute("reservation_number", reservation_number);

		return "addClinic";
	}
	
	@RequestMapping(value="/clinics/addClinic/{reservation_number}", method=RequestMethod.POST)
	public String addClinicPost(@PathVariable String reservation_number, @Valid Clinic clinic, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();
			
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "addClinic";
		}
		
		if(!hossService.addClinic(clinic))
			System.out.println("Adding Clinic cannot be done");
		else {
			Reservation reservation = hossService.getReservationByReservation_number(reservation_number);
			reservation.setReservation_status("완료");
			hossService.updateReservation(reservation);
		}
		
		return "redirect:/clinics";
	}

	@RequestMapping(value = "/clinics/updateClinic/{clinic_number}", method = RequestMethod.GET)
	public String updateClinic(@PathVariable String clinic_number, Model model, Principal principal) {
		
		Clinic clinic = hossService.getClinicByClinic_number(clinic_number);
		model.addAttribute("clinic", clinic);

		return "updateClinic";
	}

	@RequestMapping(value = "/clinics/updateClinic", method = RequestMethod.POST)
	public String updateClinicPost(@Valid Clinic clinic, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateReservation";
		}

		if (!hossService.updateClinic(clinic))
			System.out.println("Updating Clinic cannot be done");

		return "redirect:/clinics";
	}
}
