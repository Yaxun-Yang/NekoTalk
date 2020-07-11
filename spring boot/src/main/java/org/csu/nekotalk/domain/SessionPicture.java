package org.csu.nekotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionPicture {

    private String sessionId;
    private String phoneNumber;

    @NotBlank(message = "url:Not blank")
    private String url;

    private Timestamp time;


}
