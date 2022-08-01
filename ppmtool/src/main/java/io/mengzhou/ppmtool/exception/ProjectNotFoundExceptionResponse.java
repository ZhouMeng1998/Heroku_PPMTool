package io.mengzhou.ppmtool.exception;

public class ProjectNotFoundExceptionResponse {
    private String projectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }

    public String getProjectNotFoundExceptionResponse() {
        return projectNotFound;
    }

    public void setProjectNotFoundExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
