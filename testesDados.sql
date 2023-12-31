GO
CREATE DATABASE Portaria
GO

GO
USE Portaria
GO

/** USUÁRIOS **/
GO
CREATE SCHEMA usuario
GO

GO
CREATE TABLE [dbo].[tblUsuario](
	user_id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_User PRIMARY KEY,
	user_nome VARCHAR(150) NOT NULL,
	user_email VARCHAR(90) NOT NULL,
	user_telefone VARCHAR(12) NOT NULL,
	user_matricula VARCHAR(20) NOT NULL,
	user_dataNascimento DATE NOT NULL,
	user_craId INT NULL,
	user_status VARCHAR(20) NOT NULL 
)
GO

GO
ALTER TABLE tblUsuario ADD user_obs VARCHAR(150) NULL
GO

GO
CREATE PROCEDURE usuario.listarUsuarios
@id INT = 0,
@status VARCHAR(1) = ''
WITH RECOMPILE
AS
--EXEC usuario.listarUsuarios @id=0, @status='S'
BEGIN
	SELECT user_id, user_nome, user_email, user_telefone, user_matricula, user_dataNascimento AS user_dataNascimento,
			CONVERT(VARCHAR, user_dataNascimento, 103) AS dataNascimentoF,user_craId, CASE WHEN user_status = 'S' THEN 'Ativo' ELSE 'Desativo' END AS user_status, 
			COALESCE(user_obs, ' - ') AS observacao
	FROM tblUsuario WHERE ((user_id = @id OR @id = 0) AND (user_status = @status OR @status = ''))
END
GO

GO
CREATE PROCEDURE usuario.criarUsuario
@nome VARCHAR(150),
@email VARCHAR(90),
@telefone VARCHAR(12),
@data VARCHAR(10),
@matricula VARCHAR(20),
@cracha VARCHAR(50),
@observacao VARCHAR(150)
WITH RECOMPILE
AS
-- EXEC usuario.criarUsuario @nome='Miguel', @email='mitguel@gmail.com', @telefone='11 999999999', @data='03-10-2017', @matricula='000342145', @cracha='', @observacao=?
BEGIN
	IF NOT EXISTS(SELECT * FROM tblUsuario WITH(NOLOCK) WHERE CONVERT(INT, user_matricula) = CONVERT(INT, @matricula) OR user_email = @email) BEGIN
		DECLARE @craId INT = 0
		SELECT @craId = cra_id FROM tblCracha WITH(NOLOCK) WHERE cra_numeracao = @cracha
		INSERT INTO tblUsuario (user_nome, user_email, user_telefone, user_dataNascimento, user_matricula, user_craId, user_status, user_obs)
			VALUES(@nome, @email, @telefone, @data, @matricula, @craId, 'S', @observacao)
	END 
END
GO

GO
CREATE PROCEDURE usuario.alterarUsuario
@id INT
WITH RECOMPILE
AS
-- EXEC usuario.alterarUsuario @id=?
BEGIN
	IF EXISTS(SELECT * FROM tblUsuario WITH(NOLOCK) WHERE user_id = @id) BEGIN
	DECLARE @status VARCHAR(1) = ''
	SELECT @status = user_status FROM tblUsuario WITH(NOLOCK) WHERE user_id = @id
		UPDATE tblUsuario SET user_status = CASE WHEN @status = 'S' THEN 'N' ELSE 'S' END WHERE user_id = @id
	END
END
GO

GO
CREATE PROCEDURE usuario.editarUsuario
@nome VARCHAR(150),
@email VARCHAR(90),
@telefone VARCHAR(12),
@data VARCHAR(10),
@matricula VARCHAR(20),
@cracha VARCHAR(50),
@observacao VARCHAR(150),
@id INT
WITH RECOMPILE
AS
-- EXEC usuario.editarUsuario @nome='Miguel', @email='mitguel@gmail.com', @telefone='19 978566427', @data='2000-02-11', @matricula='0003697', @cracha='1', @observacao='TESTE', @id=?
BEGIN
	 UPDATE tblUsuario SET user_nome = @nome, user_email = @email, user_telefone = @telefone, 
						user_dataNascimento = @data, user_matricula = @matricula, user_craId = @cracha, 
						user_obs = @observacao
	 WHERE user_id = @id
END
GO

/** LEITORES **/
GO
CREATE SCHEMA leitor
GO

GO
CREATE TABLE [dbo].[tblLeitor](
	let_id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_Let PRIMARY KEY ,
	let_descricao VARCHAR(50) NOT NULL,
	let_ip  VARCHAR(50) NOT NULL,
	let_status VARCHAR(1) DEFAULT 'N'
)
GO

