
package in.reqres.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private List<Data> data;

    private Long page;

    @JsonProperty("per_page")
    private Long perPage;

    private Support support;

    private Long total;

    @JsonProperty("total_pages")
    private Long totalPages;
}
