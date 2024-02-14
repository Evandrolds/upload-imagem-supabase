### Api para upload de imagens redimencionadas 
Configuração do Supabase

O arquivo application.properties é um arquivo de configuração comum em muitos frameworks de desenvolvimento de aplicativos, como Spring Boot para Java. Ele é usado para definir diversas configurações relacionadas à aplicação, como propriedades do banco de dados, configurações de segurança e chaves de API.

Aqui está uma descrição da proposta dessas configurações específicas para o Supabase:

    supabase.api.url: Esta propriedade é usada para definir a URL da API do Supabase. Ela deve ser configurada com o URL da API fornecida pelo Supabase para o seu projeto específico. Geralmente, isso inclui o protocolo (HTTP ou HTTPS), o nome do host e, opcionalmente, o número da porta.

    supabase.api.key: Esta propriedade é usada para definir a chave de API do Supabase. A chave de API é necessária para autenticar solicitações à API do Supabase e acessar os recursos do projeto. Ela deve ser configurada com a chave de API fornecida pelo Supabase.

    supabase.api.secret: Esta propriedade é usada para definir a chave secreta do Supabase. A chave secreta é usada em conjunto com a chave de API para autenticar e autorizar solicitações à API do Supabase. Ela deve ser configurada com a chave secreta fornecida pelo Supabase.

    bucket.name: Esta propriedade é usada para definir o nome do bucket de armazenamento do Supabase. Um bucket é uma unidade de armazenamento na nuvem usada para armazenar arquivos, como imagens, vídeos, documentos, etc. Esta propriedade deve ser configurada com o nome do bucket especificado em sua configuração do Supabase.

Certifique-se de substituir ${API_URL}, ${API_KEY}, ${API_SECRET_KEY} e ${BUCKET_NAME} pelos valores reais fornecidos pelo Supabase para o seu projeto específico.

Este arquivo de configuração permite que a aplicação se conecte e interaja com os serviços fornecidos pelo Supabase, como autenticação de usuário, acesso ao banco de dados e armazenamento de arquivos. Certifique-se de proteger essas informações sensíveis e evitar compartilhá-las publicamente.
***
Descrição da Classe UploadService

A classe UploadService é responsável por lidar com o envio de imagens para o Supabase Storage, redimensionando-as conforme necessário. Esta classe encapsula a lógica para preparar a requisição HTTP para enviar a imagem para o Supabase, utilizando os parâmetros configurados na inicialização da classe.
Propriedades:

    supabaseApiUrl: URL da API do Supabase.
    supabaseApiKey: Chave de API do Supabase.
    supabaseApiSecret: Segredo de API do Supabase.
    bucketName: Nome do bucket no Supabase Storage.

Métodos:

    uploadImage(MultipartFile imageFile, int width, int height): Este método faz o upload de uma imagem para o Supabase Storage, redimensionando-a de acordo com a largura e altura especificadas. Ele recebe o arquivo de imagem, largura e altura desejadas como parâmetros e retorna uma mensagem indicando o resultado do upload.

    resizeImage(byte[] sourceBytes, int width, int height): Este método redimensiona uma imagem para as dimensões especificadas. Recebe os bytes da imagem original, largura e altura desejadas como parâmetros e retorna os bytes da imagem redimensionada.

    converteImagem(Image img): Este método converte uma imagem para o formato BufferedImage. Recebe a imagem a ser convertida e retorna a imagem convertida para BufferedImage.

A classe utiliza bibliotecas Java padrão para manipulação de imagens e envio de requisições HTTP. Ela segue a estrutura padrão de classes de serviço em Java, encapsulando a lógica de negócios em métodos bem definidos e reutilizáveis.

Proposta da Classe UploadController

A classe UploadController é responsável por controlar a funcionalidade de upload de imagens em um sistema. Ela atua como um ponto de entrada para receber requisições HTTP para realizar o upload e redimensionamento de imagens, delegando o processamento para o serviço correspondente.
Construtor

O construtor da classe UploadController recebe uma instância do serviço de upload (UploadService) como parâmetro, permitindo a injeção de dependência desse serviço no controlador.
Método uploadImage

Este método é mapeado para o verbo HTTP POST e rota /upload/width{width}/height/{height}. Ele recebe um arquivo de imagem (file) e as dimensões desejadas (width e height) para redimensionamento da imagem. Os parâmetros são extraídos da requisição usando a anotação @RequestParam.

    Parâmetros:
        file: Arquivo de imagem a ser enviado.
        width: Largura desejada para a imagem redimensionada.
        height: Altura desejada para a imagem redimensionada.

    Retorno: ResponseEntity<?>
        Retorna uma resposta HTTP encapsulada em um ResponseEntity contendo a resposta da operação de upload. Neste caso, a resposta é obtida a partir do serviço de upload.

    Exceções lançadas:
        IOException: Se ocorrer um erro de E/S durante o processo de upload.
        InterruptedException: Se a operação de upload for interrompida.

Considerações Adicionais

    A anotação @PostMapping define que este método é acionado apenas para requisições do tipo POST.
    A anotação @RequestParam é utilizada para mapear os parâmetros da requisição para os parâmetros do método.

Este controlador fornece uma interface simples para que os clientes enviem arquivos de imagem para o servidor, onde serão redimensionados conforme as dimensões especificadas. O processamento real é realizado pelo serviço de upload (UploadService), mantendo a separação de responsabilidades e a modularidade do sistema.
