package com.vask.ysellbtoheroku.controller;
import com.vask.ysellbtoheroku.dto.ChatDto;
import com.vask.ysellbtoheroku.dto.MessageDto;
import com.vask.ysellbtoheroku.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chats")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/{id}")
    public String  sendFirstMessage(@PathVariable("id") Integer recipientId, Principal principal,
                               @ModelAttribute(value = "messageDto") MessageDto messageDto,
                               @RequestParam(value = "productId") Integer productId){
        MessageDto message = chatService.sendFirstMessage(principal,recipientId,messageDto.getText(),productId);
        return "redirect:" + "/api/products/" + productId;
    }

    @PostMapping("/{id}/send")
    public String sendMessage(@PathVariable("id") Integer chatId,
                              @ModelAttribute(value = "messageDto") MessageDto messageDto,
                              Principal principal){
        MessageDto messageDto1 = chatService.sendMessage(chatId,messageDto.getText(),principal);

        return "redirect:" + "/api/chats/" + chatId;

    }

    @GetMapping("")
    public String getAllChats(Principal principal, Model model){
        List<ChatDto> chatDtoList =
                chatService.getAllChatDtoList(principal);
        model.addAttribute("chatDtoList",chatDtoList);
        model.addAttribute("username",principal.getName());
        return "chat/all_chats_page";
    }


    @GetMapping("{id}")
    public String getAllMessagesWithUserById(@PathVariable("id") Integer chatId,Model model){

        var chatDto = chatService.getChatByChatId(chatId);
        model.addAttribute("chatDto",chatDto);
        model.addAttribute("messageDto",new MessageDto());

        return "chat/chat_page";
    }
}
