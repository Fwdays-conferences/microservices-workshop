package fwdays.notification.service;

import fwdays.config.LegacyPreferences;
import fwdays.notification.domain.Notification;
import fwdays.notification.persistence.NotificationRepository;
import fwdays.notification.web.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    private final LegacyPreferences legacyPreferences;

    private final RestTemplate restTemplate;

    private final ModelMapper modelMapper;

    private final Environment env;

    /***
     * @see Strangler Fig pattern
     * @param notification
     */
    public void sendNotification(Notification notification) {
        if (legacyPreferences.isNotification()) {
            sendLegacy(notification);
        } else {
            sendNonLegacy(notification);
        }
    }

    private void sendNonLegacy(Notification notification) {
        restTemplate.postForEntity(env.getRequiredProperty("notification.url"),
                modelMapper.map(notification, NotificationDTO.class), ResponseEntity.class);
    }

    private void sendLegacy(Notification notification) {
        System.out.println("Sending notification ... " + notification.toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notificationRepository.save(notification);

        System.out.println("Notification sent");
    }

}
