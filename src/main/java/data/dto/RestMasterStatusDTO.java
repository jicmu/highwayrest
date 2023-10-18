package data.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestMasterStatusDTO {
    private int no;
    private String id;
    private String name;
    private int status;
}