GO
CREATE PROCEDURE leitor.listarLeitores
@status VARCHAR(1) = '',
@id INT = 0
WITH RECOMPILE
AS
-- EXEC leitor.listarLeitores @status=?, @id=?
BEGIN
	SELECT let_id AS id, let_descricao AS descricao, let_ip AS ip, CASE WHEN let_status = 'S' THEN 'Ativo' ELSE 'Desativo' END AS status
	FROM tblLeitor WITH(NOLOCK) WHERE (let_id = @id OR @id = 0) AND (let_status = @status OR @status = '')
END
GO

GO
CREATE PROCEDURE leitor.criarLeitor
@descricao VARCHAR(150),
@ip VARCHAR(90)
WITH RECOMPILE
AS
-- EXEC leitor.criarLeitor @descricao=?, @ip=?
BEGIN
	IF NOT EXISTS(SELECT * FROM tblLeitor WITH(NOLOCK) WHERE let_ip = TRIM(@ip)) BEGIN
		INSERT INTO tblLeitor (let_descricao, let_ip)
			VALUES(@descricao, @ip)
	END 
END
GO

GO
CREATE PROCEDURE leitor.alterarLeitor
@id INT
WITH RECOMPILE
AS
-- EXEC leitor.alterarLeitor @id=1
BEGIN
	IF EXISTS(SELECT * FROM tblLeitor WITH(NOLOCK) WHERE let_id = @id) BEGIN
	DECLARE @status VARCHAR(1) = ''
	SELECT @status = let_status FROM tblLeitor WITH(NOLOCK) WHERE let_id = @id
		UPDATE tblLeitor SET let_status = CASE WHEN @status = 'S' THEN 'N' ELSE 'S' END WHERE let_id = @id
	END
END
GO

GO
CREATE PROCEDURE leitor.editarLeitor
@descricao VARCHAR(150),
@ip VARCHAR(90),
@id INT 
WITH RECOMPILE
AS
-- EXEC leitor.editarLeitor @descricao=?, @ip=?, @id=?
BEGIN
	UPDATE tblLeitor SET let_descricao = @descricao, let_ip = @ip WHERE let_id = @id
END
GO

/** CRACHÁS **/
GO
CREATE SCHEMA cracha
GO

GO
CREATE TABLE [dbo].[tblCracha](
	cra_id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_Cra PRIMARY KEY ,
	cra_numeracao VARCHAR(50) NOT NULL,
	cra_status VARCHAR(1) DEFAULT 'N',
	cra_data DATETIME NOT NULL DEFAULT GETDATE()
)
GO

GO
CREATE PROCEDURE cracha.listarCrachas
@status VARCHAR(1) = '',
@id INT = 0
WITH RECOMPILE
AS
-- EXEC cracha.listarCrachas @status='', @id=0
BEGIN
	SELECT cra_id AS id, cra_numeracao AS numeracao, CASE WHEN cra_status = 'S' THEN 'Ativo' ELSE 'Desativo' END AS status, CONVERT(VARCHAR, cra_data, 103) AS data
	FROM tblCracha WITH(NOLOCK) WHERE (cra_status = @status OR @status = '') AND (cra_id = @id OR @id = 0)
END
GO

GO
CREATE PROCEDURE cracha.criarCracha
@numecao VARCHAR(150)
WITH RECOMPILE
AS
-- EXEC cracha.criarCracha @numecao=?
BEGIN
	IF NOT EXISTS(SELECT * FROM tblCracha WITH(NOLOCK) WHERE cra_numeracao = @numecao) BEGIN
		INSERT INTO tblCracha(cra_numeracao)
			VALUES(@numecao)
	END 
END
GO

GO
CREATE PROCEDURE cracha.alterarCracha
@id INT
WITH RECOMPILE
AS
-- EXEC cracha.alterarCracha @id=?
BEGIN
	IF EXISTS(SELECT * FROM tblCracha WITH(NOLOCK) WHERE cra_id = @id) BEGIN
	DECLARE @status VARCHAR(1) = ''
	SELECT @status = cra_status FROM  tblCracha WITH(NOLOCK) WHERE cra_id = @id
		UPDATE tblCracha SET cra_status = CASE WHEN @status = 'S' THEN 'N' ELSE 'S' END WHERE cra_id = @id
	END
END
GO

/** PERMISSÕES **/
GO
CREATE SCHEMA permissao
GO

