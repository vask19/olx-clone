package com.vask.ysellbtoheroku.repository;
import com.vask.ysellbtoheroku.model.Chat;
import com.vask.ysellbtoheroku.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {

    Optional<Chat> findByRecipient(User recipient);
    @Query("select ch from Chat ch where ch.recipient = ?1 and ch.sender = ?2 or ch.sender = ?1 and ch.recipient = ?2")
    Optional<Chat> findByRecipientAndSenderOrSenderAndRecipient(User sender, User recipient);

    Optional<List<Chat>> findAllByRecipientOrSender(User recipient,User sender);




}
