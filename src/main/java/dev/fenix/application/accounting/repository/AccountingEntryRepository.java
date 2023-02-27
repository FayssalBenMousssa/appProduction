package dev.fenix.application.accounting.repository;

import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.business.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountingEntryRepository extends JpaRepository<Company, Long> {






    /*
    int getId();
    Date getDate();
    String getPiece();
    String getLabel();
    Float getDebit();
    Float getCredit();
    String getType();
    String getLetter();
    */


    @Query(value = "SELECT " +
            "    trt__doc.id AS id," +
            "    bz__company.social_reason," +
            "    trt__doc.doc_date AS date," +
            "    trt__doc.code AS piece," +
            "    CONCAT_WS(' ', trt__doc_type.name, trt__doc.code) AS label," +
            "    trt__doc_type.abbreviation AS type," +
            "    SUM(((trt__doc_product.quantity * trt__doc_product.price) * (1 - trt__doc_product.discount / 100)) * (1 + trt__doc_product.tax / 100)) AS debit," +
            "    0 AS credit," +
            "    trt__doc.status ," +
            "    accounting__letter.code as letter" +
            " FROM" +
            "    bz__company" +
            "        JOIN trt__doc ON (bz__company.id = trt__doc.source_id OR trt__doc.destination_id = bz__company.id)" +
            "        JOIN trt__doc_type ON (trt__doc_type.id = trt__doc.type_id)" +
            "        JOIN trt__doc_product ON (trt__doc_product.document_id = trt__doc.id AND trt__doc_product.active = 1)" +
            "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.document_id = trt__doc.id " +
            "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id" +
            " WHERE" +
            "    bz__company.id =:company" +
            "        AND trt__doc_type.in_accounting = 1" +
            "        AND trt__doc.active = 1" +
            " GROUP BY trt__doc.id , bz__company.id , trt__doc_type.id  , accounting__letter.id" +
            " UNION SELECT " +
            "    payment__customer.id id," +
            "    bz__company.social_reason," +
            "    payment__customer.payment_date AS date," +
            "    payment__method.code AS piece," +
            "    accounting__letter.code as letter," +
            "    CONCAT_WS(' ',payment__method.name,payment__method.code) AS label," +
            "    payment__method.code AS type," +
            "    0 AS debit," +
            "    payment__customer.montant AS credit," +
            "    'TODO' AS status" +
            " FROM" +
            "    bz__company" +
            "        JOIN  payment__customer ON payment__customer.customer_id = bz__company.id" +
            "        JOIN  payment__method ON payment__customer.payment_method_id = payment__method.id" +
            "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.payment_customer_id = payment__customer.id"+
            "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id"+
            " WHERE" +
            "    bz__company.id =:company", nativeQuery = true)
    Page<AccountingEntry> accountingEntry(@Param("company") Long company, Pageable paging);


}
