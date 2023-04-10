package dev.fenix.application;

import dev.fenix.application.production.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


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


  /*  @Test
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

        *//*  Company company =  companyRepository.findTopByOrderByIdDesc();
        Page<AccountingEntry> dataStock = accountingEntryRepository.accountingEntry(company.getId(),paging);
        dataStock.getContent().forEach(stockCount -> log.info(stockCount.getId() + " " + stockCount.getPiece() + " " + stockCount.getLabel()));
        long totalElements = dataStock.getTotalElements();
        log.info("total : " + totalElements);
        Assert.assertNotNull(dataStock);*//*
    }*/


}