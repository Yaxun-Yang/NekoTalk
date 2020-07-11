package org.csu.nekotalk.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Moment {


    private String momentId;

    private String phoneNumber;

    @Size(max=400,min=0,message = "text:Between 0-400 characters")
    private String text;

    @NotBlank(message = "originality:Not blank")
    private String originality;

    @Range(max = 5, min = 1,message = "powerType is range 1-5")
    int  powerType;

    private Timestamp momentTimeStamp;

}
