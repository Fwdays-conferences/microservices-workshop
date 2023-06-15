package fwdays.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("legacy")
public class LegacyPreferences {

    private boolean notification;

    private boolean order;
}
