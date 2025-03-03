package com.mindplates.bugcase.biz.testrun.vo.response;

import com.mindplates.bugcase.biz.testrun.dto.TestrunReservationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestrunReservationResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private boolean expired;
    private Boolean deadlineClose;
    private int testcaseGroupCount;
    private int testcaseCount;
    private Long testrunId;
    private String projectName;
    private List<TestrunUserResponse> testrunUsers;
    private List<TestrunTestcaseGroupResponse> testcaseGroups;
    private Boolean selectCreatedTestcase;
    private Boolean selectUpdatedTestcase;

    private List<TestrunTestcaseGroupResponse> conditionalTestcaseGroups;

    public TestrunReservationResponse(TestrunReservationDTO testrunReservation) {
        this.id = testrunReservation.getId();
        this.name = testrunReservation.getName();
        this.description = testrunReservation.getDescription();
        this.startDateTime = testrunReservation.getStartDateTime();
        this.endDateTime = testrunReservation.getEndDateTime();
        this.expired = testrunReservation.getExpired();
        this.deadlineClose = testrunReservation.getDeadlineClose();
        this.testcaseGroupCount = testrunReservation.getTestcaseGroupCount();
        this.testcaseCount = testrunReservation.getTestcaseCount();
        this.projectName = testrunReservation.getProject().getName();
        this.selectCreatedTestcase = testrunReservation.getSelectCreatedTestcase();
        this.selectUpdatedTestcase = testrunReservation.getSelectUpdatedTestcase();
        if (testrunReservation.getTestrun() != null) {
            this.testrunId = testrunReservation.getTestrun().getId();
        }

        if (testrunReservation.getTestrunUsers() != null && !testrunReservation.getTestrunUsers().isEmpty()) {
            this.testrunUsers = testrunReservation.getTestrunUsers().stream().map(TestrunUserResponse::new).collect(Collectors.toList());
        }

        if (testrunReservation.getTestcaseGroups() != null && !testrunReservation.getTestcaseGroups().isEmpty()) {
            this.testcaseGroups = testrunReservation.getTestcaseGroups().stream().map(TestrunTestcaseGroupResponse::new).collect(Collectors.toList());
        }

        this.conditionalTestcaseGroups = testrunReservation.getConditionalTestcaseGroupList().stream().map(TestrunTestcaseGroupResponse::new).collect(Collectors.toList());


    }


}
