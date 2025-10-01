1) No Android, cada Activity tem um ciclo de vida (como nascer, pausar, morrer), controlado por métodos como onCreate(), onPause(), onDestroy(). Gerenciar bem esse ciclo é essencial para evitar gastos desnecessários de memória e travamentos. Ex: Se o usuário gira a tela, a Activity é destruída e recriada. Se você não salvar os dados certo, tudo pode se perder. Para evitar isso, usamos os métodos: onSaveInstanceState() para salvar dados importantes da tela e onRestoreInstanceState() para recuperar os dados salvos. Isso faz parte do conceito de State Restoration, que garante que o app continue de onde parou, mesmo após interrupções como rotação, chamadas ou troca de Activity.


2)
⦁	AndroidManifest.xml: arquivo de configurações do projeto, pode definir o nome da aplicação, permissões e a tela inicial.
⦁	res/: pasta com os recursos do app (imagens e layouts).
⦁	R.java: classe que da acesso aos recursos da pasta res/.
⦁	Activities: são as telas do app.