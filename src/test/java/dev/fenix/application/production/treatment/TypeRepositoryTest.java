package dev.fenix.application.production.treatment;

import dev.fenix.application.production.treatment.repository.TypeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest(
    includeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TypeRepositoryTest {
  @Autowired TestEntityManager entityManager;
  @Autowired private TypeRepository typeRepository;
  @Autowired private DataSource dataSource;
  @Autowired private JdbcTemplate jdbcTemplate;
  private static final Logger log = LoggerFactory.getLogger(TypeRepositoryTest.class);

  @Test
  public void injectedComponentsAreNotNull() {
    assertThat(dataSource).isNotNull();
    assertThat(jdbcTemplate).isNotNull();
    assertThat(entityManager).isNotNull();
    assertThat(typeRepository).isNotNull();
  }

  /*
  @Test
  @Rollback(false)
  public void testSaveFormula() throws Exception {
    Formula formula = new Formula();
    Note note = new Note();
    note.setContent("Hello Fayssal content");
    note.setActive(true);
    note.setColor("#00000");

    Product product = productRepository.findOneById(1L);
    FormulaProduct formulaProduct = new FormulaProduct();
    formulaProduct.setQuantity(10);
    formulaProduct.setProduct(product);
    SiUnit siUnit = siUnitRepository.findOneById(1L);
    formulaProduct.setSiUnit(siUnit);


    FormulaProduct formulaProduct_1 = new FormulaProduct();
    formulaProduct_1.setQuantity(10);
    formulaProduct_1.setProduct(product);
    SiUnit siUnit_2 = siUnitRepository.findOneById(2L);
    formulaProduct_1.setSiUnit(siUnit_2);

    formula.getFormulaProducts().add(formulaProduct);
    formula.getFormulaProducts().add(formulaProduct_1);



    FormulaNote formulaNote = new FormulaNote();
    formulaNote.setNote(note);
    formulaNote.setActive(true);

    FormulaNote formulaNote_2 = new FormulaNote();
    formulaNote_2.setNote(note);
    formulaNote_2.setActive(true);

    formula.getFormulaNotes().add(formulaNote);
      formula.getFormulaNotes().add(formulaNote_2);

    formula.setName("Formula name Test");
    formula.setCode("Formula  code");
    formula.setObsolete(false);
    formula.setActive(true);

    Formula formula_saved = formulaRepository.save(formula);

    assertThat(formula_saved.getId()).isEqualTo(formula.getId());
  }

  /*  @Test
  @Rollback(false)
  public void testSaveA(){
      A a = new A(UUID.randomUUID().toString() , "1aAssqsqAsA23456789");
      B b1 = new B();
      b1.setName("1aAssqsqAsA23456789");
      B b2 = new B();
      b2.setName("1aAssqsqAsdA23456789");
      List<B> bs = new ArrayList<>();
      bs.add(b1);
      bs.add(b2);
      a.setItems(bs);
      A savedInDb = entityManager.persist(a);
      A getFromDb = aRepository.getOne(savedInDb.getId());
      assertThat(getFromDb).isEqualTo(savedInDb);
  }


  @Test
  @Rollback(false)
  public void testSaveFactoryAndProduct(){


      Pro product_a = new Pro();
      product_a.setProductName("Product a");
      Pro product_b = new Pro();
      product_b.setProductName("Product b");

      List<Pro> products = new ArrayList<>();
      products.add(product_a);
      products.add(product_b);

      Fac factory = new Fac();
      factory.setFactoryName("F1");
      factory.setProducts(products);

      Fac factory_saved = factoryRepository.save(factory);

      assertThat(factory_saved).isNotNull();


     */
  /* A savedInDb = entityManager.persist(a);
  A getFromDb = aRepository.getOne(savedInDb.getId());
  assertThat(factory_saved).isEqualTo(savedInDb); */
  /*
  }*/

}
