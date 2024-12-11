package com.keschubay.discussions;

import com.keschubay.discussions.model.Message;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final List<Message> messages = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);

    @GetMapping
    public List<Message> getAllMessages() {
        return messages;
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messages.stream()
                .filter(message -> message.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Message not found"));
    }

    @PostMapping
    public Message createMessage(@RequestBody Message newMessage) {
        newMessage.setId(counter.getAndIncrement());
        messages.add(newMessage);
        return newMessage;
    }

    @PutMapping("/{id}")
    public Message updateMessage(@PathVariable Long id, @RequestBody Message updatedMessage) {
        Message message = getMessageById(id);
        message.setContent(updatedMessage.getContent());
        return message;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messages.removeIf(message -> message.getId().equals(id));
    }
}

