package com.project.student.service;

import com.project.student.dto.EmailDetails;

// Interface
public interface EmailSenderService {

    int sendEmail(EmailDetails emailDetails);

}
