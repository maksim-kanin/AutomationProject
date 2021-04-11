
package in.reqres.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatedUser {
    private String createdAt;
    private String id;
    private String job;
    private String name;
}
