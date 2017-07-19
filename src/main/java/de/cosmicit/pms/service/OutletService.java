package de.cosmicit.pms.service;

import de.cosmicit.pms.model.repository.OutletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("outletService")
public class OutletService {

    @Autowired
    OutletRepository outletRepository;

}
