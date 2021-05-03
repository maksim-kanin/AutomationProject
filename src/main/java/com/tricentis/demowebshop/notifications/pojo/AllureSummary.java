
package com.tricentis.demowebshop.notifications.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllureSummary {
    private String reportName;
    private Statistic statistic;
    private List<Object> testRuns;
    private Time time;
}
