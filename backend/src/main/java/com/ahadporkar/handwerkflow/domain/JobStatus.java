package com.ahadporkar.handwerkflow.domain;
import java.util.Set;
import java.util.Map;
public enum JobStatus {INTAKE,SURVEY,SCHEDULED,IN_PROGRESS,COMPLETED,INVOICED,CANCELLED;
 private static final Map<JobStatus,Set<JobStatus>> NEXT=Map.of(INTAKE,Set.of(SURVEY,CANCELLED),SURVEY,Set.of(SCHEDULED,CANCELLED),SCHEDULED,Set.of(IN_PROGRESS,CANCELLED),IN_PROGRESS,Set.of(COMPLETED,CANCELLED),COMPLETED,Set.of(INVOICED),INVOICED,Set.of(),CANCELLED,Set.of());
 public boolean canTransitionTo(JobStatus next){return NEXT.get(this).contains(next);}
}
