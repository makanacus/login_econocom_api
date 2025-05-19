# 🛡️ Login Backend - Spring Boot API

Este proyecto es una API backend desarrollada en **Java 8** con **Spring Boot**, diseñada para gestionar autenticación y funcionalidades de login. Está preparada para ejecutarse en **Visual Studio Code** o desde la terminal usando **Maven**.

---

## 📦 Requisitos

Asegúrate de tener lo siguiente instalado en tu sistema:

- [Java JDK 8](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [Visual Studio Code](https://code.visualstudio.com/)
- Extensiones recomendadas en VS Code:
  - Java Extension Pack
  - Spring Boot Extension Pack

> ⚠️ Este proyecto requiere **Java 8**. Si tienes un JDK más reciente, asegúrate de configurar JDK 8 como el JDK del proyecto en VS Code (`Java: Configure Java Runtime`).

---

🗄️ Base de datos de prueba local

Este proyecto incluye una base de datos de prueba en modo local, ubicada en la carpeta src/main/resources. Esta base de datos contiene un usuario preconfigurado para pruebas:

Usuario: usuario

Email: usuario@ejemplo.com

Contraseña: 123456

Puedes utilizar estas credenciales para probar las funcionalidades de autenticación y login.

---

## 🚀 Cómo ejecutar el proyecto

### ✅ Opción 1: Desde Visual Studio Code

1. Abre el proyecto en VS Code.
2. Asegúrate de tener abierta la clase principal:  
   `src/main/java/com/technical_proof/loginbackend/LoginbackendApplication.java`
3. Haz clic en el botón **Run** que aparece sobre el método `main`.
4. Alternativamente:
   - Ve a la pestaña lateral **"Run and Debug"**.
   - Selecciona la configuración: `LoginbackendApplication`
   - Haz clic en **"Run"** (`Ctrl + F5`) o **"Debug"** (`F5`)

---

### ✅ Opción 2: Desde la terminal (Maven) si lo tienes de manera global

```bash
mvn spring-boot:run

