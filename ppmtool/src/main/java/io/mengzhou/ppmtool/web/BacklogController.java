package io.mengzhou.ppmtool.web;

import io.mengzhou.ppmtool.domain.ProjectTask;
import io.mengzhou.ppmtool.services.MapErrorValidationService;
import io.mengzhou.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private MapErrorValidationService mapErrorValidationService;

    @Autowired
    private ProjectTaskService projectTaskService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTToBackLog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult result, @PathVariable String backlog_id, Principal principal) {
        ResponseEntity<?> errMap = mapErrorValidationService.mapErrorValidationService(result);
        if (errMap != null)
            return errMap;
        ProjectTask pt = projectTaskService.addProjectTask(backlog_id, projectTask, principal.getName());
        return new ResponseEntity<ProjectTask>(pt, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id, Principal principal) {
        return projectTaskService.getProjectBacklogById(backlog_id, principal.getName());
    }

    @GetMapping("/{backlog_id}/{ptSequence}")
    public ResponseEntity<?> getProjectTaskByPTSequence(@PathVariable String backlog_id, @PathVariable String ptSequence, Principal principal) {
        return new ResponseEntity<ProjectTask>(projectTaskService.findByProjectSequence(backlog_id, ptSequence, principal.getName()), HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{ptSequence}")
    public ResponseEntity<?> updateProjectTaskByPTSequence(@Valid @RequestBody ProjectTask updatedProject, BindingResult result,
                                                           @PathVariable String backlog_id, @PathVariable String ptSequence, Principal principal) {
        ResponseEntity<?> error = mapErrorValidationService.mapErrorValidationService(result);
        if (error != null)
            return error;
        ProjectTask projectTask = projectTaskService.updateByProjectSequence(updatedProject, backlog_id, ptSequence, principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @DeleteMapping("/{backlog_id}/{ptSequence}")
    public ResponseEntity<?> deleteProjectTaskByPTSequence(@PathVariable String backlog_id, @PathVariable String ptSequence, Principal principal) {
        projectTaskService.deleteByProjectSequence(backlog_id, ptSequence, principal.getName());
        return new ResponseEntity<String>("The Project Task is successfully deleted", HttpStatus.OK);
    }
}
