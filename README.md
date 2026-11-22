# SistemaMatricula

**Aplicación de escritorio para la gestión del proceso de matrículas de estudiantes del nivel primario del Colegio Sagrados Corazones.**

Proyecto académico desarrollado en el curso de Programación Orientada a Objetos.

## Descripción del proyecto

SistemaMatricula es una aplicación de escritorio desarrollada en Java para gestionar y automatizar el proceso de matrículas del nivel primario del Colegio Sagrados Corazones.

El sistema surge ante las dificultades del proceso de matrícula tradicional, que dependía de documentos físicos y hojas de cálculo, generando demoras, errores en los registros y dificultades para consultar y mantener actualizada la información académica.

La aplicación permite centralizar la gestión de estudiantes, docentes, salones, cursos, horarios y matrículas mediante una interfaz gráfica que facilita las operaciones administrativas.

Asimismo, incorpora funcionalidades para generar fichas de matrícula en formato PDF, consultar listas de estudiantes matriculados por grado y exportar los horarios académicos a archivos Excel.

## Objetivo

Desarrollar una aplicación de escritorio que permita al Colegio Sagrados Corazones gestionar de forma eficiente y automatizada el proceso de matrícula, mediante el registro de estudiantes, docentes, cursos, salones, horarios y la generación de reportes académicos.

## Funcionalidades

1. Inicio de sesión mediante un sistema de autenticación de usuarios.
2. Navegación entre los diferentes módulos mediante un menú principal.
3. Registro, consulta, actualización y eliminación de información de estudiantes.
4. Registro y mantenimiento de información de docentes.
5. Gestión de salones del nivel primario.
6. Registro y mantenimiento de cursos por grado.
7. Registro y administración de horarios académicos.
8. Búsqueda de estudiantes y gestión de sus matrículas.
9. Generación de fichas y detalles de matrícula en formato PDF.
10. Visualización de estudiantes matriculados por grado.
11. Consulta de cursos asignados a cada docente.
12. Exportación de horarios académicos a archivos Excel.

## Tecnologías utilizadas

- **Lenguaje de programación:** Java.
- **Entorno de desarrollo:** Apache NetBeans IDE.
- **Base de datos:** MySQL.
- **Servidor local:** XAMPP.
- **Administración de base de datos:** MySQL Workbench.
- **Arquitectura:** Modelo-Vista-Controlador (MVC).
- **Control de versiones:** Git y GitHub.

## Documentación

Para conocer las principales interfaces de la aplicación y el diseño de la base de datos, consulta el siguiente documento:

- [Ver interfaces del sistema y diagrama de base de datos](docs/Interfaces-del-sistema.pdf).

## Requisitos e instrucciones de instalación

### Requisitos previos

- Java JDK compatible con el proyecto.
- Apache NetBeans IDE.
- XAMPP.
- MySQL Workbench.
- Conector JDBC de MySQL.
- Librería JCalendar para los componentes de selección de fechas.

### Instalación de JCalendar

El proyecto utiliza la librería **JCalendar** para incorporar componentes de selección de fechas en los formularios de la aplicación.

**Descarga:** [JCalendar 1.4 – Sitio oficial](https://www.toedter.com/download/jcalendar-1.4.zip).

Para instalarla desde Apache NetBeans, seguir estos pasos:

1. Descargar el archivo ZIP desde el enlace proporcionado.
2. Extraer su contenido y localizar el archivo `jcalendar.jar` dentro de la carpeta `lib`.
3. Abrir el proyecto SistemaMatricula en Apache NetBeans IDE.
4. Hacer clic derecho sobre el proyecto y seleccionar **Properties**.
5. Ingresar a la sección **Libraries**.
6. Seleccionar **Add JAR/Folder**.
7. Buscar y seleccionar el archivo `jcalendar.jar`.
8. Pulsar **Apply** y luego **OK**.

**Nota:** Es necesario agregar esta librería para que los componentes de selección de fechas funcionen correctamente. Si no se incorpora, podrían presentarse errores o no mostrarse los calendarios en los formularios correspondientes.

### Instalación y ejecución

1. Descargar o clonar el repositorio desde GitHub.
2. Instalar los requisitos necesarios para ejecutar el proyecto.
3. Iniciar los servicios correspondientes de XAMPP.
4. Abrir MySQL Workbench y establecer la conexión con el servidor MySQL local.
5. Importar el script SQL de la base de datos, disponible en la carpeta `database/`.
6. Abrir el proyecto desde Apache NetBeans IDE.
7. Verificar la configuración de conexión a la base de datos.
8. Comprobar que la librería JCalendar esté agregada al proyecto.
9. Ejecutar la aplicación desde NetBeans.

**Nota:** La ejecución requiere que el servidor de base de datos se encuentre activo y que la conexión JDBC esté correctamente configurada.

## Estructura del proyecto

El proyecto se organiza mediante paquetes que separan las responsabilidades de sus componentes:

- **Modelo:** clases que representan las entidades y datos del sistema.
- **Vista:** interfaces gráficas y formularios de la aplicación.
- **Controlador:** gestión de eventos e interacción entre las vistas y el modelo.
- **DAO:** operaciones de acceso y comunicación con la base de datos.
- **DTO:** objetos utilizados para transportar información entre los componentes del sistema.

Además, el repositorio incluye la carpeta `database/`, que contiene el script de la base de datos, y la carpeta `docs/`, donde se encuentra la documentación visual del proyecto.

## Alcance académico

SistemaMatricula fue desarrollado como parte del curso de Programación Orientada a Objetos, con el propósito de aplicar los conocimientos de desarrollo de software, programación en Java y gestión de bases de datos en un escenario educativo.

El proyecto está orientado a la automatización del proceso de matrícula del nivel primario del Colegio Sagrados Corazones, contemplando la administración de información académica y la generación de reportes.

Esta versión se presenta con fines académicos y de demostración. No representa necesariamente un despliegue comercial ni una implementación oficial en producción de la institución.

## Repositorio

Código fuente disponible en:

https://github.com/ltinco-sys/SistemaMatricula
