package com.pet.pet_shelter.BackUpPackage;

import com.pet.pet_shelter.PetShelterApplication;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class DataHandle {
    public static String BackupDBToSql() {
        try {
            CodeSource codeSource = PetShelterApplication.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();


            String dbName = "mydb";
            String dbUser = "root";
            String dbPass = "#mora951753132547698#";


            String folderPath = jarDir + "\\backup";

            File f1 = new File(folderPath);
            f1.mkdir();

            String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";
            String executeCmd = "mysqldump -u " + dbUser + " -p"+ dbPass + " " + dbName + " -r " + savePath;
            System.out.println("executeCmd = " + executeCmd);
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            if (processComplete == 0) {
                return "Backup Complete";
            } else {
                return "Backup Failure";
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
            return "Error!!";
        }
    }

    public static String RestoreDBFromSql() {
        try {
            CodeSource codeSource = PetShelterApplication.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();
            String s = "backup.sql";

            String dbName = "mydb";
            String dbUser = "root";
            String dbPass = "password";


            String restorePath = jarDir + "\\backup" + "\\" + s;
            String command = "mysql -u " + dbUser +  " -p"+dbPass+ " " + dbName + " < \""+restorePath+"\"";
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "cd C:\\Program Files\\MySQL\\MySQL Server 8.1 && "+ command);
            builder.redirectErrorStream(true);
            Process p = builder.start();
            int processComplete = p.waitFor();
            if (processComplete == 0) {
                return "Restore Complete";
            } else {
                return "Restore Failure";
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
            return "Error!!";
        }

    }

}
