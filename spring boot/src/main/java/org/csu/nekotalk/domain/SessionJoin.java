package org.csu.nekotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionJoin {

    private String sessionId;

    @NotBlank(message = "phoneNumber:Not blank")
    private String phoneNumber;

}
