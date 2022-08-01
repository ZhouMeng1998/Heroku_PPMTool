package io.mengzhou.ppmtool.services;

import io.mengzhou.ppmtool.domain.Backlog;
import io.mengzhou.ppmtool.domain.ProjectTask;
import io.mengzhou.ppmtool.exception.ProjectNotFoundException;
import io.mengzhou.ppmtool.repositories.BacklogRepository;
import io.mengzhou.ppmtool.repositories.ProjectRepository;
import io.mengzhou.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

            Backlog backlog =  projectService.findByProjectIdentifier(projectIdentifier, username).getBacklog(); //backlogRepository.findByProjectIdentifier(projectIdentifier);
            Integer ptSequence = backlog.getPTSequence();
            ptSequence++;
            backlog.setPTSequence(ptSequence);
            projectTask.setProjectSequence(projectIdentifier + "-" + ptSequence);
            projectTask.setBacklog(backlog);
            projectTask.setProjectIdentifier(projectIdentifier);
            if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
                projectTask.setPriority(3);
            }
            if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
                projectTask.setStatus("TO_DO");
            }
            backlog.getProjectTasks().add(projectTask);
            return projectTaskRepository.save(projectTask);

    }


    public Iterable<ProjectTask> getProjectBacklogById(String id, String username) {
        projectService.findByProjectIdentifier(id, username);

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findByProjectSequence(String backlog_id, String ptSequence, String username) {

        //make sure we are searching on an existing backlog
        projectService.findByProjectIdentifier(backlog_id, username);


        //make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(ptSequence);

        if(projectTask == null){
            throw new ProjectNotFoundException("Project Task '"+ptSequence+"' not found");
        }

        //make sure that the backlog/project id in the path corresponds to the right project
        if(!projectTask.getProjectIdentifier().equals(backlog_id)){
            throw new ProjectNotFoundException("Project Task '"+ptSequence+"' does not exist in project: '"+backlog_id);
        }


        return projectTask;
    }
    public ProjectTask updateByProjectSequence(ProjectTask updatedProject, String backlog_id, String ptSequence, String username) {
        ProjectTask projectTask = findByProjectSequence(backlog_id, ptSequence, username);

        projectTask = updatedProject;

        return projectTaskRepository.save(projectTask);
    }

    public void deleteByProjectSequence(String backlog_id, String ptSequence, String username) {
        ProjectTask projectTask = findByProjectSequence(backlog_id, ptSequence, username);
        projectTaskRepository.delete(projectTask);
    }
}
