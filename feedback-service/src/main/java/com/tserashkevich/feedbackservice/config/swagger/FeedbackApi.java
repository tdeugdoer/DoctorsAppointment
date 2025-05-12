package com.tserashkevich.feedbackservice.config.swagger;

import com.tserashkevich.feedbackservice.dtos.ExceptionResponse;
import com.tserashkevich.feedbackservice.dtos.FeedbackRequest;
import com.tserashkevich.feedbackservice.dtos.FeedbackResponse;
import com.tserashkevich.feedbackservice.dtos.PageResponse;
import com.tserashkevich.feedbackservice.dtos.UpdateFeedbackRequest;
import com.tserashkevich.feedbackservice.dtos.ValidationErrorResponse;
import com.tserashkevich.feedbackservice.utils.FeedbackSortList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FeedbackApi {
    @Operation(summary = "Create a new feedback", description = "Create a new feedback on the provided data.")
    @ApiResponse(responseCode = "201", description = "Feedback created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeedbackResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request body",
            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
    FeedbackResponse createFeedback(@Parameter(description = "Feedback request") @Valid @RequestBody FeedbackRequest feedbackRequest);

    @Operation(summary = "Update a feedback", description = "Update an existing feedback on the provided data.")
    @ApiResponse(responseCode = "200", description = "Feedback updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeedbackResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request body",
            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
    @ApiResponse(responseCode = "400", description = "Id is not UUID",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    FeedbackResponse updateFeedback(@Parameter(description = "Id of a updating feedback") @PathVariable UUID id,
                                    @Parameter(description = "Update feedback request") @Valid @RequestBody UpdateFeedbackRequest updateFeedbackRequest);

    @Operation(summary = "Delete a feedback", description = "Deleting an existing feedback.")
    @ApiResponse(responseCode = "204", description = "Feedback deleted successfully")
    @ApiResponse(responseCode = "400", description = "Id is not UUID",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Feedback not found",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    void deleteFeedback(@Parameter(description = "Id of a deleting feedback") @PathVariable UUID id);

    @Operation(summary = "Find all feedbacks", description = "Returns a paginated list of all feedbacks.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class)))
    @ApiResponse(responseCode = "400", description = "Wrong request parameter type",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "400", description = "Wrong request parameter value",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    PageResponse<FeedbackResponse> findAllFeedbacks(@Parameter(description = "Page number") @RequestParam(defaultValue = "0") @Min(0) int page,
                                                    @Parameter(description = "Items limit") @RequestParam(defaultValue = "20") @Min(1) @Max(50) int limit,
                                                    @Parameter(description = "Sort value") @RequestParam(defaultValue = "ID_ASC") FeedbackSortList sort,
                                                    @Parameter(description = "Filter appointmentId") @RequestParam(required = false) String appointmentId,
                                                    @Parameter(description = "Filter serviceId") @RequestParam(required = false) UUID serviceId,
                                                    @Parameter(description = "Filter doctorId") @RequestParam(required = false) UUID doctorId,
                                                    @Parameter(description = "Filter patientId") @RequestParam(required = false) UUID patientId,
                                                    @Parameter(description = "Filter feedback") @RequestParam(required = false) Integer feedback,
                                                    @Parameter(description = "Filter start date") @RequestParam(required = false) LocalDateTime dateStart,
                                                    @Parameter(description = "Filter end date") @RequestParam(required = false) LocalDateTime dateEnd);

    @Operation(summary = "Find feedback by ID", description = "Returns a feedback by its ID.")
    @ApiResponse(responseCode = "200", description = "Feedback found by id",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeedbackResponse.class)))
    @ApiResponse(responseCode = "400", description = "ID is not UUID",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Feedback not found",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    FeedbackResponse findFeedbackById(@Parameter(description = "Id of а finding feedback") @PathVariable UUID id);

    @Operation(summary = "Get avg feedback by doctor ID", description = "Returns an average feedback by doctor ID.")
    @ApiResponse(responseCode = "200", description = "Feedback counted successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeedbackResponse.class)))
    @ApiResponse(responseCode = "400", description = "Doctor ID is not UUID",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Feedback not found",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    Double findDoctorAvgFeedback(@Parameter(description = "Doctor ID") @PathVariable UUID doctorId);

    @Operation(summary = "Find feedbacks by doctor ID", description = "Returns the list of feedbacks by doctor ID.")
    @ApiResponse(responseCode = "200", description = "Feedbacks found successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FeedbackResponse.class)))
    @ApiResponse(responseCode = "400", description = "Doctor ID is not UUID",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Feedback not found",
            content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    List<FeedbackResponse> findDoctorFeedbacks(@Parameter(description = "Doctor ID") @PathVariable UUID doctorId);

}
