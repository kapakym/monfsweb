package org.pedan.monfsweb.controller;

import org.pedan.monfsweb.domain.FolderControl;
import org.pedan.monfsweb.repos.folderControlRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/folderc")
public class folderRestController extends AbstractRestController<FolderControl, folderControlRepo> {

    public folderRestController(folderControlRepo repo) {
        super(repo);
    }
}
