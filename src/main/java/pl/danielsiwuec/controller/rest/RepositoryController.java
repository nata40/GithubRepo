package pl.danielsiwuec.controller.rest;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.danielsiwuec.model.RepositoryData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    @RequestMapping(path="/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getNameRepository(@PathVariable String name) throws IOException {
        List<String> repositoryData = new ArrayList<>();
        RepositoryService service = new RepositoryService();
        for (Repository repo : service.getRepositories(name)){
            repositoryData.add(repo.getName());
        }
        return  repositoryData;

    }
    @RequestMapping(path="/{name}/{repositories_name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RepositoryData> getAllRepository(@PathVariable String repositories_name, @PathVariable  String name) throws IOException {
        List<RepositoryData> repositoryData = new ArrayList<>();
        RepositoryService service = new RepositoryService();
        for (Repository repo : service.getRepositories(name)){
            if(repo.getName().equals(repositories_name))
            {
                repositoryData.add(new RepositoryData(repo.getName(),repo.getDescription(),
                        repo.getCloneUrl(),repo.getWatchers(),repo.getCreatedAt()));
            }

        }
        return  repositoryData;


    }

}