GO
CREATE TABLE [dbo].[tblPermissoesUsuario](
	per_id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_Per PRIMARY KEY ,
	per_user_id INT NULL,
	per_cra_id INT NULL,
	per_let_id INT NULL,
	per_status VARCHAR(1) DEFAULT 'S',
	per_data DATETIME DEFAULT GETDATE(),
	per_diasSemana VARCHAR(15)
)
GO

GO
ALTER PROCEDURE permissao.listarPermissoes
@usuario INT,
@cracha INT,
@leitora INT,
@status VARCHAR(1),
@diaSemana VARCHAR(1)
WITH RECOMPILE
AS
-- EXEC permissao.listarPermissoes @usuario=?, @cracha=?, @leitora=?, @status=?, @diaSemana=?
BEGIN
	SELECT per_id AS id, COALESCE(user_nome, ' - ') AS usuario, COALESCE(cra_numeracao, ' - ') AS cracha, COALESCE(let_descricao, ' - ') AS leitora, per_diasSemana AS diasSemana, 
			per_status AS status, CONVERT(VARCHAR, per_data, 103) AS data FROM tblPermissoesUsuario t0 WITH(NOLOCK)
		LEFT JOIN tblUsuario t1 WITH(NOLOCK) ON(t0.per_user_id = t1.user_id)
		LEFT JOIN tblCracha t2 WITH(NOLOCK) ON(t0.per_cra_id = t2.cra_id)
		JOIN tblLeitor t3 WITH(NOLOCK) ON(t0.per_let_id = t3.let_id)
	WHERE (per_user_id = @usuario OR @usuario = 0) AND
		 (per_cra_id = @cracha OR @cracha = 0) AND
		 (per_let_id = @leitora OR @leitora = 0) AND
		 (per_status = @status OR @status = '') AND
		 (CONVERT(INT, @diaSemana) IN(SELECT CONVERT(INT, value) FROM string_split(per_diasSemana,',')) OR @diaSemana = '')
END
GO

GO
CREATE PROCEDURE permissao.criarPermissao
@usuario INT,
@cracha INT,
@leitora INT,
@diaSemana VARCHAR(15)
WITH RECOMPILE
AS
-- EXEC permissao.criarPermissao @usuario=?, @cracha=?, @leitora=?, @diaSemana=?
BEGIN
	IF NOT EXISTS(SELECT * FROM tblPermissoesUsuario WHERE (per_user_id = @usuario OR per_cra_id = @cracha) AND per_let_id = @leitora) BEGIN
		INSERT INTO tblPermissoesUsuario(per_user_id, per_cra_id, per_let_id, per_diasSemana) 
			VALUES(@usuario, @cracha, @leitora, @diaSemana)
	END
	
END	
GO

GO
CREATE PROCEDURE listarCrachasUsuarios
@id INT = 0
WITH RECOMPILE
AS
-- EXEC listarCrachasUsuarios @id=1
BEGIN
	SELECT t0.cra_id AS id, t1.user_nome AS nome FROM tblCracha t0 WITH(NOLOCK)
	JOIN tblUsuario t1 WITH(NOLOCK) ON(t0.cra_id = t1.user_craId)
	WHERE cra_id = @id OR @id = 0
END
GO

GO
CREATE PROCEDURE permissao.alterarPermissao
@id INT
WITH RECOMPILE
AS
-- EXEC permissao.alterarPermissao @id=1
BEGIN
	IF EXISTS(SELECT * FROM tblPermissoesUsuario WITH(NOLOCK) WHERE per_id = @id) BEGIN
	DECLARE @status VARCHAR(1) = ''
	SELECT @status = per_status FROM tblPermissoesUsuario WITH(NOLOCK) WHERE per_id = @id
		UPDATE tblPermissoesUsuario SET per_status = CASE WHEN @status = 'S' THEN 'N' ELSE 'S' END WHERE per_id = @id
	END
END
GO

GO
CREATE PROCEDURE permissao.editarPermissao
@diaSemana VARCHAR(15),
@id INT 
WITH RECOMPILE
AS
-- EXEC permissao.editarPermissao @diaSemana='3,5,6,1,', @id=1
BEGIN
	UPDATE tblPermissoesUsuario SET per_diasSemana = @diaSemana WHERE per_id = @id
END
GO

GO
CREATE PROCEDURE permissao.diasPermissaoId
@id INT 
WITH RECOMPILE
AS
-- EXEC permissao.diasPermissaoId @id=?
BEGIN
	DECLARE @dias VARCHAR(15)
	SELECT @dias = per_diasSemana FROM tblPermissoesUsuario WITH(NOLOCK) WHERE per_id = @id
	SELECT value AS dias FROM string_split(@dias, ',')
