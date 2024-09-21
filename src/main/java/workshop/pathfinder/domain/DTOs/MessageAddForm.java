package workshop.pathfinder.domain.DTOs;

import lombok.Data;

import java.util.Date;

@Data
public class MessageAddForm {
    private Date date;
    private Long recipientId;
    private String text_content;
}
