# 🚀 Guía de Instalación y Ejecución - RutasFacil

## Requisitos del Sistema

- **Java**: 11 o superior
- **Maven**: 3.6+
- **PostgreSQL**: 12+
- **Git** (opcional)

---

## 📥 Instalación

### 1. Configurar Base de Datos PostgreSQL

```sql
-- Conectarse como usuario postgres
psql -U postgres

-- Crear base de datos
CREATE DATABASE BDD_RutasFacil;

-- Crear usuario (CAMBIAR CONTRASEÑA)
CREATE USER rutasfacil_user WITH PASSWORD 'tu_contraseña_segura';

-- Otorgar permisos
GRANT ALL PRIVILEGES ON DATABASE BDD_RutasFacil TO rutasfacil_user;

-- Conectarse a la BD
\c BDD_RutasFacil rutasfacil_user;
```

### 2. Actualizar application.properties

```properties
# Abrir: src/main/resources/application.properties

spring.application.name=RutasFacil
server.port=8081

# PostgreSQL - CAMBIAR CREDENCIALES
spring.datasource.url=jdbc:postgresql://localhost:5432/BDD_RutasFacil
spring.datasource.username=rutasfacil_user
spring.datasource.password=tu_contraseña_segura
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Thymeleaf
spring.thymeleaf.cache=false
```

### 3. Clonar o Descargar el Proyecto

```bash
# Si tienes Git
git clone <url_repositorio>
cd rutasfacil

# O simplemente abre la carpeta del proyecto en VS Code
```

---

## 🔧 Construcción del Proyecto

### Opción 1: Maven desde Terminal

```bash
# Limpiar y compilar
mvn clean install

# Compilar sin tests
mvn clean install -DskipTests
```

### Opción 2: IDE (VS Code / IntelliJ)

1. Abrir la carpeta del proyecto
2. Esperar a que se descarguen las dependencias
3. Click derecho en `pom.xml` → "Run Maven Build"

---

## 🚀 Ejecutar la Aplicación

### Opción 1: Maven

```bash
mvn spring-boot:run
```

### Opción 2: Ejecutar JAR

```bash
# Primero compilar
mvn package

# Luego ejecutar
java -jar target/rutasfacil-0.0.1-SNAPSHOT.jar
```

### Opción 3: IDE (VS Code Extension Spring Boot)

1. Abrir la paleta de comandos (`Ctrl+Shift+P`)
2. Buscar "Spring Boot: Start"
3. Seleccionar la aplicación

---

## 📝 Verificar que Funciona

### 1. Abrir en el Navegador

```
http://localhost:8081/login
```

Deberías ver la página de login mejorada.

### 2. Probar Registro

```bash
curl -X POST http://localhost:8081/registro \
  -H "Content-Type: application/json" \
  -d '{
    "nombres": "Juan",
    "apellidos": "Pérez",
    "cedula": "1234567890",
    "email": "juan@example.com",
    "telefono": "0912345678",
    "username": "juan123",
    "password": "password123"
  }'
```

**Respuesta esperada:**
```json
{
  "success": true,
  "message": "Registro exitoso",
  "usuario": { ... }
}
```

### 3. Probar Login

```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "juan123",
    "password": "password123"
  }'
```

**Respuesta esperada:**
```json
{
  "success": true,
  "message": "Autenticación exitosa",
  "usuario": { ... }
}
```

### 4. Acceder al Panel

```
http://localhost:8081/panel
```

Debería cargar el dashboard del cliente.

---

## 🛠️ Solución de Problemas

### Error: "Can't reach database"

```
❌ Problema: Conexión fallida a PostgreSQL

✅ Soluciones:
1. Verificar que PostgreSQL esté ejecutándose
   sudo systemctl status postgresql

2. Verificar credenciales en application.properties

3. Verificar que la BD exista
   psql -l
```

### Error: "Port 8081 is already in use"

```
❌ Problema: Puerto 8081 ocupado

✅ Soluciones:
1. Cambiar puerto en application.properties
   server.port=8082

2. O matar proceso
   lsof -i :8081
   kill -9 <PID>
```

### Error: "Could not resolve dependencies"

```
❌ Problema: Maven no puede descargar dependencias

✅ Soluciones:
mvn clean
mvn dependency:resolve
mvn clean install -DskipTests
```

