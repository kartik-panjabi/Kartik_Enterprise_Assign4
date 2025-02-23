package com.humber.assign4;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository participantRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("participant", new Participant());
        return "registration_form";
    }

    @PostMapping("/save")
    public String saveParticipant(@Valid @ModelAttribute("participant") Participant participant, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration_form";
        }
        participantRepository.save(participant);
        return "redirect:/participants/list";
    }

    @GetMapping("/list")
    public String listParticipants(Model model) {
        model.addAttribute("participants", participantRepository.findAll());
        return "participant_list";
    }

    @GetMapping("/reset")
    public String resetForm() {
        return "redirect:/participants/register";
    }
}
