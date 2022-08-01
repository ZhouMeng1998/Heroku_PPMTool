package io.mengzhou.ppmtool.services;

import io.mengzhou.ppmtool.domain.Backlog;
import io.mengzhou.ppmtool.domain.Project;
import io.mengzhou.ppmtool.domain.User;
import io.mengzhou.ppmtool.exception.ProjectIdException;
import io.mengzhou.ppmtool.exception.ProjectNotFoundException;
import io.mengzhou.ppmtool.repositories.BacklogRepository;
import io.mengzhou.ppmtool.repositories.ProjectRepository;
import io.mengzhou.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Objects;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private UserRepository userRepository;

    public Project saveOrUpdateProject(Project project, String username){
        if(project.getId() != null){
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
            if(existingProject !=null &&(!existingProject.getProjectLeader().equals(username))){
                throw new ProjectNotFoundException("Project not found in your account");
            }else if(existingProject == null){
                throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
            }
        }

        try{
            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());
            String projectIdentifier = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(projectIdentifier);
            if (Objects.isNull((project.getId()))) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(projectIdentifier);
            }
            if (!Objects.isNull(project.getId())) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(projectIdentifier));
            }
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }
    public Project findByProjectIdentifier(String projectId, String username) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("project ID: '" + projectId.toUpperCase() + "' does not exists");
        }
        if (!project.getProjectLeader().equals(username)) {
            throw new ProjectNotFoundException("This project doesn't belong to the user");
        }
        return project;
    }

    public Iterable<Project> findAll(Principal principal) {
        return projectRepository.findByProjectLeader(principal.getName());
    }

    public void deleteProjectByProjectIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException(projectId + "does not exist.");
        }
        projectRepository.delete(project);
    }
}
