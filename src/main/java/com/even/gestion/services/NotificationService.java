package com.even.gestion.services;

import com.even.gestion.models.Event;
import com.even.gestion.models.Invitation;
import com.even.gestion.repositories.EventRepository;
import com.even.gestion.repositories.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private JavaMailSender mailSender;

    // 📌 Exécution chaque jour à 08:00 AM pour envoyer les rappels
    @Scheduled(cron = "0 0 8 * * ?")
    public void sendEventReminders() {
        LocalDateTime startOfDay = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = startOfDay.withHour(23).withMinute(59).withSecond(59);

        // 📌 Récupérer les événements qui commencent demain
        List<Event> upcomingEvents = eventRepository.findByDateStartBetween(startOfDay, endOfDay);

        for (Event event : upcomingEvents) {
            List<Invitation> invitations = invitationRepository.findByEvent(event);

            for (Invitation invitation : invitations) {
                sendEmail(invitation.getGuestEmail(), "Reminder: Upcoming Event",
                        "You have an event scheduled for tomorrow: " + event.getTitle() +
                                " at " + event.getLocation() + ".");
            }
        }
    }

    // 📌 Méthode pour envoyer un e-mail
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}
