package com.tserashkevich.feedbackservice.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;

@Getter
@RequiredArgsConstructor
public enum FeedbackSortList {
    ID_ASC(Sort.by(Sort.Direction.ASC, "id")),
    ID_DESC(Sort.by(Sort.Direction.DESC, "id")),
    FEEDBACK_ASC(Sort.by(Sort.Direction.ASC, "feedback")),
    FEEDBACK_DESC(Sort.by(Sort.Direction.DESC, "feedback")),
    CREATION_TIME_ASC(Sort.by(Sort.Direction.ASC, "creation_time")),
    CREATION_TIME_DESC(Sort.by(Sort.Direction.DESC, "creation_time"));

    private final Sort value;
}
