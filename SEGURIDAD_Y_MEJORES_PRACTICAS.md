# 🛡️ Guía de Seguridad y Mejores Prácticas - RutasFacil

## 🔐 Seguridad en Producción

### 1. Configuración de Base de Datos
```properties
# ❌ NUNCA en producción
spring.datasource.username=postgres
spring.datasource.password=220704

# ✅ Usar variables de entorno
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.url=${DB_URL}
```

### 2. CORS - Restricciones
```java
// ❌ INSEGURO
@CrossOrigin(origins = "*")

// ✅ SEGURO
@CrossOrigin(origins = "https://rutasfacil.com")

// ✅ MEJOR - Configuración global
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                    .allowedOrigins("https://rutasfacil.com")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowedHeaders("*")
                    .maxAge(3600);
            }
        };
    }
}
```

### 3. Validación de Entrada
```java
// ✅ DTO con validaciones
@Data
public class RegistroDTO {
    @NotBlank(message = "Los nombres son requeridos")
    @Size(min = 2, max = 100)
    private String nombres;

    @NotNull
    @Email
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Cédula inválida")
    private String cedula;

    @Size(min = 6, message = "Mínimo 6 caracteres")
    private String password;
}
```

### 4. Encriptación de Contraseñas
```java
// ✅ Usar BCrypt (mejor que SHA256)
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

// En servicio
@Service
public class RegistroService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrar(Cliente cliente, String password) {
        usuario.setPassword(passwordEncoder.encode(password));
        return usuarioRepository.save(usuario);
    }
}
```

### 5. Autenticación con JWT
```java
// ✅ Reemplazar sesiones con JWT
@RestController
@RequestMapping("/api/auth")
public class JwtAuthController {
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // Verificar credenciales
        String token = jwtProvider.generateToken(usuario.getId());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}

// En filtro
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) {
        try {
            String token = getTokenFromRequest(request);
            if (token != null && jwtProvider.validateToken(token)) {
                UsernamePasswordAuthenticationToken auth = 
                    jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        filterChain.doFilter(request, response);
    }
}
```

### 6. SQL Injection Prevention
```java
// ❌ INSEGURO
String query = "SELECT * FROM usuarios WHERE username = '" + username + "'";
repository.createNativeQuery(query);

// ✅ SEGURO - Usar JPA
repository.findByUsername(username);

// ✅ SEGURO - Named Queries
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Optional<Usuario> findByUsername(@Param("username") String username);
}
```

### 7. CSRF Protection
```java
// ✅ Habilitar en Spring Security
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .anyRequest().authenticated();
    }
}
```

### 8. HTTPS en Producción
```properties
# ✅ Forzar HTTPS
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=${SSL_PASSWORD}
server.ssl.key-store-type=PKCS12

# Redirigir HTTP a HTTPS
server.servlet.session.cookie.secure=true
server.servlet.session.cookie.http-only=true
```

---

## 📊 Logging y Monitoreo

### 1. Configurar Logging
```xml
<!-- logback-spring.xml -->
<configuration>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>logs/application.log</file>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <root level="INFO">
        <appender-ref ref="FILE"/>
    </root>

    <logger name="com.utc.rutasfacil" level="DEBUG"/>
</configuration>
```

### 2. Auditoría de Cambios
```java
@Entity
@Table(name = "entregas_auditoria")
public class EntregaAuditoria {
    @Id
    @GeneratedValue
    private Long id;
    
    private Long entregaId;
    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fecha;
    private String usuarioId;
}

// En servicio
@Service
public class EntregaService {
    private final EntregaAuditoriaRepository auditoriaRepository;

    public void cambiarEstado(Long id, String nuevoEstado) {
        Entrega entrega = entregaRepository.findById(id).get();
        
        EntregaAuditoria auditoria = new EntregaAuditoria();
        auditoria.setEntregaId(id);
        auditoria.setEstadoAnterior(entrega.getEstado());
        auditoria.setEstadoNuevo(nuevoEstado);
        auditoria.setFecha(LocalDateTime.now());
        auditoriaRepository.save(auditoria);
        
        entrega.setEstado(nuevoEstado);
        entregaRepository.save(entrega);
    }
}
```

### 3. Actuator y Métricas
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

```properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=when-authorized
```

---

## 🧪 Testing

### 1. Test de Seguridad
```java
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        mockMvc.perform(post("/api/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\":\"invalid\",\"password\":\"wrong\"}"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    public void testRegistroWithValidData() throws Exception {
        mockMvc.perform(post("/registro")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                    "nombres":"Juan",
                    "apellidos":"Pérez",
                    "cedula":"1234567890",
                    "email":"test@example.com",
                    "telefono":"0912345678",
                    "username":"juan123",
                    "password":"secure123"
                }"""))
            .andExpect(status().isCreated());
    }
}
```

### 2. Test de Validación
```java
@SpringBootTest
public class RegistroServiceTest {
    
    @Autowired
    private RegistroService registroService;

    @Test
    public void testRegistroWithInvalidEmail() {
        Cliente cliente = new Cliente();
        cliente.setEmail("invalid-email");

        assertThrows(IllegalArgumentException.class, () -> {
            registroService.registrarCliente(cliente, "user", "pass");
        });
    }
}
```

---

## 🚀 Performance

### 1. Caché
```java
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("ciudades", "rutas");
    }
}

@Service
public class CiudadService {
    @Cacheable("ciudades")
    public List<Ciudad> obtenerCiudades() {
        return ciudadRepository.findAll();
    }
}
```

### 2. Paginación
```java
@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    Page<Entrega> findByClienteId(Long clienteId, Pageable pageable);
}

@RestController
public class EntregaController {
    @GetMapping("/mis-pedidos")
    public Page<Entrega> misPedidos(@RequestParam int page) {
        return entregaService.misPedidos(PageRequest.of(page, 10));
    }
}
```

### 3. Índices en BD
```sql
-- Índices para mejorar performance
CREATE INDEX idx_usuario_username ON usuarios(username);
CREATE INDEX idx_cliente_cedula ON clientes(cedula);
CREATE INDEX idx_entrega_cliente ON entregas(cliente_id);
CREATE INDEX idx_entrega_estado ON entregas(estado);
CREATE INDEX idx_ruta_origen_destino ON rutas(origen_id, destino_id);
```

---

## 📋 Checklist Pre-Producción

- [ ] Cambiar todas las credenciales (BD, API keys)
- [ ] Habilitar HTTPS
- [ ] Configurar CORS correctamente
- [ ] Implementar Spring Security
- [ ] Usar JWT tokens
- [ ] Validar todas las entradas
- [ ] Configurar logging
- [ ] Implementar auditoría
- [ ] Tests unitarios (cobertura > 80%)
- [ ] Tests de seguridad
- [ ] Performance testing
- [ ] Backup automático de BD
- [ ] Monitoreo con Actuator
- [ ] Documentación API (Swagger)
- [ ] Plan de recuperación ante fallos

---

## 📚 Referencias

- [OWASP Top 10](https://owasp.org/www-project-top-ten/)
- [Spring Security Docs](https://spring.io/projects/spring-security)
- [JWT Best Practices](https://tools.ietf.org/html/rfc8949)
- [PostgreSQL Security](https://www.postgresql.org/docs/current/sql-syntax.html)
