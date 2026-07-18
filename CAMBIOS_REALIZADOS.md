# 🚚 RutasFacil - Gestor Inteligente de Entregas

## Descripción
RutasFacil es una aplicación web para gestión de entregas y optimización de rutas utilizando el algoritmo de Dijkstra. Permite a clientes registrar entregas y a administradores gestionar y asignar rutas óptimas.

---

## 🔧 Cambios Realizados

### Seguridad
- ✅ **Encriptación de contraseñas**: Implementado SHA-256 con salt para mayor seguridad
- ✅ **CORS restrictivo**: Cambiado de `origins = "*"` a `origins = "http://localhost:8081"`
- ✅ **Validación de datos**: Añadida validación en frontend y backend
- ✅ **Manejo global de excepciones**: Implementado `GlobalExceptionHandler`

### Mejoras en Código Java
- ✅ **AuthController**: Nuevo controlador para autenticación segura
- ✅ **PasswordEncoder**: Clase para encriptación y verificación de contraseñas
- ✅ **ApiResponse DTO**: Respuestas genéricas y consistentes
- ✅ **Validaciones mejoradas**: En RegistroService y RegistroController
- ✅ **Métodos en repositorios**: Agregados `findByUsername()` y `findByCedula()`

### Diseño de HTMLs (Transformación Completa)
Todos los HTMLs fueron rediseñados con:

1. **Diseño Moderno y Responsive**
   - Variables CSS consistentes (colores, fuentes, espaciados)
   - Flexbox y Grid para layouts
   - Media queries para dispositivos móviles

2. **Mejor Experiencia de Usuario**
   - Animaciones suaves
   - Feedback visual para interacciones
   - Mensajes de error/éxito claros
   - Estados de carga (spinners)

3. **Accesibilidad**
   - Atributos `aria-label` en inputs
   - Labels semánticos
   - Contraste de colores mejorado
   - Navegación intuitiva

4. **Validación Frontend**
   - Validaciones en tiempo real
   - Mensajes de error descriptivos
   - Prevención de envíos duplicados

#### HTMLs Mejorados:
- **login.html**: Form seguro con validaciones y recuperación de sesión
- **registro.html**: Formulario con secciones organizadas (Datos Personales + Credenciales)
- **panel.html**: Dashboard con navbar, datos del cliente e información de acciones
- **admin.html**: Panel administrativo con búsqueda, filtrado y tarjetas modernas
- **pedido.html**: Rediseñado con mejor estructura visual
- **mispedidos.html**: Mejorado con mejor presentación
- **seguimiento.html**: Timeline mejorada con mejor CSS

---

## 🔐 API Endpoints

### Autenticación
```
POST /api/auth/login
Body: { "username": "user", "password": "pass" }
Response: { "success": true, "usuario": {...} }
```

### Registro
```
POST /registro
Body: {
  "nombres": "Juan",
  "apellidos": "Pérez",
  "cedula": "1234567890",
  "email": "user@example.com",
  "telefono": "0912345678",
  "username": "juanperez",
  "password": "secure123"
}
```

### Entregas
```
GET /entregas                      - Listar todas
POST /entregas                     - Crear nueva
GET /entregas/{id}                 - Obtener por ID
GET /entregas/mis-pedidos/{clienteId} - Pedidos del cliente
DELETE /entregas/{id}              - Eliminar
```

### Admin
```
GET /admin/entregas                - Listar todas las entregas
PUT /admin/entregas/{id}/estado?estado=ENTREGADA - Cambiar estado
```

### Rutas
```
GET /rutas/optima/{origenId}/{destinoId} - Calcular ruta óptima
```

---

## 📋 Componentes Añadidos

### Nuevos Archivos
- ✅ `PasswordEncoder.java` - Encriptación SHA-256
- ✅ `AuthController.java` - Controlador de autenticación
- ✅ `GlobalExceptionHandler.java` - Manejo de excepciones
- ✅ `ApiResponse.java` - DTO para respuestas genéricas

### Mejoras en Repositorios
- ✅ `findByUsername()` en UsuarioRepository
- ✅ `findByCedula()` en ClienteRepository

---

## ⚠️ Problemas Encontrados y Solucionados

