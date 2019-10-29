package org.pedan.monfsweb;

import org.pedan.monfsweb.domain.ActionFolder;
import org.pedan.monfsweb.repos.actionFolderRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class MyThread extends Thread {
    @Autowired
    private actionFolderRepo myRepoFolder;
    private static Map<WatchKey, Path> keyPathMap = new HashMap<>();

    public Path getPathDir() {
        return pathDir;
    }

    // private static final WatchEvent.Kind<?>[] ENTRY_CREATE = ;
    private Path pathDir;

    public MyThread(String name, String paths) {
        super(name);
        System.out.println("Create... " + paths);
        pathDir = Paths.get(paths);

    }

    @Override
    public void run() {
        try (WatchService watchService = pathDir.getFileSystem().newWatchService();) {
            System.out.println("Start monitor");


            registerDir(pathDir, watchService);
            startListening(watchService);


        } catch (Exception e) {
            System.out.println("-------------------------[");
            System.out.println(e.getMessage());

        }


    }

    void registerDir(Path path, WatchService watchService) throws
            IOException {
        if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            return;
        }
//        myForms.id_txtarea.appendText("registering: " + path+" [OK]\n");
        System.out.println("registering: " + path + "\n");
        WatchKey key = path.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
        keyPathMap.put(key, path);

        for (File f : path.toFile().listFiles()) {
            if (Files.exists(f.toPath()) && f.canRead()) registerDir(f.toPath(), watchService);
        }

    }

    private static void startListening(WatchService watchService) throws Exception {
        while (true) {
            WatchKey queuedKey = watchService.take();
            for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {

                System.out.printf("Event... kind=%s, count=%d, context=%s Context type=%s%n",
                        watchEvent.kind(),
                        watchEvent.count(), watchEvent.context(),
                        ((Path) watchEvent.context()).getClass());
                Path path = (Path) watchEvent.context();
                //do something useful here

               /* if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                    //this is not a complete path
                    Path path = (Path) watchEvent.context();
                    //need to get parent path
                    Path parentPath = keyPathMap.get(queuedKey);
                    //get complete path
                    path = parentPath.resolve(path);

                    registerDir(path, watchService);
                }
*/
                ActionFolder actionFolder = new ActionFolder();
                actionFolder.setFileName(path.toString());
                actionFolder.setAction(LocalDateTime.now().toString());
                //actionFolder.setFileSize(100);
                if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
//                    myForms.id_txtarea.appendText("File "+path.toString()+" created \n");
                    actionFolder.setAction("created");
                    System.out.println("File " + path.toString() + " created");
                }
                if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
//                    myForms.id_txtarea.appendText("File "+path.toString()+" deleted \n");
                    actionFolder.setAction("deleted");
                    System.out.println("File " + path.toString() + " deleted");
                }
                if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
//                    myForms.id_txtarea.appendText("File "+path.toString()+" modify \n");
                    actionFolder.setAction("modify");
                    System.out.println("File " + path.toString() + " modify ");
                }

            }
            if (!queuedKey.reset()) {
                keyPathMap.remove(queuedKey);
            }
            if (keyPathMap.isEmpty()) {
                break;
            }
        }
    }

}