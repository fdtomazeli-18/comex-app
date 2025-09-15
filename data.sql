insert into cliente (cpf, nome, email, telefone, logradouro, bairro, cidade, uf, cep, status) values
('123.456.789-00', 'João Silva', 'joao.silva@example.com', '(11) 98765-4321', 'Rua A', 'Bairro B', 'Cidade C', 'SP', '12345-678', 'ATIVO'),
('987.654.321-00', 'Maria Oliveira', 'maria.oliveira@example.com', '(21) 87654-3210', 'Avenida X', 'Bairro Y', 'Cidade Z', 'RJ', '87654-321', 'ATIVO'),
('456.789.123-00', 'Carlos Souza', 'carlos.souza@example.com', '(31) 76543-2109', 'Travessa Q', 'Bairro W', 'Cidade V', 'MG', '65432-109', 'ATIVO');

insert into categoria (nome, status) values
('Eletrônicos', 'ATIVA'),
('Móveis', 'ATIVA'),
('Eletrodomésticos', 'ATIVA');

insert into produto (nome, preco, descricao, quantidade_estoque, categoria_id, status) values
('Smartphone', 1500.00, 'Smartphone com 4GB de RAM e 128GB de armazenamento', 10, 1, 'ATIVO'),
('Sofá', 1200.00, 'Sofá de 3 lugares com tecido impermeável', 5, 2, 'ATIVO'),
('Geladeira', 2500.00, 'Geladeira frost-free com 380 litros de capacidade', 3, 3, 'ATIVO');