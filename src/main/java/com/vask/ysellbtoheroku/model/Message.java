package com.vask.ysellbtoheroku.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private int id;
    //TODO
    private LocalDateTime dateOfCreate;
    private String text;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "chat_id",referencedColumnName = "chat_id")
    private Chat chat;

    private boolean sent;






}