### Errores Críticos
1. **Sin encriptación de contraseñas** → ✅ Resuelto con SHA-256 + salt
2. **Sin validación en backend** → ✅ Agregada en controladores y servicios
3. **CORS abierto a todos** → ✅ Restringido a localhost:8081
4. **API Key hardcodeada en Google Maps** → ⚠️ Requiere API key válida
5. **Sin autenticación real** → ✅ Implementado AuthController

### Problemas de Presentación
- ❌ CSS desorganizado → ✅ Refactorizado con variables y estructura clara
- ❌ Sin responsive design → ✅ Añadido con media queries
- ❌ Falta accesibilidad → ✅ Mejorada con labels y aria-labels
- ❌ Formularios sin validación → ✅ Validación frontend completa

---

## 🚀 Configuración para Producción

### Variables de Entorno (application.properties)
```properties
# Cambiar contraseña de BD
spring.datasource.password=YOUR_SECURE_PASSWORD

# Cambiar usuario y BD
spring.datasource.username=YOUR_USER

# Desactivar SQL logging en producción
spring.jpa.show-sql=false

# Cambiar CORS origin
server.port=8080
```

### Recomendaciones de Seguridad
1. **Usar Spring Security** para autenticación/autorización más robusta
2. **Implementar JWT tokens** para API stateless
3. **Validar entrada de datos** más estrictamente (sanitización)
4. **Usar HTTPS** en producción
5. **Implementar rate limiting** en endpoints públicos
6. **Agregar CSRF protection**
7. **Usar prepared statements** en queries SQL

---

## 📚 Dependencias Actuales

```xml
- Spring Boot 4.0.7
- Spring Data JPA
- Spring Web MVC
- Spring Validation
- Thymeleaf
- PostgreSQL
- DevTools
```

---

## 🧪 Próximos Pasos Recomendados

1. **Tests Unitarios**: Crear tests para servicios y controllers
2. **Documentación API**: Implementar Swagger/OpenAPI
3. **Logging**: Agregar logging con SLF4J
4. **Caché**: Implementar caching para rutas frecuentes
5. **Monitoreo**: Agregar actuator de Spring Boot
6. **Auditoría**: Log de cambios en entregas
7. **Notificaciones**: Sistema de alertas por email
8. **Escalabilidad**: Base de datos con índices optimizados

---

## 👨‍💻 Estructura del Proyecto

```
src/main/java/com/utc/rutasfacil/
├── config/
│   ├── GlobalExceptionHandler.java (✅ Nuevo)
│   └── PasswordEncoder.java (✅ Nuevo)
├── controller/
│   ├── AuthController.java (✅ Nuevo)
│   ├── RegistroController.java (✅ Mejorado)
│   ├── AdminEntregaController.java (✅ Seguro)
│   ├── EntregaController.java (✅ Seguro)
│   └── ...
├── dto/
│   ├── ApiResponse.java (✅ Nuevo)
│   ├── RegistroDTO.java
│   └── RutaOptimaDTO.java
├── entity/
│   ├── Usuario.java
│   ├── Cliente.java
│   ├── Entrega.java
│   └── ...
├── graph/
│   ├── Dijkstra.java
│   ├── Grafo.java
│   └── ...
├── repository/
│   ├── UsuarioRepository.java (✅ Mejorado)
│   ├── ClienteRepository.java (✅ Mejorado)
│   └── ...
└── service/
    ├── RegistroService.java (✅ Mejorado)
    ├── AuthService.java
    └── ...

src/main/resources/templates/ (✅ Todos rediseñados)
├── login.html (✅ Mejorado)
├── registro.html (✅ Mejorado)
├── panel.html (✅ Mejorado)
├── admin.html (✅ Mejorado)
├── pedido.html
├── mispedidos.html
└── seguimiento.html
```

---

## 📝 Notas

- El proyecto usa PostgreSQL. Asegúrate de tener la BD creada
- Los puertos por defecto: Backend (8081), Frontend (localhost)
- Cambiar `localhost` por el dominio real en producción
- Implementar autenticación con sesiones o JWT según requerimientos

---

**Última actualización**: 16/07/2026
**Estado**: Análisis y mejoras completadas ✅
