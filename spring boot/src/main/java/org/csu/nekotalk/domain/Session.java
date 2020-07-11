package org.csu.nekotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    private String sessionId;
    private String more;
    private Timestamp createTimestamp;

}
