package dev.fenix.application.production.stock.repository;

import dev.fenix.application.production.logistic.model.Depot;
import dev.fenix.application.production.product.model.Product;
import dev.fenix.application.production.stock.model.StockCount;
import dev.fenix.application.production.stock.model.StockMovement;
import dev.fenix.application.production.treatment.model.Document;
import dev.fenix.application.production.treatment.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Document, Long> {

    /**
     * stock Group Product
     */
    @Query(value = "select  " +
            "case  metadata.code when 'depot_source' then sum((docProd.quantity   * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity ," +
            " prod.id as idProduct ," +
            " prod.name as nameProduct, " +
            "unit.id as idUnit," +
            "unit.name as nameUnit" +
            " from DocumentProduct as docProd " +
            "   left join docProd.product as prod   " +
            "   left join prod.siUnit as unit" +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "   left join doc.documentDataValues as doc_value" +
            "   left join doc_value.metaData as metadata" +
            "   left join doc.type as typ " +
            "where typ.inStock <> 0 " +
            "and doc.active = true " +
            "and metadata.code in ( 'depot_source','depot_destination')" +
            "and docProd.active = true  " +
            "and doc.status in  :statusStock " +
            "and doc.date  <= :dateStock  group by  prod.id,prod.name,unit , metadata.code ")
    Page<StockCount> stockGroupProduct(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, Pageable paging);






    @Query(value = " select " +
            "product.id as idProduct," +
            "product.name  as nameProduct ," +
            "depot.id as idDepot," +
            "depot.name as nameDepot," +
            "batch.code as batchNumber," +
            "unit.id as idUnit," +
            "unit.name as nameUnit," +
            "batch.productionDate as productionDate," +
            "batch.expirationDate as expirationDate," +
            "case  metadata.code when 'depot_source' then sum((docProd.quantity   * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity " +
            "  from DocumentProduct as docProd" +
            "        left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "        left join  doc.type  as typ " +
            "        left join doc.documentDataValues as doc_value" +
            "        left join doc_value.metaData as metadata" +
            "        left join docProd.product as  product" +
            "        left join docProd.batch as batch" +
            "        left join product.siUnit as unit" +
            "        left join Depot  as depot on depot.id =  doc_value.value" +
            "   where doc.active = true" +
            "   and docProd.active = true" +
            "   and typ.inStock <> 0" +
            "   and metadata.code in ( 'depot_source','depot_destination')" +
            "   and doc.status in :statusStock" +
            "   and depot = :depot " +
            "   and doc.date  <= :dateStock" +
            "   group by product  ,depot ,batch.code , batch.productionDate , batch.expirationDate  ,unit , metadata.code  ")
    Page<StockCount> stockGroupProductBatchDepot(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, @Param("depot") Depot depot, Pageable paging);








    @Query(value = "select " +
            "product.id as idProduct," +
            "product.name  as nameProduct ," +
            "depot.id as idDepot," +
            "depot.name as nameDepot," +
            "batch.code as batchNumber," +
            "unit.id as idUnit," +
            "unit.name as nameUnit," +
            "batch.productionDate as productionDate," +
            "batch.expirationDate as expirationDate," +

            "case  metadata.code when 'depot_source' then sum((docProd.quantity   * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity " +
            "  from DocumentProduct as docProd" +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "        left join  doc.type  as typ " +
            "        left join doc.documentDataValues as doc_value" +
            "        left join  doc_value.metaData as metadata" +
            "        left join docProd.product as  product" +
            "        left join docProd.batch as batch" +
            "        left join product.siUnit as unit" +
            "        left join Depot  as depot on depot.id =  doc_value.value" +
            "   where doc.active = true" +
            "   and docProd.active = true" +
            "   and typ.inStock <> 0" +
            "   and metadata.code in ( 'depot_source','depot_destination')" +
            "   and doc.status in :statusStock" +
            "  and doc.date  <= :dateStock " +
            "  group by product  ,depot  ,batch.code , batch.productionDate , batch.expirationDate ,unit ,  metadata.code")
    Page<StockCount> stockGroupProductBatch(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, Pageable paging);


    @Query(value = "select " +
            "product.id as idProduct," +
            "product.name  as nameProduct ," +
            "depot.id as idDepot," +
            "depot.name as nameDepot," +
            "unit.id as idUnit," +
            "unit.name as nameUnit," +
            "case  metadata.code when 'depot_source' then sum((docProd.quantity   * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity " +
            "  from DocumentProduct as docProd" +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "        left join  doc.type  as typ " +
            "        left join doc.documentDataValues as doc_value" +
            "        left join  doc_value.metaData as metadata" +
            "        left join docProd.product as  product" +
            "        left join product.siUnit as unit" +
            "        left join Depot  as depot on depot.id =  doc_value.value" +
            "   where doc.active = true" +
            "   and docProd.active = true" +
            "   and typ.inStock <> 0" +
            "   and metadata.code in ( 'depot_source','depot_destination')" +
            "   and depot = :depot " +
            "   and doc.status in :statusStock" +
            "   and doc.date  <= :dateStock" +
            "   group by  product  ,depot , unit , metadata.code  ")
    Page<StockCount> stockGroupProductDepot(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, @Param("depot") Depot depot, Pageable paging);


    @Query(value = "select " +
            "product.id as idProduct," +
            "product.name  as nameProduct ," +
            "unit.id as idUnit," +
            "unit.name as nameUnit," +
            "case  metadata.code when 'depot_source' then sum((docProd.quantity   * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity " +
            "  from DocumentProduct as docProd" +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "        left join  doc.type  as typ " +
            "        left join doc.documentDataValues as doc_value" +
            "        left join  doc_value.metaData as metadata" +
            "        left join docProd.product as  product" +
            "        left join product.siUnit as unit" +
            "        left join Depot  as depot on depot.id =  doc_value.value" +
            "   where doc.active = true" +
            "   and docProd.active = true" +
            "   and typ.inStock <> 0" +
            "   and metadata.code in ( 'depot_source','depot_destination')" +
            "   and product = :product " +
            "   and doc.status in :statusStock" +
            "   and doc.date  <= :dateStock" +
            "   group by  product , unit , metadata.code ")
    Page<StockCount> stockProductGroupProduct(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, @Param("product") Product product, Pageable paging);


    @Query(value = "select " +
            "product.id as idProduct," +
            "product.name  as nameProduct ," +
            "batch.code as batchNumber," +
            "unit.id as idUnit," +
            "unit.name as nameUnit," +
            "batch.productionDate as productionDate," +
            "batch.expirationDate as expirationDate," +
            "case  metadata.code when 'depot_source' then sum((docProd.quantity   * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity " +
            "  from DocumentProduct as docProd" +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "        left join  doc.type  as typ " +
            "        left join doc.documentDataValues as doc_value" +
            "        left join  doc_value.metaData as metadata" +
            "        left join docProd.product as  product" +
            "        left join docProd.batch as batch" +
            "        left join product.siUnit as unit" +
            "        left join Depot  as depot on depot.id =  doc_value.value" +
            "   where doc.active = true" +
            "   and docProd.active = true" +
            "   and typ.inStock <> 0" +
            "   and metadata.code in ( 'depot_source','depot_destination')" +
            "   and product = :product " +
            "   and doc.status in :statusStock" +
            "   and doc.date  <= :dateStock" +
            "   group by  product ,batch.code , batch.productionDate , batch.expirationDate , unit , metadata.code ")
    Page<StockCount> stockProductGroupProductBatch(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, @Param("product") Product product, Pageable paging);


    @Query(value = "select " +
            "product.id as idProduct," +
            "product.name  as nameProduct ," +
            "unit.id as idUnit," +
            "unit.name as nameUnit," +
            "case  metadata.code when 'depot_source' then sum((docProd.quantity   * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity " +
            "  from DocumentProduct as docProd" +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "        left join  doc.type  as typ " +
            "        left join doc.documentDataValues as doc_value" +
            "        left join doc_value.metaData as metadata" +
            "        left join docProd.product as  product" +
            "        left join product.siUnit as unit" +
            "        left join Depot  as depot on depot.id =  doc_value.value" +
            "   where doc.active = true" +
            "   and docProd.active = true" +
            "   and typ.inStock <> 0" +
            "   and metadata.code in ( 'depot_source','depot_destination')" +
            "   and product = :product " +
            "   and depot = :depot " +
            "   and doc.status in :statusStock" +
            "   and doc.date  <= :dateStock" +
            "   group by  product , unit  , metadata.code")
    Page<StockCount> stockProductGroupProductDepot(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, @Param("product") Product product, @Param("depot") Depot depot, Pageable paging);


    @Query(value = "select " +
            "product.id as idProduct," +
            "product.name  as nameProduct ," +
            "batch.code as batchNumber," +
            "batch.productionDate as productionDate," +
            "batch.expirationDate as expirationDate," +
            "unit.id as idUnit," +
            "unit.name as nameUnit," +
            "case  metadata.code when 'depot_source' then sum((docProd.quantity * -1) ) " +
            "else  sum(docProd.quantity * typ.inStock) end as quantity " +
            "  from DocumentProduct as docProd" +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "        left join  doc.type  as typ " +
            "        left join doc.documentDataValues as doc_value" +
            "        left join  doc_value.metaData as metadata" +
            "        left join docProd.product as  product" +
            "        left join docProd.batch as batch" +
            "        left join product.siUnit as unit" +
            "        left join Depot  as depot on depot.id =  doc_value.value" +
            "   where doc.active = true" +
            "   and docProd.active = true" +
            "   and typ.inStock <> 0" +
            "   and metadata.code in ( 'depot_source','depot_destination')" +
            "   and product = :product " +
            "   and depot = :depot " +
            "   and doc.status in :statusStock" +
            "   and doc.date  <= :dateStock" +
            "   group by  product ,batch.code , batch.productionDate , batch.expirationDate , unit , metadata.code")
    Page<StockCount> stockProductGroupProductBatchDepot(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, @Param("product") Product product, @Param("depot") Depot depot, Pageable paging);


    @Query(value = "select  " +
            "sum(docProd.quantity * typ.inStock)   as quantity ," +
            " bch.productionDate as productionDate," +
            "bch.expirationDate as expirationDate  ," +
            " bch.code as batchNumber ," +
            " prod.id as idProduct , " +
            "prod.name as nameProduct ," +
            "unit.id as idUnit," +
            "unit.name as nameUnit" +
            " from DocumentProduct as docProd " +
            "   left join docProd.product as prod  " +
            "   left join docProd.batch as bch " +
            "   left join docProd.document as doc " +
            "   left join prod.siUnit as unit" +
            "   left join doc.type as typ " +
            "where typ.inStock <> 0 " +
            "   and doc.active = true " +
            "   and docProd.active = true  " +
            "   and doc.status in  :statusStock " +
            "   and doc.date  <= :dateStock  " +
            "group by  prod.id,prod.name, bch.code ,bch.productionDate,bch.expirationDate  , unit "
    )
    Page<StockCount> stockGroupBatchNumber(@Param("statusStock") List<Status> statusStock, @Param("dateStock") Date dateStock, Pageable paging);


    @Query(value = "select  bch.productionDate as  productionDate," +
            "doc.id as documentId ," +
            "doc.code as code ," +
            "case  metadata.code when 'depot_source' then ((docProd.quantity) * -1) " +
            "else  (docProd.quantity * typ.inStock) end as quantity ," +
            "typ.id as typeId ," +
            "typ.name as typeName ," +
            "doc.code as documentCode," +
            "bch.expirationDate as expirationDate  ," +
            "bch.code as batchNumber ," +
            "prod.id as idProduct ," +
            "prod.name as nameProduct ," +
            "batch.code as batchNumber," +
            "batch.productionDate as productionDate," +
            "batch.expirationDate as expirationDate ," +
            "depot.id as idDepot," +
            "depot.name as nameDepot ," +
            "doc.date as dateDocument " +

            "from DocumentProduct as docProd " +
            "   left join docProd.product as prod  " +
            "   left join docProd.batch as bch " +
            "   left join Document  as doc  on docProd.document  =  doc or docProd.parent.document = doc " +
            "   left join doc.type as typ" +
            "   left join doc.documentDataValues as doc_value" +
            "   left join doc_value.metaData as metadata" +
            "   left join Depot  as depot on depot.id =  doc_value.value" +
            "   left join docProd.batch as batch" +

            " where typ.inStock <> 0 and doc.active = true and docProd.active = true  and doc.status in :statusStock and metadata.code in ('depot_source','depot_destination')")
    Page<StockMovement> getMovement(@Param("statusStock") List<Status> statusStock, Pageable paging);




}
