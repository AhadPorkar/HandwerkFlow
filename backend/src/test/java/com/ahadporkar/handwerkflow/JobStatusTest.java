package com.ahadporkar.handwerkflow;
import com.ahadporkar.handwerkflow.domain.JobStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class JobStatusTest { @Test void happyPath(){assertTrue(JobStatus.INTAKE.canTransitionTo(JobStatus.SURVEY));assertTrue(JobStatus.SURVEY.canTransitionTo(JobStatus.SCHEDULED));assertTrue(JobStatus.SCHEDULED.canTransitionTo(JobStatus.IN_PROGRESS));assertTrue(JobStatus.IN_PROGRESS.canTransitionTo(JobStatus.COMPLETED));assertTrue(JobStatus.COMPLETED.canTransitionTo(JobStatus.INVOICED));} @Test void cannotSkipSurvey(){assertFalse(JobStatus.INTAKE.canTransitionTo(JobStatus.COMPLETED));} @Test void terminalStates(){assertFalse(JobStatus.INVOICED.canTransitionTo(JobStatus.INTAKE));assertFalse(JobStatus.CANCELLED.canTransitionTo(JobStatus.SURVEY));} }
