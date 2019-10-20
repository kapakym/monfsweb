package org.pedan.monfsweb.controller;

import org.pedan.monfsweb.domain.actionFolder;
import org.pedan.monfsweb.repo.actionFolderRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actionf")
public class actionRestController extends AbstractRestController<actionFolder, actionFolderRepo> {

    public actionRestController(actionFolderRepo repo) {
        super(repo);
    }
}
