# Personal Trainer App

## Descripción

**Personal Trainer App** es una aplicación móvil diseñada para la gestión de entrenamientos personales. Esta herramienta permite a los entrenadores y usuarios gestionar ejercicios, rutinas, usuarios y cronogramas de manera eficiente y estructurada. Fue desarrollada como parte del primer parcial del curso de **Arquitectura de Software**.

## Funcionalidades

### Gestión de Ejercicios

- Crear, editar y eliminar ejercicios.

### Gestión de Rutinas

- Diseñar rutinas personalizadas.
- Asignar ejercicios específicos a cada rutina.
- Establecer la duración y objetivos de las rutinas.

### Gestión de Usuarios

- Registro y administración de usuarios.
- Información de perfil, incluyendo nombre y objetivos personales.

### Gestión de Cronograma

- Planificación semanal y mensual de rutinas.
- Notificaciones para recordatorios de entrenamientos.

## Patrones de Diseño

El proyecto utiliza una arquitectura en N capas para garantizar escalabilidad, modularidad y separación de responsabilidades, además de implementar los siguientes patrones de diseño:


- ### Patrón Observer:

   Utilizado para notificar al entrenador cuando un cronograma está habilitado para ser enviado al cliente. Esto asegura una comunicación reactiva y eficiente entre los componentes.

- ### Patrón Factory Method:

   Implementado para permitir al entrenador descargar el cronograma en diferentes formatos (JSON, CSV y PDF). Esto facilita la extensibilidad y el mantenimiento del código al trabajar con múltiples formatos de exportación.


## Tecnologías Utilizadas

- **Lenguaje de Programación**: Java.
- **IDE**: Android Studio.
- **Base de Datos**: SQLite.
- **Herramientas de Desarrollo**: Android Debug Bridge (ADB), Emuladores de Android.
- **Control de Versiones**: GitHub.

## Instalación y Configuración

### Prerrequisitos

- Android Studio instalado en tu sistema.

### Pasos

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Miguel-Machuca/entrenador-personal-design-patterns.git
   ```
2. Abrir el proyecto en Android Studio:
   - Inicia Android Studio y selecciona "Open an existing project".
   - Navega hasta la carpeta clonada y selecciona el proyecto.

. Ejecutar la aplicación:

- Conecta un dispositivo físico o utiliza un emulador.
- Haz clic en el botón de "Run" en Android Studio.

## Contribuciones

Este proyecto fue desarrollado como parte del curso de Arquitectura de Software. Actualmente no se aceptan contribuciones externas, pero puedes contactarme para sugerencias o comentarios.

## Autor

- **Miguel Angel Machuca Yavita**
- Curso: Arquitectura de Software
- Segundo Parcial
