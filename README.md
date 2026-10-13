# GeoFiguras: manejo de figuras geométricas

Círculo, triángulo, cuadrilátero y pentágono regular en Java, con principios SOLID, GitFlow e interfaz gráfica en Swing (IntelliJ Swing UI Designer).

## Requisitos
- JDK 17 o superior
- IntelliJ IDEA con el plugin Swing UI Designer (viene incluido)
- Git

## Configuración de IntelliJ (una sola vez)
1. *Settings → Editor → GUI Designer*
    - **Generate GUI into:** Binary class files
    - **Default layout manager:** GridBagLayout
2. *Settings → Build, Execution, Deployment → Build Tools → Maven → Runner*: dejar desmarcado **Delegate IDE build/run actions to Maven**.

## Cómo ejecutar
### Interfaz gráfica
Abrir el proyecto (pom.xml) → clic derecho en `Main.java` → **Run 'Main.main()'**.

> La interfaz usa formularios `.form` y debe ejecutarse desde IntelliJ: el compilador de IntelliJ es el que convierte los formularios en componentes.

### Demostración por consola
Clic derecho en `DemostracionConsola.java` → **Run**. Muestra creación de figuras, desplazar, escalar, comparaciones y dimensiones no válidas.

## Estructura
```
com.parcial.figuras              Modelo: Punto, Figura y sus subclases, ComparadorFiguras
com.parcial.figuras.ui           Ventana principal, panel de dibujo, lector de campos
com.parcial.figuras.ui.formularios  Un sub-formulario por figura
```
Para agregar una figura nueva: crear la clase del modelo (extiende `Figura`), su sub-formulario (implementa `FormularioFigura`), registrar su forma en `FabricaFormas` y su botón en `VentanaFiguras`.

## Variables sensibles
Copiar `.env.example` como `.env` y completar los valores. El `.env` está en `.gitignore` y no se sube al repositorio.

## Flujo de trabajo
GitFlow: `main` (estable), `develop`, `feature/`, `release/`, `hotfix/`. Todo cambio entra por Pull Request revisado por otro integrante.

## Equipo
thorua, valentina-970, juliv06

**Versión:** 2.0.0