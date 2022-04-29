package dev.fenix.application.api;

import dev.fenix.application.api.security.IndexResource;
import dev.fenix.application.api.security.util.JwtUtil;
import dev.fenix.application.security.model.AppUserDetails;
import dev.fenix.application.security.model.User;
import dev.fenix.application.security.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IndexResourceTest {

    @Autowired private IndexResource controller;

    @Autowired private MockMvc mockMvc;

    @Autowired private JwtUtil jwt;

    @Autowired private UserRepository userRepository;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    @WithMockUser(
            username = "fenix",
            roles = {"USER", "ADMIN"})
    public void indexTest() throws Exception {
        this.mockMvc
                .perform(get("/api/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("api")))
               ;
    }

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        User user = userRepository.findOneByUserName("fenix");
        AppUserDetails userDetails = new AppUserDetails(user);
        String token = "Bearer " + jwt.generateToken(userDetails);
        assertNotNull(token);
        assertNotNull(user);
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/")
                                .accept(MediaType.APPLICATION_JSON)
                                .header(HttpHeaders.AUTHORIZATION, token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.api").value(1))
               ;

        ;
    }


}