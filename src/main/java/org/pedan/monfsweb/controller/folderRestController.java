package org.pedan.monfsweb.controller;

import org.pedan.monfsweb.domain.folderControl;
import org.pedan.monfsweb.repo.folderControlRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/folderc")
public class folderRestController extends AbstractRestController<folderControl, folderControlRepo> {

    public folderRestController(folderControlRepo repo) {
        super(repo);
    }
}
