package dev.fenix.application;

import dev.fenix.application.accounting.repository.AccountingEntryRepository;
import dev.fenix.application.business.repository.CompanyRepository;
import dev.fenix.application.production.stock.model.StockCount;
import dev.fenix.application.production.stock.repository.StockRepository;
import dev.fenix.application.production.treatment.model.Status;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class StockRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(StockRepositoryTest.class);
    @Autowired
    private StockRepository stockRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void stockGroupProductTest() {
        Pageable paging = PageRequest.of(0, 100, Sort.by("id"));
        List<Status> statusStock = Arrays.asList(Status.APPROVED, Status.CLOSED);
        Date date = new Date();
        List<StockCount> data = stockRepository.stockGroupProductBatch(statusStock, date, paging).getContent();


        data.forEach(stockCount  ->  {;
            if (stockCount.getQuantity() != 0 ) {
                log.info("total"  + stockCount.toJson());
            }
        });
        Assert.assertNotNull(data);

        /*  Company company =  companyRepository.findTopByOrderByIdDesc();
        Page<AccountingEntry> dataStock = accountingEntryRepository.accountingEntry(company.getId(),paging);
        dataStock.getContent().forEach(stockCount -> log.info(stockCount.getId() + " " + stockCount.getPiece() + " " + stockCount.getLabel()));
        long totalElements = dataStock.getTotalElements();
        log.info("total : " + totalElements);
        Assert.assertNotNull(dataStock);*/
    }


}