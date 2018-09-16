/**
 * Author:  adriano
 * Created: 23/05/2018
 */

DROP TABLE IF EXISTS cad_cor;
DROP TABLE IF EXISTS cad_veiculo;


create table cad_cor
(
   id_cor integer not null,
   descricao varchar(255) not null,
   primary key(id_cor)
);


create table cad_veiculo
(
   placa varchar(7) not null,
   ano_modelo integer not null,
   id_cor integer not null,
   ano_fabricacao integer not null,
   ativo boolean not null,
   primary key(placa),
   foreign key (id_cor) references cad_cor(id_cor)
);

