package dev.fenix.application.accounting.repository;

import dev.fenix.application.accounting.model.AccountingEntry;
import dev.fenix.application.business.model.Company;
import dev.fenix.application.production.treatment.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface AccountingEntryRepository extends JpaRepository<Company, Long> {
    @Query(
            value =
                    "SELECT "
                            + "    trt__doc.id AS id,"
                            + "    bz__company.social_reason as social_reason ,"
                            + "    trt__doc.doc_date AS date,"
                            + "    trt__doc.code AS piece,"
                            + "    CONCAT_WS(' ', trt__doc_type.name, trt__doc.code) AS label,"
                            + "    trt__doc_type.abbreviation AS type,"
                            + "    SUM(((trt__doc_product.quantity * trt__doc_product.price) * (1 - trt__doc_product.discount / 100)) * (1 + trt__doc_product.tax / 100)) AS debit,"
                            + "    0 AS credit,"
                            + "    trt__doc.status ,"
                            + "    accounting__letter.code as letter,"
                            + "    IFNULL(accounting__lettering_customer.id,0) as idLetteringCustomer"
                            + " FROM"
                            + "    bz__company"
                            + "        JOIN trt__doc ON (bz__company.id = trt__doc.source_id OR trt__doc.destination_id = bz__company.id)"
                            + "        JOIN trt__doc_type ON (trt__doc_type.id = trt__doc.type_id)"
                            + "        JOIN trt__doc_product ON (trt__doc_product.document_id = trt__doc.id AND trt__doc_product.active = 1)"
                            + "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.id = trt__doc.lettering_id "
                            + "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id"
                            + " WHERE"
                            + "    bz__company.id = :company"
                            + "        AND trt__doc_type.in_accounting = 1"
                            + "        AND trt__doc.active = 1       "
                            + "          AND trt__doc.doc_date between :startDate AND  :endDate"
                            + " GROUP BY trt__doc.id , bz__company.id , trt__doc_type.id  , accounting__letter.id"
                            + " UNION SELECT "
                            + "    payment__customer.id id,"
                            + "    bz__company.social_reason as social_reason,"
                            + "    payment__customer.payment_date AS date,"
                            + "    payment__method.code AS piece,"
                            + "    CONCAT_WS(' ',payment__method.name,payment__method.code) AS label,"
                            + "    payment__method.code AS type,"
                            + "    0 AS debit,"
                            + "    payment__customer.montant AS credit,"
                            + "    'TODO' AS status,"
                            + "    accounting__letter.code as letter,"
                            + "    IFNULL(accounting__lettering_customer.id,0) as idLetteringCustomer"
                            + " FROM"
                            + "    bz__company"
                            + "        JOIN  payment__customer ON payment__customer.customer_id = bz__company.id"
                            + "        JOIN  payment__method ON payment__customer.payment_method_id = payment__method.id"
                            + "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.id = payment__customer.lettering_id"
                            + "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id"
                            + " WHERE"
                            + "    bz__company.id = :company  " +
                            "     AND payment__customer.payment_date between :startDate AND  :endDate",
            countQuery = "select count(*) from ( " +
                    "SELECT trt__doc.id AS id "
                    + " FROM"
                    + "    bz__company"
                    + "        JOIN trt__doc ON (bz__company.id = trt__doc.source_id OR trt__doc.destination_id = bz__company.id)"
                    + "        JOIN trt__doc_type ON (trt__doc_type.id = trt__doc.type_id)"
                    + "        JOIN trt__doc_product ON (trt__doc_product.document_id = trt__doc.id AND trt__doc_product.active = 1)"
                    + "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.id = trt__doc.lettering_id "
                    + "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id"
                    + " WHERE"
                    + "    bz__company.id = :company"
                    + "        AND trt__doc_type.in_accounting = 1"
                    + "        AND trt__doc.active = 1       "
                    + " GROUP BY trt__doc.id , bz__company.id , trt__doc_type.id  , accounting__letter.id"
                    + " union SELECT   payment__customer.id  as id "
                    + " FROM"
                    + "    bz__company"
                    + "        JOIN  payment__customer ON payment__customer.customer_id = bz__company.id"
                    + "        JOIN  payment__method ON payment__customer.payment_method_id = payment__method.id"
                    + "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.id = payment__customer.lettering_id"
                    + "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id"
                    + " WHERE"
                    + "    bz__company.id = :company ) as dataCount "
            ,
            nativeQuery = true)
    Page<AccountingEntry> accountingEntry(@Param("company") Long company, @Param("startDate") String startDate, @Param("endDate") String endDate, Pageable pageable);


    @Query(
            value =
                    "SELECT "
                            + "    trt__doc.id AS id,"
                            + "    bz__company.social_reason as social_reason ,"
                            + "    trt__doc.doc_date AS date,"
                            + "    trt__doc.code AS piece,"
                            + "    CONCAT_WS(' ', trt__doc_type.name, trt__doc.code) AS label,"
                            + "    trt__doc_type.abbreviation AS type,"
                            + "    SUM(((trt__doc_product.quantity * trt__doc_product.price) * (1 - trt__doc_product.discount / 100)) * (1 + trt__doc_product.tax / 100)) AS debit,"
                            + "    0 AS credit,"
                            + "    trt__doc.status ,"
                            + "    accounting__letter.code as letter,"
                            + "    IFNULL(accounting__lettering_customer.id,0) as idLetteringCustomer"
                            + " FROM"
                            + "    bz__company"
                            + "        JOIN trt__doc ON (bz__company.id = trt__doc.source_id OR trt__doc.destination_id = bz__company.id)"
                            + "        JOIN trt__doc_type ON (trt__doc_type.id = trt__doc.type_id)"
                            + "        JOIN trt__doc_product ON (trt__doc_product.document_id = trt__doc.id AND trt__doc_product.active = 1)"
                            + "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.id = trt__doc.lettering_id "
                            + "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id"
                            + " WHERE"
                            + "    bz__company.id = ?1"
                            + "        AND trt__doc_type.in_accounting = 1"
                            + "        AND trt__doc.active = 1     and  BINARY accounting__letter.code  = BINARY ?2   "
                            + " GROUP BY trt__doc.id , bz__company.id , trt__doc_type.id  , accounting__letter.id"
                            + " UNION SELECT "
                            + "    payment__customer.id id,"
                            + "    bz__company.social_reason as social_reason,"
                            + "    payment__customer.payment_date AS date,"
                            + "    payment__method.code AS piece,"
                            + "    CONCAT_WS(' ',payment__method.name,payment__method.code) AS label,"
                            + "    payment__method.code AS type,"
                            + "    0 AS debit,"
                            + "    payment__customer.montant AS credit,"
                            + "    'TODO' AS status,"
                            + "    accounting__letter.code as letter,"
                            + "    IFNULL(accounting__lettering_customer.id,0) as idLetteringCustomer"
                            + " FROM"
                            + "    bz__company"
                            + "        JOIN  payment__customer ON payment__customer.customer_id = bz__company.id"
                            + "        JOIN  payment__method ON payment__customer.payment_method_id = payment__method.id"
                            + "        LEFT JOIN accounting__lettering_customer on accounting__lettering_customer.id = payment__customer.lettering_id"
                            + "        LEFT JOIN accounting__letter on accounting__letter.id = accounting__lettering_customer.letter_id"
                            + " WHERE"
                            + "    bz__company.id = ?1  and   BINARY   accounting__letter.code  = BINARY  ?2 ", nativeQuery = true)
    List<AccountingEntry> accountingEntryByLetter(Long company, String letter);



    @Query(value = "select sum((docProduct.price * docProduct.quantity) * (1-docProduct.discount/100) * (1+docProduct.tax/100)) " +
            "from Document doc  " +
            "join  doc.type typ " +
            "join doc.documentProduct docProduct " +
            "where doc.active = true  " +
            "and docProduct.active = true " +
            "and  typ.inAccounting <> 0 and doc.date < :startDate " +
            "and (doc.source = :company or doc.destination = :company )")
    Double sumRanDocDebit(@Param("company") Company company, @Param("startDate") Date startDate);


    @Query(value = "select sum(payment.montant) " +
            "from PaymentCustomer payment " +
            "where payment.customer = :company " +
            "and payment.createDate <  :startDate " +
            "and payment.active = true ")
    Double sumRanPayment(@Param("company") Company company, @Param("startDate") Date startDate);


    @Query(value = "select sum((docProduct.price * docProduct.quantity) * (1-docProduct.discount/100) * (1+docProduct.tax/100)) " +
            "from Document doc  " +
            "join  doc.type typ " +
            "join doc.documentProduct docProduct " +
            "where doc.active = true  " +
            "and docProduct.active = true " +
            "and  typ.inAccounting <> 0 and doc.date between :startDate and :endDate " +
            "and (doc.source = :company or doc.destination = :company )")
    Double periodicBalanceDocumentDebit(@Param("company") Company company, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(value = "select sum(payment.montant) " +
            "from PaymentCustomer payment " +
            "where payment.customer = :company " +
            "and payment.createDate between :startDate and :endDate " +
            "and payment.active = true ")
    Double periodicBalancePaymentCredit(@Param("company") Company company, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query(value = "select sum((docProduct.price * docProduct.quantity) * (1-docProduct.discount/100) * (1+docProduct.tax/100)) " +
            "from Document doc  " +
            "join  doc.type typ " +
            "join doc.documentProduct docProduct " +
            "where doc.active = true  " +
            "and docProduct.active = true " +
            "and  typ.inSales <> 0 and  doc.status in  :workFlow " +
            "and (doc.source = :company or doc.destination = :company )")
    Double saleNotCashed(@Param("company") Company company,@Param("workFlow") List<Status> workFlow);

}
