package org.csu.nekotalk.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    @NotBlank(message = "powerType:Not blank")
    private String powerType;

}
