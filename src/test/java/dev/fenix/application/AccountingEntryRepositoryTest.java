package dev.fenix.application;
import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.accounting.repository.AccountingEntryRepository;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.business.repository.CompanyRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest

class AccountingEntryRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(AccountingEntryRepositoryTest.class);
    @Autowired
    private AccountingEntryRepository accountingEntryRepository;
    @Autowired
    private CompanyRepository companyRepository  ;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    public void stockGroupProductTest() {
      /*  Pageable paging = PageRequest.of(0, 100, Sort.by("id"));
        Company company =  companyRepository.findTopByOrderByIdDesc();
        Page<AccountingEntry> dataStock = accountingEntryRepository.accountingEntry(company.getId(),paging);
        dataStock.getContent().forEach(stockCount -> log.info(stockCount.getId() + " " + stockCount.getPiece() + " " + stockCount.getLabel()));
        long totalElements = dataStock.getTotalElements();
        log.info("total : " + totalElements);
        Assert.assertNotNull(dataStock);*/
    }



}