package com.pet.pet_shelter.Controllers;

import com.pet.pet_shelter.BackUpPackage.DataHandle;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/dataBackup")
@RestController
public class BackupController {
    @PostMapping("/backup")
    public ResponseEntity<String> backup(){
        String response = DataHandle.BackupDBToSql();
        if(!response.equals("Backup Complete")){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/restore")
    public ResponseEntity<String> restore(){
        String response = DataHandle.RestoreDBFromSql();
        if(!response.equals("Restore Complete")){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(response);
    }
}
