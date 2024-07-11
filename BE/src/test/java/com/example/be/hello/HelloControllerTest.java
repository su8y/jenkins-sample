package com.example.be.hello;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.assertj.core.api.Assertions;

@WebMvcTest(controllers = HelloControllerTest.class)
@Import(HelloController.class)
class HelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void hello() throws Exception {
    String username = "baesua";
    mockMvc.perform(MockMvcRequestBuilders.get("/hello/" + username))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[?(@.message == '%s')]", "안녕하십니까? " + username)

            .exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$..message").exists())
        .andDo(print());
    // XXX: ERROR Assertions.assertThat("a").isEqualTo("b")' // always test failure
  }

}
