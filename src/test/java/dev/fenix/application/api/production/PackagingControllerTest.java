package dev.fenix.application.api.production;

import dev.fenix.application.api.production.product.PackagingResource;
import dev.fenix.application.production.product.model.Packaging;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(PackagingResource.class)
@ContextConfiguration(locations = "classpath:app-context.xml")
public class PackagingControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private PackagingResource packagingResource;





}
