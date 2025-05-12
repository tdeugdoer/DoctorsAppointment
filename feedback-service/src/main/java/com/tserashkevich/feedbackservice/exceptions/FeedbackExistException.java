package com.tserashkevich.feedbackservice.exceptions;

import com.tserashkevich.feedbackservice.utils.ExceptionList;

public class FeedbackExistException extends RuntimeException {
    public FeedbackExistException() {
        super(ExceptionList.FEEDBACK_EXIST);
    }

}
