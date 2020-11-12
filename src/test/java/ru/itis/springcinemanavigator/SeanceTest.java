package ru.itis.springcinemanavigator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.itis.springcinemanavigator.dao.SeanceRepository;
import ru.itis.springcinemanavigator.dao.UserRepository;
import ru.itis.springcinemanavigator.models.Order;
import ru.itis.springcinemanavigator.models.Seance;
import ru.itis.springcinemanavigator.models.User;
import ru.itis.springcinemanavigator.services.PurchaseService;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
@Slf4j
public class SeanceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    public void setUp() throws SQLException {
        log.info(dataSource.getConnection().getClientInfo().toString());
        User user  = userRepository.findById(1l).get();
        Seance seance = seanceRepository.findById(1l).get();
        Order order = purchaseService.purchase(1, seance, user);
//        when(order != null).thenReturn();
    }

    @Test
    public void seancePurchaseTest() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("place", Collections.singletonList("1"));
        params.put("userId", Collections.singletonList("1"));
        mockMvc.perform(post("/seances/1/purchase").params(params).with(csrf())).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("purchase_seance", responseFields(
//                        fieldWithPath("seance").description("сеанс"),
                        fieldWithPath("id").description("id"),
                        fieldWithPath("datetime").description("время начала"),
//                        fieldWithPath("user").description("пользователь"),
                        fieldWithPath("place").description("место"),
                        PayloadDocumentation.subsectionWithPath("seance").description("сеанс"),
                        PayloadDocumentation.subsectionWithPath("user").description("пользователь")
                )
                ));
    }

//    private Course publishedCourse() {
//        return Course.builder()
//                .id(1L)
//                .description("Deep in Java")
//                .state("PUBLISHED")
//                .title("JAVA")
//                .build();
//    }
}
