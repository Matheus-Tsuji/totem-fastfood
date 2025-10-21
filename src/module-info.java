module Totem {
    // Isso é necessário porque seu código usa classes do pacote java.sql (JDBC).
    requires java.sql;
    
    // Isso é necessário para que a JRE (Java Runtime Environment) possa executar
    // seu método main na classe Totem, acessando classes de outros módulos.
    // Você só precisa se preocupar com esta linha se o código principal não rodar.
    // Se o seu main estiver em Totem.java, use assim:
    exports Totem;
}