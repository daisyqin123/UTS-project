package Youtube.service;


import Youtube.model.Vote;
import Youtube.repo.VolunteerRepo;
import Youtube.repo.VoteRepo;
import Youtube.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class InteractiveReviewer {

    private Scanner scanner = new Scanner(System.in) ;

    @Autowired
    private VoteRepo votes ;
    
    @Autowired
    private VolunteerRepo volunteers ;
}


