package wiks.bikesharing.rest;

import org.junit.jupiter.api.*;
import org.springframework.boot.testcontainers.service.connection.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import wiks.bikesharing.entity.*;
import wiks.bikesharing.repositories.UserRepository;
import wiks.bikesharing.services.JwtService;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Container
    @ServiceConnection
    private static final MySQLContainer <?> container = new MySQLContainer<>("mysql");

    @BeforeEach
    void setUp() {
        List<User> users = List.of(new User(0, "wiktor", "wiktor@gmail.com",
                        passwordEncoder.encode( "password"), Role.USER, null),
                new User(0, "lech", "lech@gmail.com",
                        passwordEncoder.encode( "1234"), Role.USER, null),
                new User(0, "karol", "karol@gmail.com",
                        passwordEncoder.encode( "password1234"), Role.ADMIN, null));
        userRepository.saveAll(users);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateNewUser() throws Exception {
        CreateUserRequest user = new CreateUserRequest("pawel",
                "pawel@gmail.com",
                "password",
                Role.USER);
        mvc.perform(post("/sharing/user/signUp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
        Assertions.assertEquals(4, userRepository.count());
    }

    @Test
    void shouldCreateTokenForUser() throws Exception {
        UserAuth userAuth = new UserAuth("wiktor", "password");
        MvcResult result = mvc.perform(post("/sharing/user/signIn")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userAuth)))
            .andExpect(status().isOk())
            .andReturn();
        String response = result.getResponse().getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
        String token = (String) responseMap.get("token");
        String usernameToken = jwtService.extractUsername(token);
        Assertions.assertEquals("wiktor", usernameToken);
    }

}