package portfolioDev.portfolio.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import portfolioDev.portfolio.controller.form.ContactForm;
import portfolioDev.portfolio.domain.Contact;
import portfolioDev.portfolio.repository.ContactRepository;
import portfolioDev.portfolio.service.EmailService;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final EmailService emailService;
    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(EmailService emailService, ContactRepository contactRepository) {
        this.emailService = emailService;
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public String showContactForm(Model model) {
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }

    // ContactController.java

    // ContactController.java

    @PostMapping("/submit")
    public String handleContactFormSubmission(ContactForm contactForm, Model model) {
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setMessage(contactForm.getMessage());
        contactRepository.save(contact);

        try {
            // 받는 사람의 이메일을 내 이메일 주소로 설정
            String to = "a01053522105@gmail.com";

            // 익명 처리: 사용자의 이름, 이메일을 메시지에 추가
            String anonymousMessage = "이름: " + (contactForm.getName().isEmpty() ? "익명" : contactForm.getName()) + "\n" +
                    "이메일: " + (contactForm.getEmail().isEmpty() ? "익명" : contactForm.getEmail()) + "\n" +
                    "메시지: " + contactForm.getMessage();

            emailService.sendEmail("a01053522105@gmail.com", to, "새로운 연락 메시지", anonymousMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("error", "이메일 전송 중 오류가 발생했습니다.");
            return "contact";
        }

        return "redirect:/contact?success=true";
    }

}