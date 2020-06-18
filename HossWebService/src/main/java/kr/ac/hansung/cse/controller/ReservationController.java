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

import kr.ac.hansung.cse.model.Office;
import kr.ac.hansung.cse.model.Reservation;
import kr.ac.hansung.cse.model.User;
import kr.ac.hansung.cse.service.HossService;

@Controller
public class ReservationController {

	@Autowired
	private HossService hossService;

	@RequestMapping("/reservations")
	public String getReservations(Model model, Principal principal) {

		List<Reservation> reservations = hossService.getWaitingReservationsByHospital_id(principal.getName());

		List<User> users = new ArrayList<User>();
		for (Reservation reservation : reservations)
			users.add(hossService.getUserByUser_id(reservation.getUser_id()));

		model.addAttribute("reservations", reservations);
		model.addAttribute("users", users);

		return "reservations";
	}

	@RequestMapping(value = "/reservations/updateReservation/{reservation_number}", method = RequestMethod.GET)
	public String updateReservation(@PathVariable String reservation_number, Model model, Principal principal) {

		List<Office> offices = hossService.getOfficesByHospital_id(principal.getName());
		model.addAttribute("offices", offices);

		Reservation reservation = hossService.getReservationByReservation_number(reservation_number);
		model.addAttribute("reservation", reservation);

		return "updateReservation";
	}

	@RequestMapping(value = "/reservations/updateReservation", method = RequestMethod.POST)
	public String updateReservationPost(@Valid Reservation reservation, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "updateReservation";
		}

		if (!hossService.updateReservation(reservation))
			System.out.println("Updating Reservation cannot be done");

		return "redirect:/reservations";
	}
}
