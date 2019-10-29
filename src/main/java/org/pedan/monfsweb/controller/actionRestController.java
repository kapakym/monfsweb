package org.pedan.monfsweb.controller;

import org.pedan.monfsweb.domain.ActionFolder;
import org.pedan.monfsweb.repos.actionFolderRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/actionf")
public class actionRestController extends AbstractRestController<ActionFolder, actionFolderRepo> {

    public actionRestController(actionFolderRepo repo) {
        super(repo);
    }
}
