package io.mengzhou.ppmtool.web;

import io.mengzhou.ppmtool.domain.Project;
import io.mengzhou.ppmtool.services.MapErrorValidationService;
import io.mengzhou.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/project")
@RestController
@CrossOrigin
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private MapErrorValidationService errorValidationService;

    @PostMapping("")
    ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = errorValidationService.mapErrorValidationService(result);
        if (errorMap != null)
            return errorMap;
        String username = principal.getName();
        Project project1 = projectService.saveOrUpdateProject(project, username);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    ResponseEntity<?> findByProjectIdentifier(@PathVariable String projectId, Principal principal) {
        Project project = projectService.findByProjectIdentifier(projectId, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
    @GetMapping("/all")
    public Iterable<Project> findAll(Principal principal) {
        return projectService.findAll(principal);
    }
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity<?> deleteByProjectIdentifier(@PathVariable String projectId, Principal principal) {
        Project project = projectService.findByProjectIdentifier(projectId, principal.getName());
        projectService.deleteProjectByProjectIdentifier(projectId);
        return new ResponseEntity<String>("Project '" + projectId + "' was deleted", HttpStatus.OK);
    }
}
