package sg.edu.nus.workshop13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.nus.workshop13.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;


@Controller
public class AddressbookController {
    private static final Logger logger = LoggerFactory.getLogger(AddressbookController.class);

    @GetMapping("/")
    public String contactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @GetMapping("/getContact/{contactId}")
    public String getContact(Model model, @PathVariable(value="contactId") String contactId) {
        logger.info("contactId > " + contactId);
        

        ct.getContactById(model, contactId);     
        return "showContact";
    }

    @PostMapping("/contact")
    public String contactSubmit(@ModelAttribute Contact contact, Model model) {
        long startTime = System.currentTimeMillis();
        logger.info("111 Name > " + contact.getName());
        logger.info("111 Email > " + contact.getEmail());
        logger.info("111 Phone Number > " + contact.getPhoneNumber());
        

        long endTime = System.currentTimeMillis();
        logger.info("Elapsed timing -> contactSubmit " + (endTime -startTime));
        return "showContact";
    }
}