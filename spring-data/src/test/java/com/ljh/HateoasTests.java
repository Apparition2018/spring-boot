package com.ljh;

import com.ljh.controller.EmployeeController;
import com.ljh.entity.primary.Employee;
import com.ljh.repository.primary.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * HATEOAS (Hypermedia As Engine Of Application State)
 * <p><a href="https://github.com/spring-projects/spring-hateoas-examples/tree/main/simplified">Spring HATEOAS - Basic Example</a>
 *
 * @author ljh
 * created on 2021/8/31 14:13
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class HateoasTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeRepository repository;

    @Test
    public void getShouldFetchAHalDocument() throws Exception {
        BDDMockito.given(repository.findAll()).willReturn(Arrays.asList(
                new Employee(1L, "Frodo Baggins", "ring bearer"),
                new Employee(2L, "Bilbo Baggins", "burglar")));


        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employees").accept(MediaTypes.HAL_JSON_VALUE);
        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("$._embedded.employeeList[0].id", is(1)))
                .andExpect(jsonPath("$._embedded.employeeList[0].name", is("Frodo Baggins")))
                .andExpect(jsonPath("$._embedded.employeeList[0].role", is("ring bearer")))
                .andExpect(jsonPath("$._embedded.employeeList[0]._links.self.href", is("http://localhost/employees/1")))
                .andExpect(jsonPath("$._embedded.employeeList[0]._links.employees.href", is("http://localhost/employees")))
                .andExpect(jsonPath("$._embedded.employeeList[1].id", is(2)))
                .andExpect(jsonPath("$._embedded.employeeList[1].name", is("Bilbo Baggins")))
                .andExpect(jsonPath("$._embedded.employeeList[1].role", is("burglar")))
                .andExpect(jsonPath("$._embedded.employeeList[1]._links.self.href", is("http://localhost/employees/2")))
                .andExpect(jsonPath("$._embedded.employeeList[1]._links.employees.href", is("http://localhost/employees")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/employees")))
                .andReturn();
    }
}
