# Configuração do Firebase

Este projeto já contém a integração com Firebase Firestore, mas o arquivo `google-services.json` depende da conta Firebase da autora.

1. Acesse o Firebase Console.
2. Crie um projeto.
3. Adicione um aplicativo Android com o package `com.beatriz.catalogoia`.
4. Baixe o `google-services.json`.
5. Coloque o arquivo em `app/google-services.json`.
6. Ative o Cloud Firestore.
7. Para o projeto escolar, as regras de `firestore.rules` permitem leitura e escrita sem autenticação. Isso é adequado apenas para demonstração/desenvolvimento e não para produção.
8. Sincronize o Gradle e execute o aplicativo.

Não renomeie o package depois de cadastrar o aplicativo Android no Firebase.
