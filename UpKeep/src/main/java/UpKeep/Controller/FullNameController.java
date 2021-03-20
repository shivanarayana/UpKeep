package UpKeep.Controller;

import UpKeep.DAO.FullName;
import UpKeep.Repository.FullNameRepository;
import UpKeep.Service.FullNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public
class FullNameController {

    @Autowired
    FullNameService fullNameServiceCon;

    @Autowired
    FullNameRepository fullNameRepositoryCon;

    @PostMapping("/user/addFullName")
    public String addFullNameForUser(@Valid @RequestBody FullName fullName){
        if (fullNameServiceCon.addFullName(fullName))
        {
            return Status.UPDATE_SUCCESS.toString();
        }
        else
        {
            return Status.FAILURE.toString();
        }
    }
}