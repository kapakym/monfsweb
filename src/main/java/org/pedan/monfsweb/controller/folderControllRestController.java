package org.pedan.monfsweb.controller;

import org.pedan.monfsweb.domain.FolderControl;
import org.pedan.monfsweb.repos.folderControlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/controltable")
public class folderControllRestController {
    @Autowired
    private folderControlRepo folderListRepo;

    @GetMapping()
    public  Iterable<FolderControl> loadtable() {
        return folderListRepo.findAll();
    }

    @PostMapping()
    public Iterable<FolderControl> add(@RequestParam String folder) {
        FolderControl folderControl = new FolderControl();
        folderControl.setFolderPath(folder);
        folderControl.setStatus("Stop");
        folderListRepo.save(folderControl);

//        Iterable<FolderControl> res = folderListRepo.findAll();
//        List<StatusFolder> myRes = new ArrayList<>();
//        for (FolderControl res1 : res) {
//            myRes.add(new StatusFolder(res1.getId(),res1.getFolderPath(),"Stop"));
//        }
//
//        //System.out.println(res);
//        System.out.println(myRes);
//        System.out.println(res);
        return folderListRepo.findAll();
    }




    @DeleteMapping()
    public Iterable<FolderControl> delete(@RequestParam Long id) {
        System.out.println(id);
        folderListRepo.deleteById(id);
        return folderListRepo.findAll();
    }
}
