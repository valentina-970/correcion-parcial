# Manejo de Figuras Geométricas

Círculo, triángulo, cuadrilátero y pentágono regular en Java, con SOLID y GitFlow.

## Requisitos
- JDK 17 o superior
- Git

## Cómo ejecutar
### Desde IntelliJ
Abrir el proyecto (pom.xml) → clic derecho en `Main.java` → Run 'Main.main()'.

### Desde la terminal (PowerShell)
```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out com.parcial.figuras.Main
```

### Desde la terminal (Linux/Mac)
```bash
javac -d out $(find src -name "*.java")
java -cp out com.parcial.figuras.Main
```

## Variables sensibles
Copiar `.env.example` a `.env` (ignorado por Git).

## Flujo de trabajo
GitFlow: `main` (estable), `develop`, `feature/`, `release/`, `hotfix/`. Todo cambio entra por Pull Request.

## Equipo
thorua, valentina-970, juliv06