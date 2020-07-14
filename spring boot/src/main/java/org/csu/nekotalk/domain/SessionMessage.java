package org.csu.nekotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionMessage {

    private  String messageId;

    private String sessionId;

    private String phoneNumber;

    @Size(max = 800,min = 0)
    private String text;

    private String picture;

    private Timestamp sessionTimeStamp;
}
