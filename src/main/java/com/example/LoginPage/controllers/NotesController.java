package com.example.LoginPage.controllers;

import com.example.LoginPage.models.Notes;
import com.example.LoginPage.models.User;
import com.example.LoginPage.repository.NotesRepository;
import com.example.LoginPage.services.CustomUserDetailService;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotesController {

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/notes")
    public String notes(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailService.findUserByEmail(auth.getName());
        model.addAttribute("notes", notesRepository.findAll());
        model.addAttribute("currentUser", user);
        model.addAttribute("fullName", "Welcome " + user.getFullname());
        model.addAttribute("adminMessage", "Content available only for users with Admin role");
        return "notes";
    }

    @GetMapping("/notes/create")
    public String create(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailService.findUserByEmail(auth.getName());
        model.addAttribute("currentUser", user);
        model.addAttribute("fullName", "Welcome " + user.getFullname());
        model.addAttribute("adminMessage", "Content available only for users with Admin role");
        return "create";
    }

    @PostMapping("/notes/save")
    public String save(@RequestParam String title, @RequestParam String content) {
        Notes notes = new Notes();
        notes.setTitle(title);
        notes.setContent(content);
        notes.setUpdated(new Date(System.currentTimeMillis()));
        notesRepository.save(notes);
        return "redirect:/notes/show/" + notes.getId();
    }

    @GetMapping("/notes/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailService.findUserByEmail(auth.getName());
        model.addAttribute("currentUser", user);
        model.addAttribute("fullName", "Welcome " + user.getFullname());
        model.addAttribute("note", notesRepository.findById(id).orElse(null));
        return "show";
    }

    @PostMapping("/notes/delete")
    public String delete(@RequestParam Long id) {
        Notes notes = notesRepository.findById(id).orElse(null);
        notesRepository.delete(notes);
        return "redirect:/notes";
    }

    @GetMapping("/notes/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDetailService.findUserByEmail(auth.getName());
        model.addAttribute("currentUser", user);
        model.addAttribute("fullName", "Welcome " + user.getFullname());
        model.addAttribute("note", notesRepository.findById(id).orElse(null));
        return "edit";
    }

    @PostMapping("/notes/update")
    public String update(@RequestParam Long id, @RequestParam String title, @RequestParam String content) {
        Notes notes = notesRepository.findById(id).orElse(null);
        notes.setTitle(title);
        notes.setContent(content);
        notes.setUpdated(new Date(System.currentTimeMillis()));
        notesRepository.save(notes);
        return "redirect:/notes/show/" + notes.getId();
    }
}
