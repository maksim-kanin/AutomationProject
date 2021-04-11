
package in.reqres.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private String avatar;

    private String email;

    @JsonProperty("first_name")
    private String firstName;

    private Long id;

    @JsonProperty("last_name")
    private String lastName;
}