### Error: "Invalid API Key for Google Maps"

```
❌ Problema: API Key de Maps no válida

✅ Soluciones:
1. Obtener API Key en https://console.cloud.google.com
2. Cambiar en: src/main/resources/templates/seguimiento.html
3. Reemplazar: key=TU_API_KEY
```

---

## 🔍 Verificar Instalación

### Verificar Java
```bash
java -version
# Esperado: Java 11 o superior
```

### Verificar Maven
```bash
mvn -version
# Esperado: Maven 3.6+
```

### Verificar PostgreSQL
```bash
psql -version
# Esperado: PostgreSQL 12+
```

### Verificar Puerto
```bash
lsof -i :8081
# Deberías ver: java ... LISTEN
```

---

## 📚 Archivos Importantes

```
rutasfacil/
├── pom.xml                          # Dependencias Maven
├── src/main/resources/
│   ├── application.properties       # ⚠️ CAMBIAR CREDENCIALES
│   └── templates/
│       ├── login.html              # ✅ Mejorado
│       ├── registro.html           # ✅ Mejorado
│       ├── panel.html              # ✅ Mejorado
│       └── admin.html              # ✅ Mejorado
├── CAMBIOS_REALIZADOS.md           # 📄 Documentación
├── RESUMEN_EJECUTIVO.md            # 📊 Resumen
└── SEGURIDAD_Y_MEJORES_PRACTICAS.md # 🔐 Seguridad
```

---

## 🧪 Scripts de Prueba

### Script: Crear datos de prueba

```sql
-- Conectarse a la BD
psql -U rutasfacil_user -d BDD_RutasFacil

-- Insertar ciudades de ejemplo
INSERT INTO ciudades (nombre) VALUES ('Quito'), ('Guayaquil'), ('Cuenca');

-- Insertar rutas entre ciudades
INSERT INTO rutas (origen_id, destino_id, distancia, tiempo_estimado) 
VALUES (1, 2, 250.5, 360), (2, 3, 320.2, 420);

-- Verificar
SELECT * FROM ciudades;
SELECT * FROM rutas;
```

---

## 🔐 Configuración para Producción

### 1. Cambiar Credenciales
```properties
# ❌ NUNCA en Git
spring.datasource.username=postgres
spring.datasource.password=220704

# ✅ Usar variables de entorno
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

### 2. Desactivar Logs SQL
```properties
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
```

### 3. Cambiar DDL a Validate
```properties
spring.jpa.hibernate.ddl-auto=validate
```

### 4. Habilitar HTTPS
```properties
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=${SSL_PASSWORD}
server.ssl.key-store-type=PKCS12
```

---

## 📊 Verificar Logs

### Ver logs en tiempo real
```bash
# En terminal mientras corre la app
tail -f target/spring-boot-app.log
```

### Logs esperados en startup
```
2026-07-16 10:30:45 - Starting RutasFacilApplication
2026-07-16 10:30:45 - Spring Boot v4.0.7
2026-07-16 10:30:46 - Tomcat started on port 8081
2026-07-16 10:30:46 - RutasFacilApplication started
```

---

## 📞 Soporte

Si encuentras problemas:

1. Revisa los logs en la terminal
2. Verifica `CAMBIOS_REALIZADOS.md`
3. Consulta `SEGURIDAD_Y_MEJORES_PRACTICAS.md`
4. Prueba los endpoints con curl

---

## ✅ Checklist Final

- [ ] PostgreSQL instalado y ejecutándose
- [ ] Base de datos `BDD_RutasFacil` creada
- [ ] Usuario `rutasfacil_user` configurado
- [ ] application.properties actualizado
- [ ] Maven instalado
- [ ] Java 11+ instalado
- [ ] `mvn clean install` ejecutado exitosamente
- [ ] `mvn spring-boot:run` en ejecución
- [ ] http://localhost:8081/login accesible
- [ ] Login y registro funcionando

---

**¡Listo para usar! 🎉**

Si todo funciona correctamente, deberías poder:
- ✅ Acceder a http://localhost:8081/login
- ✅ Registrar un nuevo usuario
- ✅ Iniciar sesión
- ✅ Ver el panel del cliente
- ✅ Acceder a http://localhost:8081/admin para gestión
