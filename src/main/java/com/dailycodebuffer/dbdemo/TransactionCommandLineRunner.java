package com.dailycodebuffer.dbdemo;

import com.dailycodebuffer.dbdemo.model.OrderEntity;
import com.dailycodebuffer.dbdemo.repository.OrderJPARepository;
import com.dailycodebuffer.dbdemo.service.TransactionDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile("transaction")
@Component
public class TransactionCommandLineRunner implements CommandLineRunner {
    @Autowired
    private TransactionDemoService transactionDemoService;

    @Override
    public void run(String... args) throws Exception {
        // Uncomment this for general demo of transaction
        //transactionDemoService.transactionDemo();

        //Uncomment this for  demo of transaction for checked exception
        //transactionDemoService.transactionDemoWithSpecificException();

        //Uncomment this for general demo of transaction chain with REQUIRED transactions
        //transactionDemoService.transactionChainDemo();

        //Uncomment this for general demo of transaction chain with propogation of REQUIRES_NEW
        //transactionDemoService.transactionChainDemo_RequiresNew();
    }

}
