import axios from 'axios';
import React, { useState } from 'react';
import { View, TextInput, Button, Alert } from 'react-native';

const LoginScreen = () => {
    const [cpf, setCpf] = useState('');
    const [senha, setSenha] = useState('');

    const fazerLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8080/login', { cpf, senha });

            if (response.status === 200) {
                const usuario = response.data;
                console.log('Login bem-sucedido:', usuario);
                // Faça algo com os dados do usuário, como navegar para a próxima tela
            } else {
                const errorResponse = response.data;
                Alert.alert('Erro', errorResponse.error);
            }
        } catch (error) {
            console.error('Erro ao fazer login:', error);
            Alert.alert('Erro', 'Erro ao fazer login. Por favor, tente novamente.');
        }
    };

    return (
        <View>
            <TextInput
                placeholder="CPF"
                onChangeText={setCpf}
                value={cpf}
            />
            <TextInput
                placeholder="Senha"
                onChangeText={setSenha}
                value={senha}
                secureTextEntry
            />
            <Button title="Login" onPress={fazerLogin} />
        </View>
    );
};

export default LoginScreen;
