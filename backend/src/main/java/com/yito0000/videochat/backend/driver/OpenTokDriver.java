package com.yito0000.videochat.backend.driver;

import com.opentok.OpenTok;
import com.opentok.Session;
import com.opentok.exception.OpenTokException;
import com.yito0000.videochat.backend.exception.DriverException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OpenTokDriver {

    private final int apiKey;

    private final OpenTok openTok;

    public OpenTokDriver(@Value("${opentok.api-key}") int apiKey, @Value("${opentok.secret-key}") String secretKey) {
        this.apiKey = apiKey;
        this.openTok = new OpenTok(apiKey, secretKey);
    }

    public int fetchApiKey() {
        return this.apiKey;
    }

    public String createSessionId() throws DriverException {
        try {
            return openTok.createSession().getSessionId();
        } catch (OpenTokException e) {
            log.error("OpenTok create session error : {}", e.getMessage());
            e.printStackTrace();
            throw new DriverException(e);
        }
    }

    public String generateToken(String sessionId) throws DriverException {
        try {
            return openTok.generateToken(sessionId);
        } catch (OpenTokException e) {
            log.error("OpenTok generate token error : {}", e.getMessage());
            e.printStackTrace();
            throw new DriverException(e);
        }
    }
}
