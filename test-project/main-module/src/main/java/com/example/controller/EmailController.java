package com.example.controller;

import com.example.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Path;
import java.util.List;

@Slf4j
@RestController
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        List<String> toList = List.of("cw.im@okestro.com", "bbq019@naver.com");
        String subject = "Test Email with Attachment";
        String text = "Hello! This is a test email with an attachment.";
        String attachmentPath = "static/index.html";

        Flux.fromIterable(toList)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(to -> {
                    try {
                        ClassPathResource resource = new ClassPathResource(attachmentPath);
                        Path path = resource.getFile().toPath();
                        emailService.sendEmailWithAttachment(to, subject, text, path);
                    } catch (Exception e) {
                        log.error("Error sending email: {}", e.getMessage());
                    }
                });
        return "Email sent successfully!";
    }
}