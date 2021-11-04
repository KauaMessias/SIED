create database db_escola;

use db_escola;

create table tb_professor(
id_professor int primary key auto_increment,
nome_professor varchar(10) not null,
sobrenome_professor varchar(10) not null,
nascimento_professor varchar(10) not null,
turno_professor varchar(30) not null,
formacao_professor varchar(20) not null,
fone_professor varchar(15) not null,
email_professor varchar(30),
rua_professor varchar(20) not null,
num_casa_professor int not null,
bairro_professor varchar (30) not null,
cidade_professor varchar(30) not null,
cep_professor int not null,
estado_professor varchar(30) not null,
matricula_professor int(10) not null unique,
vinculo_professor varchar(15) not null,
id_turma int not null,
foreign key(id_turma) references tb_turma(id_turma)
);


create table tb_aluno(
id_aluno int primary key auto_increment,
nome_aluno varchar(10) not null,
sobrenome_aluno varchar(10) not null,
nascimento_aluno varchar(10) not null,
turno_aluno varchar(10) not null,
fone_aluno varchar(15),
email_aluno varchar(30),
rua_aluno varchar(20) not null,
num_casa_aluno int not null,
bairro_aluno varchar (30) not null,
cidade_aluno varchar(30) not null,
cep_aluno int not null,
estado_aluno varchar(30) not null,
matricula_aluno int not null unique,
id_turma int not null,
foreign key(id_turma) references tb_turma(id_turma),
id_professor int not null,
foreign key(id_professor) references tb_professor(id_professor)
);

create table tb_turma(
id_turma int primary key auto_increment,
nome_turma varchar(10) not null unique,
turno varchar(10) not null,
id_escola int not null,
foreign key(id_escola) references tb_escola(id_escola)
);


describe tb_turma;

create table tb_escola(
id_escola int primary key auto_increment,
nome_escola varchar(50) not null,
codigo_inep int,
rua_escola varchar(20) not null,
num_escola int not null,
bairro_escola varchar (30) not null,
cidade_escola varchar(30) not null,
cep_escola int not null,
estado_escola varchar(30) not null
);


describe tb_escola;

create table tb_usuario(
id_usuario int primary key auto_increment,
nome_usuario varchar(15) not null,
perfil varchar(10) not null,
login varchar(15) not null unique,
senha varchar(15) not null
);

describe tb_usuario;