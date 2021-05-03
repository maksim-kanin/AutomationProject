
package com.tricentis.demowebshop.notifications.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    private Long broken;
    private Long failed;
    private Long passed;
    private Long skipped;
    private Long total;
    private Long unknown;
}