END
GO

/** LOGS **/
GO
CREATE TABLE [dbo].[tblLogLeitor](
	log_id INT IDENTITY(1,1) NOT NULL CONSTRAINT PK_Log PRIMARY KEY ,
	log_data DATETIME NOT NULL DEFAULT GETDATE(),
	log_let_id INT NOT NULL,
	log_user_id INT NULL,
	log_liberado VARCHAR(1) NOT NULL,
	log_motivo VARCHAR(100) NOT NULL,
	log_cra_id INT NULL
)
GO

GO
CREATE SCHEMA log
GO

GO
CREATE PROCEDURE log.verificarEntrada
@numeracao VARCHAR(50),
@ip VARCHAR(50)
WITH RECOMPILE
AS 
-- EXEC log.verificarEntrada @numeracao='C3B3C810', @ip='172.16.0.001'
BEGIN
	DECLARE @erro VARCHAR(1) = ''
	DECLARE @mensagem VARCHAR(50) = ''

	DECLARE @let_id INT = NULL
	SELECT @let_id = let_id FROM tblLeitor WITH(NOLOCK) WHERE TRIM(let_ip) = TRIM('172.16.0.001') AND let_status = 'S'

	DECLARE @user_id INT = NULL
	DECLARE @user_nome VARCHAR(100) = NULL
	SELECT @user_id = user_id, @user_nome = user_nome FROM tblUsuario t0 WITH(NOLOCK) 
	JOIN tblCracha t1 WITH(NOLOCK) ON(t0.user_craId = t1.cra_id)
	WHERE TRIM(t1.cra_numeracao) = TRIM(@numeracao)

	DECLARE @cra_id INT = NULL
	SELECT @cra_id = cra_id FROM tblCracha WITH(NOLOCK)
	WHERE TRIM(cra_numeracao) = TRIM(@numeracao) AND cra_status = 'S'

	DECLARE @permissao INT = NULL
	SELECT @permissao = per_id FROM tblPermissoesUsuario 
	WHERE DATEPART(DW,GETDATE()) IN(SELECT value FROM string_split(per_diasSemana,','))
		AND (per_user_id = @user_id OR per_cra_id = @cra_id)
		AND per_status = 'S'

	IF(@let_id IS NULL)BEGIN
		SELECT @erro = 'B', @mensagem = 'Leitora não encontrada ou inativa!'
	END ELSE IF(@cra_id IS NULL)BEGIN
		SELECT @erro = 'B', @mensagem = 'Crachá não encontrado ou inativo!'
	END ELSE IF(@user_id IS NULL)BEGIN
		SELECT @erro = 'B', @mensagem = 'Crachá não vinculado ao um usuário!'
	END ELSE IF (@permissao IS NULL) BEGIN
		SELECT @erro = 'B', @mensagem = 'Usuário não possui permissão de acesso!'
	END ELSE BEGIN
		SELECT @erro = 'L', @mensagem = @user_nome
	END

	INSERT INTO tblLogLeitor(log_let_id, log_user_id, log_liberado, log_motivo, log_cra_id)
		VALUES(@let_id, @user_id, @erro, @mensagem, @cra_id)
END
GO

GO
ALTER PROCEDURE log.listarLogs
@dataInicio VARCHAR(10),
@dataFim VARCHAR(10),
@leitora INT,
@usuario INT,
@status VARCHAR(1)
WITH RECOMPILE
AS
-- EXEC log.listarLogs @dataInicio='2023-06-03', @dataFim='2023-06-03', @leitora=0, @usuario=0, @status=''
BEGIN
	SELECT CONVERT(VARCHAR, log_data) AS data, log_let_id AS leitora, COALESCE(cra_numeracao, ' - ') AS cracha, 
	CASE WHEN log_liberado = 'B' THEN 'Bloqueado' ELSE 'Liberado' END liberado, log_motivo AS motivo	
	FROM tblLogLeitor t0 WITH(NOLOCK)
	LEFT JOIN tblCracha t1 WITH(NOLOCK) ON(t0.log_cra_id = t1.cra_id)
	WHERE (log_let_id = @leitora OR @leitora = 0) AND
		  (log_user_id = @usuario OR @usuario = 0) AND
		  CONVERT(DATE, log_data, 103) BETWEEN @dataInicio AND @dataFim
		  AND (log_liberado = @status OR @status = '')
END
GO