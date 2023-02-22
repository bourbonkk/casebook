package com.mindplates.bugcase.common.service;


import com.mindplates.bugcase.biz.project.dto.ProjectUserDTO;
import com.mindplates.bugcase.common.util.HttpRequestUtil;
import com.mindplates.bugcase.common.vo.SlackMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackService {
    private final HttpRequestUtil httpRequestUtil;

    private final MessageSourceAccessor messageSourceAccessor;

    @Value("${bug-case.web-url}")
    private String webUrl;

    public boolean sendText(String url, String message) {
        try {
            httpRequestUtil.sendPost(url, SlackMessage.builder().username("CASEBOOK").icon_url(webUrl + "/logo192.png").text(message).build(), String.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    public void sendTestrunClosedMessage(String slackUrl, String spaceCode, Long projectId, Long testrunId, String testrunName) {
        String reportUrl = webUrl + "/spaces/" + spaceCode + "/projects/" + projectId + "/reports/" + testrunId;
        String message = messageSourceAccessor.getMessage("testrun.closed", new Object[]{testrunName}) + messageSourceAccessor.getMessage("testrun.report.link", new Object[]{reportUrl});
        this.sendText(slackUrl, message);
    }

    public void sendTestrunStartMessage(String slackUrl, String spaceCode, Long projectId, Long testrunId, String testrunName, List<ProjectUserDTO> testers) {
        StringBuilder message = new StringBuilder();
        String testrunUrl = webUrl + "/spaces/" + spaceCode + "/projects/" + projectId + "/testruns/" + testrunId;
        message.append(messageSourceAccessor.getMessage("testrun.created", new Object[]{testrunName, testrunUrl}));

        if (testers != null && !testers.isEmpty()) {
            for (ProjectUserDTO projectUserDTO : testers) {
                message.append(messageSourceAccessor.getMessage("testrun.user.link", new Object[]{testrunUrl + "?tester=" + projectUserDTO.getUser().getId(), projectUserDTO.getUser().getName()}));
            }
        }

        sendText(slackUrl, message.toString());
    }

    public void sendTestrunReOpenMessage(String slackUrl, String spaceCode, Long projectId, Long testrunId, String testrunName, List<ProjectUserDTO> testers) {
        StringBuilder message = new StringBuilder();
        String testrunUrl = webUrl + "/spaces/" + spaceCode + "/projects/" + projectId + "/testruns/" + testrunId;
        message.append(messageSourceAccessor.getMessage("testrun.reopened", new Object[]{testrunName, testrunUrl}));

        if (testers != null && !testers.isEmpty()) {
            for (ProjectUserDTO projectUserDTO : testers) {
                message.append(messageSourceAccessor.getMessage("testrun.user.link", new Object[]{testrunUrl + "?tester=" + projectUserDTO.getUser().getId(), projectUserDTO.getUser().getName()}));
            }
        }

        sendText(slackUrl, message.toString());
    }

    public void sendTestrunTesterChangeMessage(String slackUrl, String spaceCode, Long projectId, Long testrunId, Long testrunTestcaseGroupTestcaseId, String testrunName, String testcaseName, String beforeUserName, String afterUserName) {
        StringBuilder message = new StringBuilder();
        String testrunUrl = webUrl + "/spaces/" + spaceCode + "/projects/" + projectId + "/testruns/" + testrunId + "?id=" + testrunTestcaseGroupTestcaseId + "&type=case";
        message.append(messageSourceAccessor.getMessage("testrun.tester.changed", new Object[]{testrunName, testrunUrl, testcaseName, beforeUserName, afterUserName}));


        sendText(slackUrl, message.toString());
    }

}