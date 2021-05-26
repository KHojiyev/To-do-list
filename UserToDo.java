package module3.lesson6_AlternativeLibraries.work2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserToDo {
    private Integer userId;
    private Integer id;
    private String title;
    private boolean completed;


}
