package com.even.gestion.repositories;

import com.even.gestion.models.Event;
import com.even.gestion.models.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    // You can add custom queries if necessary
    @Transactional
    void deleteAllByEvent_id(long event_id);
    List<Invitation> findByEvent(Event event);
}

