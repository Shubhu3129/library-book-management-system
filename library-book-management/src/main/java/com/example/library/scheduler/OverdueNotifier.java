package com.example.library.scheduler;

import com.example.library.entity.Issue;
import com.example.library.service.IssueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class OverdueNotifier {
    private static final Logger log = LoggerFactory.getLogger(OverdueNotifier.class);
    private final IssueService issueService;

    public OverdueNotifier(IssueService issueService) { this.issueService = issueService; }

    // Every day at 9:00 AM
    @Scheduled(cron = "0 0 9 * * *")
    public void notifyOverdue(){
        List<Issue> list = issueService.overdueToday();
        if (!list.isEmpty()){
            log.warn("Overdue books count: {}", list.size());
        }
    }
}
