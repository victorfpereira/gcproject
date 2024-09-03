@echo off

REM Defina o caminho para o arquivo JAR
set JAR_PATH=target\ganho-capital-1.0-jar-with-dependencies.jar

REM Verifica se o arquivo JAR existe
if not exist "%JAR_PATH%" (
    echo Erro: Arquivo JAR nao encontrado em %JAR_PATH%
    echo Certifique-se de que o projeto foi compilado com sucesso.
    exit /b 1
)

REM Executa o JAR
echo Executando o projeto...
java -jar "%JAR_PATH%"

REM Verifica se a execução foi bem-sucedida
if %ERRORLEVEL% equ 0 (
    echo Execucao concluida com sucesso.
) else (
    echo Erro durante a execucao.
    exit /b 1
)
