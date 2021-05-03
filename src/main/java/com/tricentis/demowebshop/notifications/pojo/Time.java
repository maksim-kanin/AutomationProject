
package com.tricentis.demowebshop.notifications.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Time {
    private Long duration;
    private Long maxDuration;
    private Long minDuration;
    private Long start;
    private Long stop;
    private Long sumDuration;
}
