package com.even.gestion.services;

import com.even.gestion.models.AppUser;
import com.even.gestion.models.Event;
import com.even.gestion.models.Invitation;
import com.even.gestion.repositories.AppUserRepository;
import com.even.gestion.repositories.EventRepository;
import com.even.gestion.repositories.InvitationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AppUserRepository appUserRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(Long id, String email) {
        Event event = eventRepository.findById(id).orElseThrow();
        if (event.getUser().getEmail().equals(email)) {
            invitationRepository.deleteAllByEvent_id(id);
            eventRepository.delete(event);
        } else {
            throw new SecurityException("Vous n'avez pas l'autorisation de supprimer cet événement.");
        }
    }

    public void addEvent(Event event) {
        try {
            eventRepository.save(event);
            logger.info("Event added successfully: {}", event.getTitle());
        } catch (Exception e) {
            logger.error("Error saving event: {}", event.getTitle(), e);
        }
    }
    public List<Event> getEventsByUser(String email) {
        AppUser user=appUserRepository.findByEmail(email);
        return eventRepository.findByUser(user);
    }
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public String buildInvitationHtml(Event event, String guestEmail, String confirmationLink) {
        return """
        <html>
        <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
            <div style="max-width: 600px; margin: auto; background-color: #ffffff; padding: 20px; border-radius: 10px;">
                <h2 style="color: #333;">You're Invited!</h2>
                <p>Hello,</p>
                <p>You are invited to the event:</p>
                <h3 style="color: #007bff;">%s</h3>
                <p><strong>Description:</strong> %s</p>
                <p><strong>Start:</strong> %s</p>
                <p><strong>End:</strong> %s</p>
                <p><strong>Location:</strong> %s</p>
                <p style="margin-top:20px;">Click the button below to confirm your attendance:</p>
                <a href="%s" style="display: inline-block; padding: 10px 20px; background-color: #28a745; color: #fff; text-decoration: none; border-radius: 5px;">Confirm Attendance</a>
                <p style="margin-top: 20px;">We look forward to seeing you!</p>
                <p>Best regards,<br/>Event Team</p>
            </div>
        </body>
        </html>
        """.formatted(
                event.getTitle(),
                event.getDescription(),
                event.getDateStart(),
                event.getDateEnd(),
                event.getLocationType().equals("online") ? event.getMeetingLink() : event.getLocation(),
                confirmationLink
        );
    }
    public void sendInvitation(Long eventId, String guestEmail) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        Invitation invitation = new Invitation(guestEmail, event);
        invitationRepository.save(invitation);

        // Générer le lien de confirmation (exemple fictif)
        String confirmationLink = "https://yourdomain.com/confirm?invitationId=" + invitation.getId();

        // Construire le message HTML
        String htmlContent = buildInvitationHtml(event, guestEmail, confirmationLink);

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(guestEmail);
            helper.setSubject("Invitation to: " + event.getTitle());
            helper.setText(htmlContent, true); // true pour activer le HTML
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace(); // à remplacer par une meilleure gestion des erreurs
        }
    }


    public Event updateEvent(Long id, Event updatedEvent) {
        Event existingEvent = getEventById(id);

        // Update basic fields
        existingEvent.setTitle(updatedEvent.getTitle());
        existingEvent.setDescription(updatedEvent.getDescription());
        existingEvent.setDateStart(updatedEvent.getDateStart());
        existingEvent.setDateEnd(updatedEvent.getDateEnd());
        existingEvent.setLocation(updatedEvent.getLocation());
        existingEvent.setVisibility(updatedEvent.getVisibility());

        // Update location type and related fields
        existingEvent.setLocationType(updatedEvent.getLocationType());
        existingEvent.setMeetingLink(updatedEvent.getMeetingLink());
        existingEvent.setGpsLocation(updatedEvent.getGpsLocation());

        return eventRepository.save(existingEvent);
    }

    public List<Event> getEventsByCity(String city) {
        List<Event> events1 = eventRepository.findByLocationIgnoreCase(city);
        List<Event> publicEvents = new ArrayList<>();

        for (Event event : events1) {
            if ("PUBLIC".equalsIgnoreCase(event.getVisibility())) {
                publicEvents.add(event);
            }
        }

        return publicEvents;
    }



}

