const express= require('express');
const bodyParser = require('body-parser');
const axios = require('axios');
const path = require("path");

const app = express();
const PORT = process.env.PORT || 3000;

app.use(bodyParser.json());

// Configurar o middleware para analisar dados do formulário
app.use(bodyParser.urlencoded({ extended: true }));

// Configurar o middleware para servir arquivos estáticos (HTML, CSS, JS)
app.use(express.static(path.join(__dirname, 'public')));






app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '/public', 'PaginaInicial.html'));
});

//------------------------------------------------------------------------------------------------------------//
//funcao para criar um usuario

// Rota para servir o arquivo Cadastro HTML
app.post('/Cadastro', (req, res) => {
    res.sendFile(path.join(__dirname, '/public', 'Cadastro.html'));
});

// Rota para criar um novo usuário
app.post('/usuarios/criar', async (req, res) => {
    try {
        const response = await axios.post('http://localhost:8080/usuarios/criar', req.body);
        res.json(response.data);
    } catch (error) {
        res.status(500).json({ error: 'Erro ao criar usuário' });
    }
});



//------------------------------------------------------------------------------------------------------------//
// Funcao para Obter todos os usuarios

app.get('/todos', (req, res) => {
    res.sendFile(path.join(__dirname, '/public', 'ObterUsuario.html'));
});


// Rota para obter todos os usuários
app.get('/usuarios/todos', async (req, res) => {
    try {
        const response = await axios.get('http://localhost:8080/usuarios/todos');
        res.json(response.data);
    } catch (error) {
        res.status(500).json({ error: 'Erro ao obter todos os usuários' });
    }
});

// Outras rotas para atualizar, excluir, etc., podem ser adicionadas da mesma maneira



//------------------------------------------------------------------------------------------------------------//


// Rota para servir o arquivo HTML de login
app.get('/Login', (req, res) => {
    res.sendFile(path.join(__dirname, '/public', 'Login.html'));
});

// Rota para lidar com a submissão do formulário de login
app.post('/Login', async (req, res) => {
    try {
        const response = await axios.post('http://localhost:8080/login', req.body);
        res.status(201).json({ message: 'Login bem-sucedido', data: response.data });
    } catch (error) {
        res.status(500).json({ error: 'Erro ao login' });
    }
});


//------------------------------------------------------------------------------------------------------------//





app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});