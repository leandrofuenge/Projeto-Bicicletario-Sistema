const express = require('express');
const bodyParser = require('body-parser');
const axios = require('axios');
const path = require("path");

const app = express();
const PORT = process.env.PORT || 3000;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, '/public', 'PaginaInicial.html'));
});

// Rota para criar um novo usuário
app.post('/usuarios/criar', async (req, res) => {
    try {
        const response = await axios.post('http://localhost:8080/usuarios/criar', req.body);
        res.status(201).json(response.data);
    } catch (error) {
        console.error('Erro ao criar usuário:', error.message);
        res.status(error.response.status || 500).json({ error: 'Erro ao criar usuário' });
    }
});

// Rota para obter todos os usuários
app.get('/usuarios/todos', async (req, res) => {
    try {
        const response = await axios.get('http://localhost:8080/usuarios/todos');
        res.json(response.data);
    } catch (error) {
        console.error('Erro ao obter todos os usuários:', error.message);
        res.status(error.response.status || 500).json({ error: 'Erro ao obter todos os usuários' });
    }
});


// Rota para lidar com a submissão do formulário de login
app.post('/Login', async (req, res) => {
    try {
        const { cpf, senha } = req.body; // Extrai os parâmetros cpf e senha do corpo da requisição

        // Verifica se ambos cpf e senha foram fornecidos
        if (!cpf || !senha) {
            return res.status(400).json({ error: 'CPF e senha são obrigatórios' });
        }

        const response = await axios.post('http://localhost:8080/login', { cpf, senha }); // Passa cpf e senha para a rota de login
        res.status(200).json({ message: 'Login bem-sucedido', data: response.data });
    } catch (error) {
        console.error('Erro ao fazer login:', error.message);
        res.status(error.response.status || 500).json({ error: 'Erro ao fazer login' });
    }
});


// Rota para atualizar um usuário existente
app.put('/usuarios/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const response = await axios.put(`http://localhost:8080/usuarios/${id}`, req.body);
        res.json(response.data);
    } catch (error) {
        console.error('Erro ao atualizar usuário:', error.message);
        res.status(error.response.status || 500).json({ error: 'Erro ao atualizar usuário' });
    }
});



app.listen(PORT, () => {
    console.log(`Servidor rodando na porta ${PORT}`);
});
