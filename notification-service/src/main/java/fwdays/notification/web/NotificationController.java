package fwdays.notification.web;

import fwdays.notification.domain.Notification;
import fwdays.notification.service.NotificationService;
import fwdays.notification.web.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    private final ModelMapper modelMapper;

    @PostMapping
    public void save(@RequestBody NotificationDTO notificationDTO) {
        notificationService.sendNotification(modelMapper.map(notificationDTO, Notification.class));
    }
}
