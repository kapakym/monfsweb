package org.pedan.monfsweb.controller;
/*
В данном контроле обрабатываются события по созданию, остановке и уничтожению потоков
ослеживающих информацию о состоянии контролиремых папок.
* */

import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.pedan.monfsweb.MonfswebApplication;
import org.pedan.monfsweb.MyThread;
import org.pedan.monfsweb.domain.FolderControl;
import org.pedan.monfsweb.repos.folderControlRepo;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/controltable")
public class folderControllRestController {
    @Autowired
    private folderControlRepo folderListRepo;

    @GetMapping()
    public  Iterable<FolderControl> loadtable() {
        return folderListRepo.findAll();
    }

//Метод отвечающий за добавление контролируемой директории в базу данных

    @PostMapping()
    public Iterable<FolderControl> add(@RequestParam String folder) {

        List<FolderControl> myList = folderListRepo.findByfolderPath(folder);
        System.out.println(myList);
        if (myList.size() == 0) {
            System.out.println(folder);
            FolderControl folderControl = new FolderControl();

            folderControl.setFolderPath(folder);
            folderControl.setStatus("Stop");
            folderListRepo.save(folderControl);

        }
        return folderListRepo.findAll();
    }

    @PostMapping("run")
    public Iterable<FolderControl> run(@RequestParam String folder, @RequestParam Long id) {
        System.out.println("Run " + folder+" id "+id);
        FolderControl folderControl = new FolderControl();
        folderControl.setId(id);
        folderControl.setFolderPath(folder);
        folderControl.setStatus("Run");
        MyThread myThread = new MyThread("Monitor", folder);
        myThread.setDaemon(true);
        myThread.start();
        folderListRepo.save(folderControl);
        MonfswebApplication.myThreadList.add(myThread);
        return folderListRepo.findAll();
    }




    @DeleteMapping()
    public Iterable<FolderControl> delete(@RequestParam Long id) {
        System.out.println(id);
        folderListRepo.deleteById(id);
        return folderListRepo.findAll();
    }
}
