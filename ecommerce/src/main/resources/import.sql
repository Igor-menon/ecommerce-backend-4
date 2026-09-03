insert into categoria (nome, descricao) VALUES ('Informática', 'Produtos de Informática');
insert into categoria (nome, descricao) VALUES ('Livros', 'Livros tecnicos');
insert into categoria (nome, descricao) VALUES ('Cadernos', 'Cadernos infantis');
insert into categoria (nome, descricao) VALUES ('Moda', 'Produtos de Moda');
insert into categoria (nome, descricao) VALUES ('Tabuleiro', 'Jogos de tabuleiro');

insert into produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Código Limpo', 'Livro do autor Robert C. Martin', 87.94, 20, 2);
insert into produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Vestido', 'Vestido vermelho longo', 259.99, 12, 4);
insert into produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Mundo de Gumball', 'Livro de colorir infantil', 59.99, 78, 3);
insert into produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Metamorfose', 'Livro do autor Franz Kafka', 259.99, 12, 1);
insert into produto (nome, descricao, preco, estoque, categoria_id) VALUES ('Banco Imobiliario', 'Jogo para até 6 jogadores', 500.00, 88, 5);


insert into cliente (nome, email, telefone) VALUES ('Jorge da Silva','Jorge.silva@gmail.com','149963582204');
insert into cliente (nome, email, telefone) VALUES ('Maria Oliveira', 'maria.oliveira@gmail.com', '14998761234');
insert into cliente (nome, email, telefone) VALUES ('Carlos Santos', 'carlos.santos@hotmail.com', '14981234567');
insert into cliente (nome, email, telefone) VALUES ('Ana Costa', 'ana.costa@yahoo.com.br', '14997654321');
insert into cliente (nome, email, telefone) VALUES ('Lucas Pereira', 'lucas.pereira@outlook.com', '14991238765');

insert into pedido (data, status, valor_total, cliente_id) VALUES ('2026-04-02 10:00:00','Entregue',750.00,4);
insert into pedido (data, status, valor_total, cliente_id) VALUES ('2026-05-15 10:00:00', 'Pendente', 120.50, 1);
insert into pedido (data, status, valor_total, cliente_id) VALUES ('2026-06-20 10:00:00', 'Enviado', 340.00, 2);
insert into pedido (data, status, valor_total, cliente_id) VALUES ('2026-07-10 10:00:00', 'Processando', 1050.75, 3);
insert into pedido (data, status, valor_total, cliente_id) VALUES ('2026-08-01 10:00:00', 'Cancelado', 45.90, 5);

insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (3, 87.94, 3, 1);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (2, 150.00, 1, 2);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 35.50, 2, 5);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (5, 12.99, 4, 3);
insert into item_pedido (quantidade, valor_unitario, pedido_id, produto_id) VALUES (1, 1200.00, 5, 4);

insert into pagamento (valor, data, status, tipo, pedido_id) VALUES (750.00, '2026-04-02 10:00:00', 'Aprovado', 'PIX', 1);
insert into pagamento (valor, data, status, tipo, pedido_id) VALUES (120.50, '2026-05-15 10:00:00', 'Pendente', 'Boleto', 2);
insert into pagamento (valor, data, status, tipo, pedido_id) VALUES (340.00, '2026-06-20 10:00:00', 'Aprovado', 'Cartão de Crédito', 3);
insert into pagamento (valor, data, status, tipo, pedido_id) VALUES (1050.75, '2026-07-10 10:00:00', 'Processando', 'Cartão de Crédito', 4);
insert into pagamento (valor, data, status, tipo, pedido_id) VALUES (45.90, '2026-08-08 10:00:00', 'Recusado', 'PIX', 5);