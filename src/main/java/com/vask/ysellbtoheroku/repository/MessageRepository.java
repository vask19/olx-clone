package com.vask.ysellbtoheroku.repository;

import com.vask.ysellbtoheroku.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
//    List<Message> findByRecipientAndSender(User recipient, User sender);


//    @Query("select m from Message m where m.recipient = ?1 and m.sender = ?2 or m.sender = ?1 and m.recipient = ?2")
//    List<Message> findByRecipientAndSenderOrSenderAndRecipient(User sender, User recipient);
}
