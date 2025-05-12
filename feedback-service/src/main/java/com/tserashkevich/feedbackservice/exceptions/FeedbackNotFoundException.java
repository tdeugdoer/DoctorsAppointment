package com.tserashkevich.feedbackservice.exceptions;

import com.tserashkevich.feedbackservice.utils.ExceptionList;

public class FeedbackNotFoundException extends RuntimeException {
    public FeedbackNotFoundException() {
        super(ExceptionList.FEEDBACK_NOT_FOUND);
    }

}
