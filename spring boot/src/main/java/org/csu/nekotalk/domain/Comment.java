package org.csu.nekotalk.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private String commentId;
    private String phoneNumber;
    private String momentId;
    private String replyCommentId;

    @Size(max=100,min=0)
    private String text;

    private Timestamp commentTimeStamp;
}
