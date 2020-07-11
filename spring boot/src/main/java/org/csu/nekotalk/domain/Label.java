package org.csu.nekotalk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Label {

    private String labelId;

    @NotBlank(message = "description:Not blank")
    private String description;

}
