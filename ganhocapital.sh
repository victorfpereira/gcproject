#!/bin/bash

# Defina o caminho para o arquivo JAR
JAR_PATH="target/ganho-capital-1.0-jar-with-dependencies.jar"

# Verifica se o arquivo JAR existe
if [ ! -f "$JAR_PATH" ]; then
    echo "Erro: Arquivo JAR não encontrado em $JAR_PATH"
    echo "Certifique-se de que o projeto foi compilado com sucesso."
    exit 1
fi

# Executa o JAR
echo "Executando o projeto..."
java -jar "$JAR_PATH"

# Verifica se a execução foi bem-sucedida
if [ $? -eq 0 ]; then
    echo "Execução concluída com sucesso."
else
    echo "Erro durante a execução."
    exit 1
fi
