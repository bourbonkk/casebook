package com.mindplates.bugcase.biz.testcase.vo.response;

import com.mindplates.bugcase.biz.testcase.dto.TestcaseDTO;
import com.mindplates.bugcase.biz.testcase.dto.TestcaseSimpleDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestcaseSimpleResponse {

    private Long id;
    private String seqId;
    private Long testcaseGroupId;
    private Long testcaseTemplateId;
    private List<Long> projectReleaseIds;
    private String name;
    private String description;
    private Integer itemOrder;
    private Boolean closed;
    private LocalDateTime creationDate;

    public TestcaseSimpleResponse(TestcaseDTO testcase) {
        this.id = testcase.getId();
        this.seqId = testcase.getSeqId();
        if (testcase.getTestcaseGroup() != null) {
            this.testcaseGroupId = testcase.getTestcaseGroup().getId();
        }
        if (testcase.getTestcaseTemplate() != null) {
            this.testcaseTemplateId = testcase.getTestcaseTemplate().getId();
        }
        if (testcase.getProjectReleases() != null) {
            this.projectReleaseIds = testcase.getProjectReleases()
                .stream()
                .map(projectReleaseDTO -> projectReleaseDTO.getId())
                .distinct()
                .collect(Collectors.toList());
        }
        this.name = testcase.getName();
        this.description = testcase.getDescription();
        this.itemOrder = testcase.getItemOrder();
        this.closed = testcase.getClosed();
        this.creationDate = testcase.getCreationDate();
    }

    public TestcaseSimpleResponse(TestcaseSimpleDTO testcase) {
        this.id = testcase.getId();
        this.seqId = testcase.getSeqId();
        if (testcase.getTestcaseGroup() != null) {
            this.testcaseGroupId = testcase.getTestcaseGroup().getId();
        }
        if (testcase.getTestcaseTemplate() != null) {
            this.testcaseTemplateId = testcase.getTestcaseTemplate().getId();
        }
        if (testcase.getProjectReleases() != null) {
            this.projectReleaseIds = testcase.getProjectReleases()
                .stream()
                .map(projectReleaseDTO -> projectReleaseDTO.getId())
                .distinct()
                .collect(Collectors.toList());
        }
        this.name = testcase.getName();
        this.description = testcase.getDescription();
        this.itemOrder = testcase.getItemOrder();
        this.closed = testcase.getClosed();
        this.creationDate = testcase.getCreationDate();
    }


}
