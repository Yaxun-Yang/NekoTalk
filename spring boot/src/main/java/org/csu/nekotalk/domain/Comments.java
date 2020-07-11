package org.csu.nekotalk.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    private String commentId;
    private String phoneNumber;
    private String momentId;
    private String replyCommentId;

    @Size(max=100,min=0)
    private String text;
}
