MERGE INTO tb_roles (role_id, name) VALUES (1, 'ADMIN');
MERGE INTO tb_roles (role_id, name) VALUES (2, 'RESIDENT');
MERGE INTO tb_roles (role_id, name) VALUES (3, 'CARETAKER');
MERGE INTO tb_roles (role_id, name) VALUES (4, 'DOORMAN');

-- Resident Services
MERGE INTO tb_services (service_id, name, description, icon) VALUES (1, 'Reservas', 'Fazer reservas de áreas comuns (salão de festas, churrasqueira, etc.).', 'EmojiTransportationIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (2, 'Visitantes', 'Cadastrar visitantes antecipadamente para agilizar a entrada.', 'LocalLibraryIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (3, 'Encomendas', 'Receber notificações sobre entregas e registrar o recebimento.', 'LocalShippingIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (4, 'Solicitações de Manutenção', 'Abrir tickets para manutenção dentro da unidade (por exemplo, consertos de infraestrutura).', 'BuildIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (5, 'Comunicados', 'Visualizar comunicados e avisos do condomínio.', 'AnnouncementIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (6, 'Pagamentos', 'Consultar boletos e histórico de pagamentos.', 'PaymentIcon');

-- Administrator Services
MERGE INTO tb_services (service_id, name, description, icon) VALUES (7, 'Gerenciamento de Reservas', 'Aprovar ou cancelar reservas de áreas comuns.', 'BookIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (8, 'Visitantes e Acessos', 'Visualizar histórico de visitantes e controlar acessos.', 'LocalLibraryIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (9, 'Gerenciamento de Encomendas', 'Registrar recebimento de encomendas e monitorar entregas.', 'LocalShippingIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (10, 'Manutenção', 'Gerenciar tickets de manutenção e priorizar demandas.', 'BuildIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (11, 'Funcionários', 'Gerenciar o cadastro e escalas de funcionários.', 'AssignmentIndIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (12, 'Financeiro', 'Acompanhar pagamentos, despesas e gerar relatórios financeiros.', 'AttachMoneyIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (13, 'Comunicados', 'Enviar comunicados e notificações para todos os moradores.', 'AnnouncementIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (14, 'Configurações de Sistema', 'Configurar regras e políticas do sistema, como horários de reserva e acessos.', 'SettingsIcon');

-- Caretaker Services
MERGE INTO tb_services (service_id, name, description, icon) VALUES (15, 'Tickets de Manutenção', 'Ver e gerenciar tickets de manutenção atribuídos para execução.', 'BuildIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (16, 'Áreas Comuns', 'Monitorar e relatar o status de áreas comuns e infraestrutura.', 'BeachAccessIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (17, 'Checklists de Limpeza e Manutenção', 'Atualizar e completar checklists diários/semanal.', 'BuildIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (18, 'Acompanhamento de Encomendas', 'Registrar entregas realizadas.', 'LocalShippingIcon');

-- Doorman Services
MERGE INTO tb_services (service_id, name, description, icon) VALUES (19, 'Cadastro de Visitantes', 'Registrar visitantes e liberar acesso ao condomínio.', 'LocalLibraryIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (20, 'Unidades', 'Ver lista de unidades e moradores para contato em caso de visitas ou entregas.', 'ApartmentIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (21, 'Registro de Encomendas', 'Registrar recebimento e notificar moradores sobre entregas.', 'LocalShippingIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (22, 'Monitoramento de Acesso', 'Visualizar entradas e saídas do condomínio.', 'DesktopMacIcon');
MERGE INTO tb_services (service_id, name, description, icon) VALUES (23, 'Notificações', 'Receber alertas sobre visitantes não autorizados ou restrições de acesso.', 'AnnouncementIcon');

-- Role-Service Associations
-- Resident
INSERT INTO tb_roles_services (role_id, service_id) VALUES (2, 1);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (2, 2);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (2, 3);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (2, 4);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (2, 5);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (2, 6);

-- Administrator
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 7);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 8);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 9);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 10);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 11);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 12);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 13);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (1, 14);

-- Caretaker
INSERT INTO tb_roles_services (role_id, service_id) VALUES (3, 15);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (3, 16);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (3, 17);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (3, 18);

-- Doorman
INSERT INTO tb_roles_services (role_id, service_id) VALUES (4, 19);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (4, 20);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (4, 21);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (4, 22);
INSERT INTO tb_roles_services (role_id, service_id) VALUES (4, 23);
